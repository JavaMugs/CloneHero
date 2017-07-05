package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Game;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

public class GameController {
    private Stage stage;
    private Game game;
    public Canvas canvas;

    public void setGame(Game game) {
        this.game = game;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setStroke(CloneHeroColors.RED);
        gc.setLineWidth(5);
        gc.strokeLine(400, 0, 400, 600);

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

