package scraper.configuration;

import java.util.List;

public class Email {

    private String emailFrom;
    private String secret;
    private List<String> listOfEmailsTo;

    public String getEmailFrom() {
        return emailFrom;
    }

    public String getSecret() {
        return secret;
    }

    public List<String> getListOfEmailsTo() {
        return listOfEmailsTo;
    }

}
