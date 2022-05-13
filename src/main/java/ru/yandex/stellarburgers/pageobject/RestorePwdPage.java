package ru.yandex.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class RestorePwdPage {
    public static final String RESTORE_PWD_PAGE_URL =  "https://stellarburgers.nomoreparties.site/forgot-password";

    //Entry Link
    @FindBy(how = How.XPATH, using = ".//a[text()='Войти']")
    private SelenideElement loginLink;

    //Click the login link
    @Step("Click the login link")
    public  LoginPage clickLoginLink() {
        loginLink.scrollTo().click();
        return page(LoginPage.class);
    }
}
