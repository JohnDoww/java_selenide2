import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebElementCondition;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideTest {

    @BeforeMethod
    public void setUpEnvForTests(){
        Configuration.baseUrl = "https://auto.ria.com/uk";
        Configuration.browser = "chrome";
        Configuration.timeout = 2000;
        open("/");
    }


    @Test(description = "Simple test")
    public void selectTypeOfItemMoto() {
    $("#categories").selectOption("Мото");
    $("#categories").shouldBe(innerText("Мото"), visible);
    }
}
