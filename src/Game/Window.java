package Game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/** Klasa do tworzenia okna menu */
public class Window extends JFrame {

    /** Poczatkowa tresc stringa level*/
    private String level = "";
    /** Parametry timera */
    private Double timeLevel = 20000.0;

    /** Tworzenie okna startowego
     * @param width szerokosc
     * @param height wysokosc
     * @param x wspolrzedna x dla srodka okna
     * @param y wspolrzedna y dla srodka okna
     * */
    public Window(int width, int height, int x, int y) throws IOException {
        super(); //wywołaj konstruktor klasy nadrzędnej - utwórz okno
        JFrame mainFrame = new JFrame();
        mainFrame.setSize(width, height); //ustaw wymiary okna
        mainFrame.setLocation(x, y); //ustaw pozycję okna
        mainFrame.setResizable(false); //zablokuj możliwość zmian rozmiaru okna
        mainFrame.setVisible(true); //pokaż okno

        //Ułożenie i stowrzenie przycisków
        JPanel buttonsPanel = new JPanel();
        GridLayout buttonsLayout = new GridLayout(3, 1, 100, 100);
        buttonsPanel.setLayout(buttonsLayout);
        JButton startButton = new JButton("Start Game");
        JButton levelButton = new JButton("Easy");
        JButton exitButton = new JButton("Exit");

        //Rozpoczęcie rozgrywki
        startButton.addActionListener(e -> {
            this.setVisible(false);
            new GameWindow(Constants.gameWidth, Constants.gameHeight, timeLevel);
        });

        exitButton.addActionListener((event) -> System.exit(0)); // Wyłączenie gry


        // Ustawienie trybów gry oraz zmiana czasu na równanie
        levelButton.addActionListener(e -> {
            if (levelButton.getText().equals("Easy")) {
                levelButton.setText("Medium");
                level = "Medium";
                timeLevel = 10000.0; // 10s
            } else if (levelButton.getText().equals("Medium")) {
                levelButton.setText("Hard");
                level = "Hard";
                timeLevel = 5000.0; // 5s
            } else if (levelButton.getText().equals("Hard")) {
                levelButton.setText("Easy");
                level = "Easy";
                timeLevel = 20000.0; // 20s
            }
        });

        // Dodanie przycisków
        buttonsPanel.add(startButton);
        buttonsPanel.add(levelButton);
        buttonsPanel.add(exitButton);

        mainFrame.add(buttonsPanel);
        mainFrame.setVisible(true); //pokaż okno
    }
}
