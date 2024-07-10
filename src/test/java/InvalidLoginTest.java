import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class InvalidLoginTest {
    WebDriver driver;

    @BeforeTest
    public void setUp(){
        //open page
        String url = "https://the-internet.herokuapp.com/login";
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void invalidUserTest(){
        //enter username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("incorrect");

        //enter password
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("SuperSecretPassword!");

        //click Login
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        //check error message
        WebElement errorAlert = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your username is invalid!";
        String actualErrorMessage = errorAlert.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    @Test
    public void invalidPasswordTest(){
        //enter username
        WebElement usernameInput = driver.findElement(By.id("username"));
        usernameInput.sendKeys("tomsmith");

        //enter password
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys("incorrect");

        //click Login
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

        //check error message
        WebElement errorAlert = driver.findElement(By.id("flash"));
        String expectedErrorMessage = "Your password is invalid!";
        String actualErrorMessage = errorAlert.getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }
}
