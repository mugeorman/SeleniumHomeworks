package com.automation.Homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class Links {




    private WebDriver driver;

    @Test
    public void ifDisplayedPrintTextAndHref(){

        driver.get("https://www.w3schools.com");

        List<WebElement> tagA=driver.findElements(By.tagName("a"));

        for (WebElement each:tagA){

            if(each.isDisplayed()){
                System.out.println(each.getText());
                System.out.println(each.getAttribute("href"));
            }
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
