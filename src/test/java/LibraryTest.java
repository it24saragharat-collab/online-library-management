import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class LibraryTest {

    @Test
    public void searchBookTest() {

        WebDriver driver = new ChromeDriver();

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