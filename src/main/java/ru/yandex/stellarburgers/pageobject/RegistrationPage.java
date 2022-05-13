package ru.yandex.stellarburgers.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RegistrationPage {
    //User registration page address
    public static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";


    //Name and email input fields. Name first element, email second element.
    @FindBy(how = How.XPATH, using = ".//input[@name='name']")
    private ElementsCollection nameEmailInput;

    //Password input field
    @FindBy(how = How.XPATH, using = ".//input[@name='Пароль']")
    private SelenideElement pwdInput;

    //Button Register
    @FindBy(how = How.XPATH, using = ".//button[text()='Зарегистрироваться']")
    private SelenideElement registerBtn;

    //Entry Link
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    //Message incorrect password
    @FindBy(how = How.XPATH, using = ".//p[text()='Некорректный пароль']")
    public SelenideElement incorrectPwdMsg;


    //Set username
    @Step("Set username: {name}")
    public RegistrationPage setName(String name) {
        nameEmailInput.get(0).sendKeys(name);
        return this;
    }

    //Set user email
    @Step("Set user email:  {email}")
    public RegistrationPage setEmail(String email) {
        nameEmailInput.get(1).sendKeys(email);
        return this;
    }

    //Set user password
    @Step("Set user password: {password}")
    public RegistrationPage setPassword(String password) {
        pwdInput.sendKeys(password);
        return this;
    }

    //Click register button
    @Step("Click register button")
    public LoginPage clickRegistrationButton() {
        registerBtn.click();
        return page(LoginPage.class);
    }

    //Click the login link
    @Step("Click the login link")
    public  LoginPage clickLoginLink() {
        loginLink.scrollTo().click();
        return page(LoginPage.class);
    }

    //Check incorrect password message is displayed
    @Step("Check incorrect password message is displayed")
    public boolean checkIncorrectPwdMsg() {
        return incorrectPwdMsg.isDisplayed();
    }
}
