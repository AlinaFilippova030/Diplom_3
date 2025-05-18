package site.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import site.stellarburgers.data.Data;
import site.stellarburgers.pages.LoginPage;
import site.stellarburgers.pages.MainPage;
import site.stellarburgers.pages.PersonalAccountPage;

import static org.hamcrest.CoreMatchers.is;

@DisplayName("Тесты на переходы по разделам сайта")
public class SwitchSectionsTest extends SetUpTest {
    @Before
    public void prepareUser() {
        RestAssured.baseURI = Data.MAIN_PAGE_URL;
        createTestUser();
        loginAndGetToken();
    }

    @Test
    @DisplayName("переход по клику на «Личный кабинет»")
    public void openPersonalAccountTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.isMainPageLoaded();

        objMainPage.clickLogInMainPage();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();

        objMainPage.clickPersonalAccountButton();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.isPersonalAccountPageLoaded();

        String actualProfileText = objPersonalAccountPage.getProfileText();
        MatcherAssert.assertThat("Профиль не отображается", actualProfileText, is(Data.PROFILE));
    }


    @Test
    @DisplayName("переход по клику на «Конструктор» из «Личный кабинет»")
    public void openMainPageByConstructorTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.isMainPageLoaded();

        objMainPage.clickLogInMainPage();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();

        objMainPage.clickPersonalAccountButton();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.isPersonalAccountPageLoaded();

        objMainPage.clickConstructorButton();
        objMainPage.isMainPageLoaded();


        String actualMainPageText = objMainPage.getMainPageHeaderText();
        MatcherAssert.assertThat("Главная стр не отображается", actualMainPageText, is(Data.MAIN_PAGE_HEADER_TEXT));
    }


    @Test
    @DisplayName("переход по клику логотип Stellar Burgers из «Личный кабинет»")
    public void openMainPageByBurgerLogoTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.isMainPageLoaded();

        objMainPage.clickLogInMainPage();

        LoginPage objLoginPage = new LoginPage(driver);
        objLoginPage.fillLoginForm(userEmail, userPassword);

        objMainPage.isUserLoggedIn();

        objMainPage.clickPersonalAccountButton();

        PersonalAccountPage objPersonalAccountPage = new PersonalAccountPage(driver);
        objPersonalAccountPage.isPersonalAccountPageLoaded();

        objMainPage.clickBurgerLogo();
        objMainPage.isMainPageLoaded();

        String actualMainPageText = objMainPage.getMainPageHeaderText();
        MatcherAssert.assertThat("Главная стр не отображается", actualMainPageText, is(Data.MAIN_PAGE_HEADER_TEXT));
    }

    @Test
    @DisplayName("переход к разделу: «Булки»")
    public void clickBunsTabTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.isMainPageLoaded();
        MatcherAssert.assertThat("раздел с булками не отображается", objMainPage.isBunsListLoaded());
    }

    @Test
    @DisplayName("переход к разделу: «Соусы»")
    public void clickSaucesTabTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.isMainPageLoaded();
        objMainPage.clickSaucesTab();
        MatcherAssert.assertThat("раздел с Соусами не отображается", objMainPage.isSauceListLoaded());
    }

    @Test
    @DisplayName("переход к разделу: «Ингридиенты»")
    public void clickFillingsTabTest() {
        MainPage objMainPage = new MainPage(driver);
        objMainPage.isMainPageLoaded();
        objMainPage.clickFillingsTab();
        MatcherAssert.assertThat("раздел с Ингридиентами не отображается", objMainPage.isFillingsLisLoaded());
    }


}
