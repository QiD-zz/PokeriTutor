package pokeri;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class KorttiPaneeli extends JPanel
{
    private int     vaihtoKrt;
    private JButton pelaaKasi;
    private JButton uusipeli;
    private JButton naytapakka;
    private JPanel  kortitPane;
    private JPanel  toiminnotPane;
    private JTextArea tilastot;
    private KorttipaneelinKuuntelija kuuntelija;
    private Pakka    pakka;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli(JTextArea til)
    {
        vaihtoKrt = 0;

        kortitPane = new JPanel();
        toiminnotPane = new JPanel();
        tilastot = til;

        // Layout määrittelyt
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kortitPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        toiminnotPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        pakka = Pakka.getPakka();
        pakka.sekoita();
        alustaKortit();

        // Toiminnot
        pelaaKasi = new JButton("Pelaa käsi");
        pelaaKasi.setActionCommand("pelaakasi");
        uusipeli = new JButton("Uusi peli");
        uusipeli.setActionCommand("uusipeli");
        naytapakka = new JButton("Näytä pakka");
        naytapakka.setActionCommand("naytapakka");

        kuuntelija = new KorttipaneelinKuuntelija();
        pelaaKasi.addActionListener(kuuntelija);
        uusipeli.addActionListener(kuuntelija);
        naytapakka.addActionListener(kuuntelija);

        toiminnotPane.add(pelaaKasi);
        toiminnotPane.add(uusipeli);
        toiminnotPane.add(naytapakka);

        add(kortitPane);
        add(toiminnotPane);
        kortitPane.setSize(getMaximumSize());
        toiminnotPane.setSize(getMaximumSize());

        setVisible(true);
    }

    public void alustaKortit()
    {
        for (int i = 0; i < Extern.KORTTEJA_POYDALLA; i++) {
            Kortti k = pakka.otaKortti();

            k.setLocation(0, 25);
            poytakortit[i] = k;
            kortitPane.add(poytakortit[i]);
        }
        revalidate();
    }

    public void merkkaaValituksi(Kortti k)
    {
        if (k.getValinta() == false) {
            k.toggleValinta();
            //k.repaint(); XXX Toimiiko teikällä ilman tätä?
        }
    }

    public class KorttipaneelinKuuntelija implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().equals("uusipeli")) {
                pelaaKasi.setEnabled(true);
                alustaUusiPeli();
            } else if (ae.getActionCommand().equals("pelaakasi")) {
                if (vaihtoKrt < Extern.VAIHTOJEN_LKM) {
                    Kortti[] tmp = new Kortti[poytakortit.length];

                    System.arraycopy(poytakortit, 0, tmp, 0, tmp.length);
                    Arrays.sort(tmp);
                    evaluoiKasi(tmp);
                    vaihdaKortit(poytakortit);
                    tilastot.setText(String.format("Pakassa: %d, Nostettu: %d",
                                     pakka.jaljella(), pakka.nostettu()));
                    vaihtoKrt++;
                    if (vaihtoKrt >= Extern.VAIHTOJEN_LKM)
                        pelaaKasi.setEnabled(false);
                } else {
                    tilastot.setText("Peli loppui");
                }
            } else if (ae.getActionCommand().equals("naytapakka")) {
                System.out.println("-------PAKKA-------");
                System.out.println(String.format("%s", pakka));
                System.out.println("------/PAKKA-------");
                evaluoiKasi(poytakortit);
            }
        }
    }

    public void alustaUusiPeli()
    {
        kortitPane.removeAll();
        poytakortit = null;
        poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
        pakka = Pakka.uusiPakka();
        pakka.sekoita();
        alustaKortit();
        vaihtoKrt = 0;
    }

    public void vaihdaKortit(Kortti[] kortit)
    {
       for (int i = 0; i < poytakortit.length; i++) {
        if (poytakortit[i].getValinta() == false) {
            Point vanhasijainti = poytakortit[i].getSijainti();

            kortitPane.remove(poytakortit[i]);
            poytakortit[i] = null;
            poytakortit[i] = pakka.otaKortti();
            if (poytakortit[i].getArvo() == 0) {
                System.out.println("Pakka tyhjä");
                tilastot.setText("Pakka tyhjä");
                break;
            }
            poytakortit[i].setSijainti(vanhasijainti);
            kortitPane.add(poytakortit[i]);
        }
        kortitPane.repaint();
        kortitPane.revalidate();
        }
    }

    public void testaaOtetutMaat() // XXX Testimetodi, poista jossain välissä
    {
        for (String s : Extern.MAAT)
        System.out.println(String.format("%s %d", s, pakka.getMaaOtettuCount(s)));
    }

    public void evaluoiKasi(Kortti[] kortit)
    {
        int nsamaa = 0;
        int tkasiVaiKaksiParia = 0;
        boolean onVari = false;
        boolean onSuora = false;

        onVari = tarkistaVari(poytakortit);
        onSuora = tarkistaSuora(poytakortit);
        nsamaa = tarkistaNSamaa(poytakortit);
        System.out.println(String.format("Värisuora: %s", tarkistaVarisuora(kortit)));
        System.out.println(String.format("Väri: %s", onVari));
        System.out.println(String.format("Suora: %s", onSuora));
        System.out.println(String.format("N samaa: %d", nsamaa));
        System.out.println(String.format("Hai: %d", tarkistaHai(kortit)));
        tkasiVaiKaksiParia = tarkistaTayskasiTaiKaksiParia(kortit);
        System.out.println(String.format("tarkistaTaysKjne: %d", tkasiVaiKaksiParia));
        switch (tkasiVaiKaksiParia) {
        case 2:
            System.out.println("Kaksi paria");
            break;
        case 3:
            System.out.println("Täyskäsi");
        }
        System.out.println("-------");
    }

    /*
     ******** Pokerikädet arvojärjestyksessä ********
     * (Lähde: http://nettipokeri.info/pokerikadet)
     *
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

    public boolean tarkistaVarisuora(Kortti[] kortit)
    {
        if (tarkistaVari(kortit) && tarkistaSuora(kortit))
            return true;
        return false;
    }

    /**
     * Tarkista montako samaa kortin arvoa löytyy
     * @param kortit
     * @return suurin löytynyt lukumäärä
     */
    public int tarkistaNSamaa(Kortti[] kortit)
    {
        int nsamaa = 0;
        int nykSam = 0;

        for (int i = 1; i < kortit.length; i++) {
            Kortti edel = kortit[i - 1];

            if (edel.getArvo() == kortit[i].getArvo()) {
                nykSam++;
                if (nsamaa < nykSam)
                    nsamaa = nykSam;
            } else
                nykSam = 0;
        }
        return (nsamaa > 0) ? nsamaa + 1 : 0;
    }

    /**
     * Tarkista tehottomasti onko kyseessä kaksi paria vai täyskäsi
     * @param kortit
     * @return 2, jos kyseessä on kaksi paria, 3 jos kyseessä täyskäsi,
     * 0 jos ei mitään
     */
    public int tarkistaTayskasiTaiKaksiParia(Kortti[] kortit)
    {
        int kesk = 0;
        int[] esiintymat = new int[kortit.length];

        // Toteutus on typerä, hidas, mutta toimiva
        for (int i = 0; i < kortit.length; i++) {
            esiintymat[i] = 0;

            for (int j = 0; j < kortit.length; j++) {
                if (kortit[i].getArvo() == kortit[j].getArvo())
                    esiintymat[i]++;
            }
        }
        Arrays.sort(esiintymat);
        kesk = (int) Math.ceil(esiintymat.length / 2.0);
        if (esiintymat[0] != 2 || esiintymat[1] != 2)
            return 0;

        return esiintymat[kesk];
    }

    public boolean tarkistaVari(Kortti[] kortit)
    {
        for (int i = 1; i < kortit.length; i++) {
            Kortti edel = kortit[i - 1];

            if (edel.compareVari(kortit[i]) == false)
                return false;
        }
        return true;
    }

    public boolean tarkistaSuora(Kortti[] kortit)
    {
        for (int i = 0; i < kortit.length - 1; i++) {
            Kortti seur = kortit[i + 1];

            if ((kortit[i].getArvo() + 1) != seur.getArvo())
                return false;
        }
        return true;
    }

    public int tarkistaHai(Kortti[] kortit)
    {
        int max = 0;

        for (Kortti k : kortit) {
            if (k.getArvo() > max)
                max = k.getArvo();
        }

        return max;
    }

}
