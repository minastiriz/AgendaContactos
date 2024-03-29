package com.almasb.controladores;

import com.almasb.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditTelefonoContactoController {

    EditContactoController controller;

    @FXML
    private JFXTextField txtEtiqueta;

    @FXML
    private JFXTextField txtTelefono;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void guardarCambios(MouseEvent event) {
        if(!txtTelefono.getText().equals("") && !txtEtiqueta.getText().equals("") && Utils.isNumeric(txtTelefono.getText())){
            controller.recogeDatosEditTelefono(txtTelefono.getText(), txtEtiqueta.getText());
            Stage stage = (Stage) btnSave.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("Rellene los campos correctamente");
            alert.showAndWait();
        }
    }

    @FXML
    void volverAtras(MouseEvent event) {
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    public void recibeControllerDatos (EditContactoController controller, String viejaEtiqueta, String viejoTelefono){
        this.controller=controller;
        txtTelefono.setText(viejoTelefono);
        txtEtiqueta.setText(viejaEtiqueta);
    }


}
