package ru.yandex.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    //User registration page address
    public static final String LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";

    //Email input field
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private SelenideElement emailInput;

    //Password input field
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement pwdInput;

    //Button login
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти']")
    private SelenideElement loginButton;

    //Restore Pwd Link
    @FindBy(how = How.XPATH, using = ".//a[text()='Восстановить пароль']")
    private SelenideElement restorePwd;

    //Set user email
    @Step("Set user email:  {email}")
    public LoginPage setEmail(String email) {
        emailInput.sendKeys(email);
        return this;
    }

    //Set user password
    @Step("Set user password: {password}")
    public LoginPage setPassword(String password) {
        pwdInput.sendKeys(password);
        return this;
    }

    //Click login button
    @Step("Click login button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    //Click restore Pwd Link
    @Step("Click restorePwd")
    public RestorePwdPage clickRestorePwd() {
        restorePwd.click();
        return page(RestorePwdPage.class);
    }
}
