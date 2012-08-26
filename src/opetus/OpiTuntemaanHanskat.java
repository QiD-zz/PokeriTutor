package opetus;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import pokeri.Extern;
import pokeri.MainMenu;
import raahauspeli.PokeriHanska;

public class OpiTuntemaanHanskat extends JPanel implements ActionListener
{
    private JButton oikealle;
    private JButton vasemmalle;
    private JPanel kasiTaulunPaikka;
    private JLabel nykyinenKasi;
    private JPanel nimenPaneeli;
    private JPanel ohjeTekstinPaikka;
    private KasiTaulu kasiTaulu;
    private JLabel seuraavaParempi;
    private JLabel edellinenHuonompi;
    private MainMenu main;
    private int kuvausLaskuri;

    private final String[] kuvaukset = {"Hai, eli high card, on nimensä mukaisesti käsi,"
            + " jossa ei ole mitään kombinaatioita vaan käden arvon määrittää sen "
            + "korkein kortti. Esimerkkikädessä korkein kortti on ässä, joten käden"
            + " nimi on \"ässä-hai\".","Pari on käsi, jossa on kaksi samaa korttia."
            + " Pari on pokerikäsistä toiseksi huonoin. Kahden pari-käden vahvuus "
            + "määritellään niiden parin suuruudesta. Esimerkkikätemme on kuningaspari,"
            + " joka on toiseksi paras pari heti ässäparin jälkeen. Huonoin pari on kakkospari.",
    "Kaksi paria kolmanneksi huonoin pokerikäsi. Käden vahvuuden määrittää ensisijaisesti"
            + " isompi pari ja toissijaisesti pienempi pari. Eli jos vastakkain on kaksi"
            + "kahden parin kättä ensin verrataan keskenään suurempia pareja ja jos"
            + " ne on samoja verrataan pienempiä pareja. Esimerkiksi kasit ja kakkoset voittaa "
            + "kutoset ja seiskat, vaikka jälkimmäisen käden yhteissumma on suurempi, koska"
            + " ensimmäisen käden toinen pari on suurempi.",
    "Kolme samaa eli kolmoset on neljänneksi paras käsi pokerissa. Kahden kolmoskäden "
            + "vahvuuden vertailu on helppoa, koska jokerittomassa pakassa voi olla vain "
            + "yhdet kolmoset, joka arvolla. Isoimmat kolmoset ovat parhaat, eli esimerkkikätemme "
            + "ässäkolmoset.","Suora on käsi, jossa on viisi peräkkäistä korttia eri maista."
            + " Suoran arvo suhteessa toiseen suoraan määritellään sen korkeimman tai"
            + " matalimman kortin mukaan.","Väri on käsi, jossa kaikki viisi korttia "
            + "on samasta maasta, mutta ne eivät ole peräkkäisiä keskenään.","Täyskädessä on"
            + " kolmoset ja pari samassa kädessä. Täyskäden arvon määrittää kolmosten"
            + " koko eli käsi QQQAA (kolme rouvaa ja kaksi ässää) on huonompi kuin"
            + " KKK22 (kolme kuningasta ja kakkospari).","Neljä samaa eli neloset"
            + " on pokerin toiseksi paras käsi. Nelosissa pelaajalla on kädessään"
            + " jokaisen maan kortti samalla arvolla eli esimerkiksi neljä ässää "
            + "kuten esimerkkikädessä.","Värisuora on pokerin paras käsi ja "
            + "sen erästä versioita, kuninkaallista (suora kympistä ässään) herttavärisuoraa"
            + " eli herttareetiä pidetään kaikkein parhaimpana kätenä. Todellisuudessa"
            + " maalla ei useimmissa pokeriversioissa ole väliä, jolloin patavärisuora"
            + " samoilla numeroilla"
            + " olisi aivan yhtä hyvä käsi kuin herttavärisuora. "};

