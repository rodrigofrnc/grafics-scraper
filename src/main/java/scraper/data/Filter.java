package scraper.data;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Filter {

    private final Map<String, LocalDateTime> cache;
    private static final Integer HOURS_ON_CACHE = 24;

    public Filter(Map<String, LocalDateTime> map) {
        this.cache = map;
    }

    public List<String> verifyCache(List<String> endpointWithGraphics) {
        List<String> toRemove = new LinkedList<>();
        for (String endpoint: endpointWithGraphics) {
            if (cache.containsKey(endpoint)) {
                LocalDateTime time = cache.get(endpoint);
                long hours = time.until(LocalDateTime.now() , ChronoUnit.HOURS);
                if (hours < HOURS_ON_CACHE){
                    toRemove.add(endpoint);
                } else {
                    cache.put(endpoint, LocalDateTime.now());
                }
            } else {
                cache.put(endpoint, LocalDateTime.now());
            }
        }
        endpointWithGraphics.removeAll(toRemove);
        return endpointWithGraphics;
    }

    protected Map<String, LocalDateTime> getCache() {
        return cache;
    }
}
