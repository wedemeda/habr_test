package com.example.habr_test.tests;

import com.example.habr_test.MyExtension;
import com.example.habr_test.pages.MainPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MyExtension.class)
public class MainPageTest extends BaseTest {
    private MainPage mainPage;


    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        getDriver().get("https://habr.com");
        mainPage = new MainPage(getDriver());
    }

    @Test
    @DisplayName("Проверка, что если нажать на кнопку: Написать публикацию, не залогиневшись - получаем ошибку 401")
    public void errorCodeCheck() {
        assertEquals("401", mainPage.getErrorCode());
    }

    @Test
    @DisplayName("Проверка, что после смены настроек языка на Русский, название кнопки Login изменилось на Войти")
    public void titleEnterButtonCheck() {
        assertEquals("Войти", mainPage.getTextEnterButton());
    }

    @Test
    @DisplayName("Проверка, что после смены настроек тема на Темная, тема действительно сменилась")
    public void darkThemaCheck() {
        assertEquals(true, mainPage.isDarkThema());
    }

}