    public OpiTuntemaanHanskat(MainMenu m)
    {
        main = m;

        int reunojenPaksuus = main.getContentPane().getBounds().width;
        System.out.println(reunojenPaksuus);

        kuvausLaskuri = 0;
        this.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA, Extern.KORKEUS_IKKUNA));
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        this.setBackground(Color.lightGray);
        PokeriHanska alkuHanska = new PokeriHanska(PokeriHanska.Arvo.HAI);
        kasiTaulu = new KasiTaulu(alkuHanska);
        kasiTaulunPaikka = new JPanel();
        kasiTaulunPaikka.setBackground(Color.lightGray);
        kasiTaulu.setBackground(Color.lightGray);
        kasiTaulunPaikka.add(kasiTaulu);
        this.add(kasiTaulunPaikka);
        nykyinenKasi = new JLabel();
        nykyinenKasi.setBackground(Color.lightGray);
        nykyinenKasi.setFont(new Font(Font.SERIF, Font.BOLD, 30));
        vasemmalle = new JButton("Edellinen käsi");
        edellinenHuonompi = new JLabel();
        edellinenHuonompi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        seuraavaParempi = new JLabel(PokeriHanska.PARI);
        seuraavaParempi.setFont(new Font(Font.SERIF, Font.BOLD, 25));
        oikealle = new JButton("Seuraava käsi");
        vasemmalle.addActionListener(this);
        vasemmalle.setEnabled(false);
        oikealle.addActionListener(this);
        JPanel tyhja = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tyhja.setBackground(Color.lightGray);
        tyhja.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/7, 250));
        JPanel tyhja2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        tyhja2.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/7, 250));
        tyhja2.setBackground(Color.lightGray);
        tyhja.add(vasemmalle);
        tyhja.add(edellinenHuonompi);
        tyhja2.add(oikealle);
        tyhja2.add(seuraavaParempi);
        this.add(tyhja); 
        nimenPaneeli = new JPanel(new FlowLayout(FlowLayout.CENTER));
        nimenPaneeli.setPreferredSize(new Dimension((Extern.LEVEYS_IKKUNA/3)*2, 250));
        nimenPaneeli.setBackground(Color.lightGray);
        nykyinenKasi.setBackground(this.getBackground());
        nykyinenKasi.setLayout(new FlowLayout(FlowLayout.CENTER));
        nykyinenKasi.setText(alkuHanska.getHanskaName());
        nimenPaneeli.add(nykyinenKasi);
        main.setOhjeTekstiAlue(kuvaukset[kuvausLaskuri]);
        ohjeTekstinPaikka = new JPanel();
        ohjeTekstinPaikka.add(nimenPaneeli);
        ohjeTekstinPaikka.setBackground(Color.lightGray);
        this.add(ohjeTekstinPaikka);
        this.add(tyhja2); 

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource().equals(oikealle)) {
            vasemmalle.setEnabled(true);
            PokeriHanska vanhaHanska = kasiTaulu.getHanska();
            PokeriHanska uusHanska = new PokeriHanska(vanhaHanska.arvo.ordinal()-1);
            kasiTaulu = new KasiTaulu(uusHanska);
            kasiTaulunPaikka.removeAll();

            edellinenHuonompi.setText(vanhaHanska.getHanskaName());
            kuvausLaskuri++;
            nykyinenKasi.setText(uusHanska.getHanskaName());
            main.setOhjeTekstiAlue(kuvaukset[kuvausLaskuri]);
            kasiTaulunPaikka.add(kasiTaulu);
            kasiTaulunPaikka.validate();

            if (kuvausLaskuri == 8) {
                oikealle.setEnabled(false);
                seuraavaParempi.setText("");
            } else {
                PokeriHanska seuraavaHanska = new PokeriHanska(uusHanska.arvo.ordinal()-1);
                seuraavaParempi.setText(seuraavaHanska.getHanskaName());
            }
        } else {
            oikealle.setEnabled(true);
            PokeriHanska vanhaHanska = kasiTaulu.getHanska();
            PokeriHanska uusHanska = new PokeriHanska(vanhaHanska.arvo.ordinal()+1);
            kasiTaulu = new KasiTaulu(uusHanska);
            kasiTaulunPaikka.removeAll();

            seuraavaParempi.setText(vanhaHanska.getHanskaName());

            kuvausLaskuri--;
            nykyinenKasi.setText(uusHanska.getHanskaName());
            main.setOhjeTekstiAlue(kuvaukset[kuvausLaskuri]);
            kasiTaulunPaikka.add(kasiTaulu);
            kasiTaulunPaikka.validate();

            if (kuvausLaskuri == 0) {
                vasemmalle.setEnabled(false);
                 edellinenHuonompi.setText("");
            } else {
                PokeriHanska seuraavaHanska = new PokeriHanska(uusHanska.arvo.ordinal()+1);
                edellinenHuonompi.setText(seuraavaHanska.getHanskaName());
            }
        }
    }  

}
