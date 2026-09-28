import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Lab10_RemoteWebDriver {

    static WebDriver driver;
    static String browser;

    public static void main(String[] args) throws Exception {

        // Change this to chrome / firefox / ie
        browser = "chrome";

        runTest(browser);
    }

    public static void runTest(String browserName) throws Exception {

        browser = browserName;

        DesiredCapabilities capabilities = new DesiredCapabilities();

        if (browserName.equalsIgnoreCase("chrome")) {
            capabilities = DesiredCapabilities.chrome();
        }

        else if (browserName.equalsIgnoreCase("firefox")) {
            capabilities = DesiredCapabilities.firefox();
        }

        else if (browserName.equalsIgnoreCase("ie")) {
            capabilities = DesiredCapabilities.internetExplorer();
        }

        driver = new RemoteWebDriver(
                new URL("http://localhost:4444/wd/hub"),
                capabilities
        );

        driver.manage().window().maximize();

        try {

            // STEP 1
            driver.get("http://demo.opencart.com/");
            screenshot("01_Open_URL");

            // STEP 2
            String title = driver.getTitle();
            System.out.println("Page Title: " + title);
            screenshot("02_Verify_Title");

            // STEP 3
            driver.findElement(By.xpath("//a[contains(text(),'My Account')]"))
                  .click();
            screenshot("03_Click_My_Account");

            // STEP 4
            driver.findElement(By.xpath("//a[contains(text(),'Register')]"))
                  .click();
            screenshot("04_Click_Register");

            // STEP 5
            String heading = driver.findElement(
                    By.xpath("//h1[contains(text(),'Register Account')]")
            ).getText();

            System.out.println("Heading: " + heading);
            screenshot("05_Verify_Register_Heading");

            // STEP 6
            driver.findElement(By.id("input-firstname"))
                  .sendKeys("Test");

            driver.findElement(By.id("input-lastname"))
                  .sendKeys("User");

            driver.findElement(By.id("input-email"))
                  .sendKeys("test" + System.currentTimeMillis()
                          + "@gmail.com");

            driver.findElement(By.id("input-telephone"))
                  .sendKeys("9876543210");

            driver.findElement(By.id("input-password"))
                  .sendKeys("Test@123");

            driver.findElement(By.id("input-confirm"))
                  .sendKeys("Test@123");

            screenshot("06_Enter_User_Details");

            // STEP 7
            driver.findElement(By.name("agree"))
                  .click();

            screenshot("07_Agree_Privacy_Policy");

            // STEP 8
            driver.findElement(By.cssSelector("input[value='Continue']"))
                  .click();

            screenshot("08_Click_Continue");

            // STEP 9
            if (driver.getPageSource()
                    .contains("Your Account Has Been Created")) {

                System.out.println(
                        "Account creation successful"
                );
            }

            screenshot("09_Account_Created");

            // STEP 10
            driver.findElement(By.name("search"))
                  .clear();

            driver.findElement(By.name("search"))
                  .sendKeys("Mobile");

            screenshot("10_Search_Mobile");

            // STEP 11
            driver.findElement(By.cssSelector(
                    "button.btn.btn-default.btn-lg"
            )).click();

            screenshot("11_Click_Search");

            // STEP 12
            System.out.println(
                    "Search result page title: "
                    + driver.getTitle()
            );

            screenshot("12_Search_Result");

            // STEP 13
            driver.findElement(By.name("search"))
                  .clear();

            screenshot("13_Clear_Search");

            System.out.println("Test completed successfully.");

        } finally {

            Thread.sleep(2000);

            driver.quit();
        }
    }


    // Screenshot method
    public static void screenshot(String stepName)
            throws IOException {

        String time = new SimpleDateFormat(
                "yyyyMMdd_HHmmss"
        ).format(new Date());

        String folder =
                "Screenshots" + File.separator + browser;

        new File(folder).mkdirs();

        File source =
                ((TakesScreenshot) driver)
                        .getScreenshotAs(OutputType.FILE);

        File destination =
                new File(
                        folder
                        + File.separator
                        + stepName
                        + "_"
                        + time
                        + ".png"
                );

        FileUtils.copyFile(source, destination);

        System.out.println(
                "Screenshot saved: "
                + destination.getPath()
        );
    }
}
