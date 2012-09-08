package pokeri;

import java.awt.*;
import raahauspeli.RaahausPeliPaneeli;
import opetus.OpiTuntemaanHanskat;
import javax.swing.*;
import opetus.MonivalintaTaulu;


/*
 *  +-----------------------+
 *  |[menu]                 |
 *  +-----------------------+
 *  |      paaPaneeli       |
 *  |                       |
 *  |                       |
 *  |                       |
 *  +-----------------------+
 *  |      ohjePaneeli      |
 *  +-----------------------+
 */
public class MainMenu extends JFrame
{
    private JMenuBar menubar;
    private JPanel paaPaneeli;
    private JPanel ohjePaneeli;
    private JTextArea ohjeTekstiAlue;
    private Tapahtumakuuntelija tkuuntelija;
    private JScrollPane ohjePane;
    private JButton paluu;
    private JButton lopeta;

    public MainMenu()
    {
        super("TaoTao menee metsään!");
        tkuuntelija = new Tapahtumakuuntelija(this);

        alustaElementit();
    }

    private void alustaMenu()
    {
        JMenu menu;
        JMenuItem mitem;

        menu = new JMenu("Tiedosto");

        mitem = new JMenuItem(Extern.OPETUS);
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem(Extern.RAAHAUSPELI);
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem(Extern.MONIVALINTAPELI);
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem(Extern.KORTTIPELI);
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem("Lopeta");
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        menubar = new JMenuBar();
        menubar.add(menu);
    }

    private void alustaOhjePaneeli()
    {
        ohjePaneeli = new JPanel();
        ohjeTekstiAlue = new JTextArea("Ohjeistus ja tilastot käyttävät tätä tilaa");
        paluu = new JButton(Extern.ETUSIVU);
        lopeta = new JButton(Extern.LOPETA);
        lopeta.setBackground(Extern.LOPETATAUSTA);
        lopeta.setOpaque(true);
        Dimension paluuSize;
        Insets insets;

        paluu.setBackground(new Color(220, 120, 120));
        paluu.setOpaque(true);
        paluu.setVisible(false);
        ohjePaneeli.setBackground(Color.white);
        ohjePaneeli.setLayout(null);
        ohjeTekstiAlue.setWrapStyleWord(true);
        ohjeTekstiAlue.setEditable(false);
        ohjeTekstiAlue.setLineWrap(true);

        ohjeTekstiAlue.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
        ohjeTekstiAlue.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA /
                                        8 * 7, Extern.KORKEUS_IKKUNA / 7));
        ohjePaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                             Extern.KORKEUS_IKKUNA / 7));

        paluu.addActionListener(tkuuntelija);
        paluu.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA / 8,
                               Extern.KORKEUS_IKKUNA / 7));
        lopeta.addActionListener(tkuuntelija);

        insets = ohjePaneeli.getInsets();
        paluuSize = paluu.getPreferredSize();
        paluu.setBounds(insets.left, insets.top, paluuSize.width,
                        paluuSize.height);
        lopeta.setBounds(insets.left, insets.top, paluuSize.width,
                        paluuSize.height);
        paluuSize = ohjeTekstiAlue.getPreferredSize();
        ohjeTekstiAlue.setBounds(paluu.getWidth() + insets.left, insets.top,
                                 paluuSize.width, paluuSize.height);
        ohjePane = new JScrollPane(ohjeTekstiAlue);
        ohjePane.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA /
                                        8 * 7, Extern.KORKEUS_IKKUNA / 7));
        ohjePane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        ohjePane.setBounds(paluu.getWidth() + insets.left, insets.top,
                                 paluuSize.width, paluuSize.height);
        ohjeTekstiAlue.setBackground(Extern.OHJETAUSTA);
        ohjeTekstiAlue.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA /
                                        8 * 7, Extern.KORKEUS_IKKUNA / 4));
        ohjePaneeli.add(paluu);
        ohjePaneeli.add(lopeta);
        ohjePaneeli.add(ohjePane);
    }

    private void alustaElementit()
    {
        paaPaneeli = new JPanel();

        setLayout(new BorderLayout(2, 0));
        alustaMenu();
        alustaOhjePaneeli();
        // Varsinaiset asettelut
        paaPaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                                    Extern.KORKEUS_IKKUNA / 3));
        paaPaneeli.addMouseListener(tkuuntelija);
        paaPaneeli.setBackground(Extern.PASTELLITAUSTA);

        add(paaPaneeli, BorderLayout.CENTER);
        add(new JScrollPane(ohjePaneeli), BorderLayout.SOUTH);
        paaPaneeli.add(new EtusivuPaneeli(this));

        setJMenuBar(menubar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public JPanel getPaapaneeli()
    {
        return paaPaneeli;
    }

    public String getOhjeTeksti()
    {
        return ohjeTekstiAlue.getText();
    }

    public void setOhjeTekstiAlue(String teksti)
    {
        ohjeTekstiAlue.setText(teksti);
    }

    public void setRaahauspeli()
    {
        paluu.setVisible(true);
        lopeta.setVisible(false);
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        ohjeTekstiAlue.setText("Tehtävänäsi on raahat kädet oikeaan \n"
                + "järjestykseen mahdollisimman nopeasti. \n"
                + "Järjestämissuunta vaihtuu ja se ilmoitetaan aina uudestaan "
                + "uuden pelin alkaessa.");
        paaPaneeli.add(new RaahausPeliPaneeli());
        paaPaneeli.setBackground(Extern.KADETJARJTAUSTA);
        paaPaneeli.repaint();
        paaPaneeli.revalidate();
    }

    public void setPokeripeli()
    {
        paluu.setVisible(true);
        lopeta.setVisible(false);
        ohjeTekstiAlue.setText("Aloita valitsemalla Uusi peli\n"
                + "Kortin valinta tapahtuu korttia klikkaamalla");
        paaPaneeli.removeAll();
        paaPaneeli.add(new KorttiPaneeli(this));
        paaPaneeli.setBackground(Extern.POKERIPELITAUSTA);
        paaPaneeli.repaint();
        paaPaneeli.revalidate();
    }

    public void setOpetuspaneeli()
    {
        paluu.setVisible(true);
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new OpiTuntemaanHanskat(this));
        paaPaneeli.setBackground(Extern.POKERIKADETTAUSTA);
        paaPaneeli.repaint();
        paaPaneeli.revalidate();
    }

    public void setMonivalinta()
    {
        paluu.setVisible(true);
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new MonivalintaTaulu(this));
        paaPaneeli.setBackground(Extern.POKERIPELITAUSTA);
        paaPaneeli.repaint();
        paaPaneeli.revalidate();
        paaPaneeli.setBackground(Extern.TAIDOTTAUSTA);
    }

    public void setEtusivu()
    {
        paluu.setVisible(false);
        lopeta.setVisible(true);
        lopeta.setBackground(Extern.LOPETATAUSTA);
        lopeta.setOpaque(true);
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new EtusivuPaneeli(this));
        paaPaneeli.repaint();
        paaPaneeli.revalidate();
        paaPaneeli.setBackground(Extern.PASTELLITAUSTA);
    }

}
