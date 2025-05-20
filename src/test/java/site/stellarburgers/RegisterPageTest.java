package site.stellarburgers;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import site.stellarburgers.config.Config;
import site.stellarburgers.data.Data;
import site.stellarburgers.data.DataGenerator;
import site.stellarburgers.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.pages.MainPage;
import site.stellarburgers.pages.RegisterPage;
import site.stellarburgers.utils.BrowserUtils;
import site.stellarburgers.utils.WebDriverFactory;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;


@DisplayName("Тесты по регистрации пользователя")
public class RegisterPageTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        String browser = Config.getBrowser();
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Успешная Регистрация нового клиента с валидными данными и отображение страницы входа в аккаунт")
    public void successfulRegistrationTest() {
        MainPage objisMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);

        objisMainPage.isMainPageLoaded();
        objisMainPage.clickLogInMainPage();

        objLoginPage.clickRegisterL();

        objRegisterPage.isRegisterPageLoaded();
        objRegisterPage.fillRegistrationForm(DataGenerator.getRandomName(), DataGenerator.getRandomEmail(), DataGenerator.getValidPassword());
        objRegisterPage.clickRegisterR();

        objLoginPage.isLoginPageLoaded();

        String actualLoginPageText = objLoginPage.getLoginPageText();
        assertThat("стр Логина не отображается", actualLoginPageText, is(Data.ENTER));
    }

    @Test
    @DisplayName("не Успешная регистрация")
    @Description("Не Успешная Регистрация нового клиента с не валидным паролем меньше 6 знаков и отображение подсказки для поля 'пароль'")
    public void notSuccessfulRegistrationTest() {
        MainPage objisMainPage = new MainPage(driver);
        LoginPage objLoginPage = new LoginPage(driver);
        RegisterPage objRegisterPage = new RegisterPage(driver);

        objisMainPage.isMainPageLoaded();
        objisMainPage.clickLogInMainPage();

        objLoginPage.clickRegisterL();

        objRegisterPage.isRegisterPageLoaded();
        objRegisterPage.fillRegistrationForm(DataGenerator.getRandomName(), DataGenerator.getRandomEmail(), DataGenerator.getInvalidPassword());
        objRegisterPage.clickRegisterR();

        assertTrue("подсказка поля пароль отображается", objRegisterPage.incorrectPasswordHintVisible());
        assertTrue("находимся на стр Регистрации", objRegisterPage.isRegisterPageLoaded());
    }


    @After
    public void tearDown() {
        BrowserUtils.closeBrowser(driver);
    }

}
