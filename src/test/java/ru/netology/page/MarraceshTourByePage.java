package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.conditions.Visible;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MarraceshTourByePage {
    private SelenideElement page = $("h2.heading");
    private ElementsCollection buttons = $$("button.button");
    private SelenideElement succesNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");

    public MarraceshTourByePage() {
        page.shouldBe(Condition.visible);
    }

    public void debitCardBye(DataHelper.CardData card) {
        getCardNumField().setValue(card.getNumber());
        getMonthField().setValue(card.getMonth());
        getYearField().setValue(card.getYear());
        getUserNameField().setValue(card.getOwner());
        getCVCField().setValue(card.getCvc());
        actionButtonClick();
    }

    public void checkSuccesNotification() {
        succesNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
        succesNotification.shouldHave(Condition.text("Операция одобрена Банком."));
    }

    public void checkErrorNotification() {
        errorNotification.shouldBe(Condition.visible, Duration.ofSeconds(15));
        errorNotification.shouldHave(Condition.text("Ошибка! Банк отказал в проведении операции."));
    }

    public void checkErrorCardNumField() {
        $$(".input_invalid").find(Condition.text("Номер карты")).shouldHave(Condition.text("Неверный формат"));

    }

    public void checkErrorCardMonthField() {
        $$(".input_invalid").find(Condition.text("Месяц")).shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void checkErrorEmptyCardMonthField() {
        $$(".input_invalid").find(Condition.text("Месяц")).shouldHave(Condition.text("Неверный формат"));
    }

    public void checkErrorCardYearField() {
        $$(".input_invalid").find(Condition.text("Год")).shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void checkErrorEmptyCardYearField() {
        $$(".input_invalid").find(Condition.text("Год")).shouldHave(Condition.text("Неверный формат"));
    }

    public void checkErrorCardExpiredYearField() {
        $$(".input_invalid").find(Condition.text("Год")).shouldHave(Condition.text("Истёк срок действия карты"));
    }

    public void checkErrorCardNameField() {
        $$(".input_invalid").find(Condition.text("Владелец")).shouldHave(Condition.text("Неверный формат"));
    }

    public void checkErrorCardCVCField() {
        $$(".input_invalid").find(Condition.text("CVC/CVV")).shouldHave(Condition.text("Неверный формат"));
    }

    public void checkErrorEmptyCardNameField() {
        $$(".input_invalid").find(Condition.text("Владелец")).shouldHave(Condition.text("Поле обязательно для заполнения"));
    }

    private SelenideElement getCardNumField() {
        var field = $$("span.input").find(Condition.text("Номер карты"));
        return field.$("input");

    }

    private SelenideElement getMonthField() {
        var field = $$("span.input").find(Condition.text("Месяц"));
        return field.$("input");
    }

    private SelenideElement getYearField() {
        var field = $$("span.input").find(Condition.text("Год"));
        return field.$("input");
    }

    private SelenideElement getUserNameField() {
        var field = $$("span.input").find(Condition.text("Владелец"));
        return field.$("input");
    }

    public String getValueInCVCField() {
        var field = getCVCField();
        return field.getValue();
    }

    private SelenideElement getCVCField() {
        var field = $$("span.input").find(Condition.text("CVC/CVV"));
        return field.$("input");
    }

    private void actionButtonClick() {
        buttons.find(Condition.text("Продолжить")).click();
    }

    public void byeButtonClick() {
        buttons.find(Condition.text("Купить")).click();
    }
}
