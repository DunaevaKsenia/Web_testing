package ru.yandex.stellarburgers.user;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


@RunWith(Parameterized.class)
public class smallTest {
    private boolean chrome;
    private boolean yandex;

    private WebDriver driver;
    private String chromePath;

    public smallTest(boolean chrome, boolean yandex) {
        this.chrome = chrome;
        this.yandex = yandex;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {true, false},
                {false, true}
        };
    }

    @Test
    @DisplayName("Create new browser")
    @Description("Create new browser and run it")
    public void user() {

        if (chrome) {
            closeWebDriver();
            System.setProperty("webdriver.chrome.driver", "C:\\Diplom\\Diplom_3\\chromedriver.exe");
            driver = new ChromeDriver();
            setWebDriver(driver);
        }

        if (yandex) {
            closeWebDriver();
            System.setProperty("webdriver.chrome.driver", "C:\\Diplom\\Diplom_3\\yandexdriver.exe");
            driver = new ChromeDriver();
            setWebDriver(driver);
        }


        open("https://www.ya.ru/");
    }
}
