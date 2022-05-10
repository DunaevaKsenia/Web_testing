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
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.stellarburgers.UserClient;
import ru.yandex.stellarburgers.pageobject.LoginPage;
import ru.yandex.stellarburgers.pageobject.MainPage;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

@Epic(value = "Stellar Burgers")
@Feature(value = "User account")
@Story(value = "Logout User, Yandex tests")
public class UserLogOutTest {
    private static String userName;
    private static String userPassword;
    private static String userEmail;
    private static UserClient userClient;
    private static String accessToken;
    private MainPage mainPage;
    private static WebDriver driver;

    @Before
    public void beforeTests() {
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

        mainPage = open(MainPage.MAIN_PAGE_URL, MainPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        mainPage
                .clickLoginButton()
                .setEmail(userEmail)
                .setPassword(userPassword)
                .clickLoginButton();
    }

    @After
    public void afterTests() {
        if(accessToken != null) {
            userClient.deleteUser(userEmail, accessToken.substring(7));
        }

        webdriver().driver().close();
    }

    @Test
    @DisplayName("Logout valid user")
    @Description("Create new random user, registered, login and then try to logout")
    public void userCanLoginLoginButtonMainPage() {
        mainPage
                .clickAccountLink()
                .getAccountPage()
                .clickLogoutButton();

        webdriver().shouldHave(url(LoginPage.LOGIN_PAGE_URL));
    }
}
