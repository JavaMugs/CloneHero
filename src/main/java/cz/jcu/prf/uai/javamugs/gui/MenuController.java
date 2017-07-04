package cz.jcu.prf.uai.javamugs.gui;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;

public class MenuController {
    public Label speedLabel;
    public Label difficultyLabel;
    public Slider speedSlider;
    public Slider difficultySlider;
    public Button exitButton;

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
    }

    public void exitButtonAction(ActionEvent event) {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }
}

