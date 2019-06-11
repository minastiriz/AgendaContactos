package com.almasb.controladores;

import com.almasb.IGU.Contacto;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EditContactoController implements Initializable {


    private Stage ventanaPrincipal;

    public void setStagePrincipal (Stage escenario) {
        this.ventanaPrincipal= escenario;
    }

    private Contacto contacto;

    private PaginaContactosController editController;

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXComboBox<?> cmboxGrupos;

    @FXML
    private JFXComboBox<?> cmboxMails;

    @FXML
    private JFXComboBox<?> cmboxTlfs;

    @FXML
    private JFXButton btnEditMails;

    @FXML
    private JFXButton btnAddTlfs;

    @FXML
    private JFXButton btnAddGrupos;

    @FXML
    private JFXButton btnBorrarTlfs;

    @FXML
    private JFXButton btnEditTlfs;

    @FXML
    private JFXButton btnBorrarMails;

    @FXML
    private JFXButton btnBorrarGrupos;

    @FXML
    private JFXButton btnAddMails;

    @FXML
    private JFXButton btnVolver;

    @FXML
    private JFXButton btnGuardar;


    @FXML
    void addGrupo(MouseEvent event) {
        // LLevará a una nueva página que asignará al usuario un nuevo grupo (que no pertenezca de momento)

    }

    @FXML
    void addMail(MouseEvent event) {
        // Llevará a una nueva página para asignarle un nuevo correo (y etiqueta si quiere)

    }

    @FXML
    void addTlf(MouseEvent event) {
        // Llevará a una nueva página para asignarle un nuevo telefono al usuario (y etiqueta si quiere)

    }

    @FXML
    void deleteGrupo(MouseEvent event) {
        // En base a lo que tuviera seleccionado en el combobox de grupos, se actualizará la combobox para borrarla

    }

    @FXML
    void deleteMail(MouseEvent event) {
        // En base a lo que tuviera seleccionado en el combobox de emails, se actualizará la combobox para borrarla

    }

    @FXML
    void deleteTlf(MouseEvent event) {
        // En base a lo que tuviera seleccionado en el combobox de telefonos, se actualizará la combobox para borrarla

    }

    @FXML
    void editMail(MouseEvent event) {
        // Se llevará a una nueva página para editar el mail o la etiqueta que tenía

    }

    @FXML
    void editTlf(MouseEvent event) {
        // Se llevará a una nueva página para editar los telefonos o la etiqueta que tenía

    }

    @FXML
    void guardarTodo(MouseEvent event) {
        // Se guardaran el nombre y el apellido (si cambió)

    }

    @FXML
    void volverAtras(MouseEvent event) {
        // Cerrará la nueva ventana
    }


    public void setContacto(Contacto contacto){
        this.contacto = contacto;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO
    }

    public void recogeContacto(PaginaContactosController controller, Contacto contacto){
        this.editController = controller;
        this.contacto = contacto;
        this.txtNombre.setText(this.contacto.getNombre());
        this.txtApellido.setText(this.contacto.getApellido());

        // TELEFONOS


        // EMAILS


        // GRUPOS


    }

}
