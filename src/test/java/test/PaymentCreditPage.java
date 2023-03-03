package test;

import data.DataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.MainPage;

import static com.codeborne.selenide.Selenide.open;

public class PaymentCreditPage {
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");
    }

    @Test
    @DisplayName("№2 успешная покупка по карте в кредит")
    public void happyCase() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitSuccessNotification();
    }

    @Test
    @DisplayName("№4 Оплата покупки по карте со статусом DECLINED в кредит ")
    public void bankShouldCancelCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(2, month, year, owner, cvv);
        creditForm.waitFailedNotification();
    }

    @Test
    @DisplayName("№16 Ввод невалидного номера карты в форму покупки в кредит")
    public void wrongCardNumber() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(3, month, year, owner, cvv);
        creditForm.waitWrongFormatMessage();
    }

    @Test
    @DisplayName("№17 Нулевой месяц действия карты ")
    public void zeroMonthOfCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.monthForCard("00");
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(2, month, year, owner, cvv);
        creditForm.waitWrongCardExpirationMessage();
    }

    @Test
    @DisplayName("№18 Неверный месяц действия карты")
    public void wrongMonthOfCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.monthForCard("13");
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitWrongCardExpirationMessage();
    }

    @Test
    @DisplayName("№19 Просроченная дата действия карты")
    public void wrongDateOfCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.yearForCard("22");
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitCardExpiredMessage();
    }

    @Test
    @DisplayName("№20 Неверный год действия карты")
    public void wrongYearOfCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.yearForCard("99");
        var owner = DataHelper.cardOwner("Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitWrongCardExpirationMessage();
    }

    @Test
    @DisplayName("№21 Цифры в имени держателя карты")
    public void wrongOwnerOfCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("9936544");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitInvalidCharactersMessage();
    }

    @Test
    @DisplayName("№22 Ввод спец.символов вместо имени держателя карты")
    public void symbolsInCardOwnerName() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("&$#@@!@");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitInvalidCharactersMessage();
    }

    @Test
    @DisplayName("№23 Пробел перед именем держателя карты")
    public void spaceBeforeOwnerOfCard() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("  Elena Ivanova");
        var cvv = DataHelper.validCvvCode();
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitInvalidCharactersMessage();
    }

    @Test
    @DisplayName("№24 Неверный формат CVV кода карты ( 2 цифры) ")
    public void wrongFormatOfCvvCode() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Evgenii Shipov");
        var cvv = DataHelper.cvvCode("52");
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitWrongFormatMessage();
    }

    @Test
    @DisplayName("№25 Неверный формат CVV кода карты ( 1 цифра) ")
    public void wrongFormatOfCvvCode2() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Evgenii Shipov");
        var cvv = DataHelper.cvvCode("2");
        creditForm.fillCreditForm(1, month, year, owner, cvv);
        creditForm.waitWrongFormatMessage();
    }
    @Test
    @DisplayName("№15 Отправка пустой формы")
    public void emptyForm() {
        MainPage main = new MainPage();
        main.payWithCredit();
        page.PaymentCreditPage creditForm = new page.PaymentCreditPage();
        creditForm.cleanForm();
        creditForm.clickSubmitButton();
        creditForm.waitMandatoryFieldMessage();
    }
}