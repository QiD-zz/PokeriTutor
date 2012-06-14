package pokeri;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Kortti extends JComponent implements Comparable
{
    private String maa;
    private int arvo;
    private double x;
    private double y;
    private JLabel kuva;
    private Point sijainti;
    private final double LEVEYS = 100;
    private final double KORKEUS = 150;
    private final double ARCW = 15;
    private final double ARCH = 15;
    private final String[] maat = {"ruutu", "hertta", "pata", "risti"};

    public Kortti(String s, int a, Point p)
    {
        maa = (maa != null && Arrays.asList(maat).contains(s)) ? s : "";
        arvo = (a > 0) ? a : 0;
        sijainti = (p != null) ? p : new Point();
        x = sijainti.getX();
        y = sijainti.getY();
        try {
            asetaKuva();
        } catch (IOException ioe) {
            System.out.println(String.format("Kortin kuvan lisääminen epäonnistui: %s", ioe.getStackTrace()));
        }
    }

    private void asetaKuva() throws IOException
    {
        if (maa.equalsIgnoreCase("ruutu"))
            kuva = new JLabel(new ImageIcon(ImageIO.read(new File("images/diamond.png"))));
        else if (maa.equalsIgnoreCase("hertta"))
            kuva = new JLabel(new ImageIcon(ImageIO.read(new File("images/heart.png"))));
        else if (maa.equalsIgnoreCase("pata"))
            kuva = new JLabel(new ImageIcon(ImageIO.read(new File("images/ace.png"))));
        else if (maa.equalsIgnoreCase("risti"))
            kuva = new JLabel(new ImageIcon(ImageIO.read(new File("images/club.png"))));

        if (kuva != null)
            add(kuva);
    }

    public String getMaa()
    {
        return maa;
    }

    public void setMaa(String s)
    {
       maa = (maa != null) ? s : "";
    }

    public int getArvo()
    {
        return arvo;
    }

    public void setArvo(int a)
    {
        arvo = (a > 0) ? a : 0;
    }

    public Point getSijainti()
    {
        return sijainti;
    }

    public void setSijainti(Point p)
    {
        sijainti = (p != null) ? p : new Point();
    }

    @Override
    /**
     *  Jos @param <b>k<b> on yhtäsuuri arvoltaan kuin verrattava, palauta 0
     *  Jos @param <b>k<b> on pienempi, palauta -1, muutoin 1.
     */
    public int compareTo(Object k)
    {
        Kortti kk = null;

        if (!(k instanceof Kortti))
            return -5;

        kk = (Kortti) k;
        if (kk.getArvo() == arvo)
            return 0;
        return (kk.getArvo() < arvo) ? -1 : 1;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(x, y, LEVEYS, KORKEUS, ARCW, ARCH));
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(1.0f));
        g2.draw(new RoundRectangle2D.Double(x, y, LEVEYS, KORKEUS, ARCW, ARCH));

        repaint();
    }

}
