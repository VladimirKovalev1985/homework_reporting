import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTests {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    public void shouldSendForm() {
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue(Generator.generateCity("ru"));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(Generator.generateDate(3));
        $("[data-test-id=name] input").setValue(Generator.generateName("ru"));
        $("[data-test-id=phone] input").setValue(Generator.generatePhone("ru"));
        $("[data-test-id=agreement]").click();
        $$("button").find(exactText("Запланировать")).click();
        $(".notification__content").shouldBe(visible,
                exactText("Встреча успешно запланирована на " + Generator.generateDate(3)));
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(Generator.generateDate(4));
        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id=replan-notification]").shouldBe(visible);
        $$("button").find(exactText("Перепланировать")).click();
        $(".notification__content ").shouldBe(visible,
                exactText("Встреча успешно запланирована на " + Generator.generateDate(4)));

    }
}
