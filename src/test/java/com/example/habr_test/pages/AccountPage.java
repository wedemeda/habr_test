package com.example.habr_test.pages;

import com.example.habr_test.AllureLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

// page_url = https://account.habr.com
public class AccountPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(AccountPage.class));
    WebDriver driver;

    @FindBy(css = "input[type='email'][name='email'][form='ident-form']")
    private WebElement emailField;

    @FindBy(css = "input[type='password'][name='password'][form='ident-form']")
    private WebElement passwField;

    @FindBy(css = "button[type='submit'][form='ident-form'].button.button_wide.button_primary")
    private WebElement submitButton;

    @FindBy(css = "div.s-error")
    private WebElement errorText;

    public String getErrorText(){
        emailField.sendKeys("test@test.ru");
        passwField.sendKeys("12345678");
        LOG.info("Заполнили поля Email и Пароль");
        submitButton.click();
        LOG.info("Нажали кнопку Войти");
        return errorText.getText();
    }

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
