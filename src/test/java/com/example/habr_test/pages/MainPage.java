package com.example.habr_test.pages;

import com.example.habr_test.AllureLogger;
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

    @FindBy(xpath = "//label[text()='English']")
    private WebElement englishRadioButton;

    @FindBy(xpath = "//label[text()='Русский']")
    private WebElement russischRadioButton;

    @FindBy(xpath = "//label[text()='Компактный' or text()='Compact']")
    private WebElement compactModeRadioButton;

    @FindBy(xpath = "//label[text()='Темная' or text()='Dark']")
    private WebElement darkThemaRadioButton;

    @FindBy(css = ".tm-header__become-author-btn")
    private WebElement authorButton;

    @FindBy(xpath = "//a[contains(text(),'Написать публикацию') or contains(text(),'Create publication')]")
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

    @FindBy(xpath = "//a[contains(text(), 'Разработка') or contains(text(), 'Development')]")
    private WebElement menuDevelopment;

    @FindBy(css = "button.tm-navigation-filters-spoiler__button")
    private WebElement filtersButton;

    @FindBy(xpath = "//button[contains(text(),'≥50')]")
    private WebElement filter50;

    @FindBy(css = "[data-test-id='nav-filters-apply']")
    private WebElement filterApplyButton;

    @FindBy(css = "[data-test-id='votes-meter-value']")
    private WebElement votes;

    public String getErrorCode() {
        myWait(5).clickable(authorButton);
        authorButton.click();
        LOG.info("Нажали на кнопку: Как стать автором");
        myWait(5).clickable(postButton);
        postButton.click();
        LOG.info("Нажали на кнопку: Написать публикацию");
        myWait(5).visible(errorCode);
        return errorCode.getText();
    }

    public Boolean getTextEnterButton() {
        String titleEnterButton = enterButton.getText();
        myWait(5).clickable(settingsButton);
        settingsButton.click();
        LOG.info("Нажали на кнопку Settings");

        if (titleEnterButton.equals("Login")) {
            myWait(5).clickable(russischRadioButton);
            russischRadioButton.click();
            LOG.info("Выбрали Русский");
        } else {
            myWait(5).clickable(englishRadioButton);
            englishRadioButton.click();
            LOG.info("Выбрали English");
        }
        saveButton.click();
        LOG.info("Нажали на кнопку Сохранить");
        String newTitleEnterButton = enterButton.getText();
        return !titleEnterButton.equals(newTitleEnterButton);
    }

    public Boolean isDarkThema() {
        myWait(5).clickable(settingsButton);
        settingsButton.click();
        LOG.info("Нажали на кнопку Настройки");
        myWait(5).clickable(darkThemaRadioButton);
        darkThemaRadioButton.click();
        LOG.info("Выбрали чекбокс цветовой темы Темная");
        saveButton.click();
        LOG.info("Нажали на кнопку Сохранить настройки");
        String isDisabled = lightThemeLink.getDomAttribute("disabled");
        return isDisabled != null;
    }

    public Boolean isCompactMode() {
        myWait(5).clickable(settingsButton);
        settingsButton.click();
        LOG.info("Нажали на кнопку Настройки");
        myWait(5).clickable(compactModeRadioButton);
        compactModeRadioButton.click();
        LOG.info("Выбрали Компактный вид ленты");
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
        menuDevelopment.click();
        LOG.info("Перешли в раздел Разработка");
        waitForDevelopmentSection();
        myWait(5).visible(filtersButton);
        filtersButton.click();
        LOG.info("Раскрыли список с фильтрами");
        filter50.click();
        LOG.info("Выбрали порог рейтинга ≥50");
        filterApplyButton.click();
        LOG.info("Нажали кнопку Применить");
        String votesValue = votes.getText();
        return Integer.parseInt(votesValue) >= 50;
    }

    public Boolean isDisabledFilterApplyButton() {
        myWait(5).clickable(filtersButton);
        filtersButton.click();
        LOG.info("Раскрыли список с фильтрами");
        myWait(5).visible(filterApplyButton);
        return !filterApplyButton.isEnabled();
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }
}
