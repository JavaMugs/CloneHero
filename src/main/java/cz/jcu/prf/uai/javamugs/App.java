package cz.jcu.prf.uai.javamugs;

import cz.jcu.prf.uai.javamugs.gui.EditorController;
import cz.jcu.prf.uai.javamugs.gui.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Editor.fxml"));
            Parent root = loader.load();
            EditorController controller = (EditorController) loader.getController();
            //controller.setStage(primaryStage);

            primaryStage.setTitle("Clone Hero");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
