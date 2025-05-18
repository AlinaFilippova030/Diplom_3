package site.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import site.stellarburgers.data.Data;


public class RegisterPage {

    private WebDriver driver;
    private By registrationHeader = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Регистрация']");
    private By nameInput = By.xpath("//div[label[text()='Имя']]//input[@type='text']");
    private By emailInput = By.xpath("//div[label[text()='Email']]//input[@type='text']");
    private By passInput = By.name("Пароль");
    private By registrationButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']");
    private By passwordHint = By.xpath(".//p[@class='input__error text_type_main-default']");
    private By logInFromRegisterPage = By.xpath("//a[@href='/login']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки стр регистрации")
    public boolean isRegisterPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(registrationHeader));
        String actualHeaderText = driver.findElement(registrationHeader).getText();
        return actualHeaderText.equals(Data.REGISTER);
    }

    @Step("Заполнение формы регистрации")
    public void fillRegistrationForm(String name, String email, String password) {
        driver.findElement(nameInput).clear();
        driver.findElement(nameInput).sendKeys(name);

        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);

        driver.findElement(passInput).clear();
        driver.findElement(passInput).sendKeys(password);
    }


    @Step("отображение Подсказки о некорректном пароле")
    public boolean incorrectPasswordHintVisible() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordHint));
        String actualHeaderText = driver.findElement(passwordHint).getText();
        return actualHeaderText.equals(Data.INCORRECT_PASS_HINT);
    }


    @Step("Нажать Регистрация")
    public void clickRegisterR() {
        driver.findElement(registrationButton).click();
    }

    @Step("нажать Вход на стр Регистрации")
    public void clickLogInFromRegisterPage() {
        driver.findElement(logInFromRegisterPage).click();
    }

}
