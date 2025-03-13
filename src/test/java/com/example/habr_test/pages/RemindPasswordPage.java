package com.example.habr_test.pages;

import com.example.habr_test.AllureLogger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

// page_url = https://account.habr.com/ru/restore-password
public class RemindPasswordPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(RemindPasswordPage.class));
    WebDriver driver;
    JavascriptExecutor js;

    @FindBy(css = ".form__remind-password-link")
    private WebElement remindPasswLink;

    @FindBy(css = ".form__field-input")
    private WebElement emailField;

    @FindBy(css = "button[type='submit'].button.button_wide.button_primary")
    private WebElement weiterButton;

    @FindBy(css = "div.s-error")
    private WebElement errorText;

    public Boolean checkErrorEmptyEmailRemindField(){
        remindPasswLink.click();
        LOG.info("Нажали на ссылку Забыли пароль?");
        weiterButton.click();
        LOG.info("Нажали на кнопку Далее");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", emailField);
    }

    public String getErrorTextRemind(){
        remindPasswLink.click();
        LOG.info("Нажали на ссылку Забыли пароль?");
        emailField.sendKeys("test@test.ru");
        LOG.info("Заполнили поле Email");
        weiterButton.click();
        LOG.info("Нажали на кнопку Далее");
        return errorText.getText();
    }

    public Boolean checkErrorIncorrctEmailField() {
        remindPasswLink.click();
        LOG.info("Нажали на ссылку Забыли пароль?");
        emailField.sendKeys("test");
        LOG.info("Заполнили поле Email невалидными данными");
        weiterButton.click();
        LOG.info("Нажали кнопку Далее");
        return (Boolean) js.executeScript("return arguments[0].reportValidity();", emailField);
    }

    public RemindPasswordPage(WebDriver driver) {
        this.driver = driver;
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }
}
