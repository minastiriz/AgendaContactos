
package com.almasb.logica;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FXApp extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        stage.setScene(new Scene(new Pane(), 800, 600));
        stage.show();
        System.out.println("Hola cara alfombra");

    }
    public static void main(String[] args){
        launch(args);

    }
}