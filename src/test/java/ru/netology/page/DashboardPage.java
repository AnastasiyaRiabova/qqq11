package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private SelenideElement upButton = $("[data-test-id=action-deposit]");
    private SelenideElement pageRefresh = $("[data-test-id='action-reload']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public ReplenishmentCardPage choseCard(DataHelper.InfoCard infoCard) {
        cards.findBy(attribute("data-test-id", infoCard.getId())).$("[data-test-id=action-deposit]").click();
        return new ReplenishmentCardPage();
    }

    public SelenideElement findCard(DataHelper.InfoCard card) {
        return cards.findBy(attribute("data-test-id", card.getId()));
    }

    public int getCardBalance(DataHelper.InfoCard card) {
        val text = findCard(card).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}