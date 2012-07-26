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
    }

    public static Pakka getPakka() // Käytä tätä konstruktorin sijaan
    {
        if (instance == null)
            instance = new Pakka();
        return instance;
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
