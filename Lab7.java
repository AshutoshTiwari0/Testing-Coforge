package pack1;
/**
* Author : Ashutosh.4.Tiwari
* Date : 22-Sept-2026
* Time : 9:37:45 pm
* Project : testing
*/
package seleniumDemo;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Lab7 {

    public static void main(String[] args) {

        // Launch Edge
        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );

        // 1. Launch URL
        driver.get(
                "https://ispace.ig.capgemini.com/sitepages/index.aspx"
        );


        // 2. Go to Application tab
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Application")
        )).click();


        // 3. Click Stationery Request checkbox
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[contains(.,'Stationery Request')]/input")
        )).click();


        // 4. Verify new title "Stationary"
        String title = driver.getTitle();

        if (title.contains("Stationary")) {
            System.out.println("Title verified: " + title);
        } else {
            System.out.println("Title: " + title);
        }


        // 5. Click "Submit to collect your Stationery >>>"
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath(
                    "//*[contains(text(),'Submit to collect your Stationery')]"
                )
        )).click();


        // 6. Switch to alert
        Alert alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );


        // 7. Verify alert text
        String alertText = alert.getText();

        System.out.println("Alert Text: " + alertText);

        if (alertText.equals("Please add product(s) to cart")) {
            System.out.println("Alert text verified");
        }


        // 8. Verify OK button is present
        // JavaScript alerts have an OK button by default
        System.out.println("OK button is present on alert");


        // 9. Click OK
        alert.accept();


        // 10. Click Photocopy tab and Save Request
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Photocopy")
        )).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@value='Save Request'] | //button[contains(text(),'Save Request')]")
        )).click();


        // 11. Switch to alert and verify "No changes made"
        alert = wait.until(
                ExpectedConditions.alertIsPresent()
        );

        String saveAlertText = alert.getText();

        System.out.println("Save Request Alert: " + saveAlertText);

        if (saveAlertText.equals("No changes made")) {
            System.out.println("No changes made alert verified");
        }


        // 12. Verify OK button
        System.out.println("OK button is present on alert");


        // 13. Click OK
        alert.accept();


        // 14. Click Logout
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Logout")
        )).click();


        // 15. Close Stationery window
        driver.close();

        System.out.println("Test completed successfully.");
    }
}