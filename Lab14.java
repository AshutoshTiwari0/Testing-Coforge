import java.io.FileInputStream;
import java.time.Duration;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class Lab14 {

    public static void main(String[] args) throws Exception {

        // =====================================================
        // EXCEL FILE LOCATION
        // =====================================================

        String excelPath = "UserDetails.xlsx";


        // =====================================================
        // OPEN EXCEL FILE
        // =====================================================

        FileInputStream file =
                new FileInputStream(excelPath);

        Workbook workbook =
                new XSSFWorkbook(file);

        Sheet sheet =
                workbook.getSheetAt(0);


        // DataFormatter helps read Excel values as String
        DataFormatter formatter =
                new DataFormatter();


        // =====================================================
        // START SELENIUM
        // =====================================================

        WebDriver driver =
                new ChromeDriver();

        driver.manage()
                .window()
                .maximize();

        driver.manage()
                .timeouts()
                .implicitlyWait(
                        Duration.ofSeconds(10)
                );


        // =====================================================
        // READ EACH ROW FROM EXCEL
        // =====================================================

        // Row 0 = Header
        // Therefore start from row 1

        for (int i = 1;
             i <= sheet.getLastRowNum();
             i++) {


            Row row = sheet.getRow(i);


            // =================================================
            // READ DATA FROM EXCEL
            // =================================================

            String firstName =
                    formatter.formatCellValue(
                            row.getCell(0)
                    );

            String lastName =
                    formatter.formatCellValue(
                            row.getCell(1)
                    );

            String email =
                    formatter.formatCellValue(
                            row.getCell(2)
                    );

            String telephone =
                    formatter.formatCellValue(
                            row.getCell(3)
                    );

            String password =
                    formatter.formatCellValue(
                            row.getCell(4)
                    );

            String confirmPassword =
                    formatter.formatCellValue(
                            row.getCell(5)
                    );


            // =================================================
            // PRINT EXCEL DATA
            // =================================================

            System.out.println(
                    "--------------------------------"
            );

            System.out.println(
                    "Test Data Row: " + i
            );

            System.out.println(
                    "First Name: " + firstName
            );

            System.out.println(
                    "Last Name: " + lastName
            );

            System.out.println(
                    "Email: " + email
            );

            System.out.println(
                    "Telephone: " + telephone
            );


            // =================================================
            // STEP 1: OPEN URL
            // =================================================

            driver.get(
                    "http://demo.opencart.com/"
            );


            // =================================================
            // STEP 2: CLICK MY ACCOUNT
            // =================================================

            driver.findElement(
                    By.xpath(
                        "//a[contains(text(),'My Account')]"
                    )
            ).click();


            // =================================================
            // STEP 3: CLICK REGISTER
            // =================================================

            driver.findElement(
                    By.xpath(
                        "//a[contains(text(),'Register')]"
                    )
            ).click();


            // =================================================
            // STEP 4: VERIFY REGISTER ACCOUNT
            // =================================================

            String heading =
                    driver.findElement(
                        By.xpath(
                            "//h1[contains(text(),'Register Account')]"
                        )
                    ).getText();


            if (heading.contains("Register Account")) {

                System.out.println(
                        "Register Account page verified"
                );

            } else {

                System.out.println(
                        "Register Account page not found"
                );
            }


            // =================================================
            // STEP 5: ENTER FIRST NAME
            // =================================================

            driver.findElement(
                    By.id("input-firstname")
            ).sendKeys(firstName);


            // =================================================
            // STEP 6: ENTER LAST NAME
            // =================================================

            driver.findElement(
                    By.id("input-lastname")
            ).sendKeys(lastName);


            // =================================================
            // STEP 7: ENTER EMAIL
            // =================================================

            driver.findElement(
                    By.id("input-email")
            ).sendKeys(email);


            // =================================================
            // STEP 8: ENTER TELEPHONE
            // =================================================

            driver.findElement(
                    By.id("input-telephone")
            ).sendKeys(telephone);


            // =================================================
            // STEP 9: ENTER PASSWORD
            // =================================================

            driver.findElement(
                    By.id("input-password")
            ).sendKeys(password);


            // =================================================
            // STEP 10: ENTER CONFIRM PASSWORD
            // =================================================

            driver.findElement(
                    By.id("input-confirm")
            ).sendKeys(confirmPassword);


            // =================================================
            // STEP 11: AGREE PRIVACY POLICY
            // =================================================

            driver.findElement(
                    By.name("agree")
            ).click();


            // =================================================
            // STEP 12: CLICK CONTINUE
            // =================================================

            driver.findElement(
                    By.cssSelector(
                        "input[value='Continue']"
                    )
            ).click();


            // =================================================
            // STEP 13: VERIFY ACCOUNT CREATED
            // =================================================

            Thread.sleep(2000);

            if (driver.getPageSource()
                    .contains(
                        "Your Account Has Been Created"
                    )) {

                System.out.println(
                        "Account created successfully"
                );

            } else {

                System.out.println(
                        "Account creation verification failed"
                );
            }


            // =================================================
            // WAIT BEFORE NEXT EXCEL ROW
            // =================================================

            Thread.sleep(2000);
        }


        // =====================================================
        // CLOSE EXCEL
        // =====================================================

        workbook.close();

        file.close();


        // =====================================================
        // CLOSE BROWSER
        // =====================================================

        driver.quit();


        System.out.println(
                "================================"
        );

        System.out.println(
                "LAB 14 COMPLETED SUCCESSFULLY"
        );

        System.out.println(
                "================================"
        );
    }
}
