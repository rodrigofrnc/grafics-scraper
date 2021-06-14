package scraper.data.scrapers;

public class ScraperFactory {

    private static final String PC_DIGA = "PcDiga";
    private static final String PC_COMPONENTES = "PcComponentes";
    private static final String INFOMESTRE = "Informestre";
    private static final String APP_INFORMATICA = "AppInformatica";

    public IScrape make(String site) throws ScraperNotFoundException {
        switch (site) {
            case PC_DIGA:
                return new PcDiga();
            case PC_COMPONENTES:
                return new PcComponentes();
            case INFOMESTRE:
                return new Informestre();
            case APP_INFORMATICA:
                return new AppInformatica();
            default:
                throw new ScraperNotFoundException(site);
        }
    }


}
