package autoRiaPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.switchTo;

public class BasePage {

    String loginSiginPageUrl = "https://auto.ria.com/uk/login.html?from_url=/uk/cabinet/";
    private SelenideElement closePopUp = $("[aria-label=\"Do not consent\"]");

    private SelenideElement loginSigninPicture = $(".svg.svg-avatar");

    public String getCurrentUrl(){
        return  WebDriverRunner.getWebDriver().getCurrentUrl();
    }

    public void switchToNewTab(){
        executeJavaScript("window.open('', '_blank');");
        open(switchTo().window(1).getCurrentUrl());
    }

    public void switchToFirstInitTab(){
        switchTo().window(0);
    }


    public BasePage closeInitialPopUp() {
        if (closePopUp.exists()) {
            closePopUp.shouldBe(Condition.visible).click();
        }
        return this;
    }

    public BasePage openLoginSigninPage() {
        loginSigninPicture.shouldBe(Condition.visible).click();
        Assert.assertEquals(getCurrentUrl(), loginSiginPageUrl);
        return this;
    }


}
