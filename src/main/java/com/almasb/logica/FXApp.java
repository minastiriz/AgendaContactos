package com.almasb.logica;

import com.almasb.controladores.EditContactoController;
import com.almasb.controladores.GruposController;
import com.almasb.controladores.PaginaContactosController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class FXApp extends Application {

    private Stage stagePrincipal;
    private AnchorPane rootPane;

    @Override
    public void start(Stage stagePrincipal) throws Exception {
        this.stagePrincipal = stagePrincipal;
        mostrarVentanaPrincipal();

    }

    /*
     * cargamos la ventana principal
     */
    public void mostrarVentanaPrincipal() {
        try {
            FXMLLoader loader = new FXMLLoader(FXApp.class.getResource("/view/paginaContactos.fxml"));
            rootPane=(AnchorPane) loader.load();
            Scene scene = new Scene(rootPane);
            stagePrincipal.setTitle("Ventana Principal");
            stagePrincipal.setScene(scene);
            PaginaContactosController controller = loader.getController();
            controller.setMainApp(this);
            stagePrincipal.show();
        } catch (IOException e) {
            //tratar la excepción.
        }
    }
    public void mostrarVentanaEditarContactos() {
        /*
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/editContacto.fxml"));
            AnchorPane ventanaDos = (AnchorPane) loader.load();
            Stage ventana = new Stage();
            ventana.setTitle("Venta Editar contactos");
            ventana.initOwner(stagePrincipal);
            Scene scene = new Scene(ventanaDos);
            ventana.setScene(scene);
            EditContactoController controller = loader.getController();
            controller.setStagePrincipal(ventana);
            ventana.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
         */
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void mostrarVentanaConfiguracion() throws Exception{
        Parent grp = FXMLLoader.load(getClass().getResource("/view/configuracion.fxml"));
        Scene escenario = new Scene(grp);
        Stage stage = new Stage();
        stage.setTitle("Configuración");
        stage.setScene(escenario);
        stage.show();
    }

    public void mostrarVentanaGrupos() throws Exception {
        // Método que llevará a la página de grupos
        Parent grp = FXMLLoader.load(getClass().getResource("/view/grupos.fxml"));
        Scene escenario = new Scene(grp);
        Stage stage = new Stage();
        stage.setTitle("Grupos");
        stage.setScene(escenario);
        stage.show();
    }
}