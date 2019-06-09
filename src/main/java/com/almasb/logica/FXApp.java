
package com.almasb.logica;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

public class FXApp extends Application {

    private Stage escenario;
    @Override
    public void start(Stage stage) throws Exception{
        escenario=stage;
        FXMLLoader loader = new FXMLLoader(FXApp.class.getResource("/../IGU/PaginaContactos.fxml"));
        //loader.setLocation(getClass().getResource("../IGU/PaginaContactos.fxml"));
        AnchorPane pane = loader.load();
        Scene escena = new Scene(pane);
        escenario.setScene(escena);
        escenario.show();

    }
    public static void main(String[] args){
        launch(args);

    }
}
