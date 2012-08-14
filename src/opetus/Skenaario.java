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
    private String oikeanVastauksenTeksti;
    
    private Kortti[] kortit;
    
    private Kortti[][] korttiVaihtoehdot = {{new Kortti("ruutu", 12, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 11, Extern.PERUSPISTE10X10Y), new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 9, Extern.PERUSPISTE10X10Y),new Kortti("risti", 12, Extern.PERUSPISTE10X10Y)},
        
    {new Kortti("pata", 12, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 12, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 9, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 12, Extern.PERUSPISTE10X10Y)},
    
    {new Kortti("hertta", 7, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 8, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("hertta", 9, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 7, Extern.PERUSPISTE10X10Y)},
    
    {new Kortti("ruutu", 11, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 8, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("hertta", 7, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 8, Extern.PERUSPISTE10X10Y)}};
    
    private PokeriHanska[][] vaihtoehdot = {{new PokeriHanska(PokeriHanska.Arvo.VARI),
        new PokeriHanska(PokeriHanska.Arvo.SUORA),new PokeriHanska(PokeriHanska.Arvo.PARI)},
            {new PokeriHanska(PokeriHanska.Arvo.VARI),
        new PokeriHanska(PokeriHanska.Arvo.VARISUORA),new PokeriHanska(PokeriHanska.Arvo.NELOSET)},
            {new PokeriHanska(PokeriHanska.Arvo.KOLMOSET),
        new PokeriHanska(PokeriHanska.Arvo.SUORA),new PokeriHanska(PokeriHanska.Arvo.KAKSIPARIA)},
            {new PokeriHanska(PokeriHanska.Arvo.SUORA),
        new PokeriHanska(PokeriHanska.Arvo.VARI),new PokeriHanska(PokeriHanska.Arvo.KOLMOSET)}
    };
    
    private PokeriHanska[] oikeatVastaukset = {new PokeriHanska(PokeriHanska.Arvo.VARI)
            ,new PokeriHanska(PokeriHanska.Arvo.NELOSET),new PokeriHanska(PokeriHanska.Arvo.SUORA),
    new PokeriHanska(PokeriHanska.Arvo.SUORA)};
    
    private String[] ohjeTekstit = {"Oikein. Kun pöydässä on neljä samaa värikorttia ja neljä peräkkäistä "
            + "numeroa on värin vetäminen kannattavampaa, sillä pakasta löytyy päistä avoinna olevaan"
            + " suoraan kahdeksan korttia (kuninkaat ja kasit), jotka voivat parantaa tätä kättä,"
            + " mutta värikortteja on pakassa vielä yksi enemmän eli yhdeksän, siis kaikki ruudut.",
            "Oikein. Kolmea samaa on luontevaa lähteä parantamaan kohti nelosia. Nelosten osumisen todennäköisyys "
            + "on melko matala, mutta kaiken muun vetäminen vaatisi kolmosten rikkomista. Värin ja värisuoran "
            + "vetäminen vaatisi kahden kortin osumista pakasta, joka on vielä nelosienkin saamista"
            + " epätodennäköisempää",
            "Oikein. Vaikka pöydässä on myös pari on suoran vetäminen kaikkein tuottoisin vaihtoehto."
            + " Pakassa on kahdeksan korttia, jotka tuovat pelaajalle suoran, mutta vain kaksi korttia voi"
            + " parantaa parin kolmosiksi. Asia ei tietenkään ole suoraan näin yksiselitteinen, sillä parin "
            + "jättämällä pelaajalla on kolme mahdollisuutta osua kahteen puuttuvaan korttiinsa. Kahta paria"
            + " ei kannata myöskään lähteä vetämään, koska se on näistä kolmesta kädestä huonoin ja senkään "
            + " todennäköisyys ei ole suoran vetoa juurikaan parempi/huonompi.",
            "Näin on, kolmoset on paras "
            + "vaihtoehto. Pöydässä on myös ilmeinen suoran veto, mutta suoran vetäminen on huomattavasti "
            + "epätodennäköisempää kun suorasta puuttuu kortti suoran keskeltä verrattuna sellaiseen tilanteeseen, "
            + "että suora on auki molemmista päistä. Värin vetäminen kolmesta kortista on vieläkin epätodennäköisempi "
            + "vaihtoehto."};
    
    public Skenaario(int numero) {
        
        hanska1 = vaihtoehdot[numero][0];
        hanska2 = vaihtoehdot[numero][1];
        hanska3 = vaihtoehdot[numero][2];
        
        oikeaVastaus = oikeatVastaukset[numero];
        oikeanVastauksenTeksti = ohjeTekstit[numero];
        
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
    
    public String getOhjeTeksti() {
        return oikeanVastauksenTeksti;
    }
}
