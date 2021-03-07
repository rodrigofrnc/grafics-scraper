package scraper.client;

import java.util.List;

public class Processor {

    private Host host;

    public Processor(Host host) {
        this.host = host;
    }

    public void process(List<String> listEmailAddressTo, String body){
        for (String emailAddressTo : listEmailAddressTo) {
            host.send(emailAddressTo, body);
        }
    }
}
