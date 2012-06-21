package pokeri;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.RoundRectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;


public class Kortti extends JComponent implements MouseListener
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

    public Kortti(String s, int a, Point p)
    {
        super();
        maa = (maa != null && Arrays.asList(Extern.MAAT).contains(s)) ? s : "";
        arvo = (a > 0) ? a : 0;
        sijainti = (p != null) ? p : new Point();
        x = sijainti.getX();
        y = sijainti.getY();
        addMouseListener(this);
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
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(x, y, LEVEYS, KORKEUS, ARCW, ARCH));
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(1.0f));
        g2.draw(new RoundRectangle2D.Double(x, y, LEVEYS, KORKEUS, ARCW, ARCH));

        repaint();
    }

    public boolean isInArea(Point p)
    { // XXX Vaatinee vielä tarkastusta, pää piiputta rumasti tässä vaiheessa jo
        boolean isIn = false;
        double dx = LEVEYS / 2;
        double dy = KORKEUS / 2;
        double xl = sijainti.getX() - dx;
        double xr = sijainti.getX() + dx;
        double yt = sijainti.getY() - dy;
        double yb = sijainti.getY() + dy;

        if (p.getX() < xl || p.getX() > xr)
            isIn = false;
        if (p.getY() < yt || p.getY() > yb)
            isIn = false;

        return isIn;
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        if (isInArea(me.getPoint()))
            System.out.println("KORTTIA PAINETTU");
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
    }

    @Override
    public void mouseEntered(MouseEvent me)
    {
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
    }

}
