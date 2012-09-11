package pokeri;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import raahauspeli.PokeriHanska;


public class KorttiPaneeli extends JPanel
{
    private int     vaihtoKrt;
    private JButton jaa;
    private JButton pelaaKasi;
    private JButton statistiikka;
    private JButton uusipeli;
    private JButton panos;
    private JButton tallenna;
    private JButton top5;
    private JLabel  pistenaytto;
    private JPanel  kortitPane;
    private MainMenu main;

    private Pakka        pakka;
    private Pisteytys    pisteet;
    private Top5         top5handler;
    private PokeriHanska tamanHetkinenKasi;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli(MainMenu m)
    {
        JPanel peliNappulatPane;
        JPanel tilastotPane;
        JPanel toiminnotPane;
        JPanel wrapper;
        KorttipaneelinKuuntelija kuuntelija;

        vaihtoKrt = 0;

        top5handler = Top5.getTop5();
        kortitPane = new JPanel();
        kortitPane.setBackground(Extern.POKERIPELITAUSTA);
        tilastotPane = new JPanel();
        toiminnotPane = new JPanel();
        toiminnotPane.setBackground(Extern.PAINIKETAUSTA1);
        peliNappulatPane = new JPanel();
        peliNappulatPane.setBackground(Extern.PAINIKETAUSTA2);
        wrapper = new JPanel();
        wrapper.setBackground(Extern.POKERIPELITAUSTA);
        main = m;

        // Layout määrittelyt
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kortitPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        tilastotPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        toiminnotPane.setLayout(new FlowLayout(FlowLayout.LEADING));
        peliNappulatPane.setLayout(new FlowLayout(FlowLayout.TRAILING));
        wrapper.setLayout(new BoxLayout(wrapper, BoxLayout.X_AXIS));

        pakka = Pakka.getPakka();
        pakka.sekoita();
        alustaKortit();
        pisteet = new Pisteytys();
        pistenaytto = new JLabel(String.format("Pisteet %5d  |  Panos %5d",
                                 pisteet.getPisteet(), pisteet.getPanos()));
        // Toiminnot
        pelaaKasi = new JButton("Pelaa käsi");
        pelaaKasi.setActionCommand("pelaakasi");
        pelaaKasi.setBackground(new Color(220, 255, 180));
        pelaaKasi.setOpaque(true);
        pelaaKasi.setEnabled(false);
        jaa = new JButton("Jää tähän");
        jaa.setActionCommand("jaa");
        jaa.setBackground(new Color(220, 255, 180));
        jaa.setOpaque(true);
        jaa.setEnabled(false);
        statistiikka = new JButton("Tilastoja");
        statistiikka.setActionCommand("stats");
        statistiikka.setBackground(new Color(218, 180, 110));
        statistiikka.setOpaque(true);
        statistiikka.setEnabled(false);
        uusipeli = new JButton("Uusi peli");
        uusipeli.setActionCommand("uusipeli");
        uusipeli.setBackground(new Color(120, 255, 180));
        uusipeli.setOpaque(true);
        panos = new JButton("Panos");
        panos.setActionCommand("panos");
        panos.setBackground(new Color(220, 255, 180));
        panos.setOpaque(true);
        tallenna = new JButton("Tallenna pisteet");
        tallenna.setActionCommand("tallenna");
        tallenna.setBackground(new Color(218, 180, 110));
        tallenna.setOpaque(true);
        tallenna.setEnabled(false);
        top5 = new JButton("Top 5");
        top5.setActionCommand("top5");
        top5.setBackground(new Color(218, 180, 110));
        top5.setOpaque(true);

        kuuntelija = new KorttipaneelinKuuntelija();
        pelaaKasi.addActionListener(kuuntelija);
        jaa.addActionListener(kuuntelija);
        statistiikka.addActionListener(kuuntelija);
        uusipeli.addActionListener(kuuntelija);
        panos.addActionListener(kuuntelija);
        tallenna.addActionListener(kuuntelija);
        top5.addActionListener(kuuntelija);

        tilastotPane.add(pistenaytto);

        peliNappulatPane.add(pelaaKasi);
        peliNappulatPane.add(panos);
        peliNappulatPane.add(jaa);
        peliNappulatPane.add(uusipeli);

        toiminnotPane.add(tallenna);
        toiminnotPane.add(top5);
        toiminnotPane.add(statistiikka);

        wrapper.add(toiminnotPane);
        wrapper.add(peliNappulatPane);
        add(kortitPane);
        add(tilastotPane);
        add(wrapper);
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
                vaihdaKortit(poytakortit); // Tekee myös vaihtoKrt lisäyksen
                if (vaihtoKrt == Extern.VAIHTOJEN_LKM) {
                    jaa.setEnabled(false);
                    pelaaKasi.setEnabled(false);

                    evaluoiKasi(poytakortit);
                    pisteet.laskePisteet(parsiKasiOhjetekstista());
                    paivitaPisteNaytto();
                    // Tarkasta näin lopussa, että onko peliä mahdollista jatkaa
                    if (pisteet.getPisteet() > 0) {
                        panos.setEnabled(true);
                        uusipeli.setEnabled(true);
                        tallenna.setEnabled(true);
                        statistiikka.setEnabled(true);
                    } else {
                        uusipeli.setEnabled(true);
                        alustaUusiPeli();
                        pisteet.nollaaPisteet();
                        paivitaPisteNaytto();
                        main.setOhjeTekstiAlue("Peli loppui.");
                    }
                    vaihtoKrt = 0;
                }
            } else if (ae.getActionCommand().equals("jaa")) {
                evaluoiKasi(poytakortit);
                pisteet.laskePisteet(parsiKasiOhjetekstista());
                paivitaPisteNaytto();
                vaihtoKrt = 0;

                jaa.setEnabled(false);
                pelaaKasi.setEnabled(false);
                tallenna.setEnabled(true);
                uusipeli.setEnabled(true);
                panos.setEnabled(true);
            } else if (ae.getActionCommand().equals("stats")) {
                String txt = "";

                txt += "----OTETUT MAAT----\n";
                txt += otetutMaat();
                txt += "----NOSTETTU----\n";
                txt += String.format("Pakassa: %d, Nostettu: %d\n",
                        pakka.jaljella(), pakka.nostettu());
                main.setOhjeTekstiAlue(txt);
            } else if (ae.getActionCommand().equals("top5")) {
                if (!top5handler.isShown()) {
                    top5handler.luePisteetTiedostosta();
                    top5handler.nayta();
                } else
                    top5handler.sulje();
            } else if (ae.getActionCommand().equals("tallenna")) {
                pisteet.tallennaPisteet();
                main.setOhjeTekstiAlue(String.format(
                        "Pisteet tallennettu %s tiedostoon",
                        pisteet.getTiedostoPolkuJaNimi()));
                tallenna.setEnabled(false);
            } else if (ae.getActionCommand().equals("panos")) {
                pisteet.vaihdaPanos();
                main.setOhjeTekstiAlue(pisteet.pisteytys());
                paivitaPisteNaytto();
            } else if (ae.getActionCommand().equals("uusipeli")) {
                alustaUusiPeli();
                vaihtoKrt = 0;
                pisteet.vahennaPisteita();
                jaa.setEnabled(true);
                pelaaKasi.setEnabled(true);
                statistiikka.setEnabled(true);
                panos.setEnabled(false);
                uusipeli.setEnabled(false);
                tallenna.setEnabled(false);
            }
            paivitaPisteNaytto();
        }
    }

    public String parsiKasiOhjetekstista()
    {
        int idx = 0;

        idx = main.getOhjeTeksti().indexOf(' ');
        if (idx > 0) {
            if (main.getOhjeTeksti().substring(0, idx).equals("Kaksi"))
                idx = main.getOhjeTeksti().indexOf(' ', idx + 1);
            return main.getOhjeTeksti().substring(0, idx);
        }
        return "";
    }

    public void paivitaPisteNaytto()
    {
        pistenaytto.setText(String.format("Pisteet %5d  |  Panos %5d",
                            pisteet.getPisteet(), pisteet.getPanos()));
        pistenaytto.repaint();
        pistenaytto.revalidate();
    }

    public void alustaUusiPeli()
    {
        kortitPane.removeAll();
        poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
        pakka = Pakka.uusiPakka();
        pakka.sekoita();
        alustaKortit();
        vaihtoKrt = 0;
        main.setOhjeTekstiAlue("");
    }

    public void vaihdaKortit(Kortti[] kortit)
    {
        for (int i = 0; i < poytakortit.length; i++) {
            if (poytakortit[i].getValinta() == false) {
                Point vanhasijainti = poytakortit[i].getSijainti();

                kortitPane.remove(poytakortit[i]);
                poytakortit[i] = pakka.otaKortti();
                if (poytakortit[i].getArvo() == 0) {
                    main.setOhjeTekstiAlue("Pakka tyhjä");
                    break;
                }
                poytakortit[i].setSijainti(vanhasijainti);
                kortitPane.add(poytakortit[i]);
            }
        }
        kortitPane.repaint(); // FIXME pistä poist loopista
        kortitPane.revalidate();
        vaihtoKrt++;
    }

    public String otetutMaat()
    {
        StringBuilder sb = new StringBuilder();

        for (String m : Extern.MAAT)
            sb.append(String.format("%s (%d kpl)\n", m,
                      pakka.getMaaOtettuCount(m)));

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
            tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.VARISUORA);
            main.setOhjeTekstiAlue(String.format("Värisuora (%d pistettä)",
                 pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.VARISUORA)));
            return;
        } else if (onVari) {
            main.setOhjeTekstiAlue(String.format("Väri (%d pistettä)",
                 pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.VARI)));
            tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.VARI);
        } else if (onSuora) {
            main.setOhjeTekstiAlue(String.format("Suora (%d pistettä)",
                 pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.SUORA)));
            tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.SUORA);
        } else {
            if (nsamaa > 0 && tkasiVaiKaksiParia != 5) {
                switch (nsamaa) {
                case 2:
                    main.setOhjeTekstiAlue(String.format("Pari (%d pistettä)",
                         pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.PARI)));
                    tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.PARI);
                    break;
                case 3:
                    main.setOhjeTekstiAlue(String.format("Kolmoset (%d pistettä)",
                         pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.KOLMOSET)));
                    tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.KOLMOSET);
                    break;
                case 4:
                    main.setOhjeTekstiAlue(String.format("Neloset (%d pistettä)",
                         pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.NELOSET)));
                    tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.NELOSET);
                    break;
                default:
                    break;
                }
            }

            if (tkasiVaiKaksiParia > 0) {
                switch (tkasiVaiKaksiParia) {
                case 2:
                    main.setOhjeTekstiAlue(String.format("Kaksi paria (%d pistettä)",
                         pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.KAKSIPARIA)));
                    tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.KAKSIPARIA);
                    break;
                case 5:
                    main.setOhjeTekstiAlue(String.format("Täyskäsi (%d pistettä)",
                         pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.TAYSKASI)));
                    tamanHetkinenKasi = new PokeriHanska(raahauspeli.PokeriHanska.TAYSKASI);
                    break;
                default:
                    break;
                }
            }

            if (main.getOhjeTeksti().equals("")) {
                int hai = 0;

                hai = tarkistaHai(kortit);
                main.setOhjeTekstiAlue(String.format("Hai %d (%d pistettä)",
                     hai, pisteet.getPistePanosKerroin(raahauspeli.PokeriHanska.HAI)));
            }
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
