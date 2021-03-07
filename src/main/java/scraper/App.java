package scraper;


import scraper.configuration.EmptyConfigurationException;
import scraper.configuration.FileReader;
import scraper.configuration.Importer;
import scraper.data.Processor;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class App
{
    public static void main( String[] args ) throws IOException, EmptyConfigurationException {
        Importer importer = new Importer(new FileReader(new File("configuration.txt")));
        List<String> listPath = importer.get();
        Processor processor = new Processor();
        for (String endpoint :listPath) {
            if (processor.getResult(endpoint)){
                System.out.println("Achou no " + endpoint);
            }
        }
    }
}
