package Game;

 import java.awt.Toolkit;
 import java.io.IOException;

/** Klasa glowna */
public class Main {

    /** Ladowanie okna menu */
     public static void main(String[] args) throws IOException {


         Window gw = new Window(Constants.gameWidth,Constants.gameHeight,Constants.xCenter,Constants.yCenter);
         gw.setVisible(true);
     }
 }
