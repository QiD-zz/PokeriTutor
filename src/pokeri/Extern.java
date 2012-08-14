package pokeri;

import java.awt.Point;


public class Extern
{
    public final static int KORTTEJA_PAKASSA = 52;
    public final static int KORTTEJA_POYDALLA = 5;
    public final static int MAIDEN_LKM = 4;
    public final static int KORTTEJA_MAASSA = 13;
    public final static int LEVEYS_IKKUNA = 1000;
    public final static int KORKEUS_IKKUNA = 1000;
    public final static int VAIHTOJEN_LKM = 2;
    public final static String[] MAAT = { "ruutu", "hertta", "pata", "risti" };

    public final static int SKENAARIOIDEN_LKM = 4;
    //komennot
    public final static String RAAHAUSPELI = "RaahausPeli";
    public final static String KORTTIPELI = "KorttiPeli";
    public final static String OPETUS = "Opetus";
    public final static String ETUSIVU = "Etusivulle";
    public static final String MONIVALINTAPELI = "monivalintapeli";
    
    public static final String UUSIMONIVALINTATEHTAVA = "uusi tehtävä";
    public static final String MONIVALINTAVASTAUS = "vastasin, olinko oikeassa?";
    
    public static final Point PERUSPISTE10X10Y = new Point(10, 10);
}
