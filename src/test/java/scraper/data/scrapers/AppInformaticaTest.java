package scraper.data.scrapers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppInformaticaTest {

    private AppInformatica appInformatica;
    private static final String SITE_URL = "https://www.appinformatica.com/componentes/tarjetas-graficas/$Search:3080";

    @Before
    public void setUp(){
        appInformatica = new AppInformatica();
    }

    @Test
    public void testGetBaseUrl() {
        Assert.assertEquals(
                "https://www.appinformatica.com/componentes/tarjetas-graficas/",
                appInformatica.getBaseUrl(SITE_URL));
    }

    @Test
    public void testGetSearch() throws NoSuchFieldException {
        Assert.assertEquals(
                "3080",
                appInformatica.getSearch(SITE_URL));
    }

}
