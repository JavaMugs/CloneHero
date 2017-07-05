package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class GameController {
    private Stage stage;
    private Game game;
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
        gc.fillRect(0,0, stage.getWidth(), stage.getHeight());
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);
        gc.strokeLine(250, 0, 250, stage.getHeight());
        gc.strokeLine(325, 0, 325, stage.getHeight());
        gc.strokeLine(400, 0, 400, stage.getHeight());
        gc.strokeLine(475, 0, 475, stage.getHeight());
        gc.strokeLine(550, 0, 550, stage.getHeight());
        //Circles
        gc.setFill(CloneHeroColors.RED);
        gc.fillOval(225, stage.getHeight() - 25, 50, 50);

        final long startNanoTime = System.nanoTime();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                double t = (currentNanoTime - startNanoTime) / 1000000000.0;
            }
        }.start();
    }
}

