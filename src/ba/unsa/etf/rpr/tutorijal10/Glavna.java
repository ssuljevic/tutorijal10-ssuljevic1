package ba.unsa.etf.rpr.tutorijal10;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Glavna extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/glavna.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 572, 300));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
