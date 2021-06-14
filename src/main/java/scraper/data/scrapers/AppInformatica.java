package scraper.data.scrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class AppInformatica extends AbstractSeleniumChrome{
    @Override
    public boolean scrape(String endpoint) throws MalformedURLException {
        RemoteWebDriver driver = new RemoteWebDriver(new URL(HUB_URL), new ChromeOptions());
        driver.get(getBaseUrl(endpoint));

        try {
            waitForElement(driver, By.id("buscappdor")).sendKeys(getSearch(endpoint));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        waitForElement(driver, By.id("botonbusca")).click();

        int available = waitForElements(driver,By.xpath("//div[contains(@class,'fichaescaparateart')]")).size();

        cleanup(driver);

        return available > 0;
    }

    protected String getBaseUrl(String endpoint){
        return endpoint.substring(0,endpoint.indexOf("$"));
    }

    protected String getSearch(String endpoint) throws NoSuchFieldException {
        if (!endpoint.contains("$Search:")){
            throw new NoSuchFieldException("$Search:");
        }
        return endpoint.substring(endpoint.indexOf("$")).replace("$Search:","");
    }
}
