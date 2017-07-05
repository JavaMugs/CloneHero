package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Game;
import cz.jcu.prf.uai.javamugs.logic.Parser;
import cz.jcu.prf.uai.javamugs.logic.PressChart;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuController {
    public Label speedLabel;
    public Label difficultyLabel;
    public Slider speedSlider;
    public Slider difficultySlider;
    public Button exitButton;
    public Stage stage;
    public FileChooser fileChooser = new FileChooser();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        difficultyLabel.textProperty().bind(
                Bindings.format("%.0f", difficultySlider.valueProperty())
        );
        speedLabel.textProperty().bind(
                Bindings.format("%.0f", speedSlider.valueProperty())
        );
    }

    public void playButtonAction(ActionEvent event) {
        //TODO get file, parse it, create Game instance open game window, make menu inactive
        File songFile = fileChooser.showOpenDialog(stage);
        if (songFile == null) {
            return;
        }
        String songURIstring = songFile.toURI().toString();

        File pressChartFile = fileChooser.showOpenDialog(stage);
        if (pressChartFile == null) {
            return;
        }
        String pressChartPath = pressChartFile.getAbsolutePath();

        /*Parser parser = new Parser();
        int timeOffset = (int)speedSlider.getValue();

        PressChart pressChart;
        try {
            pressChart = parser.parseFile(pressChartPath, timeOffset);
        } catch(Exception ex) {
            // TODO change exceptions
            return;
        }

        int difficulty = (int)difficultySlider.getValue();

        Game game = new Game(timeOffset, (byte)difficulty, pressChart);*/

        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Game.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Clone Hero");
            stage.setScene(new Scene(root));
            stage.show();
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editorButtonAction(ActionEvent event) {
        //TODO
    }

    public void exitButtonAction(ActionEvent event) {
        stage.close();
    }
}

