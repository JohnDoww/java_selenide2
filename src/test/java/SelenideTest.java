import com.codeborne.selenide.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTest {

    @BeforeMethod
    public void setUpEnvForTests() {
        Configuration.baseUrl = "https://auto.ria.com/uk";
        Configuration.browser = "chrome";
        Configuration.timeout = 4000;
        Configuration.browserSize = "1920x1080";
        open("/");
    }


    @Test(description = "Simple test")
    public void newUserRegistration() {
        String userFirstName = "Абжул";
        String userSecondName = "Рахман";
        String userPassword = "password123ldma";
        int randomInt = 1 + (int) (Math.random() * 100000);
        String valueToCreateEmail = "NewUser" + randomInt;
        String newUserEmail = valueToCreateEmail + "@mailinator.com";
        String mailinatorPage = "https://www.mailinator.com/v4/public/inboxes.jsp";


        $(".svg.svg-avatar").click();
        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://auto.ria.com/uk/login.html?from_url=/uk/cabinet/");

        switchTo().frame("login_frame");

        $(".login-link.text-c").click();
        $("#registrationform-name").setValue(userFirstName).shouldHave(value(userFirstName));
        $("#registrationform-second_name").setValue(userSecondName).shouldHave(value(userSecondName));

        $("#registrationform-email").setValue(newUserEmail).shouldHave(value(newUserEmail));
        $("#registration_button").shouldBe(disabled);

        $("[for=\"registrationform-agree\"]").click();

        $("#registration_button").shouldBe(enabled).click();


        executeJavaScript("window.open('', '_blank');");
        open(switchTo().window(1).getCurrentUrl());
        open(mailinatorPage);

        $("#inbox_field").setValue(newUserEmail).shouldHave(value(newUserEmail));
        $(".primary-btn").click();
        $("[style=\"padding:17px 15px 0 10px;width:20px;\"] +.ng-binding").click();


        switchTo().frame("html_msg_body");

        String emailSecureCode = $("tbody h2").text();


        switchTo().window(0);
        switchTo().frame("login_frame");

        $("#secure_code").setValue(emailSecureCode).shouldHave(value(emailSecureCode));
        $("#new_pass").setValue(userPassword);
        $("#confirm_new").setValue(userPassword);
        $("[type='submit']").click();


        Assert.assertEquals(currentUrl, "https://auto.ria.com/uk/login.html?from_url=/uk/cabinet/");
        $x("//*[@class='unlink name']").shouldHave(text(userFirstName), text(emailSecureCode));
        $x("//*[@class='unlink name']").shouldHave(text(userFirstName), text(emailSecureCode));


    }
}
