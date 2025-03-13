package com.example.habr_test.tests;

import com.example.habr_test.MyExtension;
import com.example.habr_test.pages.AccountPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class HabrTest extends BaseTest {
    private AccountPage accountPage;


    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://account.habr.com");
        accountPage = new AccountPage(getDriver());
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти не вводя капчу, появляется сообщение 'Необходимо пройти капчу'")
    public void errorTextCheck() {
        assertEquals("Необходимо пройти капчу", accountPage.getErrorText(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти c незаполненными полями Email и Пароль появляется сообщение заполнить поле Email")
    public void errorEmptyEmailFieldCheck() {
        assertEquals(false, accountPage.checkErrorEmptyEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти, заполнив поле Email невалидными данными - появляется сообщение ввести корретный Email")
    public void errorIncorrectEmailFieldCheck() {
        assertEquals(false, accountPage.checkErrorIncorrctEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти c незаполненным полем Пароль - появляется сообщение заполнить поле Пароль")
    public void errorEmptyPasswordFieldCheck() {
        assertEquals(false, accountPage.checkErrorEmptyPasswordField(), "Сообщение не отображается");
    }

}
