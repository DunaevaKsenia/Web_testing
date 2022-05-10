package ru.yandex.stellarburgers.yandex.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.stellarburgers.UserClient;
import ru.yandex.stellarburgers.pageobject.AccountPage;
import ru.yandex.stellarburgers.pageobject.MainPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;

@Epic(value = "Stellar Burgers")
@Feature(value = "Pages")
@Story(value = " Header transition, Yandex tests")
public class HeaderTest {
    private static String userName;
    private static String userPassword;
    private static String userEmail;
    private static UserClient userClient;
    private static String accessToken;
    private MainPage mainPage;
    private static WebDriver driver;

    @BeforeClass
    public static void beforeTests() {
        userClient = new UserClient();
        Faker faker = new Faker();

        userName = faker.name().username();
        userPassword = faker.internet().password(8, 10);
        userEmail = faker.internet().emailAddress();

        Map<String, String> userData = new HashMap<>();
        userData.put("email", userEmail);
        userData.put("password", userPassword);
        userData.put("name", userName);

        ValidatableResponse response = userClient.createUser(userData);
        accessToken = response.extract().path("accessToken");

        closeWebDriver();
        System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "\\yandexdriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);
    }

    @Before
    public void setUp() {
        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        mainPage
                .clickLoginButton()
                .setEmail(userEmail)
                .setPassword(userPassword)
                .clickLoginButton();
    }

    @After
    public void tearDown() {
        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterTests() {
        if(accessToken != null) {
            userClient.deleteUser(userEmail, accessToken.substring(7));
        }

        webdriver().driver().close();
    }

    @Test
    @DisplayName("User can go from Main Page to personal Account")
    @Description("Create new random user, registered it, login and then try go to personal Account")
    public void userCanGoFromMainPageToPersonalAccount() {
        mainPage.clickAccountLink();

        webdriver().shouldHave(url(AccountPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("User can go from personal Account to Constructor")
    @Description("Create new random user, registered it, login,go to personal Account and then try go to Constructor")
    public void userCanGoFromPersonalAccountToConstructor() {
        mainPage
                .clickAccountLink()
                .getAccountPage()
                .clickConstructorLink();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));
        assertTrue(mainPage.isAssembleBurgerDispleyed());
    }

    @Test
    @DisplayName("User can go from personal Account to Constructor with logo click")
    @Description("Create new random user, registered it, login,go to personal Account and then try go to Constructor with logo click")
    public void userCanGoFromPersonalAccountToConstructorLogoClick() {
        mainPage
                .clickAccountLink()
                .getAccountPage()
                .clickLogoLink();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));
        assertTrue(mainPage.isAssembleBurgerDispleyed());
    }
}
