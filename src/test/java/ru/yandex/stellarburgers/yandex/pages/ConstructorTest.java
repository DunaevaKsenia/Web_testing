package ru.yandex.stellarburgers.yandex.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.stellarburgers.pageobject.MainPage;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;


@Epic(value = "Stellar Burgers")
@Feature(value = "Pages")
@Story(value = "Constructor transition, Yandex tests")
public class ConstructorTest {
    private MainPage mainPage;
    private static WebDriver driver;

    @BeforeClass
    public static void beforeTests() {
        closeWebDriver();
        System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "\\yandexdriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);
    }

    @Before
    public void setUp() {
        //clearBrowserCookies();
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
  }

    @After
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterTests() {
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Default section in Constructor should be buns")
    @Description("Open Main Page, default section in Constructor should be buns")
    public void defaultSectionShouldBeBuns() {
        mainPage.firstBun.shouldBe(visible);
    }

    @Test
    @DisplayName("Transitions in Constructor from buns to sauces")
    @Description("Open Main Page, then try to change section from buns to sauces")
    public void transitionFromBunsToSauces() {
        mainPage.clickSaucesSpan();

        mainPage.firstSauce.shouldBe(visible);
    }

    @Test
    @DisplayName("Transitions in Constructor from buns to toppings")
    @Description("Open Main Page, then try to change section from buns to toppings")
    public void transitionFromBunsToToppings() {
        mainPage.clickSaucesSpan();

        mainPage.firstTopping.shouldBe(visible);
    }

    @Test
    @DisplayName("Transitions in Constructor from sauces to buns")
    @Description("Open Main Page, then try to change section from sauces to buns")
    public void transitionFromSaucesToBuns() {
        mainPage
                .clickSaucesSpan()
                .clickBunsSpan();

        mainPage.firstBun.shouldBe(visible);
    }

    @Test
    @DisplayName("Transitions in Constructor from sauces to toppings")
    @Description("Open Main Page, then try to change section from sauces to toppings")
    public void transitionFromSaucesToToppings() {
        mainPage
                .clickSaucesSpan()
                .clickToppingsSpan();

        mainPage.firstTopping.shouldBe(visible);
    }

    @Test
    @DisplayName("Transitions in Constructor from toppings to buns")
    @Description("Open Main Page, then try to change section from toppings to buns")
    public void transitionFromToppingsToBuns() {
        mainPage
                .clickToppingsSpan()
                .clickBunsSpan();

        mainPage.firstBun.shouldBe(visible);
    }

    @Test
    @DisplayName("Transitions in Constructor from toppings to sauces")
    @Description("Open Main Page, then try to change section from toppings to sauces")
    public void transitionFromToppingsToSauces() {
        mainPage
                .clickToppingsSpan()
                .clickSaucesSpan();

        mainPage.firstSauce.shouldBe(visible);
    }
}
