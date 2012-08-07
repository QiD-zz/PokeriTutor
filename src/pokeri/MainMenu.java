package pokeri;

import java.awt.*;
import raahauspeli.RaahausPeliPaneeli;
import opetus.OpiTuntemaanHanskat;
import javax.swing.*;


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
    private RaahausPeliPaneeli raahausPeli;
    private Tapahtumakuuntelija tkuuntelija;

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

        mitem = new JMenuItem(Extern.RAAHAUSPELI);
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem(Extern.KORTTIPELI);
        mitem.addActionListener(tkuuntelija);
        menu.add(mitem);

        mitem = new JMenuItem(Extern.OPETUS);
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
        ohjeTekstiAlue = new JTextArea("Ohjeet ja tilastot " +
                                       "käyttävät tätä tilaa");
        JButton paluu = new JButton(Extern.ETUSIVU);
        Dimension paluuSize;
        Insets insets;

        ohjePaneeli.setBackground(Color.white);
        ohjePaneeli.setLayout(null);
        ohjeTekstiAlue.setWrapStyleWord(true);
        ohjeTekstiAlue.setEditable(false);
        ohjeTekstiAlue.setLineWrap(true);

        ohjeTekstiAlue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        ohjeTekstiAlue.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA /
                                        8 * 6, Extern.KORKEUS_IKKUNA / 7));
        ohjePaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                             Extern.KORKEUS_IKKUNA / 7));

        paluu.addActionListener(tkuuntelija);
        paluu.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA / 8,
                               Extern.KORKEUS_IKKUNA / 7));

        ohjePaneeli.add(paluu);
        ohjePaneeli.add(ohjeTekstiAlue);

        insets = ohjePaneeli.getInsets();
        paluuSize = paluu.getPreferredSize();
        paluu.setBounds(insets.left, insets.top, paluuSize.width,
                        paluuSize.height);
        paluuSize = ohjeTekstiAlue.getPreferredSize();
        ohjeTekstiAlue.setBounds(paluu.getWidth() + insets.left, insets.top,
                                 paluuSize.width, paluuSize.height);
    }

    private void alustaElementit()
    {
        paaPaneeli = new JPanel();
        raahausPeli = new RaahausPeliPaneeli();

        setLayout(new BorderLayout(2, 0));
        alustaMenu();
        alustaOhjePaneeli();
        // Varsinaiset asettelut
        paaPaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                                    Extern.KORKEUS_IKKUNA / 3));
        paaPaneeli.addMouseListener(tkuuntelija);

        add(paaPaneeli, BorderLayout.CENTER);
        add(new JScrollPane(ohjePaneeli), BorderLayout.SOUTH);
        paaPaneeli.add(new EtusivuPaneeli(this));

        setJMenuBar(menubar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pack();
    }

    public Object getPaapaneeli()
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
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new RaahausPeliPaneeli());
        paaPaneeli.repaint();
        paaPaneeli.validate();
    }

    public void setPokeripeli()
    {
        ohjeTekstiAlue.setText("Aloita valitsemalla Uusi peli");
        paaPaneeli.removeAll();
        paaPaneeli.add(new KorttiPaneeli(this));
        paaPaneeli.repaint();
        paaPaneeli.validate();
    }

    public void setOpetuspaneeli()
    {
          ohjeTekstiAlue.setText("");
          paaPaneeli.removeAll();
          paaPaneeli.add(new OpiTuntemaanHanskat(this));
          paaPaneeli.repaint();
          paaPaneeli.validate();
    }

    public MainMenu getMainMenu() // FIXME Onko tälle käyttöä?
    {
        return this;
    }

    void setEtusivu()
    {
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new EtusivuPaneeli(this));
        paaPaneeli.repaint();
        paaPaneeli.validate();
    }

}
