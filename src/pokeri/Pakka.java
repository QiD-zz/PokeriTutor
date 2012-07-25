package pokeri;

import java.util.ArrayList;


public class Pakka
{
    private ArrayList<Kortti> pakka;

    public Pakka()
    {
        pakka = new ArrayList<Kortti>(Extern.KORTTEJA_PAKASSA);
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

        if (pakka.contains(k)) {
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
