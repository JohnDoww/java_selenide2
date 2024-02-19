package autoRiaPages;

import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class UserHomePage {

    BasePage basePage = new BasePage();
    LoginSigninPage loginSigninPage = new LoginSigninPage();
    String userFirstName = loginSigninPage.userFirstName;
    String userSecondName = loginSigninPage.userSecondName;
    String userHomePageUrl = "https://auto.ria.com/uk/login.html?from_url=/uk/cabinet/";




    private final SelenideElement usersInfoBlock = $x("//*[@class='unlink name']");

    public UserHomePage checkNewRegistrationUsersName(){
        Assert.assertEquals(basePage.getCurrentUrl(), userHomePageUrl);

        switchTo().defaultContent();
        usersInfoBlock.shouldBe(visible).shouldHave(innerText(userFirstName), innerText( userSecondName));

        return this;
    }

}
