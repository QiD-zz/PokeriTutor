/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package raahauspeli;

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
    public static String KAKSIPARIA = "Neloset";
    public static String PARI = "Pari";
    public static String HAI = "Hai-käsi";
    
    public static String[] kadet = {"Värisuora", "Neloset","Täyskäsi","Väri"
            ,"Suora","kolmoset","Kaksi paria","Pari","Hai-käsi"};
    
    public Arvo arvo;
    public String kasi;
    
    public static enum Arvo {
        VARISUORA, NELOSET, TAYSKASI, VARI, SUORA, KOLMOSET, KAKSIPARIA, PARI, HAI
    }
    
    public PokeriHanska(Arvo arvo_) {
        arvo = arvo_;
        kasi = kadet[arvo.ordinal()];
    }
    
    public String getHanskaName() {
        return kasi;
    }
    
    @Override
    public String toString() {
        return kasi;
    }
}
