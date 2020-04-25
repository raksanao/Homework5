package com.automation.pages;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.getDriver(), this);

        /**
         * This class will be extended by page classes
         * Ant common webelements/locators can be stored here
         * Since navigation menu doesn't belong to particular page
         * We cannot really create a dedicated page class to store
         * elements from that menu
         */
    }

    protected WebDriver driver = Driver.getDriver();
public WebDriverWait wait = new WebDriverWait(driver, 25);

    @FindBy(css = "#user-menu > a")
    protected WebElement currentUser;


    public String getCurrentUserName() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(currentUser));
        return currentUser.getText().trim();
    }



    public void navigateToCalendarEvents(){
        WebElement loaderMask= null;
        driver = Driver.getDriver();
        wait= new WebDriverWait(driver, 20);
        if(driver.findElements(By.cssSelector("div[class='loader-mask shown']")).size()>0) {
            loaderMask = driver.findElement(By.cssSelector("div[class='loader-mask shown']"));
            wait.until(ExpectedConditions.invisibilityOf(loaderMask));
        }

        WebElement activitiesElement = driver.findElement(By.linkText("Activities"));
        wait.until(ExpectedConditions.visibilityOf(activitiesElement));
        wait.until(ExpectedConditions.elementToBeClickable(activitiesElement));
        activitiesElement.click();

        WebElement calendarEventsElement = driver.findElement(By.linkText("Calendar Events"));
        wait.until(ExpectedConditions.visibilityOf(calendarEventsElement));
        wait.until(ExpectedConditions.elementToBeClickable(calendarEventsElement));
        calendarEventsElement.click();

        wait.until(ExpectedConditions.invisibilityOf(loaderMask));
    }


    }




