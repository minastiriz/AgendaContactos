package com.almasb.controladores;

import com.almasb.DAO.ContactosDao;
import com.almasb.DAO.TelefonosDao;
import com.almasb.DAO.EmailDao;
import com.almasb.DAO.GruposDao;
import com.almasb.IGU.*;
import com.almasb.logica.FXApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaginaContactosController implements Initializable {

    private FXApp mainApp;
    private Contacto contacto;
    private GruposDao grupoDao = new GruposDao();
    private EmailDao mailDao = new EmailDao();

    private ContactosDao contactosDao = new ContactosDao();
    private TelefonosDao telefonosDao = new TelefonosDao();


    public void setMainApp(FXApp mainApp){
        this.mainApp=mainApp;
    }

    @FXML
    private JFXButton btnSalir;

    @FXML
    private JFXButton btnBorrarContacto;

    @FXML
    private TableView<Contacto> tablaContactos;

    @FXML
    private TableColumn<Contacto, String> nombreColumna;

    @FXML
    private TableColumn<Contacto, String> apellidoColumna;

    @FXML
    private TableView<Telefono> tablaTelefonos;

    @FXML
    private TableColumn<Telefono, Integer> numeroColumna;

    @FXML
    private TableColumn<Telefono, String> etiquetaTelefonoColummna;

    @FXML
    private TableView<Email> tablaEmails;

    @FXML
    private TableColumn<Email, String> etiquetaCorreoColummna;

    @FXML
    private TableColumn<Email, String> correoColumna;

    @FXML
    private TableView<Grupos> tablaGrupos;

    @FXML
    private TableColumn<Grupos, String> nombreGruposColumna;

    @FXML
    private JFXComboBox<String> tipoBusqueda;

    @FXML
    private JFXTextField busqueda;

    @FXML
    private JFXButton btnBuscarContacto;

    @FXML
    private JFXButton btnCrearContacto;

    @FXML
    private JFXButton btnEditarContacto;

    @FXML
    private JFXButton btnGrupos;

    @FXML
    private JFXButton btnConfiguracion;


    @FXML
    void salirApp(MouseEvent event) {
        //Hace referencia al botón salir, saldrá de la aplicación
        Stage stage = (Stage) btnSalir.getScene().getWindow();
        stage.close();
    }

    @FXML
    void borrarContacto(MouseEvent event) {
        // Hace referencia al boton de eliminr contacto
        if(tablaContactos.getSelectionModel().getSelectedItem() != null){
            boolean borrado = contactosDao.borrarContacto(tablaContactos.getSelectionModel().getSelectedItem());
            if(borrado) {
                // Elemento borrado, actualizo tabla
                tablaContactos.getItems().remove(tablaContactos.getSelectionModel().getSelectedItem());
                borrarDatosTablaTelefono();
                borrarDatosTablaEmail();
                borrarDatosTablaGrupos();
                // Ventana informacion
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Contacto eliminado");
                alert.setHeaderText("El contacto fue borrado correctamente");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar");
            alert.setHeaderText("El contacto no se pudo borrar o no se seleccion´lo ningún contacto");
            alert.showAndWait();
        }
    }


    @FXML
    void config(MouseEvent event) throws Exception{
        mainApp.mostrarVentanaConfiguracion();
    }

    @FXML
    void crearContacto(MouseEvent event) throws Exception {

        mainApp.mostrarVentanaCrearContacto();
        // Método que llevará a la página de creación de contacto

    }

    @FXML
    void editarContacto(MouseEvent event) throws Exception {
        contacto = tablaContactos.getSelectionModel().getSelectedItem();
        mainApp.mostrarVentanaEditarContactos(contacto);
    }

    @FXML
    void contactoElegido(MouseEvent event) {

        contacto=tablaContactos.getSelectionModel().getSelectedItem();
        if (contacto!=null){
            borrarDatosTablaTelefono();
            borrarDatosTablaEmail();
            borrarDatosTablaGrupos();


            loadDataTelefonos();
            loadDataEmails();
            loadDataGrupos();
            btnEditarContacto.setDisable(false);
        }
    }

    @FXML
    void gestionarGrupos(MouseEvent event) throws Exception{
        // Método que llevará a la página de grupos
        mainApp.mostrarVentanaGrupos();

    }

    @FXML
    void buscarContacto(MouseEvent event) {
        String textFieldContenido= busqueda.getText();
        borrarDatosTablaContactos();
        borrarDatosTablaEmail();
        borrarDatosTablaGrupos();
        borrarDatosTablaTelefono();
        ArrayList<Contacto> nuevaBusqueda;
        // Este boton filtrara la tabla en base a lo seleccionado para hacer la busqueda
        if(tipoBusqueda.getValue()!=null) {
            switch (tipoBusqueda.getValue().toString()) {
                case ("Apellidos"):
                    loadData(contactosDao.getContactos(textFieldContenido, "Apellidos"));
                    System.out.println("Buscar por apellido");
                    break;
                case ("Teléfono"):
                    loadData(contactosDao.getContactosEtiquetaTelefono(textFieldContenido));
                    System.out.println("Buscar por Telefono");
                    break;
                case ("E-mail"):
                    loadData(contactosDao.getContactosEtiquetaEmail(textFieldContenido));
                    System.out.println("Buscar por Telefono");
                    break;
                case ("Grupo"):
                    loadData(contactosDao.getContactosNonmbreGrupo(textFieldContenido));
                    System.out.println("Buscar por Telefono");
                    break;
                //Por defecto busco x nombre
                case ("Nombre"):
                    loadData(contactosDao.getContactos(textFieldContenido, "Nombre"));
                    System.out.println("Buscar x Nombre");
            }
        }
        else{
            loadData(contactosDao.getContactos(textFieldContenido, "Nombre"));
            System.out.println("Buscar x Nombre");
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEditarContacto.setDisable(true);
        initTable();
        loadData(contactosDao.getContactos());


    }

    private void initTable(){
        initCols();
        initColsResto();
    }

    private void initCols(){
        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumna.setCellValueFactory(new PropertyValueFactory<>("apellido"));

    }

    private void loadData(List<Contacto>listaContactos) {
        tablaContactos.getItems().addAll(listaContactos);
        ObservableList<String> listaTipoBusqueda = FXCollections.observableArrayList("Nombre", "Apellidos", "Teléfono", "E-mail", "Grupo");
        this.tipoBusqueda.setItems(listaTipoBusqueda);
    }

    private void initColsResto(){
        rellenarTablaTelefono();
        rellenarTablaEmail();
        rellenarTablaGrupos();
    }

    private void rellenarTablaTelefono(){
        numeroColumna.setCellValueFactory(new PropertyValueFactory<>("numero"));
        etiquetaTelefonoColummna.setCellValueFactory(new PropertyValueFactory<>("etiquetaTelefono"));
    }

    private void loadDataTelefonos(){
        ArrayList<Telefono> listaTelefonos= telefonosDao.getTelefonosContacto(contacto.getId());
        tablaTelefonos.getItems().addAll(listaTelefonos);
    }

    private void rellenarTablaEmail(){
        correoColumna.setCellValueFactory(new PropertyValueFactory<>("correo"));
        etiquetaCorreoColummna.setCellValueFactory(new PropertyValueFactory<>("etiquetaEmail"));

    }
    private void loadDataEmails(){
        ArrayList<Email> listaEmails= mailDao.getEmailsContacto(contacto.getId());
        tablaEmails.getItems().addAll(listaEmails);
    }

    private void rellenarTablaGrupos(){
        nombreGruposColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    }
    private void loadDataGrupos(){
        List<Grupos> listaGrupos = grupoDao.getGruposContacto(contacto.getId());
        tablaGrupos.getItems().addAll(listaGrupos);
    }


    private void borrarDatosTablaTelefono(){
        while(tablaTelefonos.getItems().size()!=0) {
            tablaTelefonos.getItems().remove(0);
        }
    }
    private void borrarDatosTablaEmail(){
        while(tablaEmails.getItems().size()!=0) {
            tablaEmails.getItems().remove(0);
        }
    }
    private void borrarDatosTablaGrupos(){
        while(tablaGrupos.getItems().size()!=0) {
            tablaGrupos.getItems().remove(0);
        }
    }
    private void borrarDatosTablaContactos() {
        while(tablaContactos.getItems().size()!=0) {
            tablaContactos.getItems().remove(0);
        }
    }
}
