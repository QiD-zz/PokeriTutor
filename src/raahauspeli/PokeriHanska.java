package raahauspeli;

import pokeri.Kortti;

public class PokeriHanska
{
    public final static String VARISUORA  = "Värisuora";
    public final static String NELOSET    = "Neloset";
    public final static String TAYSKASI   = "Täyskäsi";
    public final static String VARI       = "Väri";
    public final static String SUORA      = "Suora";
    public final static String KOLMOSET   = "Kolmoset";
    public final static String KAKSIPARIA = "Kaksi paria";
    public final static String PARI       = "Pari";
    public final static String HAI        = "Hai";

    public  String kasi;
    public  Arvo   arvo;
    private Arvo[]   arvot;
    private Kortti[] kortit;

    public static enum Arvo { VARISUORA, NELOSET, TAYSKASI, VARI, SUORA,
                              KOLMOSET, KAKSIPARIA, PARI, HAI }
    public final static String kadet[] = { VARISUORA, NELOSET, TAYSKASI, VARI,
                                           SUORA, KOLMOSET, KAKSIPARIA, PARI,
                                           HAI };

    public PokeriHanska(Arvo arvo_)
    {
        arvo = arvo_;
        kasi = kadet[arvo.ordinal()];
    }

    public PokeriHanska(int luku)
    {
        arvot = Arvo.values();
        arvo = arvot[luku];
        kasi = kadet[luku];
    }

    public PokeriHanska(String nimi)
    {
        if (testaaNimi(nimi)) {
            kasi = nimi;
            arvot = Arvo.values();
            arvo = arvot[testaaNimenJarjNro(nimi)];
        }     
    }

    public String getHanskaName()
    {
        return kasi;
    }

    public PokeriHanska getNextHanska(PokeriHanska hanska)
    {
        if (hanska.arvo.equals(PokeriHanska.Arvo.VARISUORA)){
            return null;
        } else {
            return new PokeriHanska(hanska.arvo.ordinal()+1);
        }
    }

    @Override
    public String toString()
    {
        return kasi;
    }

    public void setKortti(Kortti kortti, int numero)
    {
        if ((numero >= 0 || numero < 5) && kortti != null){
            kortit[numero] = kortti;
        }
    }

    public Kortti[] getKortit()
    {
        return kortit;
    }

    public Kortti getKortti(int numero)
    {
        if ((numero >= 0 || numero < 5)){
            return kortit[numero];
        } else {
            return null;
        }
    }

    public static boolean testaaNimi(String nimi)
    {
        for (int i = 0; i < kadet.length; i++) {
            if (nimi.equals(kadet[i]))
                return true;
        }
        return false;
    }

    private int testaaNimenJarjNro(String nimi)
    {
        int nro = -1;
        
        for (int i = 0; i < kadet.length; i++) {
            if (nimi.equals(kadet[i])) {
                nro = i;
            }         
        }
        return nro;
    }

    @Override
    public boolean equals(Object o)
    {
        boolean totuusArvo = false;
        if (o instanceof PokeriHanska) {
            PokeriHanska toinen = (PokeriHanska)o;
            if (this.arvo.equals(toinen.arvo)) {
                totuusArvo = true;
            } 
        } 
        return totuusArvo;
    }
}
