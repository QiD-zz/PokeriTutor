package pokeri;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public final class Pakka
{
    private long seed = System.nanoTime();
    private long otettu;
    private Map<String, Long> otetutMaat = new HashMap<String, Long>();
    private ArrayList<Kortti> pakka = new ArrayList<Kortti>(0);
    private static Pakka instance = null;

    private Pakka() // Singleton, vain yksi pakka on mahdollinen
    {
        pakka = null;
        otetutMaat = null;
        otetutMaat = new HashMap<String, Long>();
        pakka = new ArrayList<Kortti>(Extern.KORTTEJA_PAKASSA);
        otettu = 0;

        for (int i = 0; i < Extern.MAIDEN_LKM; i++)
            otetutMaat.put(Extern.MAAT[i], Long.valueOf(0));

        for (int i = 2; i <= Extern.KORTTEJA_PAKASSA / Extern.MAIDEN_LKM; i++) {
            for (int maa = 0; maa < Extern.MAIDEN_LKM; maa++) {
                Kortti k = new Kortti(Extern.MAAT[maa], i, new Point(0, 0));
                pakka.add(k);
            }
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
            k = pakka.remove(pakka.size() - 1);
            long nmbr = 0;

            if (k.getMaa().equals("ruutu")) {
                nmbr = otetutMaat.get("ruutu");
                nmbr++;
                otetutMaat.put("ruutu", nmbr);
            } else if (k.getMaa().equals("hertta")) {
                nmbr = otetutMaat.get("hertta");
                nmbr++;
                otetutMaat.put("hertta", nmbr);
            } else if (k.getMaa().equals("pata")) {
                nmbr = otetutMaat.get("pata");
                nmbr++;
                otetutMaat.put("pata", nmbr);
            } else if (k.getMaa().equals("risti")) {
                nmbr = otetutMaat.get("risti");
                nmbr++;
                otetutMaat.put("risti", nmbr);
            }
            pakka.trimToSize();
            otettu++;
        } catch (IndexOutOfBoundsException iobe) {
            k = new Kortti("", 0, new Point());
        }
        return k;
    }

    public long getMaaOtettuCount(String maa)
    {
        boolean maaOK = false;

        for (String m : Extern.MAAT) {
            if (m.equals(maa)) {
                maaOK = true;
                break;
            }
        }
        if (maaOK == false)
            return 0;
        return otetutMaat.get(maa);
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
        Collections.shuffle(pakka, new Random(seed));
        Collections.shuffle(pakka, new Random());
        Collections.shuffle(pakka, new Random(seed));
        Collections.shuffle(pakka, new Random());
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (Kortti k : pakka)
            sb.append(k.toString()).append("\n");

        return sb.toString();
    }

}
