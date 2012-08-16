package pokeri;

public class Pisteytys
{
    private int pisteet;

    // Vakiot
    public final int ALKUPISTEET = 20;

    public Pisteytys()
    {
        pisteet = 0;
    }

    public void laskePisteet(String kasi)
    {
        int tienatutPst = 0;
        Object o = null;

        o = Extern.VOITTOPISTE.get(kasi);
        if (o == null)
            return;

        tienatutPst = (Integer) o;
    }

}
