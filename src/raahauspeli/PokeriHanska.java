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
    
    public static String VARISUORA = "royal flush";
    public static String NELOSET = "quads";
    public static String TAYSKASI = "full house";
    public static String VARI = "flush";
    public static String SUORA = "str8";
    public static String KOLMOSET = "trips";
    public static String KAKSIPARIA = "two pair";
    public static String PARI = "a pair";
    public static String HAI = "high card";
    
    public Arvo arvo;
    public String kasi;
    
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
        Arvo[] arvot = Arvo.values();
        arvo = arvot[luku];
        kasi = kadet[luku];
    }
    
    public String getHanskaName() {
        return kasi;
    }
    
    @Override
    public String toString() {
        return kasi;
    }
}
