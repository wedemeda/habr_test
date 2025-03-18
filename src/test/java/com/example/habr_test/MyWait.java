package com.example.habr_test;

import com.example.habr_test.tests.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class MyWait {

    AllureLogger LOG = new AllureLogger(LoggerFactory.getLogger(MyWait.class));
    private final WebDriverWait wait;
    private final int secondsToWait;

    public static MyWait myWait(int seconds) {
        return new MyWait(seconds);
    }

    public MyWait(int seconds) {
        this.secondsToWait = seconds;
        wait = new WebDriverWait(BaseTest.getDriver(), Duration.ofSeconds(seconds));
    }

    public void clickable(WebElement element) {
        LOG.info("Ждем " + secondsToWait + " секунд пока элемент станет кликабельным " + element.toString());
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void visible(WebElement element) {
        LOG.info("Ждем " + secondsToWait + " секунд пока элемент станет видимым " + element.toString());
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}