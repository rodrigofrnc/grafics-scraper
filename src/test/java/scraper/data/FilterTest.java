package scraper.data;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FilterTest {

    @Test
    public void testValidateCache_DataDoesNotExists_DateMustBeAddedInCacheEndpointIsReturned(){
        List<String> list = new LinkedList<>();
        list.add("http://google.com");

        Filter filter = new Filter(new HashMap());

        Assert.assertEquals(1, filter.verifyCache(list).size());
        Assert.assertTrue(filter.getCache().containsKey("http://google.com"));
    }

    @Test
    public void testValidateCache_DataAlreadyExistsAndWasAddedLessThanTwentyFourHours_DateMustMaintainInCacheEndpointCannotBeReturned(){
        List<String> list = new LinkedList<>();
        list.add("http://google.com");

        Map<String, LocalDateTime> map = new HashMap<>();
        map.put("http://google.com", LocalDateTime.now());
        Filter filter = new Filter(map);

        Assert.assertEquals(0, filter.verifyCache(list).size());
        Assert.assertEquals(filter.getCache(), map);
    }

    @Test
    public void testValidateCache_DataAlreadyExistsAndWasAddedMoreThanTwentyFourHours_DateMustRenewInCacheAndEndpointIsReturned(){
        List<String> list = new LinkedList<>();
        list.add("http://google.com");

        Map<String, LocalDateTime> cache = new HashMap<>();
        cache.put("http://google.com", LocalDateTime.now().minusHours(25));

        Filter filter = new Filter(cache);

        Assert.assertEquals(1, filter.verifyCache(list).size());
        Assert.assertTrue(LocalDateTime.now().until( filter.getCache().get("http://google.com"), ChronoUnit.HOURS) < 1);
    }
}
