package com.example.habr_test.tests;

import com.example.habr_test.pages.AccountPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountTest extends BaseTest{
    private AccountPage accountPage;


    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://account.habr.com");
        accountPage = new AccountPage(getDriver());
    }

    @Test
    @DisplayName("Проверка, что если не вводить капчу, появляется сообщение 'Необходимо пройти капчу'")
    public void errorTextCheck(){
        assertEquals("Необходимо пройти капчу", accountPage.getErrorText(), "Сообщение не отображается");
    }
}
