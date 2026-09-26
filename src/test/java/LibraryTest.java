import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {

    @Test
    public void searchBookTest() {

        ChromeOptions options = new ChromeOptions();

        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        WebDriver driver = new ChromeDriver(options);

        try {

            driver.get("http://localhost:8081/");

            driver.findElement(By.id("bookName"))
                    .sendKeys("Java");

            driver.findElement(By.tagName("button"))
                    .click();

            String result =
                    driver.findElement(By.id("result"))
                            .getText();

            assertTrue(result.contains("Java"));

        } finally {

            driver.quit();
        }
    }
}