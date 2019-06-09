package com.almasb.controladores;

import com.almasb.IGU.Grupos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GruposController implements Initializable {

    //FALTA

    // Acceso al modelo ficticio
    Grupos modeloGrupos = new Grupos();

    @FXML
    private JFXComboBox<String> gruposBox;

    @FXML
    private JFXButton botonEditarGrupo;

    @FXML
    private JFXButton botonBorrarGrupo;

    @FXML
    private JFXTextField txtCrearGrupo;

    @FXML
    private JFXButton botonCrearGrupo;

    @FXML
    private JFXButton botonVolver;

    @FXML
    void borrarGrupo(ActionEvent event) {
        //Hace referencia al boton de borrar, borrará un grupo seleccionado en el box

        System.out.println("El usuario ha presionado el botón para borrar el grupo "+gruposBox.getValue());

        modeloGrupos.borrarGrupo(gruposBox.getValue());

        //GESTION PARA BORRARLO DEL COMBOBOX
        gruposBox.setItems(getItemsBox());
    }

    @FXML
    void crearGrupo(ActionEvent event) {
        //Hace referencia al boton de crear, creará un grupo con el nombre en el txtfield

        System.out.println("El usuario ha presionado el botón para crear un grupo con nombre "+txtCrearGrupo.getText());

        modeloGrupos.crearGrupo(txtCrearGrupo.getText());

        //GESTION PARA AÑADIRLO AL COMBOBOX
        gruposBox.setItems(getItemsBox());
    }

    @FXML
    void editarGrupo(ActionEvent event) {
        //Hace referencia al boton modificar, llevará a una ventana en la que podrá cambiar el nombre al grupo seleccionado en el box

        System.out.println("El usuario ha decidido editar el grupo "+gruposBox.getValue());

        // Abrir una ventana nueva

    }

    @FXML
    void volverAnterior(ActionEvent event) {
        //Hace referencia al botón volver, llevará a la ventana anterior en la que se encontraba el usuario





    }

    private ObservableList<String> getItemsBox(){
        // Este metodo permitirá comunicar al controlador con el modelo para recoger los datos de los grupos
        List<String> nombresGrupos = modeloGrupos.getGrupos();
        ObservableList<String> itemsBox = FXCollections.observableArrayList(nombresGrupos);
        return itemsBox;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Al iniciar la vista se cargaran los datos de la base de datos para el combobox con todos los grupos creados
        gruposBox.setItems(getItemsBox());
        gruposBox.getSelectionModel().select(0);
    }
}
