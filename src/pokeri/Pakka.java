package pokeri;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


public class Pakka
{
    private long otettu;
    private ArrayList<Kortti> pakka = new ArrayList<Kortti>(0);
    private static Pakka instance = null;

    private Pakka() // Singleton, vain yksi pakka on mahdollinen
    {
        pakka = null;
        pakka = new ArrayList<Kortti>(Extern.KORTTEJA_PAKASSA);
        otettu = 0;

        for (int i = 0; i < Extern.KORTTEJA_PAKASSA; i++) {
            Kortti k = new Kortti(Extern.MAAT[i % 4], (i % 14) + 1, new Point(0, 0));
            pakka.add(i, k);
        }
    }

    public static synchronized Pakka getPakka() // Käytä tätä konstruktorin sijaan
    {
        if (instance == null)
            instance = new Pakka();
        return instance;
    }

    public static Pakka uusiPakka() // Kun halutaan luoda uusi pakka ja tuhota vanha
    {
        instance = null;
        instance = new Pakka();

        return instance;
    }

    public long jaljella()
    {
        return pakka.size();
    }

    public long nostettu()
    {
        return otettu;
    }

    /**
     * Palauta pakan ensimmäinen kortti
     * @return kortti, tai jos korttia ei löydy, tyhjä kortti
     */
    public Kortti otaKortti()
    {
        Kortti k;

        try {
            k = pakka.remove(0);
            otettu++;
        } catch (IndexOutOfBoundsException iobe) {
            k = new Kortti("", 0, new Point());
        }
        return k;
    }

    public void lisaaKortti(Kortti k)
    {
        pakka.add(k);
    }
    
    public Kortti poistaKortti(Kortti k)
    {
        Kortti poistettava = new Kortti("", 0, new Point());

        if (pakka.isEmpty())
            return poistettava; // XXX Jos kortti.arvo == 0 -> pakka == null

        if (pakka.contains(k)) {
            poistettava = k;
            pakka.remove(k);
            otettu++;
        }

        return poistettava;
    }

    public void sekoita()
    {
        Collections.shuffle(pakka);
    }

}
