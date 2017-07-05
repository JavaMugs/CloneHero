package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.App;
import cz.jcu.prf.uai.javamugs.logic.Game;
import cz.jcu.prf.uai.javamugs.logic.Parser;
import cz.jcu.prf.uai.javamugs.logic.Press;
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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MenuController {
    private Stage stage;
    public Label speedLabel;
    public Label difficultyLabel;
    public Slider speedSlider;
    public Slider difficultySlider;
    public Button exitButton;
    public FileChooser fileChooser = new FileChooser();
    public VBox rootContainer;

    public void start() {
        this.stage = (Stage) rootContainer.getScene().getWindow();
        difficultyLabel.textProperty().bind(
                Bindings.format("%.0f", difficultySlider.valueProperty())
        );
        speedLabel.textProperty().bind(
                Bindings.format("%.0f", speedSlider.valueProperty())
        );
    }

    public void playButtonAction(ActionEvent event) {

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

        Parser parser = new Parser();
        int timeOffset = (int)speedSlider.getValue();

        PressChart pressChart;
        try {
            pressChart = parser.parseFile(pressChartPath, timeOffset);
        } catch(Exception ex) {
            ex.printStackTrace();
            return;
        }

        int difficulty = (int)difficultySlider.getValue();

        Game game = new Game(timeOffset, (byte)difficulty, pressChart);

        openGameWindow(game, songURIstring); //TODO put method under logic
    }

    private void openGameWindow(Game game, String songURIstring) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
            Parent root = loader.load();
            GameController gameController = (GameController) loader.getController();
            gameController.setGame(game);
            gameController.setSongURIstring(songURIstring);
            Stage gameStage = new Stage();
            gameStage.setTitle("Clone Hero");
            gameStage.setScene(new Scene(root));
            gameStage.show();
            gameController.start();
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void editorButtonAction(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Editor.fxml"));
            Parent root = loader.load();
            EditorController editorController = (EditorController) loader.getController();
            Stage editorStage = new Stage();
            editorStage.setTitle("Clone Hero Editor");
            editorStage.setScene(new Scene(root));
            editorStage.show();
            File songFile = fileChooser.showOpenDialog(stage);
            if (songFile == null) {
                return;
            }
            editorController.setSongPath(songFile.toURI().toString());

            editorController.start();
            //((Node)(event.getSource())).getScene().getWindow().hide();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void exitButtonAction(ActionEvent event) {
        stage.close();
    }
}

