package com.automation.Homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class ValidLinks {



    private WebDriver driver;

    @Test
    public void verifyLinksValid(){

        driver.get("https://www.selenium.dev/documentation/en/");

        List<WebElement> links=driver.findElements(By.tagName("a"));

        for(int i=0; i<links.size();i++){

          String href=links.get(i).getAttribute("href");

            try {
                URL url=new URL(href);
                HttpURLConnection httpURLConnection= (HttpURLConnection)url.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.connect();
                Assert.assertEquals(httpURLConnection.getResponseCode(), 200);

            } catch (Exception e) {
                e.printStackTrace();
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
