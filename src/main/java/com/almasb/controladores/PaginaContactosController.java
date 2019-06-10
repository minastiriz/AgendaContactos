package com.almasb.controladores;

import com.almasb.IGU.Contacto;
import com.almasb.IGU.Email;
import com.almasb.IGU.Grupos;
import com.almasb.IGU.Telefono;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class PaginaContactosController {

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
    private TableColumn<Telefono, String> etiquetaColummna;

    @FXML
    private TableView<Email> tablaEmails;

    @FXML
    private TableColumn<Email, String> etiquetaColumna;

    @FXML
    private TableColumn<Email, String> etiquetaCorreo;

    @FXML
    private TableView<Grupos> tablaGrupos;

    @FXML
    private TableColumn<Grupos, String> nombreGruposColumna;

    @FXML
    private JFXComboBox<String> tipoBusqueda;

    @FXML
    private JFXTextField busqueda;





    @FXML
    void config(MouseEvent event) throws Exception{
        // Método que llevará a la página de configuración
        Parent grp = FXMLLoader.load(getClass().getResource("/view/configuracion.fxml"));
        Scene escenario = new Scene(grp);
        Stage stage = new Stage();
        stage.setTitle("Grupos");
        stage.setScene(escenario);
        stage.show();
    }

    @FXML
    void crearContacto(MouseEvent event) {
        // Método que llevará a la página de creación de contacto

    }

    @FXML
    void editarContacto(MouseEvent event) {
        // Método que llevará a la página de edición del contacto

    }

    @FXML
    void gestionarGrupos(MouseEvent event) throws Exception{
        // Método que llevará a la página de grupos
        Parent grp = FXMLLoader.load(getClass().getResource("/view/grupos.fxml"));
        Scene escenario = new Scene(grp);
        Stage stage = new Stage();
        stage.setTitle("Grupos");
        stage.setScene(escenario);
        stage.show();
    }

    @FXML
    void buscarContacto(MouseEvent event) {
        // Este boton filtrara la tabla en base a lo seleccionado para hacer la busqueda

    }

    @FXML
    private void initialize() {
        //nombreColumna.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        //apellidoColumna.setCellValueFactory(cellData -> cellData.getValue().apellidoProperty());

        //Al iniciar la vista se cargaran los datos de la base de datos para el combobox con todos los grupos creados
        ObservableList<String> listaTipoBusqueda = FXCollections.observableArrayList("Nombre", "Apellidos", "Teléfono", "E-mail", "Grupo");
        this.tipoBusqueda.setItems(listaTipoBusqueda);
    }
}
