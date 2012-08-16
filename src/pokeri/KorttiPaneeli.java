package pokeri;

import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import raahauspeli.PokeriHanska;
import raahauspeli.PokeriHanska.Arvo;


public class KorttiPaneeli extends JPanel
{
    private int     vaihtoKrt;
    private JButton jaa;
    private JButton pelaaKasi;
    private JButton statistiikka;
    private JButton uusipeli;
    private JPanel  kortitPane;
    private JPanel  toiminnotPane;
    private MainMenu main;

    private KorttipaneelinKuuntelija kuuntelija;
    private Pakka    pakka;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
    
    private PokeriHanska tamanHetkinenKasi;

    public KorttiPaneeli(MainMenu m)
    {
        vaihtoKrt = 0;

        kortitPane = new JPanel();
        toiminnotPane = new JPanel();
        main = m;

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
        pelaaKasi.setEnabled(false);
        jaa = new JButton("Jää tähän");
        jaa.setActionCommand("jaa");
        jaa.setEnabled(false);
        statistiikka = new JButton("Tilastoja");
        statistiikka.setActionCommand("stats");
        statistiikka.setEnabled(false);
        uusipeli = new JButton("Uusi peli");
        uusipeli.setActionCommand("uusipeli");

        kuuntelija = new KorttipaneelinKuuntelija();
        pelaaKasi.addActionListener(kuuntelija);
        jaa.addActionListener(kuuntelija);
        statistiikka.addActionListener(kuuntelija);
        uusipeli.addActionListener(kuuntelija);

        toiminnotPane.add(pelaaKasi);
        toiminnotPane.add(jaa);
        toiminnotPane.add(statistiikka);
        toiminnotPane.add(uusipeli);

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
        if (k.getValinta() == false)
            k.toggleValinta();
    }

    public class KorttipaneelinKuuntelija implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().equals("pelaakasi")) {
                if (vaihtoKrt < Extern.VAIHTOJEN_LKM) {
                    vaihdaKortit(poytakortit);
                    evaluoiKasi(poytakortit);
                    laskeMahdollisuudet();

                    vaihtoKrt++;
                    if (vaihtoKrt == Extern.VAIHTOJEN_LKM) {
                        pelaaKasi.setEnabled(false);
                        jaa.setEnabled(false);
                        uusipeli.setEnabled(true);
                        evaluoiKasi(poytakortit);
                    }
                }
            } else if (ae.getActionCommand().equals("jaa")) {
                evaluoiKasi(poytakortit);
                jaa.setEnabled(false);
                pelaaKasi.setEnabled(false);
                uusipeli.setEnabled(true);
            } else if (ae.getActionCommand().equals("uusipeli")) {
                alustaUusiPeli();
                pelaaKasi.setEnabled(true);
                statistiikka.setEnabled(true);
                uusipeli.setEnabled(false);
                jaa.setEnabled(true);
            } else if (ae.getActionCommand().equals("stats")) {
                String txt = "";

                txt += "-------<PAKKA>-------\n";
                txt += pakka + "\n";
                txt += "------</PAKKA>-------\n\n";
                txt += "----<OTETUT MAAT>----\n";
                txt += otetutMaat();
                txt += "---</OTETUT MAAT>----\n\n";
                txt += "-------NOSTETTU-------\n";
                txt += String.format("Pakassa: %d, Nostettu: %d",
                        pakka.jaljella(), pakka.nostettu());
                txt += "------/NOSTETTU-------\n\n";
                main.setOhjeTekstiAlue(txt);
            }
        }
    }
    
    public void laskeMahdollisuudet()  {
        
        if (tamanHetkinenKasi.equals(new PokeriHanska(PokeriHanska.Arvo.HAI))) {
            long korttejaPakassa = pakka.jaljella();
            int valittu = poytakortit[0].getArvo();
            System.out.println("Mahikset papoytakortitrantua pariin on: " );
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
        main.setOhjeTekstiAlue("");
        evaluoiKasi(poytakortit);
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
                main.setOhjeTekstiAlue("Pakka tyhjä");
                break;
            }
            poytakortit[i].setSijainti(vanhasijainti);
            kortitPane.add(poytakortit[i]);
        }
        kortitPane.repaint();
        kortitPane.revalidate();
        }
    }

    public String otetutMaat()
    {
        StringBuilder sb = new StringBuilder();

        for (String m : Extern.MAAT)
            sb.append(String.format("%s %d", m, pakka.getMaaOtettuCount(m)));

        return sb.toString();
    }

    public void evaluoiKasi(Kortti[] kortit)
    {
        int nsamaa = 0;
        int tkasiVaiKaksiParia = 0;
        boolean onVariSuora = false;
        boolean onVari = false;
        boolean onSuora = false;
        Kortti[] tmp = new Kortti[poytakortit.length];

        System.arraycopy(poytakortit, 0, tmp, 0, tmp.length);
        Arrays.sort(tmp);

        onVariSuora = tarkistaVarisuora(tmp);
        onVari = tarkistaVari(tmp);
        onSuora = tarkistaSuora(tmp);
        nsamaa = tarkistaNSamaa(tmp);
        tkasiVaiKaksiParia = tarkistaTayskasiTaiKaksiParia(tmp);

        if (onVariSuora) {
            main.setOhjeTekstiAlue("Värisuora");
            tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.VARISUORA);
            return;
        } else if (onVari) {
            main.setOhjeTekstiAlue("Väri");
            tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.VARI);
        }          
        else if (onSuora) {
            main.setOhjeTekstiAlue("Suora"); 
            tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.SUORA);
        }         
        else {
            if (nsamaa > 0 && tkasiVaiKaksiParia != 5) {
                switch (nsamaa) {
                case 2:
                    main.setOhjeTekstiAlue("Pari");
                    tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.PARI);
                    break;
                case 3:
                    main.setOhjeTekstiAlue("Kolme samaa");
                    tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.KOLMOSET);
                    break;
                case 4:
                    main.setOhjeTekstiAlue("Neljä samaa");
                    tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.NELOSET);
                    break;
                default:
                    break;
                }
            }

            if (tkasiVaiKaksiParia > 0) {
                switch (tkasiVaiKaksiParia) {
                case 2: // Kaksi paria
                    main.setOhjeTekstiAlue("Kaksi paria");
                    tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.KAKSIPARIA);
                    break;
                case 5:
                    main.setOhjeTekstiAlue("Täyskäsi");
                    tamanHetkinenKasi = new PokeriHanska(PokeriHanska.Arvo.TAYSKASI);
                    break;
                default:
                    break;
                }
            }

            if (main.getOhjeTeksti().equals(""))
                main.setOhjeTekstiAlue("Hai: " + Integer.toString(tarkistaHai(kortit)));
        }
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
     * @return 2, jos kyseessä on kaksi paria, 5 jos kyseessä täyskäsi,
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

        kesk = (int) Math.floor(esiintymat.length / 2.0);
        // Täydessä kädessä: 2 2 [3] 3 3 tai 3 3 [3] 2 2
        if (esiintymat[kesk] == 3) { // Mahdollinen täyskäsi
            if (esiintymat[1] == 2 || // Jos 2. tai 4. kortti on 2 -> tk
                esiintymat[esiintymat.length - 2] == 2)
                    return 5;
        }
        // Kaksi paria: 1 2 [2] 2 2 tai 2 2 [2] 2 1
        if (esiintymat[0] == 1 ||
            esiintymat[esiintymat.length - 1] == 1) {
                if (esiintymat[kesk - 1] == 2 &&
                    esiintymat[kesk + 1] == 2) {
                        return 2;
                }
        }

        return 0;
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
