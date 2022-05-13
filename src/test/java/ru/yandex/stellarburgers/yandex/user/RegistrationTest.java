package ru.yandex.stellarburgers.yandex.user;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.stellarburgers.UserClient;
import ru.yandex.stellarburgers.pageobject.LoginPage;
import ru.yandex.stellarburgers.pageobject.RegistrationPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;
import static org.junit.Assert.assertTrue;

@Epic(value = "Stellar Burgers")
@Feature(value = "User account")
@Story(value = "Create User account, Yandex tests")
public class RegistrationTest {
    private static String userName;
    private static String userPassword;
    private static String userEmail;
    private static UserClient userClient;
    private static WebDriver driver;

    @BeforeClass
    public static void beforeTests() {
        userClient = new UserClient();
        Faker faker = new Faker();

        userName = faker.name().username();
        userPassword = faker.internet().password(8, 10);
        userEmail = faker.internet().emailAddress();

        closeWebDriver();
        System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir") + "\\yandexdriver.exe");
        driver = new ChromeDriver();
        setWebDriver(driver);
    }

    @After
    public void tearDown() {
        Map<String, String> userData = new HashMap<>();
        userData.put("email", userEmail);
        userData.put("password", userPassword);

        ValidatableResponse responseLogin = userClient.loginUser(userData);
        String accessToken = responseLogin.extract().path("accessToken");

        if(accessToken != null) {
            userClient.deleteUser(userEmail, accessToken.substring(7));
        }

        clearBrowserCookies();
        clearBrowserLocalStorage();
    }

    @AfterClass
    public static void afterTests() {
        webdriver().driver().close();
    }

    @Test
    @DisplayName("Create new valid user")
    @Description("Create new user with valid random credentials and registration it")
    public void userCanBeRegisteredWithValidRandomCredentials() {
        RegistrationPage registrationPage = open(RegistrationPage.REGISTER_PAGE_URL, RegistrationPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        registrationPage
                .setName(userName)
                .setEmail(userEmail)
                .setPassword(userPassword)
                .clickRegistrationButton();

        webdriver().shouldHave(url(LoginPage.LOGIN_PAGE_URL));
    }

    @Test
    @DisplayName("Create user with invalid password")
    @Description("Create new user with invalid password and then trying to register it")
    public void userCanNotBeRegisteredWithInvalidPwd() {
        RegistrationPage registrationPage = open(RegistrationPage.REGISTER_PAGE_URL, RegistrationPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        Faker faker = new Faker();
        String invalidPassword = faker.internet().password(3, 4);

        registrationPage
                .setName(userName)
                .setEmail(userEmail)
                .setPassword(invalidPassword)
                .clickRegistrationButton();

        assertTrue("Incorrect password message must be displayed", registrationPage.checkIncorrectPwdMsg());
    }
}
