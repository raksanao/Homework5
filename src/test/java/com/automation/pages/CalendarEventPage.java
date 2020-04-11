package com.automation.pages;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

public class CalendarEventPage extends BasePage {
    public CalendarEventPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//a[@class='dropdown-toggle'])[4]")
    public WebElement threeDots;
    @FindBy(css = "[title='Grid Settings']")
    public WebElement grid;
    @FindBy(css = "td[class='title-cell']")
    public List<WebElement> title;
    @FindBy(css = "[title='Create Calendar event']")
    public WebElement createCalendar;
    @FindBy(xpath = "//a[@class='btn-success btn dropdown-toggle'] ")
    public WebElement caret;

    @FindBy(xpath = "//a[@title='Cancel']")
    public WebElement cencel;

    @FindBy(xpath = "(//input[@placeholder='time'])[1]")
    public WebElement time;
    @FindBy(xpath = "(//li[@class='ui-timepicker-pm'])[19]")
    public WebElement nine;
//@FindBy (xpath = "//div[@id='bottom-anchor']/following-sibling::ul//a")
//public WebElement EditViewDelet;

    @FindBy(name = "oro_calendar_event_form[allDay]")
    public WebElement allDayCheckbox;

    @FindBy(css = "input[id^='recurrence-repeat']")
    public WebElement repeatCheckbox;


    public WebElement startTime;
    @FindBy(css = "[id^='time_selector_oro_calendar_event_form_end']")
    public WebElement endTime;
    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_start']")
    public WebElement startDate;
    @FindBy(css = "[id^='date_selector_oro_calendar_event_form_end']")
    public WebElement endDate;
    @FindBy(css = "[id^='oro_calendar_event_form_allDay']")
    public WebElement allDayEventCheckBox;

    @FindBy(xpath = "//li/button[contains(text(), 'Save and Close')]")
    public WebElement saveAndCloseOption;

    @FindBy(xpath = "//li/button[contains(text(), 'Save and New')]")
    public WebElement saveAndNewOption;

    @FindBy(xpath = "//li/button[normalize-space()='Save']")
    public WebElement saveOption;

    @FindBy(xpath = "(//input[@type='radio'])[4]")
    public WebElement afterCheckbox;
    @FindBy(xpath = "//span[text()='After']/following-sibling::input")
    public WebElement inputBox;

    @FindBy(xpath = "(//input[@type='radio'])[1]")
    public WebElement repeatEvery;

    @FindBy(xpath = "(//input[@type='radio'])[3]")
    public WebElement endsNever;
    @FindBy(xpath = "//label[text()='Summary:']/../following-sibling::div/div")
    public WebElement sumary;
    @FindBy(xpath = "(//input[@type='radio'])[5]")
    public WebElement byCheckbox;

    @FindBy(xpath = "(//input[@placeholder='Choose a date'] )[3]")
    public WebElement choseDate;

    @FindBy(css = "select[class='ui-datepicker-month']")
    public WebElement month;

    @FindBy(css = "select[class='ui-datepicker-year']")
    public WebElement year;


    public void set18() {//this for testcase 11
        List<WebElement> allDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));
        for (WebElement ele : allDates) {
            String date = ele.getText();
            if (date.equalsIgnoreCase("28")) {
                ele.click();
                break;
            }
        }
    }

    public void moveTo() {//this for testcase 1 to move to 3dots
        String treeDot = "(//a[@class='dropdown-toggle'])[4]";
        WebElement treeDotEl = driver.findElement(By.xpath(treeDot));
        Actions actions = new Actions(driver);
        BrowserUtils.wait(4);
        actions.moveToElement(treeDotEl).
                pause(2000).perform();
        //increase this wait time if still failing
        BrowserUtils.wait(4);

    }

    public void EditDeletView() {//this for testcase 1 for displaying deleteviewEdit
        List<WebElement> l1 = Driver.getDriver().findElements(By.xpath("//div[@id='bottom-anchor']/following-sibling::ul//a"));
        for (WebElement each : l1) {
            Assert.assertTrue(each.isDisplayed());
        }
    }

    public String getStartTime() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[id^='time_selector_oro_calendar_event_form_start']")));
        wait.until(ExpectedConditions.visibilityOf(startTime));
        return startTime.getAttribute("value");
    }

    public String getEndTime() {
        BrowserUtils.waitForPageToLoad(10);
        wait.until(ExpectedConditions.visibilityOf(endTime));
        return endTime.getAttribute("value");

    }


    }
