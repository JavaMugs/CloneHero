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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Stage stage;
    public Label speedLabel;
    public Label difficultyLabel;
    public Slider speedSlider;
    public Slider difficultySlider;
    public Button exitButton;
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
        openGameWindow(null); //TODO put method under logic
        return;

        /*File songFile = fileChooser.showOpenDialog(stage);
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
            // TODO change exceptions
            return;
        }

        int difficulty = (int)difficultySlider.getValue();

        Game game = new Game(timeOffset, (byte)difficulty, pressChart);*/
    }

    private void openGameWindow(Game game) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game.fxml"));
            Parent root = loader.load();
            GameController gameController = (GameController) loader.getController();
            gameController.setGame(game);
            Stage gameStage = new Stage();
            gameStage.setTitle("Clone Hero");
            gameStage.setScene(new Scene(root));
            gameController.setStage(gameStage);
            gameStage.show();
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
            GameController editorController = (EditorController) loader.getController();
            Stage editorStage = new Stage();
            editorStage.setTitle("Clone Hero Editor");
            editorStage.setScene(new Scene(root));
            editorController.setStage(editorStage);
            editorStage.show();
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

