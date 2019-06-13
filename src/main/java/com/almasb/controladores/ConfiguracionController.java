package com.almasb.controladores;

import com.almasb.DAO.ContactosDao;
import com.almasb.DAO.EmailDao;
import com.almasb.DAO.GruposDao;
import com.almasb.DAO.TelefonosDao;
import com.almasb.IGU.Contacto;
import com.almasb.IGU.Email;
import com.almasb.IGU.Grupos;
import com.almasb.IGU.Telefono;
import com.almasb.Utils;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class ConfiguracionController  implements Initializable {

    private ContactosDao contactosDao= new ContactosDao();
    private TelefonosDao telefonosDao = new TelefonosDao();
    private EmailDao emailDao = new EmailDao();
    private GruposDao gruposDao = new GruposDao();

    @FXML
    private JFXButton btnVolver;

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
                    break;
                case ("Nombre"):
                    Collections.sort( listaContactos, (o1, o2) -> o1.getNombre().compareTo(o2.getNombre()) );
                    break;
            }

            crearFichero(listaContactos);

        }

    }

    private void crearFichero (List<Contacto> lista) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter("contactos.txt");
            pw = new PrintWriter(fichero);
            ArrayList<Telefono> tlfs;
            List<Grupos> grps;
            ArrayList<Email> mails;

            for(Contacto contacto : lista){
                tlfs = telefonosDao.getTelefonosContacto(contacto.getId());
                grps = gruposDao.getGruposContacto(contacto.getId());
                mails = emailDao.getEmailsContacto(contacto.getId());
                pw.write(contacto.getNombre() + ", " + contacto.getApellido());
                pw.write("  ||  TELEFONOS: ");
                for(Telefono tlf : tlfs){
                    pw.write("  etiqueta: "+tlf.getEtiquetaTelefono()+", "+"telefono: "+tlf.getNumero());
                }
                pw.write("  ||  EMAILS: ");
                for(Email mail : mails){
                    pw.write("  etiqueta: "+mail.getEtiquetaEmail()+", "+"correo: "+mail.getCorreo());
                }
                pw.write("  ||  GRUPOS: ");
                for(Grupos grp : grps){
                    pw.write("  nombre: "+grp.getNombre());
                }
                pw.write("\n");
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Fichero creado");
            alert.setHeaderText("El fichero se creó correctamente");
            alert.showAndWait();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear el fichero");
            alert.setHeaderText("El fichero no se pudo crear");
            alert.showAndWait();
        } finally {
            try {
                // Nuevamente aprovechamos el finally para
                // asegurarnos que se cierra el fichero.
                if (null != fichero)
                    fichero.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    @FXML
    void volverAtras(MouseEvent event) {
        // Cerrará la nueva ventana
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> listaTipoBusqueda = FXCollections.observableArrayList("Nombre", "Apellido");
        this.tipoOrdenacion.setItems(listaTipoBusqueda);

    }

}
