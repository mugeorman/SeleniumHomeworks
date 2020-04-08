package com.automation.Homework4;

import com.automation.utilities.BrowserUtils;
import com.automation.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class Cart_Prime_MoreSpoons_CheapSpoons {

   private WebDriver driver;


    @Test
    public void cart(){

        WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("wooden spoon");
        WebElement searchBtn=driver.findElement(By.xpath("//span[@id='nav-search-submit-text']/following-sibling::input"));
        searchBtn.click();


        List<WebElement> prices= driver.findElements(By.xpath("//span[@class='a-price']/span[@class='a-offscreen']"));


        int x=new Random().nextInt(prices.size());
        x=x==0?1:x; // ternary operator, this code means--> if x equals zero assign 1 if not continue with x

        String originName= driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])["+x+"]")).getText();
        String originPrice= "$"+
                driver.findElement(By.xpath("(//span[@class='a-price']/span[2]/span[2])["+x+"]")).getText()+"."+
                driver.findElement(By.xpath("(//span[@class='a-price']/span[2]/span[3])["+x+"]")).getText();


        driver.findElement(By.xpath("(//span[@class='a-price-fraction'])["+x+"]")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='a-autoid-0-announce']//span/following-sibling::span")).getText(),"1");

        Assert.assertEquals(driver.findElement(By.id("productTitle")).getText(),originName);
        Assert.assertEquals(driver.findElement(By.id("price_inside_buybox")).getText(),originPrice);

        Assert.assertTrue(driver.findElement(By.id("add-to-cart-button")).isDisplayed());




    }







    @Test
   public void prime(){



       WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
       searchBox.sendKeys("wooden spoon");
       WebElement searchBtn=driver.findElement(By.xpath("//span[@id='nav-search-submit-text']/following-sibling::input"));
       searchBtn.click();

       String firstPrimeOpt=driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText();
       System.out.println("firstPrimeOpt = " + firstPrimeOpt);

       WebElement primeBox=driver.findElement(By.xpath("(//i[@class='a-icon a-icon-checkbox'])[1]"));
       primeBox.click();

       String afterClickFirstOpt=driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText();
       System.out.println("afterClickFirstOpt = " + afterClickFirstOpt);


       Assert.assertEquals(firstPrimeOpt,afterClickFirstOpt);

       WebElement lastBrand =driver.findElement(By.xpath("//div[@id='brandsRefinements']//ul/li[last()]//i"));
       lastBrand.click();

        String afterClickBrand=driver.findElement(By.xpath("(//span[@class='a-size-base-plus a-color-base a-text-normal'])[1]")).getText();


        Assert.assertNotEquals(firstPrimeOpt,afterClickBrand);

   }


   @Test
   public void moreSpoons(){

       WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
       searchBox.sendKeys("wooden spoon", Keys.ENTER);


       List<WebElement>brandNames=driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
       List<String> brandNamesString=BrowserUtils.getTextFromWebElement(brandNames);


       WebElement primeCheckBox=driver.findElement(By.xpath("//li[starts-with (@id,'p_85')]//i[@class='a-icon a-icon-checkbox']"));
       primeCheckBox.click();

       BrowserUtils.wait(3);
       List<WebElement>brandsNameAfter=driver.findElements(By.xpath("//div[@id='brandsRefinements']//li"));
       List<String> brandsNameAfterSt=BrowserUtils.getTextFromWebElement(brandsNameAfter);

       BrowserUtils.wait(3);
       Assert.assertEquals(brandNamesString,brandsNameAfterSt);



   }



   @Test
   public void cheapSpoons(){

       WebElement searchBox=driver.findElement(By.id("twotabsearchtextbox"));
       searchBox.sendKeys("wooden spoon", Keys.ENTER);

       WebElement under25=driver.findElement(By.xpath("//span[text()='Under $25']"));
       under25.click();

       BrowserUtils.wait(3);

       List<WebElement>pricesUnder25=driver.findElements(By.xpath("//span[@class='a-price-whole']"));

       List<String> pricesString=BrowserUtils.getTextFromWebElement(pricesUnder25);



       for(String each:pricesString){

           Assert.assertTrue(Integer.parseInt(each)<25);

       }
   }






    @BeforeMethod
    public void setup(){

        driver= DriverFactory.createDriver("chrome");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
        driver.get("https://amazon.com");

    }

    @AfterMethod
    public void teardown(){

        BrowserUtils.wait(2);
        driver.quit();
    }
}
