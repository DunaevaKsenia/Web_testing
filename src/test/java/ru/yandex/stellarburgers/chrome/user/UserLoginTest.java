package ru.yandex.stellarburgers.chrome.user;

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
import ru.yandex.stellarburgers.UserClient;
import ru.yandex.stellarburgers.pageobject.MainPage;
import ru.yandex.stellarburgers.pageobject.RegistrationPage;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

@Epic(value = "Stellar Burgers")
@Feature(value = "User account")
@Story(value = "Login User, Chrome tests")
public class UserLoginTest {
    private static String userName;
    private static String userPassword;
    private static String userEmail;
    private static UserClient userClient;
    private static String accessToken;

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
        Configuration.browser = "Chrome";
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
    @DisplayName("Login valid user - click login Button on Main Page")
    @Description("Create new random user, registered it and then try to login - click login Button on Main Page")
    public void userCanLoginLoginButtonMainPage() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        mainPage
                .clickLoginButton()
                .setEmail(userEmail)
                .setPassword(userPassword)
                .clickLoginButton();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Login valid user - click Account Link on Main Page")
    @Description("Create new random user, registered it and then try to login - click Account Link on Main Page")
    public void userCanLoginAccountLinkMainPage() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        mainPage
                .clickAccountLink()
                .getLoginPage()
                .setEmail(userEmail)
                .setPassword(userPassword)
                .clickLoginButton();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Login valid user - click Login Link on Registration Page")
    @Description("Create new random user, registered it and then try to login - click Login Link on Registration Page")
    public void userCanLoginLoginLinkRegistrationPage() {
        RegistrationPage registrationPage = open(RegistrationPage.REGISTER_PAGE_URL, RegistrationPage.class);

        WebDriverRunner.getWebDriver().manage().window().fullscreen();

       registrationPage
               .clickLoginLink()
               .setEmail(userEmail)
               .setPassword(userPassword)
               .clickLoginButton();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));
    }

    @Test
    @DisplayName("Login valid user - click Restore Pwd  on Login Page")
    @Description("Create new random user, registered it and then try to login - click Restore Pwd  on Login Page")
    public void userCanLoginRestorePwdLoginPage() {
        MainPage mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        mainPage
                .clickLoginButton()
                .clickRestorePwd()
                .clickLoginLink()
                .setEmail(userEmail)
                .setPassword(userPassword)
                .clickLoginButton();

        webdriver().shouldHave(url(MainPage.MAIN_PAGE_URL));
    }
}
