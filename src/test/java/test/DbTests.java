package test;

import data.DataHelper;
import data.DbUtils;
import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import page.MainPage;
import page.PaymentCreditPage;
import page.PaymentDebitPage;

import static com.codeborne.selenide.Selenide.open;

public class DbTests {
    @BeforeEach
    public void setUp() {
        open("http://localhost:8080/");
    }

    @AfterEach
    public void cleanDb(){
        var settings = new DbUtils();
        settings.cleanDb();
    }

    @SneakyThrows
    @Test
    @DisplayName("27. Отправка запроса в СУБД при покупке тура по карте со статусом 'APPROVED'")
    public void checkApprovedStatusInDb() {
        MainPage mainPage = new MainPage();
        mainPage.payWithDebit();
        page.PaymentDebitPage page = new PaymentDebitPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Daria Nikova");
        var cvv = DataHelper.validCvvCode();
        page.fillForm(1, month, year, owner, cvv);
        DbUtils sql = new DbUtils();
        sql.waitNotificationForDb();
        var actual = sql.getDebitStatus();
        var expected = "APPROVED";
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    @DisplayName("28. Отправка запроса в СУБД при покупке тура по карте со статусом 'APPROVED' в кредит ")
    public void checkApprovedStatusInDbInCaseCreditPayment() {
        MainPage mainPage = new MainPage();
        mainPage.payWithCredit();
        page.PaymentCreditPage page = new PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Daria Nikulina");
        var cvv = DataHelper.validCvvCode();
        page.fillCreditForm(1, month, year, owner, cvv);
        DbUtils sql = new DbUtils();
        sql.waitNotificationForDb();
        var actual = sql.getCreditStatus();
        var expected = "APPROVED";
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    @DisplayName("29. Отправка запроса в СУБД при покупке тура по карте со статусом 'DECLINED'")
    public void checkDeclinedStatusInDb() {
        MainPage mainPage = new MainPage();
        mainPage.payWithDebit();
        page.PaymentDebitPage page = new PaymentDebitPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Oleg Tinkoff");
        var cvv = DataHelper.validCvvCode();
        page.fillForm(2, month, year, owner, cvv);
        DbUtils sql = new DbUtils();
        sql.waitNotificationForDb();
        var actual = sql.getDebitStatus();
        var expected = "DECLINED";
        Assertions.assertEquals(expected, actual);
    }

    @SneakyThrows
    @Test
    @DisplayName("30. Отправка запроса в СУБД при покупке тура по карте со статусом 'DECLINED' в кредит ")
    public void checkDeclinedStatusInDbInCaseCreditPayment() {
        MainPage mainPage = new MainPage();
        mainPage.payWithCredit();
        page.PaymentCreditPage page = new PaymentCreditPage();
        var month = DataHelper.validMonthForCard();
        var year = DataHelper.validYearForCard();
        var owner = DataHelper.cardOwner("Oleg Tinkoff");
        var cvv = DataHelper.validCvvCode();
        page.fillCreditForm(1, month, year, owner, cvv);
        DbUtils sql = new DbUtils();
        sql.waitNotificationForDb();
        var actual = sql.getCreditStatus();
        var expected = "APPROVED";
        Assertions.assertEquals(expected, actual);
    }

}
