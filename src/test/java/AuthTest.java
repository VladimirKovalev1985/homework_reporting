import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.find;

public class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldSuccessfulLoginIfRegisteredActiveUser() {
        //Configuration.holdBrowserOpen = true;
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("h2").shouldBe(visible, exactText("Личный кабинет"));

    }

    @Test
    public void shouldGetErrorIfNotRegisteredUser() {
        //Configuration.holdBrowserOpen = true;
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var notRegisteredUser = DataGenerator.getRandomLogin();
        $("[data-test-id='login'] input").setValue(notRegisteredUser);
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(visible).find(String.valueOf(exactText("Ошибка")));

    }

    @Test
    public void shouldGetErrorIfBlockedUser() {
        //Configuration.holdBrowserOpen = true;
        var registeredUser = DataGenerator.Registration.getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(visible).find(String.valueOf
                (exactText("Пользователь заблокирован")));
    }

    @Test
    public void shouldGetErrorIfWrongLogin() {
        //Configuration.holdBrowserOpen = true;
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongLogin = DataGenerator.getRandomLogin();
        $("[data-test-id='login'] input").setValue(wrongLogin);
        $("[data-test-id='password'] input").setValue("registeredUser");
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(visible).find(String.valueOf(exactText("Ошибка")));
    }

    @Test
    public void shouldGetErrorIfWrongPassword() {
        Configuration.holdBrowserOpen = true;
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        var wrongPassword = DataGenerator.getRandomPassword();
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(wrongPassword);
        $("[data-test-id='action-login']").click();
        $("[data-test-id='error-notification']").shouldBe(visible).find(String.valueOf(exactText("Ошибка")));


    }
}

