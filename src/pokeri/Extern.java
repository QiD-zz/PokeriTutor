package pokeri;

import java.awt.Color;
import java.awt.Point;


public final class Extern
{
    
    private Extern()
    {
    }

    // Visuaaliset hommat
    public static final int LEVEYS_IKKUNA  = 1000;
    public static final int KORKEUS_IKKUNA = 1000;
    public static final Point PERUSPISTE10X10Y = new Point(10, 10);

    // Variteemat
    public static final Color PASTELLITAUSTA = new Color(238, 232, 170);
    public static final Color PAINIKETAUSTA1 = new Color(218, 200, 120);
    public static final Color PAINIKETAUSTA2 = new Color(200, 212, 150);
    public static final Color RAAHAUSPELINTAUSTAVARI = new Color(111, 46, 170);
    public static final Color SKENAARIOPELINTAUSTAVARI = new Color(199, 116, 190);

    // Peliin liittyvät asiat
    public static final int KORTTEJA_MAASSA   = 13;
    public static final int KORTTEJA_PAKASSA  = 52;
    public static final int KORTTEJA_POYDALLA =  5;
    public static final int MAIDEN_LKM        =  4;
    public static final int SKENAARIOIDEN_LKM =  5;
    public static final int VAIHTOJEN_LKM     =  2;

    public static final String[] MAAT = { "ruutu", "hertta", "pata", "risti" };

    // Komennot
    public static final String RAAHAUSPELI = "RaahausPeli";
    public static final String KORTTIPELI  = "KorttiPeli";
    public static final String OPETUS      = "Opetus";
    public static final String ETUSIVU     = "Etusivulle";
    public static final String MONIVALINTAPELI = "Monivalintapeli";
    public static final String UUSIMONIVALINTATEHTAVA = "uusi tehtävä";
    public static final String MONIVALINTAVASTAUS = "vastasin, olinko oikeassa?";

}
