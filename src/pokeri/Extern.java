package pokeri;

import java.awt.Color;
import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Extern
{
    // Visuaaliset hommat
    public final static int LEVEYS_IKKUNA  = 1000;
    public final static int KORKEUS_IKKUNA = 1000;
    public final static Point PERUSPISTE10X10Y = new Point(10, 10);

    // Variteemat
    public final static Color PASTELLITAUSTA = new Color(238, 232, 170);

    // Peliin liittyvät asiat
    public final static int KORTTEJA_MAASSA   = 13;
    public final static int KORTTEJA_PAKASSA  = 52;
    public final static int KORTTEJA_POYDALLA =  5;
    public final static int MAIDEN_LKM        =  4;
    public final static int SKENAARIOIDEN_LKM =  5;
    public final static int VAIHTOJEN_LKM     =  2;

    public final static String[] MAAT = { "ruutu", "hertta", "pata", "risti" };
    public final static Map<String, Integer> VOITTOPISTE =
        Collections.unmodifiableMap(new HashMap<String, Integer>() {{
        /*
         * (Arvokkain ensin)
         * Värisuora
         * Neljä samaa
         * Täyskäsi
         * Väri
         * Suora
         * Kolme samaa
         * Kaksi paria
         * Pari
         * Hai
         */
        put(raahauspeli.PokeriHanska.VARISUORA, 100);
        put(raahauspeli.PokeriHanska.NELOSET, 80);
        put(raahauspeli.PokeriHanska.TAYSKASI, 50);
        put(raahauspeli.PokeriHanska.VARI, 40);
        put(raahauspeli.PokeriHanska.SUORA, 40);
        put(raahauspeli.PokeriHanska.KOLMOSET, 20);
        put(raahauspeli.PokeriHanska.KAKSIPARIA, 15);
        put(raahauspeli.PokeriHanska.PARI, 5);
        put(raahauspeli.PokeriHanska.HAI, 0);
    }});

    // Komennot
    public final static String RAAHAUSPELI = "RaahausPeli";
    public final static String KORTTIPELI  = "KorttiPeli";
    public final static String OPETUS      = "Opetus";
    public final static String ETUSIVU     = "Etusivulle";
    public final static String MONIVALINTAPELI = "monivalintapeli";
    public final static String UUSIMONIVALINTATEHTAVA = "uusi tehtävä";
    public final static String MONIVALINTAVASTAUS = "vastasin, olinko oikeassa?";

}
