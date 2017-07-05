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
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class GameController {
    private Stage stage;
    private Game game;
    private MediaPlayer mediaPlayer;
    private Chord pressedButtons;

    public Canvas canvas;
    public BorderPane rootContainer;

    public void setGame(Game game) {
        this.game = game;
    }

    public void start() {
        this.stage = (Stage) rootContainer.getScene().getWindow();
        GraphicsContext gc = canvas.getGraphicsContext2D();
        //Strings
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(250, 0, 250, canvas.getHeight());
        gc.strokeLine(325, 0, 325, canvas.getHeight());
        gc.strokeLine(400, 0, 400, canvas.getHeight());
        gc.strokeLine(475, 0, 475, canvas.getHeight());
        gc.strokeLine(550, 0, 550, canvas.getHeight());
        //Circles
        gc.setFill(CloneHeroColors.RED);
        gc.setLineWidth(3);
        gc.fillOval(225, canvas.getHeight() - 75, 50, 50);
        gc.strokeOval(225, canvas.getHeight() - 75, 50, 50);
        gc.setFill(CloneHeroColors.YELLOW);
        gc.fillOval(300, canvas.getHeight() - 75, 50, 50);
        gc.strokeOval(300, canvas.getHeight() - 75, 50, 50);
        gc.setFill(CloneHeroColors.GREEN);
        gc.fillOval(375, canvas.getHeight() - 75, 50, 50);
        gc.strokeOval(375, canvas.getHeight() - 75, 50, 50);
        gc.setFill(CloneHeroColors.BLUE);
        gc.fillOval(450, canvas.getHeight() - 75, 50, 50);
        gc.strokeOval(450, canvas.getHeight() - 75, 50, 50);
        gc.setFill(CloneHeroColors.MAGENTA);
        gc.fillOval(525, canvas.getHeight() - 75, 50, 50);
        gc.strokeOval(525, canvas.getHeight() - 75, 50, 50);
        //Key presses
        rootContainer.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()) {
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
            }
        });

        Media sound = new Media(new File("song.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        final long startNanoTime = System.nanoTime();

        new AnimationTimer() // 60 FPS
        {
            public void handle(long currentNanoTime)
            {
                GameReport report = game.tick(mediaPlayer.getCurrentTime().toMillis(), pressedButtons);
                System.out.println(report.getScore());

                pressedButtons = new Chord(false, false, false, false, false);
            }
        }.start();


    }
}

