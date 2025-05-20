package site.stellarburgers.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import site.stellarburgers.data.Data;


public class MainPage {
    private WebDriver driver;
    private By mainPageHeader = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']/h1[text()='Соберите бургер']");
    private By enterButtonMainPage = By.xpath("//button[text()='Войти в аккаунт']");
    private By personalAccountButton = By.xpath("//a[@href='/account']");
    private By placeOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private By constructorButton = By.xpath("//p[text()='Конструктор']");
    private By burgerLogo = By.xpath("//div[@class='AppHeader_header__logo__2D0X2']");

    private By bunsTab = By.xpath("//span[text()='Булки']");
    private By bunsTabSelected = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Булки']");


    private By bunsList = By.xpath("//h2[text()='Булки']/ancestor::div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");
    private By saucesTab = By.xpath("//span[text()='Соусы']");
    private By saucesTabSelected = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Соусы']");



    private By sauceList = By.xpath("//h2[text()='Соусы']/ancestor::div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");
    private By fillingsTab = By.xpath("//span[text()='Начинки']");
    private By fillingsTabSelected = By.xpath("//div[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']//span[text()='Начинки']");


    private By fillingsList = By.xpath("//h2[text()='Начинки']/ancestor::div[contains(@class, 'BurgerIngredients_ingredients__menuContainer')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Проверка загрузки главной стр")
    public boolean isMainPageLoaded() {
        driver.get(Data.MAIN_PAGE_URL);
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainPageHeader));
        return isElementVisible(mainPageHeader);

    }

    @Step("Отображается главная стр")
    public String getMainPageHeaderText() {
        return driver.findElement(mainPageHeader).getText();
    }


    @Step("Нажать Вход на главной стр")
    public void clickLogInMainPage() {
        driver.findElement(enterButtonMainPage).click();
    }

    @Step("Нажать на Личный кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }


    @Step("Проверка автризовался ли пользователь")
    public boolean isUserLoggedIn() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton));
        return isElementVisible(placeOrderButton);
    }

    @Step("Проверка отображения кнопки 'Оформить заказ' полсе авторизации")
    public String getPlaceOrderButtonText() {
        return driver.findElement(placeOrderButton).getText();
    }


    @Step("Нажать на конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажать на лого Бургера")
    public void clickBurgerLogo() {
        driver.findElement(burgerLogo).click();
    }


    @Step("Нажать на вкладку Булки")
    public void clickBunsTab() {
        driver.findElement(bunsTab).click();
    }

    @Step("Проверка выбрана ли вкладка Булки")
    public boolean isBunsTabSelected() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsTabSelected));
        return isElementVisible(bunsTabSelected);
    }



    @Step("Нажать на вкладку Соусы")
    public void clickSaucesTab() {
        driver.findElement(saucesTab).click();
    }

    @Step("Проверка выбрана ли вкладка Соусы")
    public boolean isSaucesTabSelected() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(saucesTabSelected));
        return isElementVisible(saucesTabSelected);
    }


    @Step("Нажать на вкладку Начинки")
    public void clickFillingsTab() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверка выбрана ли вкладка Начинки")
    public boolean isFillingsTabSelected() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsTabSelected));
        return isElementVisible(fillingsTabSelected);
    }


    @Step("Проверка загрузки списка Булок")
    public boolean isBunsListLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(bunsList));
        return isElementVisible(bunsList);
    }

    @Step("Проверка загрузки списка Соусов")
    public boolean isSauceListLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(sauceList));
        return isElementVisible(sauceList);
    }

    @Step("Проверка загрузки списка Начинок")
    public boolean isFillingsLisLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(fillingsList));
        return isElementVisible(fillingsList);
    }


    private boolean isElementVisible(By locator) {
        try {
            return driver.findElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

}
