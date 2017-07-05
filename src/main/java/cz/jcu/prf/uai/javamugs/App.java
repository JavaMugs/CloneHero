package cz.jcu.prf.uai.javamugs;

import cz.jcu.prf.uai.javamugs.gui.MenuController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Menu.fxml"));
            Parent root = loader.load();
            MenuController controller = (MenuController) loader.getController();
            primaryStage.setTitle("Clone Hero");
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);
            primaryStage.getIcons().add(new Image(getClass().getResource("/icon.png").toString()));
            primaryStage.show();
            controller.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
