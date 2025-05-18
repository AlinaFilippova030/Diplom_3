package site.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import site.stellarburgers.data.Data;


public class PersonalAccountPage {
    private WebDriver driver;
    private By profileText = By.xpath(".//li[@class='Account_listItem__35dAP']/a[text()='Профиль']");
    private By exitButton = By.xpath(".//button[@class='Account_button__14Yp3 text text_type_main-medium text_color_inactive' and text()='Выход']");


    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки Личного кабинета")
    public boolean isPersonalAccountPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(profileText));
        String actualHeaderText = driver.findElement(profileText).getText();
        return actualHeaderText.equals(Data.PROFILE);
    }

    @Step("Отображается личный кабинет")
    public String getProfileText() {
        return driver.findElement(profileText).getText();
    }

    @Step("Нажать Выход из аккаунта")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }
}

