package site.stellarburgers;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import site.stellarburgers.data.Data;
import site.stellarburgers.pages.*;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;

@DisplayName("Тесты по авторизации пользователя")
public class MainPageTest extends SetUpTest {

    @Before
    public void prepareUser() {
        RestAssured.baseURI = Data.MAIN_PAGE_URL;
        createTestUser();
        loginAndGetToken();
    }


    @Test
    @DisplayName("вход по кнопке «Войти в аккаунт» на главной")
    @Description("пользователь переходит к авторизации через кнопку «Войти в аккаунт» расположенную на главной стр Stellarburgers")
    public void logInFromMainPageTest() {
        MainPage objMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);

        objMainPage.isMainPageLoaded();
        objMainPage.clickLogInMainPage();

        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();
        String actualButtonText = objMainPage.getPlaceOrderButtonText();
        MatcherAssert.assertThat("Логин не выполнен", actualButtonText, is(Data.PLACE_ORDER_BUTTON));
    }


    @Test
    @DisplayName("вход через кнопку «Личный кабинет»")
    @Description("пользователь переходит к авторизации через кнопку «Войти» расположенную на стр «Личный кабинет»")
    public void logInFromPersonalAccountTest() {
        MainPage objMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);

        objMainPage.isMainPageLoaded();
        objMainPage.clickPersonalAccountButton();

        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();
        String actualButtonText = objMainPage.getPlaceOrderButtonText();
        MatcherAssert.assertThat("Логин не выполнен", actualButtonText, is(Data.PLACE_ORDER_BUTTON));
    }


    @Test
    @DisplayName("вход через кнопку в форме регистрации")
    @Description("пользователь переходит к авторизации через кнопку «Войти» расположенную на стр регистрации")
    public void logInFromRegisterPageTest() {
        MainPage objMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);

        objMainPage.isMainPageLoaded();
        objMainPage.clickLogInMainPage();

        objLoginPage.clickRegisterL();

        objRegisterPage.clickLogInFromRegisterPage();

        objLoginPage.clickEnter();
        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();
        String actualButtonText = objMainPage.getPlaceOrderButtonText();
        MatcherAssert.assertThat("Логин не выполнен", actualButtonText, is(Data.PLACE_ORDER_BUTTON));
    }

    @Test
    @DisplayName("вход через кнопку в форме восстановления пароля")
    @Description("пользователь переходит к авторизации через кнопку «Войти» расположенную на стр восстановления пароля")
    public void logInFromForgotPasswordPageTest() {
        MainPage objMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        ForgotPasswordPage objForgotPasswordPage = new ForgotPasswordPage(driver);

        objMainPage.isMainPageLoaded();
        objMainPage.clickLogInMainPage();

        objLoginPage.clickRecoverPassButton();

        objForgotPasswordPage.isPassRecoverPageLoaded();
        objForgotPasswordPage.clickLogInFromPassRecoverPage();

        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();

        String actualButtonText = objMainPage.getPlaceOrderButtonText();
        MatcherAssert.assertThat("Логин не выполнен", actualButtonText, is(Data.PLACE_ORDER_BUTTON));
    }


    @Test
    @DisplayName("выход по кнопке «Выйти» в личном кабинете")
    @Description("пользователь разлогинивается из Аккаунта через кнопку «Выйти» расположенную на стр личного кабинета")
    public void exitFromAccountTest() {
        MainPage objMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);

        objMainPage.isMainPageLoaded();
        objMainPage.clickLogInMainPage();

        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();

        objMainPage.clickPersonalAccountButton();

        objPersonalAccountPage.isPersonalAccountPageLoaded();
        objPersonalAccountPage.clickExitButton();

        objLoginPage.isLoginPageLoaded();

        String actualLoginPageText = objLoginPage.getLoginPageText();
        MatcherAssert.assertThat("стр Логина не отображается", actualLoginPageText, is(Data.ENTER));
    }


}
