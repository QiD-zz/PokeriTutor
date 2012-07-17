package pokeri;

import java.util.Arrays;
import java.util.Vector;


public class Pakka
{
    private Vector<Kortti> pakka;

    public Pakka()
    {
        pakka = new Vector<Kortti>(Extern.KORTTEJA_PAKASSA);
    }

    public void lisaaKortti(Kortti k)
    {
        if (pakka == null)
            return; // TODO näytä mahdollisesti ruudulla, että pakkaa ei ole alustettu
        pakka.add(k);
    }
    
    public Kortti poistaKortti(Kortti k)
    {
        Kortti poistettava = null;

        if (pakka == null)
            return null; // TODO näytä mahdollisesti ruudulla, että pakkaa ei ole alustettu

        if (Arrays.asList(pakka).contains(k)) {
            poistettava = k;
            pakka.remove(k);
            return poistettava;
        }

        return poistettava;
    }

    public void sekoitaPakka()
    {
    }

}
