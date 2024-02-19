package autoRiaPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class LoginSigninPage {
    BasePage basePage = new BasePage();

    public String userFirstName = "Абжул";
    public String userSecondName = "Рахман";

    String userPassword = "password123ldma";
    int randomInt = 1 + (int) (Math.random() * 100000);
    String valueToCreateEmail = "heyAmiggo" + randomInt;
    String newUserEmail = valueToCreateEmail + "@mailinator.com";
    String mailinatorPage = "https://www.mailinator.com/v4/public/inboxes.jsp";


    private final SelenideElement signinLinkedText = $(".login-link.text-c");
    private final SelenideElement userFirstNameSignInField = $("#registrationform-name");
    private final SelenideElement userSecondNameSignInField = $("#registrationform-second_name");
    private final SelenideElement emailSignInField = $("#registrationform-email");
    private final SelenideElement privacyPolicyAgreeCheckbox = $("[for=\"registrationform-agree\"]");
    private final SelenideElement submitRegistrationButton = $("#registration_button");
    private final SelenideElement creationEmailFieldMailinator = $("#inbox_field");
    private final SelenideElement searchEmailLettersMailinator = $(".primary-btn");
    private final SelenideElement openFirstInboxEmailMailinatorr = $("[style=\"padding:17px 15px 0 10px;width:20px;\"] +.ng-binding");
    private final SelenideElement secureCodePlaceMailinator = $("tbody h2");
    private final SelenideElement secureCodeSiginField = $("#secure_code");
    private final SelenideElement passwordSigninField = $("#new_pass");
    private final SelenideElement confirmPasswordSigninField = $("#confirm_new");
    private final SelenideElement submitSiginButton = $("[type='submit']");


    public LoginSigninPage fillInSigInForm1() {

        switchTo().frame("login_frame");

        signinLinkedText.shouldBe(visible, enabled).click();
        userFirstNameSignInField.setValue(userFirstName).shouldHave(value(userFirstName));
        userSecondNameSignInField.setValue(userSecondName).shouldHave(value(userSecondName));

        emailSignInField.setValue(newUserEmail).shouldHave(value(newUserEmail));

        submitRegistrationButton.shouldBe(disabled);

        privacyPolicyAgreeCheckbox.shouldNot(checked).click();

        submitRegistrationButton.shouldBe(enabled).click();

        return this;
    }

    public LoginSigninPage fillInSigInForm2() {

        //there we opeen new windows
        basePage.switchToNewTab();

        open(mailinatorPage);

        creationEmailFieldMailinator.setValue(newUserEmail).shouldHave(value(newUserEmail));
        searchEmailLettersMailinator.click();
        openFirstInboxEmailMailinatorr.click();


        switchTo().frame("html_msg_body");

        String emailSecureCode = secureCodePlaceMailinator.text();

        basePage.switchToFirstInitTab();
        switchTo().frame("login_frame");

        secureCodeSiginField.setValue(emailSecureCode).shouldHave(value(emailSecureCode));
        passwordSigninField.setValue(userPassword);
        confirmPasswordSigninField.setValue(userPassword);
        submitSiginButton.click();

        return this;
    }

}
