import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Lab11 {

    static WebDriver driver;


    // =================================================
    // POM LOCATORS
    // =================================================

    static class POMPage {

        WebDriver driver;

        By firstName = By.id("input-firstname");
        By lastName = By.id("input-lastname");
        By email = By.id("input-email");
        By telephone = By.id("input-telephone");
        By password = By.id("input-password");
        By confirmPassword = By.id("input-confirm");

        By agree = By.name("agree");

        By continueButton =
                By.cssSelector("input[value='Continue']");

        By heading =
                By.xpath("//h1[contains(text(),'Register Account')]");


        POMPage(WebDriver driver) {
            this.driver = driver;
        }


        void register() {

            driver.findElement(firstName)
                    .sendKeys("Test");

            driver.findElement(lastName)
                    .sendKeys("User");

            driver.findElement(email)
                    .sendKeys(
                        "test"
                        + System.currentTimeMillis()
                        + "@gmail.com"
                    );

            driver.findElement(telephone)
                    .sendKeys("9876543210");

            driver.findElement(password)
                    .sendKeys("Test@123");

            driver.findElement(confirmPassword)
                    .sendKeys("Test@123");

            driver.findElement(agree)
                    .click();

            driver.findElement(continueButton)
                    .click();
        }
    }


    // =================================================
    // PAGE FACTORY LOCATORS
    // =================================================

    static class PFPage {

        WebDriver driver;


        @FindBy(id = "input-firstname")
        WebElement firstName;


        @FindBy(id = "input-lastname")
        WebElement lastName;


        @FindBy(id = "input-email")
        WebElement email;


        @FindBy(id = "input-telephone")
        WebElement telephone;


        @FindBy(id = "input-password")
        WebElement password;


        @FindBy(id = "input-confirm")
        WebElement confirmPassword;


        @FindBy(name = "agree")
        WebElement agree;


        @FindBy(css = "input[value='Continue']")
        WebElement continueButton;


        PFPage(WebDriver driver) {

            this.driver = driver;

            PageFactory.initElements(
                    driver,
                    this
            );
        }


        void register() {

            firstName.sendKeys("Test");

            lastName.sendKeys("User");

            email.sendKeys(
                    "user"
                    + System.currentTimeMillis()
                    + "@gmail.com"
            );

            telephone.sendKeys("9876543210");

            password.sendKeys("Test@123");

            confirmPassword.sendKeys("Test@123");

            agree.click();

            continueButton.click();
        }
    }


    // =================================================
    // MAIN METHOD
    // =================================================

    public static void main(String[] args)
            throws InterruptedException {


        driver = new ChromeDriver();

        driver.manage().window().maximize();

        driver.manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));


        // Open OpenCart
        driver.get(
                "http://demo.opencart.com/"
        );


        // My Account
        driver.findElement(
                By.xpath(
                    "//a[contains(text(),'My Account')]"
                )
        ).click();


        // Register
        driver.findElement(
                By.xpath(
                    "//a[contains(text(),'Register')]"
                )
        ).click();


        // =============================================
        // PART 1: PAGE OBJECT MODEL
        // =============================================

        POMPage pom =
                new POMPage(driver);

        pom.register();

        System.out.println(
                "Page Object Model completed"
        );


        Thread.sleep(2000);


        // =============================================
        // PART 2: PAGE FACTORY
        // =============================================

        driver.get(
                "http://demo.opencart.com/"
        );


        driver.findElement(
                By.xpath(
                    "//a[contains(text(),'My Account')]"
                )
        ).click();


        driver.findElement(
                By.xpath(
                    "//a[contains(text(),'Register')]"
                )
        ).click();


        PFPage pageFactory =
                new PFPage(driver);

        pageFactory.register();

        System.out.println(
                "Page Factory completed"
        );


        Thread.sleep(3000);

        driver.quit();
    }
}
