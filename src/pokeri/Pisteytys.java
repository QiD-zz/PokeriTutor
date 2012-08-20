package pokeri;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Pisteytys
{
    private int pisteet;
    private int panos;
    // Vakiot
    public  final int ALKUPISTEET = 10;
    public  final int PANOSMAX    =  5;
    private final String PISTEET_TIEDOSTO = "pisteet.txt";
    private final static Map<String, Integer> VOITTOPISTE =
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

    public Pisteytys()
    {
        pisteet = ALKUPISTEET;
        panos = 1;
    }

    public int getPistePanosKerroin(String kasi)
    {
        return VOITTOPISTE.get(kasi) * panos;
    }

    public void laskePisteet(String kasi)
    {
        int tienatutPst = 0;

        if (raahauspeli.PokeriHanska.testaaNimi(kasi) == false)
            return;

        tienatutPst = VOITTOPISTE.get(kasi) * panos;
        pisteet += tienatutPst;
    }

    public void nollaaPisteet()
    {
        pisteet = ALKUPISTEET;
        panos = 1;
    }

    public int getPanos()
    {
        return panos;
    }

    public int getPisteet()
    {
        return pisteet;
    }

    public void vaihdaPanos()
    {
        if (panos + 1 <= pisteet && panos + 1 <= PANOSMAX)
            panos++;
        else {
            if (pisteet == 0)
                panos = 0;
            else
                panos = 1;
        }
    }

    /* Vähennä panoksen määrän verran pisteitä */
    public void vahennaPisteita()
    {
        if (panos > pisteet)
            panos = pisteet;
        pisteet -= panos;
    }

    public String getTiedostoPolkuJaNimi()
    {
        File tiednimi = new File(PISTEET_TIEDOSTO);

        if (tiednimi.exists())
            return String.format("%s", tiednimi.getAbsolutePath());
        return "";
    }

    public void tallennaTiedLoppuun(String teksti)
    {
        FileWriter      fwrter;
        BufferedWriter  bwrter;
        File tiednimi = new File(PISTEET_TIEDOSTO);

        try {
            if (!tiednimi.exists())
                tiednimi.createNewFile();

            fwrter = new FileWriter(tiednimi, true); // append
            bwrter = new BufferedWriter(fwrter);

            bwrter.write(teksti);
            bwrter.close(); // sulkee myös fwrterin
        } catch (IOException ex) {
            System.out.println(String.format("Tiedostoon %s kirjoitus epäonnistui: %s",
                    PISTEET_TIEDOSTO, ex.getMessage()));
        }
    }

    public void tallennaPisteet()
    {
        Date pvm = new Date();
        SimpleDateFormat pvmFmt;
        String tallennusMuoto = "";

        pvmFmt = new SimpleDateFormat("[dd.MM.yyyy] HH:mm");
        tallennusMuoto = String.format("%s\t%d\n", pvmFmt.format(pvm), pisteet);

        tallennaTiedLoppuun(tallennusMuoto);
    }

}
