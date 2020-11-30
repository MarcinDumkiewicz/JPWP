package Game;

import java.awt.*;

/** Utworzenie stalych paramtetrow */
public class Constants {
    /** Szerokosc okna */
    public static int gameWidth = 800;
    /** Wysokosc okna */
    public static int gameHeight = 600;
    /** Szerokosc ekranu */
    public static int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
    /** Szerokosc ekranu */
    public static int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    /** Wspolrzedne srodka*/
    public static int xCenter = (screenWidth - gameWidth)/2;
    /** Wspolrzedne srodka */
    public static int yCenter = (screenHeight - gameHeight)/2;
    /** Poziom startowy */
    public static int level = 1;
}
