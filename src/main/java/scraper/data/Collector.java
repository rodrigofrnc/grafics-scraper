package scraper.data;

import java.net.MalformedURLException;
import java.util.LinkedList;
import java.util.List;

public class Collector {

    private final SeleniumScraper scraper;
    private final Filter filter;

    public Collector(SeleniumScraper scraper, Filter filter) {
        this.scraper = scraper;
        this.filter = filter;
    }

    public List<String> collect(List<String> listPath) throws MalformedURLException {
        List<String> endpointWithGraphics = new LinkedList<>();
        for (String endpoint : listPath) {
            if (validateEndpoint(endpoint)) {
                endpointWithGraphics.add(endpoint);
            }
        }
        return filter.verifyCache(endpointWithGraphics);
    }

    private boolean validateEndpoint(String endpoint) throws MalformedURLException {
        try {
            return scraper.scrape(endpoint);
        } catch (Exception e) {
            return false;
        }
    }
}