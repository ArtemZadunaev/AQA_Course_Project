package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MarraceshTourByePage {
    private SelenideElement page = $("h2.heading");
    private ElementsCollection buttons = $$("button.button");
    private SelenideElement succesNotification = $(".notification_status_ok");
    private SelenideElement errorNotification = $(".notification_status_error");
    private ElementsCollection errorFieldState = $$(".input_invalid");
    private SelenideElement numField = $$("span.input:has(input)").find(Condition.text("Номер карты")).$("input");
    private SelenideElement monthField = $$("span.input:has(input)").find(Condition.text("Месяц")).$("input");
    private SelenideElement yearField = $$("span.input:has(input)").find(Condition.text("Год")).$("input");
    private SelenideElement nameField = $$("span.input:has(input)").find(Condition.text("Владелец")).$("input");
    private SelenideElement cvcField = $$("span.input:has(input)").find(Condition.text("CVC/CVV")).$("input");

    public MarraceshTourByePage() {
        page.shouldBe(Condition.visible);
    }

    public void debitCardBye(DataHelper.CardData card) {
        numField.setValue(card.getNumber());
        monthField.setValue(card.getMonth());
        yearField.setValue(card.getYear());
        nameField.setValue(card.getOwner());
        cvcField.setValue(card.getCvc());
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

    public void checkErrorField(String fieldName, String errorText) {
        errorFieldState.find(Condition.text(fieldName)).shouldHave(Condition.text(errorText));
    }

    public String getValueInCVCField() {
        return cvcField.getValue();
    }

    private void actionButtonClick() {
        buttons.find(Condition.text("Продолжить")).click();
    }

    public void byeButtonClick() {
        buttons.find(Condition.text("Купить")).click();
    }
}
