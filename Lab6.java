package pack1;
/**
* Author : Ashutosh.4.Tiwari
* Date : 22-Sept-2026
* Time : 9:35:26 pm
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

public class Lab6 {

    public static void main(String[] args) {

        // Launch Edge
        WebDriver driver = new EdgeDriver();

        driver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open OpenCart
        driver.get("http://demo.opencart.com/");


        // ------------------------------------------------
        // 1. Login with credentials created in Lab 1
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("My Account"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Login"))).click();

        // Enter Email
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("input-email"))).sendKeys("YOUR_EMAIL");

        // Enter Password
        driver.findElement(By.id("input-password"))
                .sendKeys("YOUR_PASSWORD");

        // Click Login
        driver.findElement(
                By.cssSelector("button[type='submit']")
        ).click();


        // ------------------------------------------------
        // 2. Go to Components
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Components"))).click();


        // ------------------------------------------------
        // 3. Select Monitors
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Monitors"))).click();


        // ------------------------------------------------
        // 4. Select 25 from Show dropdown
        // ------------------------------------------------

        WebElement showDropdown = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("input-limit")
                )
        );

        Select show = new Select(showDropdown);

        show.selectByVisibleText("25");


        // ------------------------------------------------
        // 5. Click Add to Cart for first item
        // ------------------------------------------------

        WebElement firstAddToCart = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("(//button[contains(@onclick,'cart.add')])[1]")
                )
        );

        firstAddToCart.click();


        // ------------------------------------------------
        // 6. Click Specification tab
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Specification"))).click();


        // ------------------------------------------------
        // 7. Verify details present on page
        // ------------------------------------------------

        WebElement specification = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("tab-specification")
                )
        );

        if (specification.isDisplayed()) {
            System.out.println("Specification details are present");
        }


        // ------------------------------------------------
        // 8. Click Add to Wish List
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(@onclick,'wishlist.add')]")
        )).click();


        // ------------------------------------------------
        // 9. Verify wishlist success message
        // ------------------------------------------------

        WebElement wishlistMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert-success")
                )
        );

        System.out.println("Wishlist Message: "
                + wishlistMessage.getText());

        if (wishlistMessage.getText().contains(
                "You have added Apple Cinema 30")) {

            System.out.println("Wishlist message verified");
        }


        // ------------------------------------------------
        // 10. Enter Mobile in Search
        // ------------------------------------------------

        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.name("search")
                )
        );

        searchBox.clear();
        searchBox.sendKeys("Mobile");


        // ------------------------------------------------
        // 11. Click Search
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.btn.btn-light.btn-lg")
        )).click();


        // ------------------------------------------------
        // 12. Click Search in product descriptions
        // ------------------------------------------------

        WebElement descriptionCheckbox = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.name("description")
                )
        );

        if (!descriptionCheckbox.isSelected()) {
            descriptionCheckbox.click();
        }


        // ------------------------------------------------
        // 13. Click HTC Touch HD
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("HTC Touch HD")
        )).click();


        // ------------------------------------------------
        // 14. Change Qty from 1 to 3
        // ------------------------------------------------

        WebElement quantity = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("input-quantity")
                )
        );

        quantity.clear();
        quantity.sendKeys("3");


        // ------------------------------------------------
        // 15. Click Add to Cart
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.id("button-cart")
        )).click();


        // ------------------------------------------------
        // 16. Verify success message
        // ------------------------------------------------

        WebElement cartMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.cssSelector(".alert-success")
                )
        );

        System.out.println("Cart Message: "
                + cartMessage.getText());

        if (cartMessage.getText().contains(
                "You have added HTC Touch HD")) {

            System.out.println("Cart success message verified");
        }


        // ------------------------------------------------
        // 17. Click View Cart
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("View Cart")
        )).click();


        // ------------------------------------------------
        // 18. Verify Mobile name added to cart
        // ------------------------------------------------

        WebElement mobileName = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.linkText("HTC Touch HD")
                )
        );

        if (mobileName.isDisplayed()) {
            System.out.println("HTC Touch HD is present in cart");
        }


        // ------------------------------------------------
        // 19. Click Checkout
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Checkout")
        )).click();


        // ------------------------------------------------
        // 20. Click My Account
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("My Account")
        )).click();


        // ------------------------------------------------
        // 21. Select Logout
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Logout")
        )).click();


        // ------------------------------------------------
        // 22. Verify Account Logout heading
        // ------------------------------------------------

        WebElement logoutHeading = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h1[contains(text(),'Account Logout')]")
                )
        );

        if (logoutHeading.getText().contains("Account Logout")) {
            System.out.println("Account Logout heading verified");
        }


        // ------------------------------------------------
        // 23. Click Continue
        // ------------------------------------------------

        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Continue")
        )).click();


        System.out.println("=================================");
        System.out.println("Test Case Passed Successfully");
        System.out.println("=================================");


        // Close browser
        driver.quit();
    }
}