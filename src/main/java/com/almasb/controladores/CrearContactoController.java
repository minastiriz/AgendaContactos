package com.almasb.controladores;

import com.almasb.DAO.ContactosDao;
import com.almasb.DAO.EmailDao;
import com.almasb.DAO.GruposDao;
import com.almasb.DAO.TelefonosDao;
import com.almasb.IGU.*;
import com.almasb.IGU.Grupos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CrearContactoController implements Initializable {

    private ContactosDao contactosDao = new ContactosDao();
    private GruposDao gruposDao = new GruposDao();
    private TelefonosDao telefonosDao = new TelefonosDao();
    private EmailDao emailDao  = new EmailDao();


    private ArrayList<Telefono> listaTelefonos;
    private ArrayList<Email> listaEmail;
    private ArrayList<String> listaGrupos;
    private Contacto contacto;


    @FXML
    private JFXTextField nombre;

    @FXML
    private JFXTextField apellido;

    @FXML
    private JFXTextField numero;

    @FXML
    private JFXTextField etiquetaTelefono;

    @FXML
    private JFXButton btnAnadirTelefono;

    @FXML
    private JFXTextField correo;

    @FXML
    private JFXTextField etiquetaCorreo;

    @FXML
    private JFXButton btnAnadirCorreo;

    @FXML
    private JFXComboBox<String> comboBGrupos;

    @FXML
    private JFXButton btnAnadirGrupo;

    @FXML
    private JFXButton btnCrearContacto;

    @FXML
    private JFXButton btnVolver;

    @FXML
    void anadirCorreo(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String[] aviso = {"E-mail no añadido", "Completa los campos correctamente"};

        if (!estaVacio(etiquetaCorreo) && !estaVacio(correo)){
            Email email = new Email();
            email.setCorreo(correo.getText());
            email.setEtiquetaEmail(etiquetaCorreo.getText());

            if (existeEmailLista(listaEmail, email) || emailDao.existeCorreo(correo.getText())){
                aviso[1] = "Email ya existente";
            }
            else {
                listaEmail.add(email);
                alert.setAlertType(Alert.AlertType.INFORMATION);
                aviso[0] = "Grupo añadido";
                aviso[1] = "Se ha añadido correctamente, sigue añadiendole e-mails si lo deseas";
            }
        }
        etiquetaCorreo.setText(null);
        correo.setText(null);


        alert.setTitle(aviso[0]);
        alert.setHeaderText(aviso[1]);
        alert.showAndWait();


    }

    @FXML
    void anadirGrupo(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String[] aviso = {"Grupo no añadido", "Completa los campos correctamente"};
        if (comboBGrupos.getSelectionModel().isEmpty()){
            Grupos grupo = new Grupos();
            grupo.setNombre(comboBGrupos.getValue().toString());
            if (existeGrupoLista(listaGrupos, grupo)) {
                aviso[1] = "Grupo ya existente";
            }
            else {
                listaGrupos.add(grupo.getNombre());
                alert.setAlertType(Alert.AlertType.INFORMATION);
                aviso[0] = "Grupo añadido";
                aviso[1] = "Se ha añadido correctamente, sigue añadiendole grupos si lo deseas";
            }
        }

        alert.setTitle(aviso[0]);
        alert.setHeaderText(aviso[1]);
        alert.showAndWait();

    }

    @FXML
    void anadirTelefono(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String[] aviso = {"Telefono no añadido", "Completa los campos correctamente"};
        if (!estaVacio(etiquetaTelefono) && !estaVacio((numero) /*&& Utils.esNumeric(numero.getText())*/)){

                aviso[1]="Este telefono ya esta asignado";


        Telefono telefono = new Telefono();
        telefono.setNumero(Integer.parseInt(numero.getText()));
        telefono.setEtiquetaTelefono(etiquetaTelefono.getText());
        if (existeTelefonoLista(listaTelefonos, telefono) || telefonosDao.existeTelefono(Integer.parseInt(numero.getText()))) {
            aviso[1] = "Telefono ya existente";
        } else {
            listaTelefonos.add(telefono);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            aviso[0] = "Telefono añadido";
            aviso[1] = "Se ha añadido correctamente, sigue añadiendole telefonos si lo deseas";
        }

        }

        etiquetaTelefono.setText(null);
        numero.setText(null);

        alert.setTitle(aviso[0]);
        alert.setHeaderText(aviso[1]);
        alert.showAndWait();

    }

    @FXML
    void cerrarVenanaVolver(ActionEvent event) throws Exception{
        //Hace referencia al botón volver, llevará a la ventana anterior en la que se encontraba el usuario
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();

    }

    @FXML
    void crearContacto(ActionEvent event) throws Exception{
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String[] aviso = {"Contacto no añadido", "Completa los campos correctamente"};
        if (!estaVacio(nombre) && !estaVacio(apellido)){
            Contacto contacto= new Contacto();
            contacto.setNombre(nombre.getText());
            contacto.setApellido(apellido.getText());
            contacto.setTelefonosContacto(listaTelefonos);
            contacto.setEmailsContacto(listaEmail);
            contacto.setGruposContacto(listaGrupos);
            contactosDao.anadirContacto(contacto);
            alert.setAlertType(Alert.AlertType.INFORMATION);
            aviso[0]="Contacto añadido!";
            aviso[1]="Puedes seguir adiendo contactos";
        }
        alert.setTitle(aviso[0]);
        alert.setHeaderText(aviso[1]);
        alert.showAndWait();
        nombre.setText(null);
        apellido.setText(null);
        initListas();


    }


    private void initListas(){
        listaTelefonos= new ArrayList<Telefono>();
        listaEmail = new ArrayList<Email>();
        listaGrupos = new ArrayList<String>();
        contacto = new Contacto();
    }

    private Boolean estaVacio(JFXTextField jfxTextField){
        return jfxTextField.getText() == null || jfxTextField.getText().equals("");
    }


    private Boolean existeTelefonoLista(ArrayList<Telefono> lista, Telefono objeto){
        for (int i = 0; i<lista.size(); i++){
            if (lista.get(i).compareTo(objeto)==-1) return true;
        }
        return false;
    }

    private Boolean existeEmailLista(ArrayList<Email> lista, Email objeto){
        for (int i = 0; i<lista.size(); i++){
            if (lista.get(i).compareTo(objeto)==-1) return true;
        }
        return false;
    }

    private Boolean existeGrupoLista(ArrayList<String> lista, Grupos objeto){
        for (int i = 0; i<lista.size(); i++){
            if (lista.get(i).equals(objeto.getNombre())) return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> listaGrupos= gruposDao.getGrupos();
        ObservableList<String> listaGruposEncapsulados = FXCollections.observableArrayList(listaGrupos);
        this.comboBGrupos.setItems(listaGruposEncapsulados);
        initListas();

    }
}
