package pokeri;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


public class KorttiPaneeli extends JPanel
{
    private JButton pelaaKasi;
    private JButton uusipeli;
    private JPanel  kortitPane;
    private JPanel  toiminnotPane;
    private JTextArea tilastot;
    private KorttipaneelinKuuntelija kuuntelija;
    private Pakka    pakka;
    private Kortti[] poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];

    public KorttiPaneeli(JTextArea til)
    {
        kortitPane = new JPanel();
        toiminnotPane = new JPanel();
        tilastot = til;

        // Layout määrittelyt
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        kortitPane.setLayout(new FlowLayout(FlowLayout.CENTER));
        toiminnotPane.setLayout(new FlowLayout(FlowLayout.CENTER));

        pakka = Pakka.getPakka();
        alustaKortit();

        // Toiminnot
        pelaaKasi = new JButton("Pelaa käsi");
        pelaaKasi.setActionCommand("pelaakasi");
        uusipeli = new JButton("Uusi peli");
        uusipeli.setActionCommand("uusipeli");
        kuuntelija = new KorttipaneelinKuuntelija();
        pelaaKasi.addActionListener(kuuntelija);
        uusipeli.addActionListener(kuuntelija);
        toiminnotPane.add(pelaaKasi);
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
        if (k.getValinta() == false) {
            k.toggleValinta();
            k.repaint();
        }
    }

    public class KorttipaneelinKuuntelija implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            if (ae.getActionCommand().equals("uusipeli")) {
                kortitPane.removeAll();
                poytakortit = null;
                poytakortit = new Kortti[Extern.KORTTEJA_POYDALLA];
                pakka = Pakka.uusiPakka();
                pakka.sekoita();
                alustaKortit();
            } else if (ae.getActionCommand().equals("pelaakasi")) {
                boolean vari = false;
                int suora = 0;

                System.out.println(String.format("PAKKA: \n%s", pakka));
                tilastot.setText(String.format("Pakassa: %d, Nostettu: %d",
                                 pakka.jaljella(), pakka.nostettu()));
                vari = tarkistaVari(poytakortit);
                suora = tarkistaSuora(poytakortit);
                System.out.println(String.format("Väri: %s", vari));
                System.out.println(String.format("Max suora: %d", suora));
            }
        }
    }

    public void testaaOtetutMaat() // XXX Testimetodi, poista jossain välissä
    {
        for (String s : Extern.MAAT)
        System.out.println(String.format("%s %d", s, pakka.getMaaOtettuCount(s)));
    }

    public boolean tarkistaVari(Kortti[] kortit)
    {
        for (int i = 1; i < kortit.length; i++) {
            Kortti k1 = kortit[i - 1];
            Kortti k2 = kortit[i];

            if (k1.compareVari(k2) == false)
                return false;
        }
        return true;
    }

    public int tarkistaSuora(Kortti[] kortit)
    {
        int nykParas = 0;
        int maxSuora = 0;
        Kortti[] tmp = new Kortti[kortit.length];

        System.arraycopy(kortit, 0, tmp, 0, tmp.length);
        Arrays.sort(tmp);
        for (int i = 1; i < tmp.length; i++) {
            Kortti k1 = tmp[i - 1];
            Kortti k2 = tmp[i];

            if ((k1.getArvo() + 1) == k2.getArvo()) {
                nykParas++;
                if (nykParas > maxSuora)
                    maxSuora = nykParas;
            } else
                nykParas = 0;
        }
        // Ei ota huomioon sitä, että parikin on jo kahden suora, joten
        // korjataan se tässä
        if (maxSuora > 0)
            maxSuora++;

        return maxSuora;
    }

}
