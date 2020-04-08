package com.automation.Homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TodaysDate_YearsMonthsDays {



     private WebDriver driver;

     @Test
     public void verifyYearMonthDayDisplayed(){

         WebElement year=driver.findElement(By.id("year"));
         Select yearSelect=new Select(year);
        String yearYext= yearSelect.getFirstSelectedOption().getText();

         WebElement months= driver.findElement(By.id("month"));
         Select monthsSelect=new Select(months);
         String monthText=monthsSelect.getFirstSelectedOption().getText();

         WebElement day= driver.findElement(By.id("day"));
         Select daySelect= new Select(day);
         String dayText=daySelect.getFirstSelectedOption().getText();


         String expected= LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MMMM-d"));
         System.out.println("expected = " + expected);
         String actual= yearYext+"-"+monthText+"-"+dayText;
         System.out.println("actual = " + actual);

         Assert.assertEquals(actual,expected);





     }

    @Test
    public void verifyCurrentNumberOfDays(){

      Random r=new Random();

       List<WebElement> years=driver.findElements(By.xpath("//select[@id='year']//option"));
//        System.out.println(BrowserUtils.getTextFromWebElement(years));
//
       int randomNum=r.nextInt(years.size());

        Select yearSelect=new Select(driver.findElement(By.id("year")));
        yearSelect.selectByIndex(randomNum);
        BrowserUtils.wait(2);
        boolean isLeap=Year.isLeap(Long.parseLong(yearSelect.getFirstSelectedOption().getText()));

        WebElement month=driver.findElement(By.cssSelector("[id='month']"));
        List<WebElement>allMonths=driver.findElements(By.cssSelector("[id='month']>option"));

        Select monthSelect= new Select(month);

        //FOR JANUARY:
//        monthSelect.selectByIndex(0);  for january
//        BrowserUtils.wait(3);

        List<String> months30Days= Arrays.asList("April","June","September","November");
        List<String> months31Days=Arrays.asList("January","March","May","July","August","October","December");

        for (WebElement eachMonth:allMonths) {
            monthSelect.selectByVisibleText(eachMonth.getText());
            BrowserUtils.wait(2);
            List<WebElement> days= driver.findElements(By.cssSelector("[id='day']>option"));
            if(months30Days.contains(eachMonth.getText())){
                Assert.assertEquals(days.size(),30);
            }else if(months31Days.contains(eachMonth.getText())){
                Assert.assertEquals(days.size(),31);
            }else if(eachMonth.getText().equals("February") && isLeap==true ){
                 Assert.assertEquals(days.size(),29);
            }else{
                Assert.assertEquals(days.size(),28);
            }


        }


    }



     @BeforeTest
     public void setup(){

         driver= DriverFactory.createDriver("chrome");
         driver.get("http://practice.cybertekschool.com/dropdown");
         driver.manage().window().maximize();
         BrowserUtils.wait(2);


     }

     @AfterMethod
    public void teardown(){
         BrowserUtils.wait(2);
         driver.quit();
     }
}
