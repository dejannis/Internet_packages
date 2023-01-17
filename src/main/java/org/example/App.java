package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        URL fxmlURL = getClass().getClassLoader().getResource("add_package.fxml");
        HBox root = FXMLLoader.load(fxmlURL);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("style.css");

        stage.setScene(scene);
        stage.show();

    }


    public static void main(String[] args) {
        launch();
    }

}