package scraper.data.scrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class Informestre extends AbstractSeleniumChrome{
    @Override
    public boolean scrape(String endpoint) throws MalformedURLException {
        RemoteWebDriver driver = new RemoteWebDriver(new URL(HUB_URL), new ChromeOptions());
        driver.get(endpoint);

        boolean exists = waitForElements(driver, By.xpath("//*[@uk-tooltip='title: Disponível']")).size() > 0;

        cleanup(driver);

        return exists;
    }
}
