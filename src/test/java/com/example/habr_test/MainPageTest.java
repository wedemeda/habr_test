package com.example.habr_test;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;


import java.time.Duration;
import java.util.List;

public class MainPageTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.habr.com");

        WebElement userIcon = driver.findElement(By.cssSelector("svg[data-test-id='menu-toggle-guest']"));
        userIcon.click();

        WebElement regButton = driver.findElement(By.cssSelector(".tm-user-menu__auth-buttons"));
        regButton.click();
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    @DisplayName("При снятой галки чек-бокса лицензионного соглашения, выводится сообщение об ошибке.")
    public void testRegPage1() {


        WebElement agreeCheckBox = driver.findElement(By.xpath("//*[contains(text(),'Я принимаю')] "));


        new Actions(driver).moveToElement(agreeCheckBox, -50,0).click().build().perform();

        List<WebElement> error_text = driver.findElements(By.cssSelector("div.s-error"));

        assertFalse(error_text.isEmpty(),"Сообщение не выводится.");
    }

    @Test
    @DisplayName("По-умолчанию при открытии страницы регистрации, в чек-боксе лицензионного соглашения стоит галка.")
    public void testRegPage2() {


        WebElement agreeCheckBox_cl = driver.findElement(By.cssSelector(".checkbox__input"));

        assertTrue(agreeCheckBox_cl.isSelected(), "Галка в чекбоксе лицензионного соглашения не установлена.");
    }

    @Test
    @DisplayName("При всех заполненных полях формы регистрации, но не нажатом чек-боксе капчи, выводится сообщение об ошибке.")
    public void testRegPage3() {

        String data = "$12345";

        driver.findElement(By.cssSelector("[id='email_field']")).sendKeys(data);
        driver.findElement(By.cssSelector("[id='nickname_field']")).sendKeys(data);
        driver.findElement(By.cssSelector("[id='password_field']")).sendKeys(data);
        driver.findElement(By.cssSelector("[id='password_repeat']")).sendKeys(data);

        driver.findElement(By.cssSelector("[id='registration_button']")).click();

        List<WebElement> error_text = driver.findElements(By.cssSelector("div.notice__text"));

        assertFalse(error_text.isEmpty(),"Сообщение не выводится.");
    }

}
