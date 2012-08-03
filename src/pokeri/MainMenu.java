package pokeri;

import java.awt.*;
import raahauspeli.RaahausPeliPaneeli;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
        ohjePaneeli.setLayout(null);
        JButton paluu = new JButton(Extern.ETUSIVU);
        paluu.addActionListener(tkuuntelija);
        paluu.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/8,
                                     Extern.KORKEUS_IKKUNA / 7));

        this.add(paaPaneeli, BorderLayout.CENTER);
        ohjeTekstiAlue= new JTextArea("OHJE");
        ohjeTekstiAlue.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 18));
        ohjeTekstiAlue.setPreferredSize(new Dimension(Extern.LEVEYS_IKKUNA/8*6,
                                     Extern.KORKEUS_IKKUNA / 7));
     //   ohjeTekstiAlue.setLayout(new FlowLayout(FlowLayout.LEFT));

        ohjePaneeli.add(paluu);
        ohjePaneeli.add(ohjeTekstiAlue);

        Insets insets = ohjePaneeli.getInsets();
        Dimension size = paluu.getPreferredSize();
        paluu.setBounds(insets.left, insets.top,
             size.width, size.height);

        size = ohjeTekstiAlue.getPreferredSize();
        ohjeTekstiAlue.setBounds(paluu.getWidth() + insets.left, insets.top,
             size.width, size.height);

        ohjeTekstiAlue.setWrapStyleWord(true);
        ohjeTekstiAlue.setEditable(false);
        ohjeTekstiAlue.setLineWrap(true);

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

    void setEtusivu() {
        ohjeTekstiAlue.setText("");
        paaPaneeli.removeAll();
        paaPaneeli.add(new EtusivuPaneeli(this));
        paaPaneeli.repaint();
        paaPaneeli.validate();
    }
}
