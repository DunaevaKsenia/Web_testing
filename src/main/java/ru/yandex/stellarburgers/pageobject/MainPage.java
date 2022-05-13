package ru.yandex.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage {
    //Main page address
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";

    //Button login
    @FindBy(how = How.XPATH, using = ".//button[text()='Войти в аккаунт']")
    private SelenideElement loginButton;

    //Link personal account
    @FindBy(how = How.LINK_TEXT, using = "Личный Кабинет")
    private SelenideElement accountLink;

    //Assemble a burger text
    @FindBy(how = How.XPATH, using = ".//h1[text()='Соберите бургер']")
    private SelenideElement assembleBurger;

    //Section buns
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Булки')]")
    private SelenideElement bunsSpan;

    //Section sauces
    @FindBy(how = How.XPATH, using = ".//span[contains(text(), 'Соусы')]")
    private SelenideElement saucesSpan;

    //Section toppings
    @FindBy(how = How.XPATH, using = ".//span[contains(text(),'Начинки')]")
    private SelenideElement toppingsSpan;

    //First toppings
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Мясо бессмертных моллюсков Protostomia')]")
    public SelenideElement firstTopping;

    //First sauce
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Соус Spicy-X')]")
    public SelenideElement firstSauce;

    //First bun
    @FindBy(how = How.XPATH, using = ".//p[contains(text(),'Флюоресцентная булка R2-D3')]")
    public SelenideElement firstBun;

    //Click the account entry button
    @Step("Click the account entry button")
    public LoginPage clickLoginButton() {
        loginButton.click();
        return page(LoginPage.class);
    }

    //Click the account link
    @Step("Click the account entry link")
    public MainPage clickAccountLink() {
        accountLink.click();
        return this;
    }

    //Click the buns
    @Step("Click the buns")
    public MainPage clickBunsSpan() {
        bunsSpan.parent().click();
        return this;
    }

    //Click the sauces
    @Step("Click the sauces")
    public MainPage clickSaucesSpan() {
        saucesSpan.parent().click();
        return this;
    }

    //Click the sauces
    @Step("Click the toppings")
    public MainPage clickToppingsSpan() {
        toppingsSpan.parent().click();
        return this;
    }

    public boolean isAssembleBurgerDispleyed() {
        return assembleBurger.isDisplayed();
    }

    public  LoginPage getLoginPage() {
        return page( LoginPage.class);
    }

    public  AccountPage getAccountPage() {
        return page( AccountPage.class);
    }
}
