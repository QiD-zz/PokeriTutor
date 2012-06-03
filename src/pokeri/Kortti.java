package pokeri;

import javax.swing.ImageIcon;


public class Kortti
{
    private String maa;
    private short arvo;
    private ImageIcon kuva;

    public Kortti(String s, short a)
    {
        maa = (maa != null) ? s : "";
        arvo = (a > 0) ? a : 0;
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

    public short getArvo()
    {
        return arvo;
    }

    public void setArvo(short a)
    {
        arvo = (a > 0) ? a : 0;
    }

}
