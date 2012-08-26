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
    public static final Color PASTELLITAUSTA = new Color(250, 220, 70);
    public static final Color OHJETAUSTA     = new Color(210, 210, 210);
    public static final Color PAINIKETAUSTA1 = new Color(218, 200, 120);
    public static final Color PAINIKETAUSTA2 = new Color(200, 212, 150);
    public static final Color LOPETATAUSTA   = new Color(200, 50, 50);
    public static final Color POKERIKADETTAUSTA = new Color(107, 142, 35);
    public static final Color KADETJARJTAUSTA   = new Color(154, 205, 50);
    public static final Color POKERIPELITAUSTA  = new Color(240, 230, 140);
    public static final Color TAIDOTTAUSTA      = new Color(189, 183, 107);

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
    public static final String LOPETA = "Lopeta";

}
