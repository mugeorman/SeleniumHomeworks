package com.automation.Homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Days {


    private WebDriver driver;


    @Test
    public void checkUncheckFriday() {

        Random r = new Random();

        List<WebElement> checkBoxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
        List<WebElement> texts = driver.findElements(By.xpath("//input[@type='checkbox']/following-sibling::label"));
        List<String> textsString = BrowserUtils.getTextFromWebElement(texts);

        int count = 0;


        while (count<3) {


            int randomNum = r.nextInt(checkBoxes.size());
            if(checkBoxes.get(randomNum).isEnabled()){
                checkBoxes.get(randomNum).click();
                //BrowserUtils.wait(2);
                System.out.println(textsString.get(randomNum));
                checkBoxes.get(randomNum).click();
                if (textsString.get(randomNum).equals("Friday")) {
                    count++;
                }
            }


        }

        Assert.assertEquals(count,3);


    }

    //WebElement fridayCheckBox=driver.findElement(By.xpath("//input[@id='gwt-debug-cwCheckBox-Friday-input']"));


    @BeforeTest
    public void setup() {
        driver = DriverFactory.createDriver("chrome");
        driver.get("http://samples.gwtproject.org/samples/Showcase/Showcase.html#!CwCheckBox");
        driver.manage().window().maximize();

        BrowserUtils.wait(3);


    }

    @AfterMethod
    public void teardown() {

        driver.quit();


    }

}
