package pack1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Lab8_JUnit {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {

        driver = new FirefoxDriver();

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.manage().window().maximize();

        driver.get("http://demo.opencart.com/");
    }

    @Test
    public void openCartTest() {

        // Lab 4 - Verify page title
        String title = driver.getTitle();

        System.out.println("Page Title: " + title);

        assertTrue(
                "Title should contain OpenCart",
                title.toLowerCase().contains("opencart")
        );

        // Click Desktops
        WebElement desktops = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Desktops")
                )
        );

        desktops.click();

        // Select Mac
        WebElement mac = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Mac")
                )
        );

        mac.click();

        // Verify Mac heading
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1")
                )
        );

        assertEquals(
                "Mac",
                heading.getText()
        );

        // Select sorting Name A-Z
        WebElement sortDropdown = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("input-sort")
                )
        );

        sortDropdown.click();

        WebElement nameAZ = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//option[contains(text(),'Name (A - Z)')]")
                )
        );

        nameAZ.click();

        // Add first product to cart
        WebElement addToCart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(@formaction,'cart.add')])[1]")
                )
        );

        addToCart.click();

        // Search Mobile
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("search")
                )
        );

        searchBox.clear();
        searchBox.sendKeys("Mobile");

        driver.findElement(
                By.cssSelector("button.btn.btn-light.btn-lg")
        ).click();

        // Verify search result page
        WebElement searchHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1")
                )
        );

        assertEquals(
                "Search",
                searchHeading.getText()
        );
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}}
