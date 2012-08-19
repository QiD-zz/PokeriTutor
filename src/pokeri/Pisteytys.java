package pokeri;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pisteytys
{
    private int pisteet;
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
        put(raahauspeli.PokeriHanska.NELOSET, 90);
        put(raahauspeli.PokeriHanska.TAYSKASI, 20);
        put(raahauspeli.PokeriHanska.VARI, 15);
        put(raahauspeli.PokeriHanska.SUORA, 15);
        put(raahauspeli.PokeriHanska.KOLMOSET, 10);
        put(raahauspeli.PokeriHanska.KAKSIPARIA, 5);
        put(raahauspeli.PokeriHanska.PARI, 1);
        put(raahauspeli.PokeriHanska.HAI, 0);
    }});

    // Vakiot
    public final int ALKUPISTEET = 20;

    public Pisteytys()
    {
        pisteet = 0;
    }

    public void laskePisteet(String kasi)
    {
        int tienatutPst = 0;

        if (raahauspeli.PokeriHanska.testaaNimi(kasi) == false)
            return;

        tienatutPst = VOITTOPISTE.get(kasi);

        pisteet += tienatutPst;
    }

    public void nollaaPisteet()
    {
        pisteet = 0;
    }

    public int getPisteet()
    {
        return pisteet;
    }

}
