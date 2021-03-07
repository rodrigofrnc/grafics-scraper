package scraper.configuration;

import java.util.List;

public class Configuration {

    private Email email;
    private List<String> listOfEndPoints;

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public List<String> getListOfEndPoints() {
        return listOfEndPoints;
    }

    public void setListOfEndPoints(List<String> listOfEndPoints) {
        this.listOfEndPoints = listOfEndPoints;
    }
}
