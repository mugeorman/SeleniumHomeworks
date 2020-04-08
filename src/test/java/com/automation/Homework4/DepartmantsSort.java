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

import java.util.Collections;
import java.util.List;

public class DepartmantsSort {


    private WebDriver driver;

    @Test
    public void verifyDefaultValIsAll(){

        driver.get("https://www.amazon.com");

        WebElement all=driver.findElement(By.xpath("//span[@class='nav-search-label']"));


        Assert.assertEquals(all.getText(),"All");




        List<WebElement> allDepartmentsOptions =driver.findElements(By.xpath("//select/option"));

        List<String> optionsText=BrowserUtils.getTextFromWebElement(allDepartmentsOptions);



        for (int x=0; x<optionsText.size();x++){
            String value= optionsText.get(x);
            String nextValue=optionsText.get(x+1);

            System.out.println(value.compareTo(nextValue));

            Assert.assertTrue(value.compareTo(nextValue)>0,"not alphabetically sorted");
        }

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
