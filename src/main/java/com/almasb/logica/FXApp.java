package com.almasb.logica;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXApp extends Application {

    @Override
    public void start(Stage stage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/view/paginaContactos.fxml"));
        Scene escenario = new Scene(root);
        stage.setTitle("Contactos");
        stage.setScene(escenario);
        stage.show();

    }
    public static void main(String[] args){
        launch(args);
    }
}
