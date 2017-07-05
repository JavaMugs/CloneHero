package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Chord;
import cz.jcu.prf.uai.javamugs.logic.Press;
import cz.jcu.prf.uai.javamugs.logic.Saver;
import javafx.animation.FadeTransition;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.security.InvalidParameterException;


public class EditorController {

    private Press actualPress;
    private Saver saver;
    private MediaPlayer mediaPlayer;
    private String songPath;
    private boolean isRecording;
    public Label countLabel;
    public Circle circle0;
    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    public Circle circle4;
    public Button startBtn;
    public TextArea textPresses;

    /**
     * Round double to x decimals
     * @param value
     * @param places
     * @return
     */
    private double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * Throw actual press to textarea
     * @param press
     */
    private void setNewPressToTextarea(Press press){
        String colorName = "";

        switch (press.getColor()){
            case 0:
                colorName = "Red\t";
                break;
            case 1:
                colorName = "Yellow";
                break;
            case 2:
                colorName = "Green";
                break;
            case 3:
                colorName = "Blue\t";
                break;
            case 4:
                colorName = "Magenta";
                break;
        }

        textPresses.setText(colorName + "\t= " + Double.toString(round(press.getDrawTime(), 4)) + "\n" + textPresses.getText());
    }

    private void setFadeIn(Circle c){
        FadeTransition ft = new FadeTransition(Duration.millis(300), c);
        ft.setFromValue(0.3);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(true);
        ft.play();
    }

    /**
     * Start listen keys to press
     */
    private void startListenButtons(){
        saver = new Saver();

        startBtn.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCode()){
                    case A:
                        actualPress = new Press(Chord.RED, mediaPlayer.getCurrentTime().toMillis());
                        setFadeIn(circle0);
                        break;
                    case S:
                        actualPress = new Press(Chord.YELLOW, mediaPlayer.getCurrentTime().toMillis());
                        setFadeIn(circle1);
                        break;
                    case D:
                        actualPress = new Press(Chord.GREEN, mediaPlayer.getCurrentTime().toMillis());
                        setFadeIn(circle2);
                        break;
                    case K:
                        actualPress = new Press(Chord.BLUE, mediaPlayer.getCurrentTime().toMillis());
                        setFadeIn(circle3);
                        break;
                    case L:
                        actualPress = new Press(Chord.MAGENTA, mediaPlayer.getCurrentTime().toMillis());
                        setFadeIn(circle4);
                        break;
                }

                setNewPressToTextarea(actualPress);
                saver.addPress(actualPress);
            }
        });
    }

    /**
     * Start view
     */
    public void start() {
        Stage stage = (Stage) startBtn.getScene().getWindow();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                mediaPlayer.stop();
                mediaPlayer = null;
            }
        });

        isRecording = false;
        textPresses.setDisable(true);
    }

    /**
     * Start button
     * @param event
     */
    public void startBtnAction(ActionEvent event){
        if(!isRecording){
            countLabel.setText("Recording");
            startBtn.setText("Stop");
            isRecording = true;

            Media sound = new Media(this.songPath);
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        }else{
            countLabel.setText("Editor");
            startBtn.setText("Start");
            isRecording = false;

            mediaPlayer.stop();
            mediaPlayer = null;

            try{
                saver.save(songPath.replace(".mp3", ""));
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        mediaPlayer.setOnEndOfMedia(new Runnable() {

            public void run() {
                try{
                    saver.save(songPath.replace(".mp3", ""));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        startListenButtons();
    }

    /**
     * Set song path
     * @param path
     */
    public void setSongPath(String path){
        this.songPath = path;
    }
}