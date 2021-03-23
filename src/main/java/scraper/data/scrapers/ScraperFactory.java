package scraper.data.scrapers;

public class ScraperFactory {

    private static final String PC_DIGA = "PcDiga";
    private static final String PC_COMPONENTES = "PcComponentes";

    public Scraper make(String site) throws ScraperNotFoundException {
        switch (site) {
            case PC_DIGA:
                return new PcDiga();
            case PC_COMPONENTES:
                return new PcComponentes();
            default:
                throw new ScraperNotFoundException(site);
        }

    }


}
