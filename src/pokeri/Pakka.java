package pokeri;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;


public class Pakka
{
    private static Pakka instance = null;
    private ArrayList<Kortti> pakka = new ArrayList<Kortti>(0);

    private Pakka() // Singleton, vain yksi pakka on mahdollinen
    {
        pakka = new ArrayList<Kortti>(Extern.KORTTEJA_PAKASSA);
        for (int i = 0; i < Extern.KORTTEJA_PAKASSA; i++) {
            Kortti k = new Kortti(Extern.MAAT[i % 4], i + 1, new Point(0, 0));
            pakka.add(i, k);
        }
    }

    public static synchronized Pakka getPakka() // Käytä tätä konstruktorin sijaan
    {
        if (instance == null)
            instance = new Pakka();
        return instance;
    }

    public void uusiPakka() // Kun halutaan luoda uusi pakka ja tuhota vanha
    {
        if (pakka.size() > 0)
            pakka.clear();
        pakka = null;
        instance = new Pakka();
    }

    public void lisaaKortti(Kortti k)
    {
        if (pakka.isEmpty())
            return; // TODO näytä mahdollisesti ruudulla, että pakkaa ei ole alustettu
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
            return poistettava;
        }

        return poistettava;
    }

    public void sekoitaPakka()
    {
        Collections.shuffle(pakka);
    }

}
