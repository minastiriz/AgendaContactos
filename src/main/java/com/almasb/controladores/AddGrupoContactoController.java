package com.almasb.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.List;

public class AddGrupoContactoController {

    EditContactoController controller;

    @FXML
    private JFXComboBox<String> cmboxGrupo;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void addGrupo(MouseEvent event) {
        // Añade a un contacto a un grupo y cierra la ventana
        if(cmboxGrupo.getItems().indexOf(cmboxGrupo.getValue()) >= 0){
            controller.recibeGrupoAdded(cmboxGrupo.getValue());
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al añadir");
            alert.setHeaderText("No has seleccionado ningún grupo");
            alert.showAndWait();
        }

    }

    @FXML
    void volverAtras(MouseEvent event) {
        // Cerrará la nueva ventana
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    public void recibeController (EditContactoController controller, List<String> grupos){
        this.controller = controller;
        ObservableList<String> lista = FXCollections.observableArrayList(grupos);
        this.cmboxGrupo.setItems(lista);
    }
}
