package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.Style;
import jfxtras.styles.jmetro.JMetro;


public class Main extends Application {
    private static final Style STYLE = Style.LIGHT;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1233, 700));
        primaryStage.show();
        new JMetro(root, STYLE);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
