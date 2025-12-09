package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Locale;
import java.time.format.DateTimeFormatter;

public class DataHelper {
    static Faker faker = new Faker(new Locale("en"));

    public static CardData getValidUser() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithDeclinedCard() {
        return new CardData(getDeclinedCard(), getValidMonth(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidLongCVC() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getValidUserName(), getInvalidLongCVC());
    }

    public static CardData getUserWithInvalidNumsInCVC() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getValidUserName(), getInvalidCVCWithChars());
    }

    public static CardData getUserWithInvalidShortCVC() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getValidUserName(), getInvalidShortCVC());
    }

    public static CardData getUserWithInvalidLongCardNumber() {
        return new CardData(getInvalidLongCard(), getValidMonth(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidShortCardNumber() {
        return new CardData(getInvalidShortCard(), getValidMonth(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidCharsInCardNumber() {
        return new CardData(getInvalidCardWithChars(), getValidMonth(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidMonth00() {
        return new CardData(getValidCard(), getInvalidMonth00(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidMonth13() {
        return new CardData(getValidCard(), getInvalidMonth13(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidCharsInMonth() {
        return new CardData(getValidCard(), getInvalidMonthWithChars(), getValidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidYearInPast() {
        return new CardData(getValidCard(), getValidMonth(), generateYearInPast(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidYearInFuture() {
        return new CardData(getValidCard(), getValidMonth(), getInvalidYear(), getValidUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidName() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getInvalidRusUserName(), getValidCVC());
    }

    public static CardData getUserWithInvalidNameWithNums() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getInvalidUserNameWithNums(), getValidCVC());
    }

    public static CardData getUserWithEmptyNameField() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), " ", getValidCVC());
    }

    public static CardData getUserWithEmptyCardField() {
        return new CardData(" ", getValidMonth(), getValidYear(), getInvalidUserNameWithNums(), getValidCVC());
    }

    public static CardData getUserWithEmptyMonthField() {
        return new CardData(getValidCard(), " ", getValidYear(), getInvalidUserNameWithNums(), getValidCVC());
    }

    public static CardData getUserWithEmptyYearField() {
        return new CardData(getValidCard(), getValidMonth(), " ", getInvalidUserNameWithNums(), getValidCVC());
    }

    public static CardData getUserWithEmptyCVCField() {
        return new CardData(getValidCard(), getValidMonth(), getValidYear(), getInvalidUserNameWithNums(), " ");
    }

    public static int getTourPrice() {
        return 45_000;
    }

    //генератор валидного CVC
    private static String getValidCVC() {
        return faker.numerify("###");
    }

    //генератор CVC 4 цифры
    private static String getInvalidLongCVC() {
        return faker.numerify("####");
    }

    //генератор CVC 2 цифры
    private static String getInvalidShortCVC() {
        return faker.numerify("##");
    }

    private static String getInvalidCVCWithChars() {
        return getInvalidShortCVC() + faker.letterify("#");
    }

    //генератор валидного имени user
    private static String getValidUserName() {
        return faker.name().firstName().toUpperCase() + " " + faker.name().lastName().toUpperCase();
    }

    private static String getInvalidUserNameWithNums() {
        return faker.name().firstName().toUpperCase() + faker.numerify("##") + " " +
                faker.name().lastName().toUpperCase() + faker.numerify("##");
    }

    //генератор имени user на кириллице
    private static String getInvalidRusUserName() {
        return new Faker(new Locale("ru")).name().fullName();
    }

    private static int generateMonth() {
        return LocalDate.now().getMonthValue();
    }

    //генератор валидного месяца
    private static String getValidMonth() {
        return Integer.toString(faker.number().numberBetween(generateMonth(), 12));
    }

    private static String getInvalidMonth00() {
        return "00";
    }

    private static String getInvalidMonth13() {
        return "13";
    }

    private static String getInvalidMonthWithChars() {
        return faker.letterify("##");
    }

    //генератор  не валидного месяца
    private static String getInvalidMonth() {
        return Integer.toString(generateMonth() - 1);
    }

    //текущий год в формате 2 цифр
    private static int generateYear() {
        LocalDate yearNum = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy");
        return Integer.parseInt(yearNum.format(formatter));
    }

    private static String generateYearInPast() {
        return Integer.toString(generateYear() - faker.number().randomDigitNotZero());
    }

    //генератор валидного года
    private static String getValidYear() {
        int year = generateYear();
        return Integer.toString(faker.number().numberBetween(year, year + 5));
    }


    //генератор не валидного года
    private static String getInvalidYear() {
        int year = generateYear();
        return Integer.toString(faker.number().numberBetween(year + 6, 99));
    }

    private static String getDeclinedCard() {
        return "5555 6666 7777 8888";
    }

    private static String getInvalidShortCard() {
        return faker.numerify("###############");
    }

    private static String getInvalidLongCard() {
        return faker.numerify("#################");
    }

    private static String getValidCard() {
        return "1111 2222 3333 4444";
    }

    private static String getInvalidCardWithChars() {
        String tmp = faker.numerify("#### #### #### ##");
        return tmp + "DF";
    }


    @AllArgsConstructor
    @Getter
    public static class CardData {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }
}
