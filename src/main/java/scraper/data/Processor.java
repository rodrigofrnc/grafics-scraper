package scraper.data;

import java.net.MalformedURLException;

public class Processor {

    public boolean getResult(String endpoint) throws MalformedURLException {
        Scraper scraper = new Scraper();
        return scraper.scrape(endpoint);
    }
}
