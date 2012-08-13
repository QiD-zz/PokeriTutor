package raahauspeli;

import pokeri.Kortti;

/**
 *
 * @author hurvittelu
 */
public class PokeriHanska {
    
    public static String VARISUORA = "Värisuora";
    public static String NELOSET = "Neloset";
    public static String TAYSKASI = "Täyskäsi";
    public static String VARI = "Väri";
    public static String SUORA = "Suora";
    public static String KOLMOSET = "Kolmoset";
    public static String KAKSIPARIA = "Kaksi paria";
    public static String PARI = "Pari";
    public static String HAI = "Hai";
    
    public Arvo arvo;
    private Arvo[] arvot;
    public String kasi;
    private Kortti[] kortit;
    
    public static enum Arvo {
        VARISUORA, NELOSET, TAYSKASI, VARI, SUORA, KOLMOSET, KAKSIPARIA, PARI, HAI
    }
    
    public static String kadet[] = {"Värisuora","Neloset","Täyskäsi","Väri","Suora"
    ,"Kolmoset","Kaksi paria","Pari","Hai"};
    
    public PokeriHanska(Arvo arvo_) {
        arvo = arvo_;
        kasi = kadet[arvo.ordinal()];
    }
    
    public PokeriHanska(int luku) {
        arvot = Arvo.values();
        arvo = arvot[luku];
        kasi = kadet[luku];
    }
    
    public PokeriHanska(String nimi) {
        if (testaaNimi(nimi)) {
            kasi = nimi;
            System.out.println(nimi);
            arvot = Arvo.values();
            arvo = arvot[testaaNimenJarjNro(nimi)];
        }     
    }
    
    public String getHanskaName() {
        return kasi;
    }
    
    public PokeriHanska getNextHanska(PokeriHanska hanska) {
        if (hanska.arvo.equals(PokeriHanska.Arvo.VARISUORA)){
            return null;
        } else {
            return new PokeriHanska(hanska.arvo.ordinal()+1);
        }
    }
    
    @Override
    public String toString() {
        return kasi;
    }
    
    public void setKortti(Kortti kortti, int numero) {
        if ((numero >= 0 || numero < 5) && kortti != null){
            kortit[numero] = kortti;
        }
    }
    
    public Kortti[] getKortit() {
        return kortit;
    }
    
    public Kortti getKortti(int numero) {
        if ((numero >= 0 || numero < 5)){
            return kortit[numero];
        } else {
            return null;
        }
    }
    
    private boolean testaaNimi(String nimi) {
        boolean tosi = false;
        
        for (int i = 0; i < kadet.length; i++) {
            if (nimi.equals(kadet[i])) {
                tosi = true;
            }         
        }
        
        return tosi;
    }
    
    private int testaaNimenJarjNro(String nimi) {
        int nro = -1;
        
        for (int i = 0; i < kadet.length; i++) {
            System.out.println(kadet[i]);
            if (nimi.equals(kadet[i])) {
                nro = i;
            }         
        }
        System.out.println(nro);
        return nro;
    }
}
