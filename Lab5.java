package pack1;
/**
* Author : Ashutosh.4.Tiwari
* Date : 22-Sept-2026
* Time : 2:20:54 pm
* Project : testing
*/

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lab5 {

    public static void main(String[] args) {

        // Launch Firefox
        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open URL
        driver.get("http://demo.opencart.com/");

        // Verify Title
        System.out.println("Title: " + driver.getTitle());

        // Go to Desktops
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Desktops"))).click();

        // Click Mac
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Mac"))).click();

        // Verify Mac heading
        WebElement heading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h2[normalize-space()='Mac']")
                )
        );

        if (heading.getText().equals("Mac")) {
            System.out.println("Mac heading verified");
        }

        // Select Name (A-Z)
        WebElement sortDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("input-sort")
                )
        );

        Select select = new Select(sortDropdown);
        select.selectByVisibleText("Name (A - Z)");

        // Click Add to Cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@onclick,'cart.add')]")
        )).click();

        // Enter Monitors in Search
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("search")
                )
        );

        searchBox.clear();
        searchBox.sendKeys("Monitors");

        // Click Search
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.btn.btn-light.btn-lg")
        )).click();

        // Wait for search page
        wait.until(ExpectedConditions.urlContains("search"));

        // Clear Search Criteria
        WebElement searchCriteria = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("input-search")
                )
        );

        searchCriteria.clear();

        // Click Search in product descriptions
        WebElement checkbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.name("description")
                )
        );

        if (!checkbox.isSelected()) {
            checkbox.click();
        }

        // Click Search again
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input.btn.btn-primary")
        )).click();

        System.out.println("Test Case Passed");

        // Close browser
        driver.quit();
    }
}
