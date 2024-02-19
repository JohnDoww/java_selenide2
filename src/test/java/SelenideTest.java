import autoRiaPages.BasePage;
import autoRiaPages.LoginSigninPage;
import autoRiaPages.UserHomePage;
import com.codeborne.selenide.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.value;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.testng.AssertJUnit.assertTrue;


public class SelenideTest {
    BasePage basePage = new BasePage();
    LoginSigninPage logSignPage = new LoginSigninPage();
    UserHomePage userHomePage = new UserHomePage();

    @BeforeMethod
    public void setUpEnvForTests() {
        Configuration.baseUrl = "https://auto.ria.com/uk";
        Configuration.browser = "chrome";
        Configuration.timeout = 25000;
        Configuration.browserSize = "1920x1080";
        open("/");
    }


    @Test(description = "Simple test")
    public void newUserRegistration() {

        basePage.closeInitialPopUp();

        basePage.openLoginSigninPage();

        logSignPage.fillInSigInForm1();

        logSignPage.fillInSigInForm2();

        userHomePage.checkNewRegistrationUsersName();
    }
}
