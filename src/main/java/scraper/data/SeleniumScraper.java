package scraper.data;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class SeleniumScraper implements Scraper{

    public boolean scrape(String endpoint) throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        String hubRrl = "http://127.0.0.1:4444/wd/hub";
        WebDriver driver = new RemoteWebDriver(new URL(hubRrl), options);
        driver.get(endpoint);

        waitForElement(driver, By.xpath("//*[text()='Resultados: ']"));

        WebElement total = waitForElement(driver, By.xpath("//*[@data-role='total']"));
        String totalText = total.getText();

        driver.close();
        driver.quit();

        return Integer.parseInt(totalText) > 0;
    }

    public WebElement waitForElement(WebDriver driver, By element){
        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);

        return wait.until(driver1 -> driver1.findElement(element));
    }
}
