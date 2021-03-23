package scraper.data.scrapers;

import java.net.MalformedURLException;

public interface Scraper {
    boolean scrape(String endpoint) throws MalformedURLException;
}
