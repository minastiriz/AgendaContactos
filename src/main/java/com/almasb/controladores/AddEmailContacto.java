package com.almasb.controladores;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddEmailContacto {

    EditContactoController controller;

    @FXML
    private JFXTextField txtEtiqueta;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void guardarCambios(MouseEvent event) {
        if(!txtEmail.getText().equals("")) {
            controller.recogeDatosAddMail(txtEmail.getText(), txtEtiqueta.getText());
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear");
            alert.setHeaderText("Introduzca, al menos, un email");
            alert.showAndWait();
        }
    }

    @FXML
    void volverAtras(MouseEvent event) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    public void recibeController (EditContactoController controller){
        this.controller=controller;
    }
}
