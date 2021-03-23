package scraper.data.scrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class PcDiga extends AbstractSeleniumChrome {

    public boolean scrape(String endpoint) throws MalformedURLException {
        RemoteWebDriver driver = new RemoteWebDriver(new URL(HUB_URL), new ChromeOptions());

        driver.get(endpoint);

        waitForElement(driver, By.xpath("//*[text()='Resultados: ']"));

        WebElement total = waitForElement(driver, By.xpath("//*[@data-role='total']"));
        String totalText = total.getText();

        cleanup(driver);
        return Integer.parseInt(totalText) > 0;
    }
}
