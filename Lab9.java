package pack1;
/**
* Author : Ashutosh.4.Tiwari
* Date : 25-Sept-2026
* Time : 11:14:13 am
* Project : testing
*/
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Duration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lab9 {

    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void setUp() {

        String browser = System.getProperty(
                "browser",
                "chrome"
        );

        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("ie")) {

            driver = new InternetExplorerDriver();

        } else {

            throw new IllegalArgumentException(
                    "Browser not supported: " + browser
            );
        }

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        driver.manage().window().maximize();

        driver.get("http://demo.opencart.com/");
    }

    @Test
    public void openCartCrossBrowserTest() {

        // Verify title
        String title = driver.getTitle();

        System.out.println(
                "Browser: " +
                System.getProperty("browser", "chrome")
        );

        System.out.println(
                "Title: " + title
        );

        assertTrue(
                "OpenCart title verification failed",
                title.toLowerCase().contains("opencart")
        );

        // Click Desktops
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Desktops")
                )
        ).click();

        // Click Mac
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.linkText("Mac")
                )
        ).click();

        // Verify Mac heading
        String heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1")
                )
        ).getText();

        assertEquals(
                "Mac",
                heading
        );

        // Sort Name A-Z
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.id("input-sort")
                )
        ).click();

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                            "//option[contains(text(),'Name (A - Z)')]"
                        )
                )
        ).click();

        // Add first product to cart
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                            "(//button[contains(@formaction,'cart.add')])[1]"
                        )
                )
        ).click();

        // Search Mobile
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("search")
                )
        ).sendKeys("Mobile");

        driver.findElement(
                By.cssSelector(
                    "button.btn.btn-light.btn-lg"
                )
        ).click();

        // Verify Search page
        String searchHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector("h1")
                )
        ).getText();

        assertEquals(
                "Search",
                searchHeading
        );
    }

    @After
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
}
