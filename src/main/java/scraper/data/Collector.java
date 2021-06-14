package scraper.data;

import scraper.data.scrapers.IScrape;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

public class Collector {

    private final IScrape scraper;
    private final Logger logger;

    public Collector(IScrape scraper) {
        this.scraper = scraper;
        this.logger = Logger.getLogger(getClass().getName());
    }

    public List<String> collect(List<String> listPath) {
        List<String> endpointWithGraphics = new LinkedList<>();
        for (String endpoint : listPath) {
            if (validateEndpoint(endpoint)) {
                endpointWithGraphics.add(endpoint);
            }
        }
        return endpointWithGraphics;
    }

    private boolean validateEndpoint(String endpoint) {
        try {
            logger.info("Scrape: " + scraper.getClass().getName());
            return scraper.scrape(endpoint);
        } catch (Exception e) {
            logger.warning(e.toString());
            return false;
        }
    }
}