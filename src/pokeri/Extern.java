package pokeri;

import java.awt.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Extern
{
    public final static int KORTTEJA_PAKASSA = 52;
    public final static int KORTTEJA_POYDALLA = 5;
    public final static int MAIDEN_LKM = 4;
    public final static int KORTTEJA_MAASSA = 13;
    public final static int LEVEYS_IKKUNA = 1000;
    public final static int KORKEUS_IKKUNA = 1000;
    public final static int VAIHTOJEN_LKM = 2;
    public final static int SKENAARIOIDEN_LKM = 5;
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
        put("varisuora", 100);
        put("neloset", 80);
        put("tayskasi", 50);
        put("vari", 40);
        put("suora", 40);
        put("kolmoset", 20);
        put("kaksiparia", 15);
        put("pari", 10);
    }});

    //komennot
    public final static String RAAHAUSPELI = "RaahausPeli";
    public final static String KORTTIPELI = "KorttiPeli";
    public final static String OPETUS = "Opetus";
    public final static String ETUSIVU = "Etusivulle";
    public final static String MONIVALINTAPELI = "monivalintapeli";
    public final static String UUSIMONIVALINTATEHTAVA = "uusi tehtävä";
    public final static String MONIVALINTAVASTAUS = "vastasin, olinko oikeassa?";

    public final static Point PERUSPISTE10X10Y = new Point(10, 10);
}
