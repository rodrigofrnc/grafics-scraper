package scraper.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Importer {
    private FileReaderInterface fileReader;

    public Importer(FileReaderInterface fileReader) {
        this.fileReader = fileReader;
    }

    public List<String> get() throws IOException, EmptyConfigurationException {
        String fileContent = fileReader.read();
        if (fileContent.isEmpty()){
            throw new EmptyConfigurationException();
        }
        return new ArrayList<String>(Arrays.asList(fileContent.split(",")));
    }
}
