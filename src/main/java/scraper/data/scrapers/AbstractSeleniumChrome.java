package scraper.data.scrapers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;

public abstract class AbstractSeleniumChrome implements Scraper {

    protected final String HUB_URL = "http://127.0.0.1:4444/wd/hub";

    protected WebElement waitForElement(WebDriver driver, By element){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> driver1.findElement(element));
    }

    protected List<WebElement> waitForElements(WebDriver driver, By element){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> driver1.findElements(element));
    }

    public void cleanup(WebDriver driver) {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }


}
