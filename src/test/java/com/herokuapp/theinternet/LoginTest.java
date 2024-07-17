package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {

    @Test
    public void loginTest(){
        //open page
        String url = "https://the-internet.herokuapp.com/login";
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

        //enter username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("tomsmith");

        //enter password
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("SuperSecretPassword!");

        //click Login
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        //check landing url
        String expectedUrl = "https://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);

        //check logout is present
        WebElement logoutButton = driver.findElement(By.linkText("Logout"));
        Assert.assertTrue(logoutButton.isDisplayed());

        //check success login message
        WebElement successAlert = driver.findElement(By.className("flash"));
        String expectedMessage = "You logged into a secure area!";
        String actualMessage = successAlert.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage));

        //close page
        driver.close();


    }



}
