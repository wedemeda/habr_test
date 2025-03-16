package com.example.habr_test.tests;

import com.example.habr_test.MyExtension;
import com.example.habr_test.pages.MainPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
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
        WebDriverManager.chromedriver().setup();
    }

    @Test
    @DisplayName("Проверка, что если нажать на кнопку: Написать публикацию, не залогиневшись - получаем ошибку 401")
    public void errorCodeCheck() {
        assertEquals("401", mainPage.getErrorCode(), "Ошибка 401 не отображается");
    }

    @Test
    @DisplayName("Проверка, что после смены настроек языка , название кнопки входа изменилось")
    public void titleEnterButtonCheck() throws InterruptedException {
        assertEquals(true, mainPage.getTextEnterButton(), "Название кнопки не изменилось");
    }

    @Test
    @DisplayName("Проверка, что после смены настроек темы на Темная, тема действительно сменилась")
    public void darkThemaCheck() {
        assertEquals(true, mainPage.isDarkThema(), "Тема не изменилась");
    }

    @Test
    @DisplayName("Проверка, что после смены настроек вида ленты на Компактный, вид действительно сменился")
    public void compactModeCheck() {
        assertEquals(true, mainPage.isCompactMode(), "Вид ленты не изменился");
    }

    @Test
    @DisplayName("Проверка, что при выборе раздела статей Разработка и установленном фильтре Порог рейтинга   >= 50 вывелась статья с рейтингом >= 100")
    public void votesEqualsFilterCheck() throws InterruptedException {
        assertEquals(true, mainPage.isVotesEqualsFilter(), "Порог рейтинга статьи <50");
    }

    @RepeatedTest(10)
    @DisplayName("Проверка, что не залогонившись кнопка Применить в настройках ленты не активна")
    public void disabledFilterApplyButtonCheck() throws InterruptedException {
        assertEquals(true, mainPage.isDisabledFilterApplyButton(), "Кнопка активная");
    }

}
