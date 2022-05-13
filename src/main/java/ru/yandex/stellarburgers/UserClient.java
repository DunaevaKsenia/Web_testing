package ru.yandex.stellarburgers;

import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_ACCEPTED;

public class UserClient {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";
    private static final String CREATE_USER = "/auth/register";
    private static final String INFO_USER = "/auth/user";
    private static final String USER_LOGIN = "/auth/login";
    private static final String USER_LOGOUT = "/auth/logout";

    protected RequestSpecification getBaseSpec() {
        return new RequestSpecBuilder()
                .setContentType(JSON)
                .setBaseUri(BASE_URL)
                .build();
    }

    @Step("User login with {userData}")
    public ValidatableResponse loginUser(Map<String, String> userData) {
        return given()
                .spec(getBaseSpec())
                .body(userData)
                .when()
                .post(USER_LOGIN)
                .then();
    }

    @Step("User logout with user Email:{userEmail}")
    public ValidatableResponse logoutUser(String userEmail, Map<String, String> userData) {
        return given()
                .spec(getBaseSpec())
                .body(userData)
                .when()
                .post(USER_LOGOUT)
                .then();
    }

    @Step("Delete user with Email:{userEmail}  AccessToken:{accessToken}")
    public void deleteUser(String userEmail, String accessToken) {
        given()
                .spec(getBaseSpec())
                .auth().oauth2(accessToken)
                .when()
                .delete(INFO_USER)
                .then()
                .assertThat()
                .statusCode(SC_ACCEPTED);
    }

    @Step("Create new user with: userData ")
    public ValidatableResponse createUser(Map<String, String> userData) {
        return given()
                .spec(getBaseSpec())
                .body(userData)
                .when()
                .post(CREATE_USER)
                .then();
    }
}
