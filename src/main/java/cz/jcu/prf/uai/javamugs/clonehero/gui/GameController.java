package cz.jcu.prf.uai.javamugs.clonehero.gui;

import cz.jcu.prf.uai.javamugs.clonehero.logic.Chord;
import cz.jcu.prf.uai.javamugs.clonehero.logic.Game;
import cz.jcu.prf.uai.javamugs.clonehero.logic.GameReport;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.ArrayList;
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
    private int[] lights = new int[5];

    public Canvas canvas;
    public BorderPane rootContainer;
    public Label scoreLabel;
    public Label multiplierLabel;

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
                try {
                    mediaPlayer.stop();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
                mediaPlayer = null;
                try {
                    mainCycle.stop();
                } catch (NullPointerException ex) {
                    ex.printStackTrace();
                }
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
                for (int i = 0; i < highlightedStrings.length; i++) {
                    if (report.getHitChord().getChords()[i]) {
                        highlightedStrings[i] = 20;
                    }
                    if (report.getMissChord().getChords()[i]) {
                        highlightedStrings[i] = -20;
                    }
                    if (lights[i] > 0 && pressedButtons.getChords()[i]) {
                        lights[i] = 0;
                    } else if (report.getHitChord().getChords()[i] || report.getMissChord().getChords()[i]) {
                        lights[i] = 120;
                    }

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
                if (mediaPlayer.getCurrentTime().toMillis() >= mediaPlayer.getTotalDuration().toMillis()) {
                    stop();
                    end(report);
                }
            }
        };
        mainCycle.start();
    }

    private void end(GameReport lastReport) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Clone Hero");
        alert.setHeaderText("You've completed this song!");
        alert.setContentText("Your score: " + lastReport.getScore());
        alert.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent event) {
                stage.hide();
            }
        });
        alert.show();
    }

    private void renderCanvas(GameReport report) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(new Image(getClass().getResource("/bg.jpg").toString()), 0, 0);
        Random random = new Random();
        for (int i = 0; i < highlightedStrings.length; i++) {
            //Strings
            gc.setLineWidth(5);
            double x = 250 + i * 75;
            if (highlightedStrings[i] > 0) {
                gc.setStroke(Color.GREEN);
                highlightedStrings[i]--;
                int randomInt = random.nextInt(6);
                randomInt -= randomInt / 2;
                x += randomInt;
            } else if (highlightedStrings[i] < 0) {
                gc.setStroke(Color.RED);
                highlightedStrings[i]++;
            } else
                gc.setStroke(Color.LIGHTGREY);
            gc.strokeLine(x, 0, x, canvas.getHeight());
            //Circles
            gc.setFill(cz.jcu.prf.uai.javamugs.clonehero.gui.CloneHeroColors.COLORARRAY[i]);
            gc.setLineWidth(3);
            x = 225 + i * 75;
            gc.fillOval(x, canvas.getHeight() - 75, 50, 50);
            gc.strokeOval(x, canvas.getHeight() - 75, 50, 50);

            gc.setStroke(Color.BLACK);
            gc.setFill(gc.getStroke());
            gc.setFont(Font.font(30));
            gc.setTextAlign(TextAlignment.CENTER);
            String text = "";
            switch (i) {
                case 0:
                    text = "A";
                    break;
                case 1:
                    text = "S";
                    break;
                case 2:
                    text = "D";
                    break;
                case 3:
                    text = "K";
                    break;
                case 4:
                    text = "L";
                    break;
            }
            gc.fillText(text, x + 25, canvas.getHeight() - 40);

            if (lights[i] > 0) {
                lights[i]--;
                Color color = cz.jcu.prf.uai.javamugs.clonehero.gui.CloneHeroColors.COLORARRAY[i];
                gc.setFill(new Color(color.getRed(), color.getGreen(), color.getBlue(), 0.2));
                gc.fillPolygon(
                        new double[]{
                                i * (canvas.getWidth() / (lights.length - 1)),
                                0,
                                canvas.getWidth()
                        },
                        new double[]{
                                -50,
                                canvas.getHeight() + 1500,
                                canvas.getHeight() + 1500
                        },
                        3);
            }
        }
        scoreLabel.setText(String.valueOf(report.getScore()));
        multiplierLabel.setText(String.format("%.1f", report.getMultiplier()) + "x");
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
            this.y = -50;
            finished = false;
            this.startTime = startTime;
            this.endTime = startTime + timeOffset;
        }

        public void animate(double currentTime) {
            double ratio = (currentTime - startTime) / (endTime - startTime);
            this.y = (canvas.getHeight() - 25) * ratio - 50;
            GraphicsContext gc = canvas.getGraphicsContext2D();
            gc.setFill(cz.jcu.prf.uai.javamugs.clonehero.gui.CloneHeroColors.COLORARRAY[color]);
            gc.fillOval(225 + 75 * color, y, 50, 50);
            if (currentTime >= endTime + 500) {
                finished = true;
            }
        }

        public boolean isFinished() {
            return finished;
        }
    }
}

