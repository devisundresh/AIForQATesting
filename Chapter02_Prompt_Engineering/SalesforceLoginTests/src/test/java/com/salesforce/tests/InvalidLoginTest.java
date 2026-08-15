package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import io.github.bonigarcia.webdriver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class InvalidLoginTest {

    WebDriver driver;
    WebDriverWait wait;
    LoginPage loginPage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://login.salesforce.com/?locale=in");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testInvalidLogin() {
        try {
            loginPage.doLogin("invalid@example.com", "wrongpassword");
            wait.until(driver -> loginPage.isErrorMessageDisplayed());
            Assert.assertTrue(loginPage.isErrorMessageDisplayed());
        } catch (Exception e) {
            Assert.fail("Invalid login test failed: " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
