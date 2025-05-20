package site.stellarburgers;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import site.stellarburgers.config.Config;
import site.stellarburgers.user.CreateNewUser;
import site.stellarburgers.user.UserLogin;
import site.stellarburgers.user.UserSteps;
import site.stellarburgers.utils.BrowserUtils;
import site.stellarburgers.utils.WebDriverFactory;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.apache.http.HttpStatus.SC_OK;

public class SetUpTest {
    protected WebDriver driver;
    protected UserSteps userSteps = new UserSteps();
    protected String userEmail;
    protected String userPassword;
    protected String userToken;
    Faker faker = new Faker();
    String email = faker.internet().emailAddress();
    String password = faker.internet().password();
    String userName = faker.name().username();

    @Before
    public void setUpDriver() {
        String browser = Config.getBrowser();
        driver = WebDriverFactory.createDriver(browser);
        driver.manage().window().maximize();
    }

    @Step("Создать тестового пользователя")
    public void createTestUser() {
        userEmail = email;
        userPassword = password;
        String name = userName;

        CreateNewUser newUser = new CreateNewUser(userEmail, userPassword, userName);
        Response response = userSteps.createNewUser(newUser);
        response.then().statusCode(SC_OK);
    }

    @Step("Авторизоваться и получить токен")
    public void loginAndGetToken() {
        UserLogin loginCreds = new UserLogin(userEmail, userPassword);
        Response loginResponse = userSteps.loginUser(loginCreds);
        loginResponse.then().statusCode(SC_OK);

        userToken = loginResponse.path("accessToken");
        assertThat(userToken, notNullValue());
    }

    @After
    public void tearDown() {
        if (userToken != null) {
            userSteps.deleteUser(userToken);
        }
        BrowserUtils.closeBrowser(driver);
    }
}
