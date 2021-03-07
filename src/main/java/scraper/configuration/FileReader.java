package scraper.configuration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader implements FileReaderInterface {

    private File pathToFile;

    public FileReader(File pathToFile) {
        this.pathToFile = pathToFile;
    }

    @Override
    public String read() throws IOException{
        return new String(Files.readAllBytes(Paths.get(pathToFile.getAbsolutePath())));
    }
}
