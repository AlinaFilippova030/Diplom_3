package site.stellarburgers.pages;


import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import site.stellarburgers.data.Data;


public class LoginPage {

    private WebDriver driver;
    private By loginHeader = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Вход']");
    private By registerButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Зарегистрироваться']");
    private By enterButtonLoginPage = By.xpath("//button[text()='Войти']");
    private By emailInput = By.xpath("//div[label[text()='Email']]//input[@type='text']");
    private By passInput = By.name("Пароль");
    private By recoverPassButton = By.xpath("//a[@href='/forgot-password']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки стр входа")
    public boolean isLoginPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
        String actualHeaderText = driver.findElement(loginHeader).getText();
        return actualHeaderText.equals(Data.ENTER);
    }

    @Step("стр входа загружена")
    public String getLoginPageText() {
        return driver.findElement(loginHeader).getText();
    }

    @Step("нажать зарегистрироваться")
    public void clickRegisterL() {
        driver.findElement(registerButton).click();
    }

    @Step("нажать войти")
    public void clickEnter() {
        driver.findElement(enterButtonLoginPage).click();
    }

    @Step("Заполнить логин")
    public void fillEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Заполнить пароль")
    public void fillPass(String password) {
        driver.findElement(passInput).sendKeys(password);
    }

    public void fillLoginForm(String email, String password) {
        fillEmail(email);
        fillPass(password);
        clickEnter();
    }


    @Step("Нажать восстановить пароль")
    public void clickRecoverPassButton() {
        driver.findElement(recoverPassButton).click();
    }

}
