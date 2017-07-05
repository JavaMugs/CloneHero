package cz.jcu.prf.uai.javamugs.gui;

import cz.jcu.prf.uai.javamugs.logic.Press;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class EditorController {

    public Label countLabel;
    public Circle circle0;
    public Circle circle1;
    public Circle circle2;
    public Circle circle3;
    public Circle circle4;
    public Button startBtn;
    public TableView<Press> tablePresses;
   /* public TableCell buttonCol;
    public TableColumn timeCol;*/


    public void start() {
        /*ObservableList<Press> data = tablePresses.getItems();
        data.add(new Press(Integer.parseInt(buttonCol.getText()), Double.parseDouble(timeCol.getText())));

        buttonCol.setText("");
        timeCol.setText("");*/



        startBtn.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                System.out.println(ke);
            }
        });
    }

    public void startBtnAction(ActionEvent event){
        countLabel.setText("Recording");
        startBtn.setVisible(false);
    }
}

