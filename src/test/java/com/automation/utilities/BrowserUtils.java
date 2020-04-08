package com.automation.utilities;

import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    public static void wait(int seconds){

        try{
            Thread.sleep(1000*seconds);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }



    public static List<String> getTextFromWebElement(List<WebElement> elements){

        List<String> textValues=new ArrayList<>();

        for(WebElement eachElement:elements){

            textValues.add(eachElement.getText());

        }

        return textValues;
    }


}
