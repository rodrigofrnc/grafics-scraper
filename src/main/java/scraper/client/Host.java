package scraper.client;


public interface Host {
    public boolean send(String emailAddressTo, String body);
}
