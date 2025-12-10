package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.MarraceshTourByePage;


import static com.codeborne.selenide.Selenide.open;


public class MarraceshTourByeTest {
    @BeforeEach
    void setupAll() {
        open("http://localhost:8080");
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SQLHelper.cleanDB();
    }

    @Test
    @DisplayName("TC‑001")
    public void shouldByeWithValidCustomerWithDebitCard() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getValidUser();
        page.debitCardBye(card);
        Assertions.assertAll(
                () -> page.checkSuccesNotification(),
                () -> Assertions.assertEquals("APPROVED", SQLHelper.getStatus()),
                () -> Assertions.assertEquals(SQLHelper.getOrderId(), SQLHelper.getPaymentId()),
                () -> Assertions.assertEquals(DataHelper.getTourPrice(), SQLHelper.getAmount()));
    }

    @Test
    @DisplayName("TC‑002")
    public void shouldNotByeWithDeclinedDebitCard() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithDeclinedCard();
        page.debitCardBye(card);
        Assertions.assertAll(
                () -> page.checkErrorNotification(),
                () -> Assertions.assertEquals("DECLINED", SQLHelper.getStatus()),
                () -> Assertions.assertEquals(SQLHelper.getOrderId(), SQLHelper.getPaymentId()));
    }

    @Test
    @DisplayName("TC‑003")
    public void shouldNotByeWithShortDebitCard() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidShortCardNumber();
        page.debitCardBye(card);
        page.checkErrorField("Номер карты", "Неверный формат");

    }

    @Test
    @DisplayName("TC‑004")
    public void shouldNotByeWithLongDebitCard() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidLongCardNumber();
        page.debitCardBye(card);
        page.checkErrorNotification();
    }

    @Test
    @DisplayName("TC‑005")
    public void shouldNotByeWithCharsInDebitCardNum() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidCharsInCardNumber();
        page.debitCardBye(card);
        page.checkErrorField("Номер карты", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑006")
    public void shouldNotByeWithInvalidInMonth00() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidMonth00();
        page.debitCardBye(card);
        page.checkErrorField("Месяц", "Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC‑007")
    public void shouldNotByeWithInvalidInMonth13() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidMonth13();
        page.debitCardBye(card);
        page.checkErrorField("Месяц", "Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC‑008")
    public void shouldNotByeWithCharsInMonth() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidCharsInMonth();
        page.debitCardBye(card);
        page.checkErrorField("Месяц", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑009")
    public void shouldNotByeWithInvalidYearInPast() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidYearInPast();
        page.debitCardBye(card);
        page.checkErrorField("Год", "Истёк срок действия карты");
    }

    @Test
    @DisplayName("TC‑010")
    public void shouldNotByeWithInvalidYearInFuture() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidYearInFuture();
        page.debitCardBye(card);
        page.checkErrorField("Год", "Неверно указан срок действия карты");
    }

    @Test
    @DisplayName("TC‑011")
    public void shouldNotByeWithInvalidUserName() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidName();
        page.debitCardBye(card);
        page.checkErrorField("Владелец", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑012")
    public void shouldNotByeWithInvalidUserNameWithNums() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidNameWithNums();
        page.debitCardBye(card);
        page.checkErrorField("Владелец", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑013")
    public void shouldNotByeWithInvalidShortCVC() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidShortCVC();
        page.debitCardBye(card);
        page.checkErrorField("CVC/CVV", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑014")
    public void shouldNotByeWithInvalidLongCVC() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidLongCVC();
        page.debitCardBye(card);
        Assertions.assertEquals(card.getCvc().substring(0, 3), page.getValueInCVCField());
    }

    @Test
    @DisplayName("TC‑015")
    public void shouldNotByeWithInvalidNumsInCVC() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithInvalidNumsInCVC();
        page.debitCardBye(card);
        Assertions.assertEquals(card.getCvc().substring(0, 2), page.getValueInCVCField());
        page.checkErrorField("CVC/CVV", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑016")
    public void shouldNotByeWithEmptyCardField() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithEmptyCardField();
        page.debitCardBye(card);
        page.checkErrorField("Номер карты", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑017")
    public void shouldNotByeWithEmptyMonthField() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithEmptyMonthField();
        page.debitCardBye(card);
        page.checkErrorField("Месяц", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑017")
    public void shouldNotByeWithEmptyYearField() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithEmptyYearField();
        page.debitCardBye(card);
        page.checkErrorField("Год", "Неверный формат");
    }

    @Test
    @DisplayName("TC‑018")
    public void shouldNotByeWithEmptyNameField() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithEmptyNameField();
        page.debitCardBye(card);
        page.checkErrorField("Владелец", "Поле обязательно для заполнения");

    }

    @Test
    @DisplayName("TC‑019")
    public void shouldNotByeWithEmptyCVCField() {
        var page = new MarraceshTourByePage();
        page.byeButtonClick();
        var card = DataHelper.getUserWithEmptyCVCField();
        page.debitCardBye(card);
        page.checkErrorField("CVC/CVV", "Неверный формат");
    }

}
