package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Chord;
import cz.jcu.prf.uai.javamugs.logic.Game;
import cz.jcu.prf.uai.javamugs.logic.GameReport;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameController {
    private Stage stage;
    private Game game;
    private MediaPlayer mediaPlayer;
    private Chord pressedButtons;
    private String songURIstring;
    private ArrayList<BallAnimation> ballAnimations;
    private AnimationTimer mainCycle;
    private int[] highlightedStrings = new int[5];

    public Canvas canvas;
    public BorderPane rootContainer;
    public Label scoreLabel;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setSongURIstring(String songURIstring) {
        this.songURIstring = songURIstring;
    }

    public void start() {
        this.stage = (Stage) rootContainer.getScene().getWindow();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                mediaPlayer.stop();
                mediaPlayer = null;
                mainCycle.stop();
                mainCycle = null;
            }
        });
        //Key presses
        rootContainer.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case A:
                        pressedButtons.getChords()[0] = true;
                        break;
                    case S:
                        pressedButtons.getChords()[1] = true;
                        break;
                    case D:
                        pressedButtons.getChords()[2] = true;
                        break;
                    case K:
                        pressedButtons.getChords()[3] = true;
                        break;
                    case L:
                        pressedButtons.getChords()[4] = true;
                        break;
                }
                System.out.println(Arrays.toString(pressedButtons.getChords()));
            }
        });

        Media sound = new Media(songURIstring);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        //final long startNanoTime = System.nanoTime();
        pressedButtons = new Chord(false, false, false, false, false);

        ballAnimations = new ArrayList<BallAnimation>();
        mainCycle = new AnimationTimer() // 60 FPS
        {
            @Override
            public void handle(long currentNanoTime) {
                GameReport report = game.tick(mediaPlayer.getCurrentTime().toMillis(), pressedButtons);
                if(!report.getHitChord().isEmpty()) {
                    int ik = 1+1;
                }
                for (int i = 0; i < highlightedStrings.length; i++) {
                    if (report.getHitChord().getChords()[i])
                        highlightedStrings[i] = 20;
                    if (report.getMissChord().getChords()[i])
                        highlightedStrings[i] = -20;
                }
                if (!report.getChordToDraw().isEmpty()) {
                    boolean[] chordArr = report.getChordToDraw().getChords();
                    for (int i = 0; i < chordArr.length; i++) {
                        if (chordArr[i]) {
                            ballAnimations.add(new BallAnimation(i, game.getTimeOffset(), mediaPlayer.getCurrentTime().toMillis()));
                        }
                    }
                }
                renderCanvas(report);
                pressedButtons = new Chord(false, false, false, false, false);
            }
        };
        mainCycle.start();


    }

    private void renderCanvas(GameReport report) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        Random random = new Random();
        for (int i = 0; i < highlightedStrings.length; i++) {
            //Strings
            gc.setLineWidth(5);
            double x = 250 + i * 75;
            if (highlightedStrings[i] > 0) {
                gc.setStroke(Color.web("#00FF00"));
                highlightedStrings[i]--;
                int randomInt = random.nextInt(6);
                randomInt -= randomInt/2;
                x += randomInt;
            } else if (highlightedStrings[i] < 0) {
                gc.setStroke(Color.web("#FF0000"));
                highlightedStrings[i]++;
            } else
                gc.setStroke(Color.BLACK);
            gc.strokeLine(x, 0, x, canvas.getHeight());
            //Circles
            gc.setFill(CloneHeroColors.COLORARRAY[i]);
            gc.setLineWidth(3);
            x = 225 + i * 75;
            gc.fillOval(x, canvas.getHeight() - 75, 50, 50);
            gc.strokeOval(x, canvas.getHeight() - 75, 50, 50);
        }
        scoreLabel.setText(String.valueOf(report.getScore()) + "\n" + String.valueOf(report.getMultiplier() + "x"));
        for (int i = 0; i < ballAnimations.size(); i++) {
            if (ballAnimations.get(i).isFinished()) {
                ballAnimations.remove(i);
                i--;
            } else {
                ballAnimations.get(i).animate(mediaPlayer.getCurrentTime().toMillis());
            }
        }
    }

    private class BallAnimation {
        private int color;
        private double y;
        private boolean finished;
        private double startTime;
        private double endTime;

        public BallAnimation(int color, double timeOffset, double startTime) {
            this.color = color;
            this.y = 0;
            finished = false;
            this.startTime = startTime;
            this.endTime = startTime + timeOffset;
        }

        public void animate(double currentTime) {
            double ratio = (currentTime - startTime) / (endTime - startTime);
            this.y = (canvas.getHeight() - 75) * ratio;
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(CloneHeroColors.COLORARRAY[color]);
            gc.fillOval(225 + 75 * color, y, 50, 50);
            if (currentTime >= endTime) {
                finished = true;
            }
        }

        public boolean isFinished() {
            return finished;
        }
    }
}

