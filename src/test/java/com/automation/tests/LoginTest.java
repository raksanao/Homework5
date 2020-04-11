package com.automation.tests;

import com.automation.pages.BasePage;
import com.automation.pages.CalendarEventPage;
import com.automation.pages.DashboardPage;
import com.automation.pages.LoginPage;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.ConfigReader;
import com.automation.utilities.DateTimeUtilities;
import com.automation.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTest extends TestBase {

    /*Test Case #1
1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Hover on three dots “…” for “Testers meeting”
calendar even
5. Verify that “view”, “edit” and “delete” options
are available
*/

    //Test Case 1
    @Test
    public void testCase1() {//done

        LoginPage loginPage = new LoginPage();
        loginPage.login();
     CalendarEventPage calendarEventPage = new CalendarEventPage();
       calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.moveTo();
        calendarEventPage.EditDeletView();


    }

    @Test
    /*1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Grid Options” button
5. Deselect all options except “Title”
6. Verify that “Title” column still displayed*/
    public void testCase2() {//done
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEventPage calendarEventPage = new CalendarEventPage();
        calendarEventPage.navigateTo("Activities", "Calendar Events");
        calendarEventPage.grid.click();
        for (int i = 0; i < calendarEventPage.title.size() - 1; i++) {
            if (calendarEventPage.title.get(i).getText().contains("Title")) {
                continue;
            }
                calendarEventPage.title.get(i).click();
               String actual = calendarEventPage.title.get(0).getText();
                String expected = "Title";
                Assert.assertEquals(actual, expected);


        }
    }

        @Test
        /*1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Expand “Save And Close” menu
6. Verify that “Save And Close”, “Save And New”
and “Save” options are available
*/
        public void testcase3(){//done
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateTo("Activities", "Calendar Events");
          calendarEventPage.createCalendar.click();
          calendarEventPage.caret.click();
            Assert.assertTrue(calendarEventPage.saveAndCloseOption.isDisplayed());
            Assert.assertTrue(calendarEventPage.saveAndNewOption.isDisplayed());
            Assert.assertTrue(calendarEventPage.saveOption.isDisplayed());


        }

        @Test
        /*1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Then, click on “Cancel” button
6. Verify that “All Calendar Events” page subtitle is
displayed
*/
        public void testCase4(){//done
            LoginPage loginPage = new LoginPage();
            loginPage.login();

            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateTo("Activities", "Calendar Events");
            calendarEventPage.createCalendar.click();
          calendarEventPage.cencel.click();
            BrowserUtils.wait(3);
            String expected=driver.getTitle();
            String actual="All - Calendar Events - Activities";
            Assert.assertEquals(expected,actual,"its not matching");

        }
        @Test//not done
        /*1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button*/
    public void testCase5() {
            LoginPage loginPage = new LoginPage();
            loginPage.login();

            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateTo("Activities", "Calendar Events");
           calendarEventPage.createCalendar.click();

           //couldent Verify that difference between end and start time
            //is exactly 1 hour

            String startTime =calendarEventPage.getStartTime(); //get start time
            String endTime = calendarEventPage.getEndTime(); //get end time
            String format = "h:mm a";//format 5:15 AM for example
            long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);
            Assert.assertEquals(actual, 1, "Time difference is not correct");



        }
        @Test
        public void testCase6(){//not done
        /*1. Go to “https://qa1.vytrack.com/"
2. Login as a store manager
3. Navigate to “Activities -> Calendar Events”
4. Click on “Create Calendar Event” button
5. Select “9:00 PM” as a start time
6. Verify that end time is equals to “10:00 PM”
*/
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateTo("Activities", "Calendar Events");
           calendarEventPage.createCalendar.click();
calendarEventPage.time.click();
calendarEventPage.nine.click();
WebElement ele=driver.findElement(By.xpath("(//input[@placeholder='time'])[2]"));
String expected=ele.getText();
String actual="10:00 PM";
Assert.assertEquals(actual,expected,"not match");
//last step not done yet
}
@Test
public void testCase7(){//not done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateTo("Activities", "Calendar Events");
    calendarEventPage.createCalendar.click();
calendarEventPage.allDayCheckbox.click();
Assert.assertTrue(calendarEventPage.allDayCheckbox.isSelected());
/*this steps not done yet 7. Verify that start and end time input boxes are
not displayed
*/
    Assert.assertTrue(calendarEventPage.startDate.isDisplayed());
    Assert.assertTrue(calendarEventPage.endDate.isDisplayed());

}

@Test
public void testCase8(){//not done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateTo("Activities", "Calendar Events");
    calendarEventPage.createCalendar.click();
    calendarEventPage.repeatCheckbox.click();
    Assert.assertTrue(calendarEventPage.repeatCheckbox.isSelected());
/*7. Verify that “Daily” is selected by default and
following options are available in
“Repeats” drop-down: not done this step*/
}
@Test
public void testCase9(){//done

    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateTo("Activities", "Calendar Events");
    calendarEventPage.createCalendar.click();
calendarEventPage.repeatCheckbox.click();
Assert.assertTrue(calendarEventPage.repeatCheckbox.isSelected());
Assert.assertTrue(calendarEventPage.repeatEvery.isSelected());
calendarEventPage.endsNever.click();
Assert.assertTrue(calendarEventPage.endsNever.isSelected());
    Assert.assertTrue(calendarEventPage.sumary.isDisplayed());
}
@Test
    public void testCase10(){//done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateTo("Activities", "Calendar Events");
    calendarEventPage.createCalendar.click();
    BrowserUtils.wait(5);
    calendarEventPage.repeatCheckbox.click();
    calendarEventPage.afterCheckbox.click();
    calendarEventPage.inputBox.sendKeys("10",Keys.ENTER);
    Assert.assertTrue(calendarEventPage.sumary.isDisplayed());



}
@Test
public void testCase11(){//done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateTo("Activities", "Calendar Events");
    calendarEventPage.createCalendar.click();

calendarEventPage.repeatCheckbox.click();
calendarEventPage.byCheckbox.click();
calendarEventPage.choseDate.click();
Select selectMonth=new Select(calendarEventPage.month);
    selectMonth.selectByVisibleText("Nov");
    Select selectYear=new Select(calendarEventPage.year);
    selectYear.selectByVisibleText("2021");
    calendarEventPage.set18();
    Assert.assertTrue(calendarEventPage.sumary.isDisplayed());
    }


@Test
public void testCase12(){//didnt started yet
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateTo("Activities", "Calendar Events");
    calendarEventPage.createCalendar.click();
calendarEventPage.repeatCheckbox.click();
}
}









