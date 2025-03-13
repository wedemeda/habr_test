package com.example.habr_test.tests;

import com.example.habr_test.MyExtension;
import com.example.habr_test.pages.AccountPage;
import com.example.habr_test.pages.RegisterPage;
import com.example.habr_test.pages.RemindPasswordPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class HabrTest extends BaseTest {
    private AccountPage accountPage;
    private RemindPasswordPage remindPasswordPage;
    private RegisterPage registerPage;


    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://account.habr.com");
        accountPage = new AccountPage(getDriver());
        remindPasswordPage = new RemindPasswordPage(getDriver());
        registerPage = new RegisterPage(getDriver());
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти не вводя капчу, появляется сообщение 'Необходимо пройти капчу'")
    public void errorTextCheck() {
        assertEquals("Необходимо пройти капчу", accountPage.getErrorText(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти c незаполненными полями Email и Пароль появляется сообщение заполнить поле: Email")
    public void errorEmptyEmailFieldCheck() {
        assertEquals(false, accountPage.checkErrorEmptyEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти, заполнив поле Email невалидными данными - появляется сообщение ввести корретный Email")
    public void errorIncorrectEmailFieldCheck() {
        assertEquals(false, accountPage.checkErrorIncorrctEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Войти c незаполненным полем Пароль - появляется сообщение заполнить поле: Пароль")
    public void errorEmptyPasswordFieldCheck() {
        assertEquals(false, accountPage.checkErrorEmptyPasswordField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Далее c незаполненным полем Email появляется сообщение заполнить поле: Email")
    public void errorEmptyEmailRemindFieldCheck() {
        assertEquals(false, remindPasswordPage.checkErrorEmptyEmailRemindField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если не вводить капчу при восстановлении пароля, появляется сообщение 'Необходимо пройти капчу'")
    public void errorTextRemindCheck() {
        assertEquals("Необходимо пройти капчу", remindPasswordPage.getErrorTextRemind(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Далее, заполнив поле: Email невалидными данными - появляется сообщение ввести корретный Email")
    public void errorIncorrectEmailReminderFieldCheck() {
        assertEquals(false, remindPasswordPage.checkErrorIncorrctEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Регистрация c незаполненными полями появляется сообщение заполнить поле: Email")
    public void errorEmptyEmailRegistrFieldCheck() {
        assertEquals(false, registerPage.checkErrorEmptyEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Регистрация, заполнив поле: Email невалидными данными - появляется сообщение ввести корретный Email")
    public void errorIncorrectEmailRegistrFieldCheck() {
        assertEquals(false, registerPage.checkErrorIncorrctEmailField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Регистрация c незаполненным полем: Никнейм - появляется сообщение заполнить поле: Никнейм")
    public void errorEmptyNameFieldCheck() {
        assertEquals(false, registerPage.checkErrorEmptyNameField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Регистрация c незаполненным полем: Пароль - появляется сообщение заполнить поле: Пароль")
    public void errorEmptyPasswordRegistrFieldCheck() {
        assertEquals(false, registerPage.checkErrorEmptyPasswordField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Регистрация c незаполненным полем: Пароль ещё раз - появляется сообщение заполнить поле: Пароль ещё раз")
    public void errorEmptyConfPasswordRegistrFieldCheck() {
        assertEquals(false, registerPage.checkErrorEmptyConfPasswordField(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если нажать кнопку Регистрация c не установенной галкой в чекбоксе - появляется сообщение требующее кликнуть чекбокс")
    public void errorEmptyCheckBoxCheck() {
        assertEquals(false, registerPage.checkErrorEmptyCheckBox(), "Сообщение не отображается");
    }

    @Test
    @DisplayName("Проверка, что если не вводить капчу при регистрации, появляется сообщение 'Необходимо пройти капчу'")
    public void errorTextRegistrCheck() {
        assertEquals("Необходимо пройти капчу", registerPage.getErrorTextRegistr(), "Сообщение не отображается");
    }

}
