package scraper;

import scraper.client.Gmail;
import scraper.client.Processor;
import scraper.configuration.Configuration;
import scraper.configuration.InvalidYamlException;
import scraper.configuration.Parser;
import scraper.data.Collector;
import scraper.data.Filter;
import scraper.data.SeleniumScraper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException, InvalidYamlException, InterruptedException {
        Parser parser = new Parser();
        Configuration configuration = parser.configurationParser(new File("configuration.yaml"));
        List<String> listPath = configuration.getListOfEndPoints();
        SeleniumScraper scraper = new SeleniumScraper();
        Filter filter = new Filter(new HashMap());
        Collector collector = new Collector(scraper, filter);
        do {
            List<String> endpointWithGraphics = collector.collect(listPath);
            if (endpointWithGraphics.size() > 0) {
                Gmail gmail = new Gmail(configuration.getEmail().getEmailFrom(), configuration.getEmail().getSecret());
                Processor processor = new Processor(gmail);
                processor.process(configuration.getEmail().getListOfEmailsTo(), endpointWithGraphics.toString());
            }
            Thread.sleep(30000);
        } while (true);
    }
}
