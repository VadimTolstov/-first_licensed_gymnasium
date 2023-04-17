package ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import helpers.Regress;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


@DisplayName("Regress")
@Tag("remote")
public class LicensedGymnasiumTests extends TestBase {

    @Test
    @Regress
    @DisplayName("Просмотреть подробную информацию о преподавателях")
    void viewTeachers() {
        step("Открыть главную страницу", () -> {
            Selenide.open("https://og1.ru/");
        });
        step("Зактрыть Cookie", () -> {
            $(byText("Принять")).click();
        });
        step("Перейти на странуцу учетеля", () -> {
            $("a[href=\"https://og1.ru/prepodavateli\"]").click();
        });
        step("Читать подробно про \"Киселева Дарья Николаевна\"", () -> {
            $("a[href=\"#person-t4157\"]").scrollTo();
            $("a[href=\"#person-t4157\"]").click();
        });
        step("Проверяем, что открылось модальное окно \"Киселева Дарья Николаевна\"", () -> {
            $("#person-t4157").shouldHave(text("Киселева Дарья Николаевна"));
        });
    }

    @Test
    @DisplayName("Отправить пустую заявку на обучение")
    void signUpForFreeTrialDay() {
        step("Открыть главную страницу", () -> {
            Selenide.open("https://og1.ru/");
        });
        step("Нажать \"Подать заявку на обучение\"", () -> {
            $x("//*[@popup_title='Отправить заявку на обучение']").click();
        });
        step("Нажать кнопку отправить", () -> {
            $x("//p//input[@value='Отправить заявку']").click();
        });
        step("Отображается подсказка \"Заполните поле\"", () -> {
            $x("//*[@id='form']").shouldHave(text("Заполните поле"));
        });
    }

    @Test
    @DisplayName("Посмотреть Акции")
    void openingPromotions() {
        step("Открыть главную страницу", () -> {
            Selenide.open("https://og1.ru/");
        });
        step("Перейти на странуцу Новости Гимназии", () -> {
            $("a[href=\"https://og1.ru/novosti\"]").click();
        });
        step("Нажать на Акции", () -> {
            $("a[href=\"https://og1.ru/akczii\"]").click();
        });
        step("Проверяем, что открылось страница Акции", () -> {
            $(".hero__title").shouldHave(text("Акции"));
        });
    }

    @Test
    @DisplayName("Вопросы и ответы -> Аттестация")
    void questionsAboutCertification() {
        step("Открыть главную страницу", () -> {
            Selenide.open("https://og1.ru/");
        });
        step("Перейти на странуцу Вопросы и ответы", () -> {
            $("a[href=\"https://og1.ru/voprosy-i-otvety\"]").click();
        });
        step("Выбрать вкладку Аттестация", () -> {
            $("a[href=\"#faq-tab-2\"]").click();
        });
        step("Открыть вопрос \"Как проходит аттестация, где?\"", () -> {
            $("#faq-tab-2 div[class$=accordion]").click();
        });
        step("Проверяем, что ответ отображается", () -> {
            $x("//div[@id='faq-tab-2']//div[@class='accordion__content']//p")
                    .shouldBe(visible)
                    .shouldHave(text("Аттестация проходит онлайн, " +
                                     "в режиме реального времени на платформе ЧОУ \"ОНЛАЙН ГИМНАЗИЯ №1\"."));
        });
    }

    @Test
    @DisplayName("Подписаться на рассылку")
    void guse() {
        step("Открыть главную страницу", () -> {
            Selenide.open("https://og1.ru/");
        });
        step("Ввести имя", () -> {
            $("[name=your-name]").setValue("семен");
        });
        step("Ввести e-mail", () -> {
            $("[name=your-email]").setValue("vadim@mail.ru");
        });
        step("Нажать \"Подписаться на рассылку\"", () -> {
            $(byText("Подписаться на рассылку")).click();
        });


    }
}
