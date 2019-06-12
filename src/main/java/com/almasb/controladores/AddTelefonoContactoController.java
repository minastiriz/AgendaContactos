package com.almasb.controladores;

import com.almasb.IGU.Telefono;
import com.almasb.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddTelefonoContactoController {

    private EditContactoController controller;

    @FXML
    private JFXTextField txtEtiqueta;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXButton btnAdd;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void guardarCambios(MouseEvent event) {
        if(!txtTelefono.getText().equals("") && !txtEtiqueta.getText().equals("") && Utils.isNumeric(txtTelefono.getText())) {
            Telefono tlf = new Telefono();
            tlf.setNumero(Integer.parseInt(txtTelefono.getText()));
            tlf.setEtiquetaTelefono(txtEtiqueta.getText());
            controller.recogeDatosAddTlf(tlf);
            Stage stage = (Stage) btnAdd.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear");
            alert.setHeaderText("Rellene todos los campos de manera correcta");
            alert.showAndWait();
        }
    }

    @FXML
    void volverAtras(MouseEvent event) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    public void recibeController (EditContactoController controller){
        this.controller = controller;
    }
}
