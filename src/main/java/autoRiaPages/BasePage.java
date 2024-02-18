package autoRiaPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import static org.testng.AssertJUnit.*;
import org.testng.Assert;


import static com.codeborne.selenide.Selenide.$;

public class BasePage {

    private SelenideElement closePopUp = $("[aria-label=\"Do not consent\"]");
    private SelenideElement loginSigninPicture = $(".svg.svg-avatar");


    public BasePage closeInitialPopUp() {
        if (closePopUp.exists()) {
            closePopUp.shouldBe(Condition.visible).click();
        }
        return this;
    }

    public BasePage openLoginSigninPage() {

        loginSigninPicture.shouldBe(Condition.visible).click();

        String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();

        Assert.assertEquals(currentUrl, "https://auto.ria.com/uk/login.html?from_url=/uk/cabinet/");

        return this;
    }


}
