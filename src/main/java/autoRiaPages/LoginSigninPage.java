package autoRiaPages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.open;

public class LoginSigninPage {

    String userFirstName = "Абжул";
    String userSecondName = "Рахман";
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

    public  LoginSigninPage fillInSigInForm1(){

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

    public  LoginSigninPage fillInSigInForm2(){
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

        return this;
    }

}
