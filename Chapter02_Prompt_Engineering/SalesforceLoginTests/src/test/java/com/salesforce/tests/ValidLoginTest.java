package com.salesforce.tests;

import com.salesforce.pages.LoginPage;
import io.github.bonigarcia.webdriver.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class ValidLoginTest {

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
    public void testValidLogin() {
        try {
            String username = System.getenv("SF_USERNAME");
            String password = System.getenv("SF_PASSWORD");
            Assert.assertNotNull(username, "SF_USERNAME environment variable not set");
            Assert.assertNotNull(password, "SF_PASSWORD environment variable not set");
            loginPage.doLogin(username, password);
            wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("login.salesforce.com")));
            Assert.assertFalse(driver.getCurrentUrl().contains("login.salesforce.com"));
        } catch (Exception e) {
            Assert.fail("Valid login test failed: " + e.getMessage());
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
