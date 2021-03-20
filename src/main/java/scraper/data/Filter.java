package scraper.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

public class Filter {

    private Map<String, LocalDateTime> cache;

    public Filter(Map map) {
        this.cache = map;
    }

    public List<String> verifyCache(List<String> endpointWithGraphics) {
        LocalDateTime time;
        for (String endpoint: endpointWithGraphics) {
            if (cache.containsKey(endpoint)) {
                time = cache.get(endpoint);
                long hours = time.until(LocalDateTime.now() , ChronoUnit.HOURS);
                if (hours < 24){
                   endpointWithGraphics.remove(endpoint);
                } else {
                    cache.put(endpoint, LocalDateTime.now());
                }
            } else {
                cache.put(endpoint, LocalDateTime.now());
            }
        }
        return endpointWithGraphics;
    }

    protected Map<String, LocalDateTime> getCache() {
        return cache;
    }
}
