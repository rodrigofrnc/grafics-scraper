package scraper;

import scraper.client.Gmail;
import scraper.client.Processor;
import scraper.configuration.Configuration;
import scraper.configuration.InvalidYamlException;
import scraper.configuration.Parser;
import scraper.data.Scraper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException, InvalidYamlException {
        Parser parser = new Parser();
        Configuration configuration = parser.configurationParser(new File("configuration.yaml"));
        List<String> listPath = configuration.getListOfEndPoints();
        Scraper scraper = new Scraper();

        List<String> endpointWithGraphics = new LinkedList<>();
        for (String endpoint :listPath) {
            if (scraper.scrape(endpoint)){
                endpointWithGraphics.add(endpoint);
            }
        }

        Gmail gmail = new Gmail(configuration.getEmail().getEmailFrom(), configuration.getEmail().getSecret());
        Processor processor = new Processor(gmail);
        processor.process(configuration.getEmail().getListOfEmailsTo(), endpointWithGraphics.toString());
    }
}
