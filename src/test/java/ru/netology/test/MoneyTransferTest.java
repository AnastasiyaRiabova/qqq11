package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MoneyTransferTest {
    DataHelper.InfoCard card1 = DataHelper.getFirstCard();
    DataHelper.InfoCard card2 = DataHelper.getSecondCard();

    @BeforeEach
    void shouldTransfer() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyBetweenCards1() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount = 10000;
        int firstBalance = dashboardPage.getCardBalance(card1);
        int secondBalance = dashboardPage.getCardBalance(card2);
        var transfer = dashboardPage.choseCard(card2);
        transfer.upCard(amount,card1);
        assertEquals(firstBalance - amount, dashboardPage.getCardBalance(card1));
        assertEquals(secondBalance + amount, dashboardPage.getCardBalance(card2));
        }
    @Test
    void shouldTransferMoneyBetweenCards2() {
        DashboardPage backDashboardPage = new DashboardPage();
        int backAmount = 10000;
        int backFirstBalance = backDashboardPage.getCardBalance(card1);
        int backSecondBalance = backDashboardPage.getCardBalance(card2);
        var backTransfer = backDashboardPage.choseCard(card1);
        backTransfer.upCard(backAmount,card2);
        assertEquals(backFirstBalance + backAmount, backDashboardPage.getCardBalance(card1));
        assertEquals(backSecondBalance - backAmount, backDashboardPage.getCardBalance(card2));
    }
    @Test
    void shouldTransferMoneyBetweenCards3() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount = 5000;
        int firstBalance = dashboardPage.getCardBalance(card1);
        int secondBalance = dashboardPage.getCardBalance(card2);
        var transfer = dashboardPage.choseCard(card2);
        transfer.upCard(amount,card1);
        assertEquals(firstBalance - amount, dashboardPage.getCardBalance(card1));
        assertEquals(secondBalance + amount, dashboardPage.getCardBalance(card2));
    }
    @Test
    void shouldTransferMoneyBetweenCards4() {
        DashboardPage backDashboardPage = new DashboardPage();
        int backAmount = 5000;
        int backFirstBalance = backDashboardPage.getCardBalance(card1);
        int backSecondBalance = backDashboardPage.getCardBalance(card2);
        var backTransfer = backDashboardPage.choseCard(card1);
        backTransfer.upCard(backAmount,card2);
        assertEquals(backFirstBalance + backAmount, backDashboardPage.getCardBalance(card1));
        assertEquals(backSecondBalance - backAmount, backDashboardPage.getCardBalance(card2));
    }
    @Test
    void shouldCancel() {
        DashboardPage dashboardPage = new DashboardPage();
        int firstBalance = dashboardPage.getCardBalance(card1);
        int secondBalance = dashboardPage.getCardBalance(card2);
        var transfer = dashboardPage.choseCard(card1);
        transfer.cancel();
        assertEquals(firstBalance, dashboardPage.getCardBalance(card1));
        assertEquals(secondBalance, dashboardPage.getCardBalance(card2));
    }
    @Test
    void shouldNotTransferMoneyBetweenCards() {
        DashboardPage dashboardPage = new DashboardPage();
        int amount = 50000;
        int firstBalance = dashboardPage.getCardBalance(card1);
        int secondBalance = dashboardPage.getCardBalance(card2);
        var transfer = dashboardPage.choseCard(card2);
        transfer.upCard(amount,card1);
        assertEquals(firstBalance, dashboardPage.getCardBalance(card1));
        assertEquals(secondBalance, dashboardPage.getCardBalance(card2));
    }

}
