package scraper.data;

import java.net.MalformedURLException;

public interface Scraper {
    boolean scrape(String endpoint) throws MalformedURLException;
}
