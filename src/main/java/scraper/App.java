package scraper;

import scraper.client.Gmail;
import scraper.client.Processor;
import scraper.configuration.Configuration;
import scraper.configuration.InvalidYamlException;
import scraper.configuration.Parser;
import scraper.data.Collector;
import scraper.data.Filter;
import scraper.data.scrapers.Scraper;
import scraper.data.scrapers.ScraperFactory;
import scraper.data.scrapers.ScraperNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class App {

    public static void main( String[] args ) throws IOException, InvalidYamlException, ScraperNotFoundException {
        List<String> endpointWithGraphics = new ArrayList<>();
        Parser parser = new Parser();
        Configuration configuration = parser.configurationParser(new File("configuration.yaml"));
        ScraperFactory scraperFactory = new ScraperFactory();
        Filter filter = new Filter(new HashMap());

        Gmail gmail = new Gmail(configuration.getEmail().getEmailFrom(), configuration.getEmail().getSecret());
        Processor processor = new Processor(gmail);
        do {
            for (Map.Entry<String,List<String>> entry : configuration.getListOfEndPoints().entrySet()) {
                Scraper scraper = scraperFactory.make(entry.getKey());
                Collector collector = new Collector(scraper);
                endpointWithGraphics.addAll(filter.verifyCache(collector.collect(entry.getValue())));
                if (endpointWithGraphics.size() > 0) {
                    processor.process(
                            configuration.getEmail().getListOfEmailsTo(),
                            entry.getKey(),
                            endpointWithGraphics);
                }
                endpointWithGraphics.clear();
            }
            System.gc();
        } while (true);
    }
}
