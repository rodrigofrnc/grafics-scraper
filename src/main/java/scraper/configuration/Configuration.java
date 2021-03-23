package scraper.configuration;

import java.util.List;
import java.util.Map;

public class Configuration {

    private Email email;
    private Map<String,List<String>> listOfEndPoints;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public Map<String, List<String>> getListOfEndPoints() {
        return listOfEndPoints;
    }

    public void setListOfEndPoints(Map<String, List<String>> listOfEndPoints) {
        this.listOfEndPoints = listOfEndPoints;
    }
}
