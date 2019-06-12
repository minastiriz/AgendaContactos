package com.almasb.controladores;

import com.almasb.IGU.*;
import com.almasb.logica.FXApp;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PaginaContactosController implements Initializable {

    private FXApp mainApp;
    private Contacto contacto;
    private DaoGrupo grupoDao = new DaoGrupo();
    private DaoEmail mailDao = new DaoEmail();

    public void setMainApp(FXApp mainApp){
        this.mainApp=mainApp;
    }
    public void setGrupoDao (DaoGrupo daoGrupo){ this.grupoDao = daoGrupo; }
    public void setMailDao (DaoEmail daoMail) { this.mailDao = daoMail; }

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

        mainApp.mostrarVentanaGrupos();

        // Método que llevará a la página de grupos
        /*FXMLLoader loader = new FXMLLoader();
        Parent grp = loader.load(getClass().getResource("/view/grupos.fxml"));
        Scene escena = new Scene(grp);
        mainApp.setScene(escena);
        GruposController gruposController = loader.getController();
        gruposController.setMainApp(mainApp);
        */

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
                    loadData(Contacto.getContactos(textFieldContenido, "Apellidos"));
                    System.out.println("Buscar x apellido");
                    break;
                case ("Teléfono"):
                    loadData(Contacto.getContactosEtiquetaTelefono(textFieldContenido));
                    System.out.println("Buscar por Telefono");
                    break;
                case ("E-mail"):
                    loadData(Contacto.getContactosEtiquetaEmail(textFieldContenido));
                    System.out.println("Buscar por Telefono");
                    break;
                case ("Grupo"):
                    loadData(Contacto.getContactosNonmbreGrupo(textFieldContenido));
                    System.out.println("Buscar por Telefono");
                    break;
                //Por defecto busco x nombre
                case ("Nombre"):
                    loadData(Contacto.getContactos(textFieldContenido, "Nombre"));
                    System.out.println("Buscar x Nombre");
            }
        }
        else{
            loadData(Contacto.getContactos(textFieldContenido, "Nombre"));
            System.out.println("Buscar x Nombre");
        }

    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnEditarContacto.setDisable(true);
        initTable();
        loadData(Contacto.getContactos());


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
        ArrayList<Telefono> listaTelefonos= Telefono.getTelefonosContacto(contacto.getId());
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
