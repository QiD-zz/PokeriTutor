package pokeri;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.imageio.ImageIO;
import javax.swing.JComponent;


public class Kortti extends JComponent
{
    private String maa;
    private int arvo;
    private double x;
    private double y;
    private BufferedImage kuva;
    private Point sijainti;
    public final double LEVEYS = 100;
    public final double KORKEUS = 150;
    public final double ARCW = 15;
    public final double ARCH = 15;

    public Kortti(String s, int a, Point p)
    {
        this.setPreferredSize(new Dimension(120, 200));
        maa = (s != null && Arrays.asList(Extern.MAAT).contains(s)) ? s : "";
        arvo = (a > 0) ? a : 0;
        sijainti = (p != null) ? p : new Point();
        x = sijainti.getX();
        y = sijainti.getY();

        try {
            asetaKuva();
        } catch (IOException ioe) {
            System.out.println(String.format(
                    "Kortin kuvan lisääminen epäonnistui: %s",
                    ioe.getStackTrace()));
        }
    }

    private void asetaKuva() throws IOException
    {
        if (maa.equalsIgnoreCase("ruutu")) {
            kuva = ImageIO.read(new File("images/diamond.png"));
        } else if (maa.equalsIgnoreCase("hertta")) {
            kuva = ImageIO.read(new File("images/heart.png"));
        } else if (maa.equalsIgnoreCase("pata")) {
            kuva = ImageIO.read(new File("images/ace.png"));
        } else if (maa.equalsIgnoreCase("risti")) {
            kuva = ImageIO.read(new File("images/club.png"));
        }
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
        sijainti = (p != null) ? p : new Point(5, 5);
    }

    /**
     *  Jos @param <b>k<b> on yhtäsuuri arvoltaan kuin verrattava, palauta 0
     *  Jos @param <b>k<b> on pienempi, palauta -1, muutoin 1.
     */
    public int compareTo(Kortti k)
    {
        if (k.getArvo() == arvo)
            return 0;
        return (k.getArvo() < arvo) ? -1 : 1;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int img_w = 0;
        int img_h = 0;
        BufferedImage kuva2;

        img_w = kuva.getWidth() + 5;
        img_h = kuva.getHeight() + 5;

        g2.setPaint(Color.white);
        g2.fill(new RoundRectangle2D.Double(x, y, LEVEYS, KORKEUS, ARCW, ARCH));
        g2.setPaint(Color.black);
        g2.setStroke(new BasicStroke(1.0f));
        g2.draw(new RoundRectangle2D.Double(x, y, LEVEYS, KORKEUS, ARCW, ARCH));

        g.drawImage(kuva, (int) x + 5, (int) y + 5, null);
        kuva2 = rotate(kuva, Math.toRadians(180));
        g.drawImage(kuva2, (int) x + (int) LEVEYS - img_w, (int) y + (int) KORKEUS - img_h, null);
        g.drawString(Integer.toString(arvo), (int)x + (int)(LEVEYS / 2) - 5,
                                             (int)y + (int)(KORKEUS / 2));
    }

    public boolean isInArea(Point p)
    { // XXX Vaatinee vielä tarkastusta, pää piiputta rumasti tässä vaiheessa jo
        boolean isIn = true;
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

    public BufferedImage rotate(BufferedImage image, double angle)
    {
        int h = 0;
        int w = 0;
        int newh = 0;
        int neww = 0;
        double cos = 0.0;
        double sin = 0.0;
        GraphicsEnvironment ge;
        GraphicsDevice dev;
        GraphicsConfiguration gc;
        BufferedImage result;

        sin = Math.abs(Math.sin(angle));
        cos = Math.abs(Math.cos(angle));
        w = image.getWidth();
        h = image.getHeight();
        neww = (int) Math.floor(w * cos + h * sin);
        newh = (int) Math.floor(h * cos + w * sin);
        ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        dev = ge.getDefaultScreenDevice();
        gc = dev.getDefaultConfiguration();
        result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);

        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(angle, (double)w / 2, (double)h / 2);
        g.drawRenderedImage(image, null);
        g.dispose();

        return result;
    }

}
