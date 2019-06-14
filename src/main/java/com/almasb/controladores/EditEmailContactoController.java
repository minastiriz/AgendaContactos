package com.almasb.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditEmailContactoController {

    EditContactoController controller;

    @FXML
    private JFXTextField txtEtiqueta;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void guardarCambios(MouseEvent event) {
        if(!txtEmail.getText().equals("") && !txtEtiqueta.getText().equals("")){
            controller.recogeDatosEditMail(txtEmail.getText(), txtEtiqueta.getText());
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("Rellene todos los campos");
            alert.showAndWait();
        }
    }

    @FXML
    void volverAtras(MouseEvent event) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    public void recibeControllerDatos (EditContactoController controller, String viejaEtiqueta, String viejoMail){
        this.controller=controller;
        txtEmail.setText(viejoMail);
        txtEtiqueta.setText(viejaEtiqueta);
    }
}
