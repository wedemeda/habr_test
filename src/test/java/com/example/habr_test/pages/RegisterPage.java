package com.example.habr_test.pages;

import com.example.habr_test.AllureLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

// page_url = https://account.habr.com/ru/register
public class RegisterPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(RegisterPage.class));
    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(css = ".form-additional-message__link")
    private WebElement regLink;

    @FindBy(css = "input[type='email'].form__field-input")
    private WebElement emailField;

    @FindBy(css = "input[type='text'].form__field-input")
    private WebElement nameField;

    @FindBy(css = "input[name='password1'].form__field-input")
    private WebElement passwordField;

    @FindBy(css = "input[name='password2'].form__field-input")
    private WebElement confPasswordField;

    @FindBy(css = "#register-agree")
    private WebElement checkBox;

    @FindBy(css = "button[type='submit'].button")
    private WebElement regButton;

    @FindBy(css = "div.s-error")
    private WebElement errorText;

    public Boolean checkErrorEmptyEmailField() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", emailField);
    }

    public Boolean checkErrorIncorrctEmailField() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        emailField.sendKeys("test");
        LOG.info("Заполнили поле Email невалидными данными");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", emailField);
    }

    public Boolean checkErrorEmptyNameField() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        emailField.sendKeys("test@test.ru");
        LOG.info("Заполнили поле Email");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", nameField);
    }

    public Boolean checkErrorEmptyPasswordField() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        emailField.sendKeys("test@test.ru");
        LOG.info("Заполнили поле Email");
        nameField.sendKeys("Test");
        LOG.info("Заполнили поле Никнейм");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", passwordField);
    }

    public Boolean checkErrorEmptyConfPasswordField() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        emailField.sendKeys("test@test.ru");
        LOG.info("Заполнили поле Email");
        nameField.sendKeys("Test");
        LOG.info("Заполнили поле Никнейм");
        passwordField.sendKeys("12345678");
        LOG.info("Заполнили поле Пароль");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", confPasswordField);
    }

    public Boolean checkErrorEmptyCheckBox() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        emailField.sendKeys("test@test.ru");
        LOG.info("Заполнили поле Email");
        nameField.sendKeys("Test");
        LOG.info("Заполнили поле Никнейм");
        passwordField.sendKeys("12345678");
        LOG.info("Заполнили поле Пароль");
        confPasswordField.sendKeys("12345678");
        LOG.info("Заполнили поле Пароль ещё раз");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", checkBox);
    }

    public String getErrorTextRegistr() {
        regLink.click();
        LOG.info("Нажали по ссылке Зарегистрируйтесь");
        emailField.sendKeys("test@test.ru");
        LOG.info("Заполнили поле Email");
        nameField.sendKeys("Test");
        LOG.info("Заполнили поле Никнейм");
        passwordField.sendKeys("12345678");
        LOG.info("Заполнили поле Пароль");
        confPasswordField.sendKeys("12345678");
        LOG.info("Заполнили поле Пароль ещё раз");
        checkBox.click();
        LOG.info("Установили галку в чекбоксе");
        regButton.click();
        LOG.info("Нажали кнопку Регистрация");
        return errorText.getText();
    }

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
}
