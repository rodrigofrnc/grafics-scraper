package scraper.configuration;

import java.util.List;

public class Email {

    private String emailFrom;
    private String secret;
    private List<String> listOfEmailsTo;

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public List<String> getListOfEmailsTo() {
        return listOfEmailsTo;
    }

    public void setListOfEmailsTo(List<String> listOfEmailsTo) {
        this.listOfEmailsTo = listOfEmailsTo;
    }
}
