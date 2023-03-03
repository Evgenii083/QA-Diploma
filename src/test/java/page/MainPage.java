package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {


    private SelenideElement heading = $x("//*[(text()= 'Путешествие дня')]");
    private SelenideElement paymentForm = $x("//*[(@class= 'heading heading_size_m heading_theme_alfa-on-white')]");
    private SelenideElement buyByDebit = $x("//*[(text()= 'Купить')]");
    private SelenideElement buyByCredit = $x("//*[(text()= 'Купить в кредит')]");

    public MainPage() {
        heading.shouldBe(visible);
    }

    public PaymentCreditPage payWithCredit() {
        buyByCredit.click();
        paymentForm.shouldBe(visible).shouldHave(text("Кредит по данным карты"));
        return new PaymentCreditPage();
    }

    public PaymentDebitPage payWithDebit() {
        buyByDebit.click();
        paymentForm.shouldBe(visible).shouldHave(text("Оплата по карте"));
        return new PaymentDebitPage();
    }


}
