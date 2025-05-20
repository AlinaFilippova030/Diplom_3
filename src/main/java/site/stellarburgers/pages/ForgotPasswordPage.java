package site.stellarburgers.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import site.stellarburgers.data.Data;
import io.qameta.allure.Step;

public class ForgotPasswordPage {
    private WebDriver driver;
    private By passRecoverHeader = By.xpath(".//div[@class='Auth_login__3hAey']/h2[text()='Восстановление пароля']");
    private By logInFromPassRecoverPage = By.xpath(" //a[@href='/login']");


    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки стр восстановления пароля")
    public boolean isPassRecoverPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passRecoverHeader));
        String actualHeaderText = driver.findElement(passRecoverHeader).getText();
        return actualHeaderText.equals(Data.PASS_RECOVER_PAGE_HEADER);
    }

    @Step("нажать Вход на стр восстановления пароля")
    public void clickLogInFromPassRecoverPage() {
        driver.findElement(logInFromPassRecoverPage).click();
    }

}
