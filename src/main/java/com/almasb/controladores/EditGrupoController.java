package com.almasb.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
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
        controllerGrupos.realizaModificacion(txtGrupo.getText());
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void recibeNombre(GruposController controller, String nombre){
        this.controllerGrupos = controller;
        this.txtGrupo.setText(nombre);
    }
}
