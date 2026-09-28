import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Lab12 {

    static WebDriver driver;
    static Properties prop = new Properties();


    // ==========================================================
    // CREATE CONFIG.PROPERTIES
    // ==========================================================

    public static void createPropertiesFile()
            throws IOException {

        Properties p = new Properties();

        // Browser / URL
        p.setProperty(
                "url",
                "http://demo.opencart.com/"
        );

        // Lab 4 Locators
        p.setProperty(
                "mac",
                "//a[contains(text(),'Mac')]"
        );

        p.setProperty(
                "sortDropdown",
                "input-sort"
        );

        p.setProperty(
                "searchBox",
                "input-search"
        );

        p.setProperty(
                "searchButton",
                "button-search"
        );

        p.setProperty(
                "productDescription",
                "description"
        );

        p.setProperty(
                "addToCart",
                "button-cart"
        );


        FileOutputStream output =
                new FileOutputStream("config.properties");

        p.store(
                output,
                "Selenium Object Repository"
        );

        output.close();

        System.out.println(
                "config.properties created successfully"
        );
    }


    // ==========================================================
    // READ PROPERTIES FILE
    // ==========================================================

    public static void loadProperties()
            throws IOException {

        FileInputStream input =
                new FileInputStream("config.properties");

        prop.load(input);

        input.close();

        System.out.println(
                "Properties file loaded successfully"
        );
    }


    // ==========================================================
    // MAIN METHOD
    // ==========================================================

    public static void main(String[] args)
            throws Exception {


        // Create properties file
        createPropertiesFile();


        // Read properties file
        loadProperties();


        // Start Chrome
        driver = new ChromeDriver();

        driver.manage()
              .window()
              .maximize();


        // ======================================================
        // STEP 1: OPEN URL
        // ======================================================

        driver.get(
                prop.getProperty("url")
        );

        System.out.println(
                "URL opened successfully"
        );


        // ======================================================
        // STEP 2: CLICK MAC
        // ======================================================

        driver.findElement(
                By.xpath(
                    prop.getProperty("mac")
                )
        ).click();

        System.out.println(
                "Mac category opened"
        );


        // ======================================================
        // STEP 3: SELECT SORT OPTION
        // ======================================================

        WebElement sort =
                driver.findElement(
                    By.id(
                        prop.getProperty(
                            "sortDropdown"
                        )
                    )
                );

        Select select =
                new Select(sort);

        select.selectByVisibleText(
                "Name (A - Z)"
        );

        System.out.println(
                "Products sorted by Name A-Z"
        );


        // ======================================================
        // STEP 4: ADD PRODUCT TO CART
        // ======================================================

        driver.findElement(
                By.cssSelector(
                    prop.getProperty(
                        "addToCart"
                    )
                )
        ).click();

        System.out.println(
                "Product added to cart"
        );


        // ======================================================
        // STEP 5: SEARCH MOBILE
        // ======================================================

        WebElement search =
                driver.findElement(
                    By.id(
                        prop.getProperty(
                            "searchBox"
                        )
                    )
                );

        search.clear();

        search.sendKeys("Mobile");


        // ======================================================
        // STEP 6: CLICK SEARCH
        // ======================================================

        driver.findElement(
                By.id(
                    prop.getProperty(
                        "searchButton"
                    )
                )
        ).click();

        System.out.println(
                "Mobile searched successfully"
        );


        Thread.sleep(2000);


        // ======================================================
        // STEP 7: CLEAR SEARCH
        // ======================================================

        search =
                driver.findElement(
                    By.id(
                        prop.getProperty(
                            "searchBox"
                        )
                    )
                );

        search.clear();

        System.out.println(
                "Search box cleared"
        );


        // ======================================================
        // STEP 8: SEARCH AGAIN
        // ======================================================

        search.sendKeys("Mobile");

        driver.findElement(
                By.id(
                    prop.getProperty(
                        "searchButton"
                    )
                )
        ).click();

        System.out.println(
                "Search completed again"
        );


        Thread.sleep(3000);


        // ======================================================
        // CLOSE BROWSER
        // ======================================================

        driver.quit();

        System.out.println(
                "Lab 12 completed successfully"
        );
    }
}
