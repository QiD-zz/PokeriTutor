/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package opetus;

import pokeri.Extern;
import pokeri.Kortti;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class Skenaario {
    
    private PokeriHanska hanska1;
    private PokeriHanska hanska2;
    private PokeriHanska hanska3;
    
    private PokeriHanska oikeaVastaus;
    
    private Kortti[] kortit;
    
    private Kortti[][] korttiVaihtoehdot = {{new Kortti("ruutu", 12, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 11, Extern.PERUSPISTE10X10Y), new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 9, Extern.PERUSPISTE10X10Y),new Kortti("risti", 12, Extern.PERUSPISTE10X10Y)},
    {new Kortti("pata", 12, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 12, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 9, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 12, Extern.PERUSPISTE10X10Y)}};
    
    private PokeriHanska[][] vaihtoehdot = {{new PokeriHanska(PokeriHanska.Arvo.VARI),
        new PokeriHanska(PokeriHanska.Arvo.SUORA),new PokeriHanska(PokeriHanska.Arvo.PARI)},
        {new PokeriHanska(PokeriHanska.Arvo.VARI),
        new PokeriHanska(PokeriHanska.Arvo.VARISUORA),new PokeriHanska(PokeriHanska.Arvo.NELOSET)}
    };
    
    private PokeriHanska[] oikeatVastaukset = {new PokeriHanska(PokeriHanska.Arvo.VARI)
            ,new PokeriHanska(PokeriHanska.Arvo.NELOSET)};
    
    public Skenaario(int numero) {
        
        hanska1 = vaihtoehdot[numero][0];
        hanska2 = vaihtoehdot[numero][1];
        hanska3 = vaihtoehdot[numero][2];
        
        oikeaVastaus = oikeatVastaukset[numero];
        
        kortit = korttiVaihtoehdot[numero];
    }
    
    public PokeriHanska getEkaHanska() {
        return hanska1;
    }
    
    public PokeriHanska getTokaHanska() {
        return hanska2;
    }
    
    public PokeriHanska getKolmasHanska() {
        return hanska3;
    }
    
    public PokeriHanska getOikeaVastaus() {
        return oikeaVastaus;
    }
    
    public Kortti getKortti(int numero) {
        return kortit[numero];
    }
}
