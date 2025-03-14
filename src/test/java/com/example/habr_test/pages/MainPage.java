package com.example.habr_test.pages;

import com.example.habr_test.AllureLogger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

// page_url = https://habr.com
public class MainPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MainPage.class));
    WebDriver driver;
    Actions actions;

    @FindBy(css = ".tm-header-user-menu__toggle")
    private WebElement settingsButton;

    @FindBy(id = "uiRussian")
    private WebElement englishCheckBox;

    @FindBy(css = ".tm-input-radio-labeled__fake")
    private List<WebElement> radioButtons;

    @FindBy(css = ".tm-header__become-author-btn")
    private WebElement authorButton;

    @FindBy(css = "a.button:nth-child(5)")
    private WebElement postButton;

    @FindBy(css = ".tm-error-message__code")
    private WebElement errorCode;

    @FindBy(css = ".btn_large")
    private WebElement saveButton;

    @FindBy(css = ".btn")
    private WebElement enterButton;

    @FindBy(id = "light-colors")
    private WebElement lightThemeLink;


    public String getErrorCode() {
        authorButton.click();
        LOG.info("Нажали на кнопку: Как стать автором");
        postButton.click();
        LOG.info("Нажали на кнопку: Написать публикацию");
        return errorCode.getText();
    }

    public String getTextEnterButton() {
        settingsButton.click();
        LOG.info("Нажали на кнопку Настройки");
        actions.moveToElement(englishCheckBox).click().perform();
        LOG.info("Выбрали чекбокс Русский");
        saveButton.click();
        LOG.info("Нажали на кнопку Сохранить настройки");
        return enterButton.getText();
    }

    public Boolean isDarkThema() {
        settingsButton.click();
        LOG.info("Нажали на кнопку Настройки");
        radioButtons.get(4).click();
        LOG.info("Выбрали чекбокс цветовой темы Темная");
        saveButton.click();
        LOG.info("Нажали на кнопку Сохранить настройки");
        String isDisabled = lightThemeLink.getDomAttribute("disabled");
        return isDisabled != null;
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}
