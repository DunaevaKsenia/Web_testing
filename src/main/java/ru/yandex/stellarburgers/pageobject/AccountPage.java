package ru.yandex.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class AccountPage {
    //Account page address
    public static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/account/profile";

    //Button logout
    @FindBy(how = How.XPATH, using = ".//button[text()='Выход']")
    private SelenideElement logoutButton;

    //Constructor Link
    @FindBy(how = How.XPATH, using = ".//p[text()='Конструктор']")
    public SelenideElement constructorLink;

    //Constructor Link
    @FindBy(how = How.CLASS_NAME, using = "AppHeader_header__logo__2D0X2")
    public SelenideElement logoLink;

    //Click the logout button
    @Step("Click the logout button")
    public LoginPage clickLogoutButton() {
        logoutButton.click();
        return page(LoginPage.class);
    }

    //Click the logout button
    @Step("Click constructor Link")
    public MainPage clickConstructorLink() {
        constructorLink.click();
        return page(MainPage.class);
    }

    //Click the logout button
    @Step("Click logo Link")
    public MainPage clickLogoLink() {
        logoLink.click();
        return page(MainPage.class);
    }
}
