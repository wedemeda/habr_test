package com.example.habr_test.pages;

import com.example.habr_test.AllureLogger;
import com.example.habr_test.MyWait;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

import static com.example.habr_test.MyWait.myWait;

// page_url = https://habr.com
public class MainPage {

    private final AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MainPage.class));
    WebDriver driver;
    Actions actions;

    @FindBy(css = ".tm-header-user-menu__toggle")
    private WebElement settingsButton;

    @FindBy(id = "uiEnglish")
    private WebElement englishCheckBox;

    @FindBy(id = "uiRussian")
    private WebElement russishCheckBox;

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

    @FindBy(css = "[data-test-id='articleHubsList']")
    private List<WebElement> articleList;

    @FindBy(css = "[data-test-id='main-menu-item']")
    private List<WebElement> menuChapters;

    @FindBy(css = "button[data-test-id='nav-filters-button']")
    private WebElement filtersButton;

    @FindBy(xpath = "//button[contains(text(),'≥50')]")
    private WebElement filter50;

    @FindBy(css = "[data-test-id='nav-filters-apply']")
    private WebElement filterApplyButton;

    @FindBy(css = "[data-test-id='votes-meter-value']")
    private WebElement votes;

    public String getErrorCode() {
        authorButton.click();
        LOG.info("Нажали на кнопку: Как стать автором");
        postButton.click();
        LOG.info("Нажали на кнопку: Написать публикацию");
        return errorCode.getText();
    }

    public Boolean getTextEnterButton() throws InterruptedException {
        Thread.sleep(3000);
        String titleEnterButton = enterButton.getText();
        if (titleEnterButton.equals("Login")) {
            settingsButton.click();
            LOG.info("Нажали на кнопку Settings");
            Thread.sleep(3000);
            actions.moveToElement(russishCheckBox).click().perform();
            LOG.info("Выбрали чекбокс Русский");
            Thread.sleep(3000);
            saveButton.click();
            LOG.info("Нажали на кнопку Save preferences");
        } else {
            settingsButton.click();
            LOG.info("Нажали на кнопку Настройки");
            Thread.sleep(3000);
            actions.moveToElement(englishCheckBox).click().perform();
            LOG.info("Выбрали чекбокс English");
            Thread.sleep(3000);
            saveButton.click();
            LOG.info("Нажали на кнопку Сохранить настройки");
        }
        String newTitleEnterButton = enterButton.getText();
        return !titleEnterButton.equals(newTitleEnterButton);
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

    public Boolean isCompactMode() {
        settingsButton.click();
        LOG.info("Нажали на кнопку Настройки");
        radioButtons.get(3).click();
        LOG.info("Выбрали чекбокс вида ленты Компактный");
        saveButton.click();
        LOG.info("Нажали на кнопку Сохранить настройки");
        return articleList.isEmpty();
    }

    // Метод для проверки, что раздел "Разработка" стал активным
    public void waitForDevelopmentSection() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.attributeContains(
                By.xpath("//a[contains(text(), 'Разработка') or contains(text(), 'Development')]"),
                "class", "tm-main-menu__item_active"
        ));
    }

    public Boolean isVotesEqualsFilter() {
        menuChapters.get(1).click();
        LOG.info("Перешли в раздел Разработка");
        waitForDevelopmentSection();
        filtersButton.click();
        LOG.info("Раскрыли список с фильтрами");
        filter50.click();
        LOG.info("Выбрали порог рейтинга ≥100");
        filterApplyButton.click();
        LOG.info("Нажали кнопку Применить");
        String votesValue = votes.getText();
        return Integer.parseInt(votesValue) >= 50;
    }

    public Boolean isDisabledFilterApplyButton() {
        myWait(3).clickable(filtersButton);
        filtersButton.click();
        LOG.info("Раскрыли список с фильтрами");
        myWait(3).visible(filterApplyButton);
        return !filterApplyButton.isEnabled();
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}
