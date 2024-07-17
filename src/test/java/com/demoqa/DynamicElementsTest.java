package com.demoqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;


public class DynamicElementsTest {

    WebDriver driver;
    @Parameters({"browserParam"})
    @BeforeTest(alwaysRun = true)
    public void setUp(@Optional("chrome") String browser){
        //open page
        String url = "https://demoqa.com/dynamic-properties";
        //driver = new ChromeDriver();

        switch (browser){
            case "chrome": driver = new ChromeDriver(); break;
            case "edge": driver = new EdgeDriver(); break;
            case "firefox": driver = new FirefoxDriver(); break;
            default: driver = new ChromeDriver();
        }


        driver.get(url);
        driver.manage().window().maximize();
    }
    @Test
    public void checkDynamicElementTest(){

        //waitNumberOfMilliseconds(6000);
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("visibleAfter")));

        WebElement visibleAfterButton = driver.findElement(By.id("visibleAfter"));
        Assert.assertTrue(visibleAfterButton.isDisplayed());

    }

    @AfterTest(alwaysRun = true)
    public void tearDown(){
        driver.close();
    }


    public static void waitNumberOfMilliseconds(int milliseconds){
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
