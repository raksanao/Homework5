package com.automation.tests;
import com.automation.pages.CalendarEventPage;

import com.automation.pages.LoginPage;
import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DateTimeUtilities;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;


public class LoginTest extends TestBase {


    //Test Case 1
    @Test
    public void testCase1() {//done

        LoginPage loginPage = new LoginPage();
        loginPage.login();
     CalendarEventPage calendarEventPage = new CalendarEventPage();
     calendarEventPage.navigateToCalendarEvents();
        calendarEventPage.moveToThreeDotes();
        calendarEventPage.EditDeletView();

    }

    @Test
    public void testCase2() {//done
        LoginPage loginPage = new LoginPage();
        loginPage.login();
        CalendarEventPage calendarEventPage = new CalendarEventPage();
        calendarEventPage.navigateToCalendarEvents();
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
        public void testcase3(){//done
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateToCalendarEvents();
          calendarEventPage.createCalendar.click();
          calendarEventPage.caret.click();
            Assert.assertTrue(calendarEventPage.saveAndCloseOption.isDisplayed());
            Assert.assertTrue(calendarEventPage.saveAndNewOption.isDisplayed());
            Assert.assertTrue(calendarEventPage.saveOption.isDisplayed());
        }
        @Test
        public void testCase4(){//done
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateToCalendarEvents();
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
    public void testCase5() {//not done yet
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateToCalendarEvents();
           calendarEventPage.createCalendar.click();
           //couldent Verify that difference between end and start time
            //is exactly 1 hour
            String startTime =calendarEventPage.startTime.getAttribute("value"); //get start time
            String endTime = calendarEventPage.endTime.getAttribute("value"); //get end time
           String format = "h:mm a";//format 5:15 AM for example
          long actual = DateTimeUtilities.getTimeDifference(startTime, endTime, format);
          Assert.assertEquals(actual, 1, "Time difference is not correct");
           // SimpleDateFormat sf = new SimpleDateFormat("HH:mm aa");

        }
        @Test
        public void testCase6(){//done
            LoginPage loginPage = new LoginPage();
            loginPage.login();
            CalendarEventPage calendarEventPage = new CalendarEventPage();
            calendarEventPage.navigateToCalendarEvents();
           calendarEventPage.createCalendar.click();
calendarEventPage.time.click();
calendarEventPage.startTimeNine.click();
Assert.assertEquals(calendarEventPage.endTime.getAttribute("value"),"10:00 PM");
}
@Test
public void testCase7(){//not done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateToCalendarEvents();
    calendarEventPage.createCalendar.click();
calendarEventPage.allDayCheckbox.click();
Assert.assertTrue(calendarEventPage.allDayCheckbox.isSelected());
/*this steps not done yet 7. Verify that start and end time input boxes are
not displayed
*/

   calendarEventPage.wait.until(ExpectedConditions.invisibilityOf(calendarEventPage.startTime));//not working
   calendarEventPage.wait.until(ExpectedConditions.invisibilityOf(calendarEventPage.endTime));//not working
    Assert.assertFalse(calendarEventPage.startTime.isDisplayed());//not working
    Assert.assertFalse(calendarEventPage.endTime.isDisplayed());//not working
    Assert.assertTrue(calendarEventPage.startDate.isDisplayed());
    Assert.assertTrue(calendarEventPage.endDate.isDisplayed());

}
@Test
public void testCase8(){// done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateToCalendarEvents();
    calendarEventPage.createCalendar.click();
    calendarEventPage.repeatCheckbox.click();
    Assert.assertTrue(calendarEventPage.repeatCheckbox.isSelected());

    Select s = new Select(calendarEventPage.repeatDropDown);
    s.getFirstSelectedOption().isSelected();
    Assert.assertEquals("Daily", s.getFirstSelectedOption().getText());

    List<String> l1 = new ArrayList<>();
    for (WebElement each : s.getOptions()) {
        l1.add(each.getText());
    }
    for (String each : l1) {
        Assert.assertTrue(l1.contains(each));
    }

}
@Test
public void testCase9(){//done

    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();

    calendarEventPage.navigateToCalendarEvents();
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
    calendarEventPage.navigateToCalendarEvents();
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
    calendarEventPage.navigateToCalendarEvents();
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
public void testCase12(){//done
    LoginPage loginPage = new LoginPage();
    loginPage.login();
    CalendarEventPage calendarEventPage = new CalendarEventPage();
    calendarEventPage.navigateToCalendarEvents();
    calendarEventPage.createCalendar.click();
calendarEventPage.repeatCheckbox.click();
    Select s = new Select(calendarEventPage.repeatDropDown);
 s.selectByValue("weekly");
 calendarEventPage.mondayCheckbox.click();
 calendarEventPage.fridayCheckbox.click();
 Assert.assertTrue(calendarEventPage.mondayCheckbox.isSelected());
 Assert.assertTrue(calendarEventPage.fridayCheckbox.isSelected());
 Assert.assertTrue(calendarEventPage.sumary.isDisplayed());
 String expected=calendarEventPage.sumary.getText();
 String actual="Weekly every 1 week on Monday, Friday";
 Assert.assertEquals(expected,actual,"wrong summery");
}
}









