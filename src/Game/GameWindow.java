package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

    /** Klasa opisujaca wyglad i dzialanie rozgrywki*/
    public class GameWindow extends JFrame{

        /** Poziom rozgrywki */
        private final GameStatus gStatus = new GameStatus();
        /** Stworzone równanie */
        private GeneratingEquation equation;
        /** Odpowiedzi */
        private List answers = new ArrayList<>();
        /** Czas na odpowiedz */
        private Double time;
        /** Timer */
        private Timer timer;
        /** Odliczanie */
        private final Double[] timeLeft = {time};
        /** Lista przyciskow */
        private List<JButton> allButtons = new ArrayList<>();
        /** Punkty */
        private int points;
        /** Label z punktami */
        private JLabel pointsLabel;

    /**
     * Tworzenie ulozenia elementow skladajacych sie na gre
     * Funckja sluzaca do sprawdzenia czy wybrana zostala poprawna odpowiedz i w takim przypadku stworzenie kolejnego rownania
     * Stworzona funckja opisujaca dzialanie przyciskow
     * @param x szerokosc orkna
     * @param y wysokosc okna
     * @param time czas timera
     */

    public GameWindow(int x, int y, Double time){
        this.time = time;
        //rozkład gry
        JFrame gameFrame = new JFrame("game");
        JPanel answersPanel = new JPanel();
        JLabel equationLabel = new JLabel();
        pointsLabel = new JLabel();
        JPanel pointsPanel = new JPanel();
        JPanel buttonsPanel = new JPanel();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        gameFrame.setSize(x, y);
        gameFrame.setLocation(Constants.xCenter, Constants.yCenter);

        gameFrame.setResizable(false);
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GridLayout answersLayout = new GridLayout(1, 7, 10,10);
        answersPanel.setLayout(answersLayout);
        answersPanel.setMaximumSize(new Dimension(800, 100));

        GridLayout buttonsGroupLayout = new GridLayout(1, 3, 50, 50);
        buttonsPanel.setLayout(buttonsGroupLayout);
        buttonsPanel.setFont(new Font("Arial", Font.PLAIN, 30));
        buttonsPanel.setMaximumSize(new Dimension(400, 50));
        buttonsPanel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        JLabel levelLabel = new JLabel();

        GridLayout fakeLayout = new GridLayout(2, 1, 100, 100);
        JPanel fakeLabel = new JPanel();
        fakeLabel.setLayout(fakeLayout);
        fakeLabel.setMaximumSize(new Dimension(100, 100));
        JPanel fakeLabel2 = new JPanel();
        fakeLabel2.setLayout(fakeLayout);
        fakeLabel2.setMaximumSize(new Dimension(100, 200));

        nextLevel();
        // działanie gry (tworzenie równania i porównanie wybranej odpowiedzi z poprawną)
        for(int i = 0; i < 8; i++){
            JButton answerButton = new JButton(answers.get(i).toString());
            answerButton.setPreferredSize(new Dimension(50, 50));
            answerButton.addActionListener(e -> {
                if(Integer.parseInt(answerButton.getText()) == equation.getMissingNumber()){
                    gStatus.nextLevel();
                    Constants.level++;
                    timer.stop();

                    addPoints(time);

                    nextLevel();
                    equationLabel.setText(equation.getEq());
                    levelLabel.setText("Level: " + String.valueOf(Constants.level));
                    timer.start();
                }
                else{
                    endOfGame();
                }
            });
            allButtons.add(answerButton);
            answersPanel.add(answerButton);
        }

        equationLabel.setText(equation.getEq());
        equationLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        equationLabel.setVerticalTextPosition(SwingConstants.CENTER);
        equationLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        equationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        equationLabel.setVerticalAlignment(SwingConstants.CENTER);

        //opis przycisków timera
        Button getExitButton = new Button("Exit");
        getExitButton.addActionListener(e -> {
            this.setVisible(false);
            try {
                Window gw = new Window(Constants.gameWidth, Constants.gameHeight,Constants.xCenter, Constants.yCenter);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        getExitButton.setSize(50, 50);
        buttonsPanel.add(getExitButton);

        JLabel timeLabel = new JLabel();
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        ActionListener countDown = e -> {
            timeLeft[0] -= 100;
            SimpleDateFormat df = new SimpleDateFormat("ss:S");
            timeLabel.setText(df.format(timeLeft[0]));
            if (timeLeft[0] <= 0) {
                endOfGame();
            }
        };

        timer = new Timer(100, countDown);
        timer.start();

        JButton pauseButton = new JButton("Pause");
        pauseButton.addActionListener(e -> {
            if(timer.isRunning())
                timer.stop();
            else
                timer.start();
        });
        buttonsPanel.add(pauseButton);

        pointsLabel.setText("Points: " + points);

        GridLayout levelScoreLayout = new GridLayout(2, 1, 20, 20);
        pointsPanel.setLayout(levelScoreLayout);
        pointsPanel.add(pointsLabel);
        pointsPanel.add(levelLabel);

        levelLabel.setText("Level: " + String.valueOf(Constants.level));
        buttonsPanel.add(pointsPanel);

        mainPanel.add(answersPanel);
        mainPanel.add(fakeLabel);
        mainPanel.add(equationLabel);
        mainPanel.add(fakeLabel2);
        mainPanel.add(timeLabel);
        mainPanel.add(buttonsPanel);
        gameFrame.setContentPane(mainPanel);

        gameFrame.setVisible(true);
    }

    /** Dodawanie punktow za odpowiednie odpowiedzi ( ilosc pktow zalezy od czasu w jakim została wybrana odpowiedz)
     *
     * */
    private void addPoints(Double time) {
        if(timeLeft[0].compareTo(time / 2) > 0){
            points += 200;
            pointsLabel.setText("Points: " + points);
        }
        else if(timeLeft[0].compareTo(1000.0) < 0){
            points += 50;
            pointsLabel.setText("Points: " + points);
        }
        else{
            points += 100;
            pointsLabel.setText("Points: " + points);
        }
    }
    /** Brak odpowiedzi lub bledna odpowiedz
     *
     * */
    private void endOfGame() {
        timer.stop();
        for (JButton b : allButtons) {
            b.setEnabled(false);
        }
        showMessageDialog(null, "KONIEC GRY");
    }
    /** Tworzenie nowego rownania i zeruowanie timer
     *
     * */
    private void nextLevel() {
        timeLeft[0] = time;

        equation = new GeneratingEquation(0, 0, 0, 0);
        equation.getFirstNumber();
        equation.getSecondNumber();
        equation.Calculating();
        equation.setEmptyPlace();
        answers = equation.creatingAnswers();

        int i = 0;
        for (JButton b : allButtons) {
            b.setText(String.valueOf(answers.get(i++)));
        }
    }
}
