package com.almasb.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditGrupoController {

    GruposController controllerGrupos;

    @FXML
    private JFXTextField txtGrupo;

    @FXML
    private JFXButton btnGuardar;

    @FXML
    void guardarNombre(MouseEvent event) {
        // Al guardar se guarda el nuevo nombre
        if(!txtGrupo.getText().equals("")){
            controllerGrupos.realizaModificacion(txtGrupo.getText());
            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("Rellene los campos correctamente");
            alert.showAndWait();
        }
    }

    @FXML
    public void recibeNombre(GruposController controller, String nombre){
        this.controllerGrupos = controller;
        this.txtGrupo.setText(nombre);
    }
}
