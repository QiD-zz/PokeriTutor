package pokeri;

import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.util.Arrays;
import javax.swing.ImageIcon;


public class Kortti extends RoundRectangle2D.Double implements Comparable
{
    private String maa;
    private int arvo;
    private double x;
    private double y;
    private ImageIcon kuva;
    private Point sijainti;
    private final double LEVEYS = 50;
    private final double KORKEUS = 150;
    private final double ARCW = 5;
    private final double ARCH = 5;
    private final String[] maat = {"ruutu", "hertta", "pata", "risti"};

    public Kortti(String s, int a, Point p)
    {
        maa = (maa != null && Arrays.asList(maat).contains(s)) ? s : "";
        arvo = (a > 0) ? a : 0;
        sijainti = (p != null) ? p : new Point();
        x = sijainti.getX();
        y = sijainti.getY();
        kuva = null;
    }

    private void asetaKuva()
    {
        if (maa.equalsIgnoreCase("ruutu"))
            kuva = null;
        else if (maa.equalsIgnoreCase("hertta"))
            kuva = null;
        else if (maa.equalsIgnoreCase("pata"))
            kuva = null;
        else if (maa.equalsIgnoreCase("risti"))
            kuva = null;
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

    // Toteutetut RoundRectangle2D metodit
    @Override
    public double getArcWidth()
    {
        return ARCW;
    }

    @Override
    public double getArcHeight()
    {
        return ARCH;
    }

    @Override // Ei toteuteta, koska pyöristys ja koko on vakioita -> ei mahdollista muuttaa
    public void setRoundRect(double d, double d1, double d2, double d3, double d4, double d5)
    {
        return;
    }

    @Override
    public double getX()
    {
        return x;
    }

    @Override
    public double getY()
    {
        return y;
    }

    @Override
    public double getWidth()
    {
        return LEVEYS;
    }

    @Override
    public double getHeight()
    {
        return KORKEUS;
    }

    @Override
    public boolean isEmpty()
    {
        return (x <= 0 && y <= 0) ? true : false;
    }

    @Override
    public Rectangle2D getBounds2D()
    {
        double x1 = x - (LEVEYS / 2);
        double x2 = LEVEYS;
        double y1 = y - (KORKEUS / 2);
        double y2 = KORKEUS;

        return new Rectangle2D.Double(x, y, LEVEYS, KORKEUS);
    }

}
