package com.automation.Homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainDepartments {


    private WebDriver driver;

    @Test
    public void main_departments(){

        driver.get("https://www.amazon.com/gp/site-directory");
        List<WebElement> mainDep= driver.findElements(By.tagName("h2"));
        List<WebElement> allDep =new Select(driver.findElement(By.id("searchDropdownBox"))).getOptions();

         Set<String> mainDepS=new HashSet<>();
         Set<String> allDepS=new HashSet<>();

         for(WebElement each:mainDep){

             mainDepS.add(each.getText());
         }

         for (WebElement each:allDep){

             allDepS.add(each.getText());

         }

         for(String each :mainDepS){

             if(!allDepS.contains(each)){
                 System.out.println(each);
                 System.out.println("This main dep is not in All departments list");
             }
         }

         Assert.assertTrue(allDepS.contains(mainDepS));

    }



    @BeforeMethod
    public void setup(){

        driver= DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);

    }

    @AfterMethod
    public void teardown(){

        BrowserUtils.wait(2);
        driver.quit();
    }

}
