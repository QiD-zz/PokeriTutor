package opetus;

import pokeri.Extern;
import pokeri.Kortti;
import raahauspeli.PokeriHanska;

/**
 *
 * @author hurvittelu
 */
public class Skenaario
{
    private PokeriHanska hanska1;
    private PokeriHanska hanska2;
    private PokeriHanska hanska3;
    
    private PokeriHanska oikeaVastaus;
    private String oikeanVastauksenTeksti;
    
    private Kortti[] kortit;
    
    private Kortti[][] korttiVaihtoehdot = {{new Kortti("ruutu", 12, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 4, Extern.PERUSPISTE10X10Y), new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 9, Extern.PERUSPISTE10X10Y),new Kortti("risti", 12, Extern.PERUSPISTE10X10Y)},
        
    {new Kortti("pata", 12, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 12, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 9, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 12, Extern.PERUSPISTE10X10Y)},
    
    {new Kortti("hertta", 7, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 8, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("hertta", 9, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 7, Extern.PERUSPISTE10X10Y)},
    
    {new Kortti("ruutu", 11, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 8, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 10, Extern.PERUSPISTE10X10Y),new Kortti("hertta", 7, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 8, Extern.PERUSPISTE10X10Y)},
    
    {new Kortti("ruutu", 7, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 6, Extern.PERUSPISTE10X10Y),
        new Kortti("ruutu", 8, Extern.PERUSPISTE10X10Y),new Kortti("ruutu", 5, Extern.PERUSPISTE10X10Y),
        new Kortti("risti", 8, Extern.PERUSPISTE10X10Y)}};
    
    private PokeriHanska[][] vaihtoehdot = {{new PokeriHanska(PokeriHanska.Arvo.VARI),
        new PokeriHanska(PokeriHanska.Arvo.SUORA),new PokeriHanska(PokeriHanska.Arvo.PARI)},
            {new PokeriHanska(PokeriHanska.Arvo.VARI),
        new PokeriHanska(PokeriHanska.Arvo.VARISUORA),new PokeriHanska(PokeriHanska.Arvo.NELOSET)},
            {new PokeriHanska(PokeriHanska.Arvo.KOLMOSET),
        new PokeriHanska(PokeriHanska.Arvo.SUORA),new PokeriHanska(PokeriHanska.Arvo.KAKSIPARIA)},
            {new PokeriHanska(PokeriHanska.Arvo.SUORA),
        new PokeriHanska(PokeriHanska.Arvo.VARI),new PokeriHanska(PokeriHanska.Arvo.KOLMOSET)},
            {new PokeriHanska(PokeriHanska.Arvo.VARISUORA),
        new PokeriHanska(PokeriHanska.Arvo.KAKSIPARIA),new PokeriHanska(PokeriHanska.Arvo.KOLMOSET)}
    };
    
    private PokeriHanska[] oikeatVastaukset = {new PokeriHanska(PokeriHanska.Arvo.VARI)
            ,new PokeriHanska(PokeriHanska.Arvo.NELOSET),new PokeriHanska(PokeriHanska.Arvo.SUORA),
    new PokeriHanska(PokeriHanska.Arvo.KOLMOSET), new PokeriHanska(PokeriHanska.Arvo.VARISUORA)
};
    
    private String[] ohjeTekstit = {"Oikein. Kun pöydässä on neljä samaa värikorttia ja neljä peräkkäistä "
            + "numeroa on värin vetäminen kannattavampaa, sillä pakasta löytyy päistä avoinna olevaan"
            + " suoraan kahdeksan korttia (kuninkaat ja kasit), jotka voivat parantaa tätä kättä,"
            + " mutta värikortteja on pakassa vielä yksi enemmän eli yhdeksän, siis kaikki ruudut. "
            + "Värin osumaprosentti tässä on yhdellä vaihdolla 19% ja kahdella vaihdolla 35%. Suoralla "
            + "samat arvot ovat 17% ja 32%. Parin parantumisen (kahdeksi pariksi tai kolmosiksi) "
            + "todennäköisyys on 11% yhdellä ja 20% kahdella vaihdolla.",
            "Oikein. Kolmea samaa on luontevaa lähteä parantamaan kohti nelosia. Nelosten osumisen todennäköisyys "
            + "on melko matala, mutta kaiken muun vetäminen vaatisi kolmosten rikkomista. Nelosia hakemalla"
            + " voidaan myös osua täyskäteen. Nelosiin tai täyskäteen osumisen todennäköisyys tästä tilanteesta "
            + " on noin 15%. Värin ja värisuoran vetäminen vaatisi kahden kortin osumista pakasta, "
            + "joka on vielä nelosienkin saamista epätodennäköisempää.",
            "Oikein. Vaikka pöydässä on myös pari on suoran vetäminen kaikkein tuottoisin vaihtoehto."
            + " Päistä auki olevan suoran osumisen todennäköisyys on 17% kun parin kehittyminen kolmosiksi "
            + "tai kahdeksi pariksi tapahtuu noin 11% todennäköisyydellä.",
            "Tämä on kinkkinen, mutta kolmoset lienee kuitenkin paras vaihtoehto. "
            + "Pöydässä on myös ilmeinen suoran veto, mutta suoran vetäminen on"
            + " huomattavasti epätodennäköisempää kun suorasta puuttuu kortti suoran"
            + " keskeltä verrattuna sellaiseen tilanteeseen, että suora on auki "
            + " päistä. Suoraan osumisen onnistumisprosentti tämmöisellä ns. "
            + "välivedolla on vain noin 9%. Parin parantuminen kolmosiksi vain 4%, mutta "
            + "jos otetaan mukaan ne kerrat, jolloin pari parantuu kahdeksi pariksi on "
            + "käden parantumisen todennäköisyys pari jättämällä yhteensä 11%. "
            + "Värin vetäminen kolmesta kortista on vieläkin epätodennäköisempi "
            + "vaihtoehto, sillä se onnistuu vain noin joka 50. kerta.",
    "Värisuoraapa hyvinkin. Tässä tilanteessa värisuoraan osumisen todennäköisyys on "
            + "suurimmillaan eli 4%, mutta koska pöydässä on myös värin ja suoran vedot"
            + " on todennäköisyys vedon parantumiseen oikeaan käteen todella suuret "
            + "eli 33%. Tässä tilanteessa värisuoraa vetämällä osuu siis joka kolmannella"
            + " kerralla johonkin, suoraan väriin ja joskus jopa siihen värisuoraan. "};

    public Skenaario(int numero)
    {
        hanska1 = vaihtoehdot[numero][0];
        hanska2 = vaihtoehdot[numero][1];
        hanska3 = vaihtoehdot[numero][2];

        oikeaVastaus = oikeatVastaukset[numero];
        oikeanVastauksenTeksti = ohjeTekstit[numero];

        kortit = korttiVaihtoehdot[numero];
    }

    public PokeriHanska getEkaHanska()
    {
        return hanska1;
    }

    public PokeriHanska getTokaHanska()
    {
        return hanska2;
    }

    public PokeriHanska getKolmasHanska()
    {
        return hanska3;
    }

    public PokeriHanska getOikeaVastaus()
    {
        return oikeaVastaus;
    }

    public Kortti getKortti(int numero)
    {
        return kortit[numero];
    }

    public String getOhjeTeksti()
    {
        return oikeanVastauksenTeksti;
    }

}
