package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Press;
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
import javafx.util.Duration;

import java.io.File;


public class EditorController {

    private MediaPlayer mediaPlayer;
    private String songPath;
    public Label countLabel;
    public Circle circle0;
    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    public Circle circle4;
    public Button startBtn;
    public TableView<Press> tablePresses;
    public TableColumn<Press, Integer> buttonCol;
    public TableColumn<Press, Long> timeCol;


    public void start() {

        startBtn.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                switch (ke.getCode()){
                    case A:
                        FadeTransition ft0 = new FadeTransition(Duration.millis(300), circle0);
                        ft0.setFromValue(0.3);
                        ft0.setToValue(1);
                        ft0.setCycleCount(1);
                        ft0.setAutoReverse(true);
                        ft0.play();
                        break;
                    case S:
                        FadeTransition ft1 = new FadeTransition(Duration.millis(300), circle1);
                        ft1.setFromValue(0.3);
                        ft1.setToValue(1);
                        ft1.setCycleCount(1);
                        ft1.setAutoReverse(true);
                        ft1.play();
                        break;
                    case D:
                        FadeTransition ft2 = new FadeTransition(Duration.millis(300), circle2);
                        ft2.setFromValue(0.3);
                        ft2.setToValue(1);
                        ft2.setCycleCount(1);
                        ft2.setAutoReverse(true);
                        ft2.play();
                        break;
                    case K:
                        FadeTransition ft3 = new FadeTransition(Duration.millis(300), circle3);
                        ft3.setFromValue(0.3);
                        ft3.setToValue(1);
                        ft3.setCycleCount(1);
                        ft3.setAutoReverse(true);
                        ft3.play();
                        break;
                    case L:
                        FadeTransition ft4 = new FadeTransition(Duration.millis(300), circle4);
                        ft4.setFromValue(0.3);
                        ft4.setToValue(1);
                        ft4.setCycleCount(1);
                        ft4.setAutoReverse(true);
                        ft4.play();
                        break;
                }
            }
        });
    }

    public void startBtnAction(ActionEvent event){
        countLabel.setText("Recording");
        startBtn.setVisible(false);

        Media sound = new Media(this.songPath);
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        ObservableList<Press> data =
                FXCollections.observableArrayList(
                        new Press(1, 50),
                        new Press(3, 80)
                );

        tablePresses.setItems(data);
    }

    public void setSongPath(String path){
        this.songPath = path;
    }
}

