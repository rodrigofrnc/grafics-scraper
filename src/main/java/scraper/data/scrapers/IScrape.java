package scraper.data.scrapers;

import java.net.MalformedURLException;

public interface IScrape {
    boolean scrape(String endpoint) throws MalformedURLException;
}
