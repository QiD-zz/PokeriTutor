package pokeri;

import raahauspeli.RaahausPeliPaneeli;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import opetus.OpiTuntemaanHanskat;


/*
 *  +---------+-------------+
 *  |[menu]                 |
 *  +-----------------------+
 *  |      paaPaneeli       |
 *  |                       | }--.
 *  |                       |     `-   `wrapper` sitoo paaPaneeli
 *  |                       |      .´¨  ja ohjePaneelin yhteen
 *  +-----------------------+    .´
 *  |      ohjePaneeli      | }-´
 *  +-----------------------+
 */
public class MainMenu extends JFrame
{
    private JMenuBar menubar;
    private JPanel paaPaneeli;
    private JPanel ohjePaneeli;
    private JPanel wrapper;
    private JTextArea ohjeTekstiAlue;
    private RaahausPeliPaneeli raahausPeli;
    private Tapahtumakuuntelija tkuuntelija;
    private final String leiska[] = { BorderLayout.EAST, BorderLayout.WEST,
                                      BorderLayout.SOUTH, BorderLayout.NORTH,
                                      BorderLayout.CENTER };

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

    private void alustaElementit()
    {
        paaPaneeli = new JPanel();
        ohjePaneeli = new JPanel();
        wrapper = new JPanel();
        raahausPeli = new RaahausPeliPaneeli();

        this.setLayout(new BorderLayout(2, 0));
        alustaMenu();
        // Varsinaiset asettelut
        paaPaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                                    Extern.KORKEUS_IKKUNA / 3));
        paaPaneeli.setBackground(Color.lightGray);
        paaPaneeli.addMouseListener(tkuuntelija);
        ohjePaneeli.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA,
                                     Extern.KORKEUS_IKKUNA / 7));
        ohjePaneeli.setBackground(Color.white);

        this.add(paaPaneeli, BorderLayout.CENTER);
        ohjeTekstiAlue= new JTextArea("OHJE");
        ohjeTekstiAlue.setColumns(50);
        ohjeTekstiAlue.setWrapStyleWord(true);
        ohjeTekstiAlue.setEditable(false);
        ohjeTekstiAlue.setLineWrap(true);
        ohjePaneeli.add(ohjeTekstiAlue);
        JScrollPane jsc = new JScrollPane(ohjePaneeli);
        this.add(jsc, BorderLayout.SOUTH);

        paaPaneeli.add(new EtusivuPaneeli(this));

        this.setJMenuBar(menubar);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
    }

    public Object getPaapaneeli()
    {
        return paaPaneeli;
    }

    public void setOhjeTekstiAlue(String teksti)
    {
        ohjeTekstiAlue.setText(teksti);
    }

    public void setRaahauspeli() {
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new RaahausPeliPaneeli());
        paaPaneeli.repaint();
        paaPaneeli.validate();
    }

    public void setPokeripeli()
    {
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new KorttiPaneeli());
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

}
