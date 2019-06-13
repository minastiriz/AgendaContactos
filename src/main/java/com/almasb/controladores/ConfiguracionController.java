package com.almasb.controladores;

import com.almasb.DAO.ContactosDao;
import com.almasb.IGU.Contacto;
import com.almasb.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ConfiguracionController  implements Initializable {

    private ContactosDao contactosDao= new ContactosDao();

    @FXML
    private JFXComboBox<String> tipoOrdenacion;

    @FXML
    private JFXButton btnDescargar;

    @FXML
    void crearFichero(ActionEvent event) {
        List<Contacto> listaContactos= contactosDao.getContactos();
        if(tipoOrdenacion.getValue()!=null) {
            switch (tipoOrdenacion.getValue().toString()) {
                case ("Apellido"):
                    Collections.sort( listaContactos, (o1, o2) -> o1.getApellido().compareTo(o2.getApellido()) );
                    System.out.println("Ordenar x apellido");
                    break;
                case ("Nombre"):
                    Collections.sort( listaContactos, (o1, o2) -> o1.getNombre().compareTo(o2.getNombre()) );
                    System.out.println("Ordenar por nombre");
                    break;
            }

            Utils.guardarArrayList(listaContactos);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listaTipoBusqueda = FXCollections.observableArrayList("Nombre", "Apellido");
        this.tipoOrdenacion.setItems(listaTipoBusqueda);

    }

}
