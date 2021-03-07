package scraper.configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.yaml.snakeyaml.Yaml;

public class Parser {

    public Configuration configurationParser(File configurationFile)
            throws IOException, InvalidYamlException {
        assertYaml(configurationFile);
        String content = getContentFromFile(configurationFile);
        String json = convertToJson(content);
        Gson gson = new Gson();

        return gson.fromJson(json, Configuration.class);
    }

    private void assertYaml(File file) throws InvalidYamlException, FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException(file.getPath());
        }
        try {
            String content = getContentFromFile(file);
            Yaml yaml = new Yaml();
            yaml.load(content);
        } catch (Exception e) {
            throw new InvalidYamlException(file.getAbsolutePath());
        }
    }

    private String getContentFromFile(java.io.File file) throws IOException {
        return new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);
    }


    private String convertToJson(String yaml) throws JsonProcessingException {
        ObjectMapper yamlReader = new ObjectMapper(new YAMLFactory());
        Object obj = yamlReader.readValue(yaml, Object.class);

        ObjectMapper jsonWriter = new ObjectMapper();
        return jsonWriter.writeValueAsString(obj);
    }

}
