package com.almasb.controladores;

import com.almasb.DAO.ContactosDao;
import com.almasb.DAO.TelefonosDao;
import com.almasb.DAO.EmailDao;
import com.almasb.DAO.GruposDao;
import com.almasb.IGU.*;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EditContactoController {

    private Stage ventanaPrincipal;
    private Contacto contacto;
    private EmailDao mailDao = new EmailDao();
    private GruposDao grupoDao = new GruposDao();
    private TelefonosDao telefonosDao = new TelefonosDao();
    private ContactosDao contactosDao = new ContactosDao();

    public void setStagePrincipal (Stage escenario) {
        this.ventanaPrincipal= escenario;
    }
    public void setMailDao (EmailDao mailDao) { this.mailDao = mailDao; }
    public void setGrupoDao (GruposDao grupoDao) { this.grupoDao = grupoDao; }

    @FXML
    private JFXTextField txtNombre;

    @FXML
    private JFXTextField txtApellido;

    @FXML
    private JFXComboBox<String> cmboxGrupos;

    @FXML
    private JFXComboBox<String> cmboxMails;

    @FXML
    private JFXComboBox<String> cmboxTlfs;

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
    void addGrupo(MouseEvent event) throws IOException {
        // LLevará a una nueva página que asignará al usuario un nuevo grupo (que no pertenezca de momento)
        List<String> listaGrupos = grupoDao.getGrupos();
        listaGrupos.removeAll(cmboxGrupos.getItems());
        Stage stageEdit = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/view/addGrupoContacto.fxml").openStream());
        AddGrupoContactoController editController = (AddGrupoContactoController) loader.getController();
        editController.recibeController(this, listaGrupos);
        Scene escenario = new Scene(root);
        stageEdit.setTitle("Editar Grupos del contacto");
        stageEdit.setScene(escenario);
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.show();
    }

    public void recibeGrupoAdded (String grupo) {
        // Recibe el grupo y hace la llamada al modelo, posteriormente actualiza la combobox añadiendo el nuevo grupo
        boolean added = grupoDao.addContactoGrupo(contacto.getId(), grupo);
        if(added) {
            cmboxGrupos.getItems().add(grupo);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contacto añadido al grupo");
            alert.setHeaderText("El contacto fue añadido al grupo correctamente");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al añadir");
            alert.setHeaderText("Hubo un problema al intentar añadir al contacto al grupo seleccionado");
            alert.showAndWait();
        }
    }

    @FXML
    void addMail(MouseEvent event) throws IOException {
        // Llevará a una nueva página para asignarle un nuevo correo (y etiqueta si quiere)
        Stage stageEdit = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/view/addEmailContacto.fxml").openStream());
        AddEmailContacto editController = (AddEmailContacto) loader.getController();
        editController.recibeController(this);
        Scene escenario = new Scene(root);
        stageEdit.setTitle("Añadir email");
        stageEdit.setScene(escenario);
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.show();
    }

    public void recogeDatosAddMail (Email mail){
        // LLamada al modelo para crear el objeto
        boolean added = mailDao.addEmailContacto(contacto.getId(), mail);

        // Actualizacion de la vista
        if(added){
            String valor = mail.getEtiquetaEmail()+": "+mail.getCorreo();
            cmboxMails.getItems().add(valor);
            cmboxMails.getSelectionModel().select(0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email añadido");
            alert.setHeaderText("El email fue añadido correctamente");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al añadir");
            alert.setHeaderText("Hubo un problema al intentar añadir el nuevo email");
            alert.showAndWait();
        }
    }

    @FXML
    void addTlf(MouseEvent event) throws IOException {
        // Llevará a una nueva página para asignarle un nuevo telefono al usuario (y etiqueta si quiere)
        Stage stageEdit = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/view/addTelefonoContacto.fxml").openStream());
        AddTelefonoContactoController tlfController = (AddTelefonoContactoController) loader.getController();
        tlfController.recibeController(this);
        Scene escenario = new Scene(root);
        stageEdit.setTitle("Crear telefono");
        stageEdit.setScene(escenario);
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.show();
    }

    public void recogeDatosAddTlf (Telefono tlf){
        // Recibe los datos y hace una llamada al modelo para crear el telefono

        // boolean added = daoTelefono.addTelefono(int id, Telefono tlf);
        boolean added = true;
        // Actualizacion de la vista
        if(added){
            String valor = tlf.getEtiquetaTelefono()+": "+tlf.getNumero();
            cmboxTlfs.getItems().add(valor);
            cmboxTlfs.getSelectionModel().select(0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Telefono añadido");
            alert.setHeaderText("El telefono fue añadido correctamente");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al añadir");
            alert.setHeaderText("Hubo un problema al intentar añadir el nuevo telefono");
            alert.showAndWait();
        }

    }

    @FXML
    void deleteGrupo(MouseEvent event) {
        // En base a lo que tuviera seleccionado en el combobox de grupos, se actualizará la combobox para borrarla
        boolean borrado = grupoDao.borrarContactoGrupo(contacto.getId(), cmboxGrupos.getValue());
        // Se hace la llamada a la bbdd y en base a lo devuelto se actualiza o no la combobox
        if(borrado && cmboxGrupos.getItems().indexOf(cmboxGrupos.getValue()) >= 0){
            //GESTION PARA BORRARLO
            cmboxGrupos.getItems().remove(cmboxGrupos.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Contacto borrado del grupo");
            alert.setHeaderText("El contacto fue borrado del grupo correctamente");
            alert.showAndWait();
        }else{
            // NO SE PUDO BORRAR, MENSAJE DE ERROR
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar");
            alert.setHeaderText("Hubo un problema al intentar borrar el contacto del grupo seleccionado");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteMail(MouseEvent event) {
        // En base a lo que tuviera seleccionado en el combobox de emails, se actualizará la combobox para borrarla
        String valores = cmboxGrupos.getValue();
        String[] valoresSeparados = valores.split(": ");
        boolean borrado = mailDao.borrarMail(contacto.getId(), valoresSeparados[1]);
        // Se hace la llamada a la bbdd y en base a lo devuelto se actualiza o no la combobox
        if(borrado && cmboxMails.getItems().indexOf(cmboxMails.getValue()) >= 0){
            //GESTION PARA BORRARLO
            cmboxMails.getItems().remove(cmboxMails.getValue());
            cmboxMails.getSelectionModel().select(0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email del contacto borrado con exito");
            alert.setHeaderText("El email del contacto fue borrado correctamente");
            alert.showAndWait();
        }else{
            // NO SE PUDO BORRAR, MENSAJE DE ERROR
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar");
            alert.setHeaderText("Hubo un problema al intentar borrar el email seleccionado");
            alert.showAndWait();
        }
    }

    @FXML
    void deleteTlf(MouseEvent event) {
        // En base a lo que tuviera seleccionado en el combobox de telefonos, se actualizará la combobox para borrarla
        boolean borrado = true;
        // Se hace la llamada a la bbdd y en base a lo devuelto se actualiza o no la combobox
        if(borrado && cmboxTlfs.getItems().indexOf(cmboxTlfs.getValue()) >= 0){
            //GESTION PARA BORRARLO
            cmboxTlfs.getItems().remove(cmboxTlfs.getValue());
            cmboxTlfs.getSelectionModel().select(0);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Telefono del contacto borrado");
            alert.setHeaderText("El telefono del contacto fue borrado correctamente");
            alert.showAndWait();
        }else{
            // NO SE PUDO BORRAR, MENSAJE DE ERROR
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar");
            alert.setHeaderText("Hubo un problema al intentar borrar el telefono seleccionado");
            alert.showAndWait();
        }
    }

    @FXML
    void editMail(MouseEvent event) throws IOException {
        // Se llevará a una nueva página para editar el mail o la etiqueta que tenía
        Stage stageEdit = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/view/editEmailContacto.fxml").openStream());
        EditEmailContactoController editController = (EditEmailContactoController) loader.getController();

        String valores = cmboxMails.getValue();
        String[] valoresSeparados = valores.split(": ");

        editController.recibeControllerDatos(this, valoresSeparados[0], valoresSeparados[1]);
        Scene escenario = new Scene(root);
        stageEdit.setTitle("Modificar email");
        stageEdit.setScene(escenario);
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.show();
    }

    public void recogeDatosEditMail (String mailNuevo, String etiqueta){
        // LLamada al modelo para crear el objeto
        String valores = cmboxMails.getValue();
        String[] valoresSeparados = valores.split(": ");
        boolean added = mailDao.editEmailContacto(contacto.getId(), valoresSeparados[1], mailNuevo, etiqueta);

        // Actualizacion de la vista
        if(added){
            String valor = etiqueta+": "+mailNuevo;
            cmboxMails.getItems().set(cmboxMails.getItems().indexOf(cmboxMails.getValue()), valor);
            cmboxMails.getSelectionModel().select(0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Email modificado");
            alert.setHeaderText("El email fue modificado correctamente");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("Hubo un problema al intentar modificar el nuevo email");
            alert.showAndWait();
        }
    }

    @FXML
    void editTlf(MouseEvent event) throws IOException {
        // Se llevará a una nueva página para editar los telefonos o la etiqueta que tenía
        Stage stageEdit = new Stage();
        FXMLLoader loader = new FXMLLoader();
        AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/view/editTelefonoContacto.fxml").openStream());
        EditTelefonoContactoController editController = (EditTelefonoContactoController) loader.getController();

        String valores = cmboxTlfs.getValue();
        String[] valoresSeparados = valores.split(": ");

        editController.recibeControllerDatos(this, valoresSeparados[0], valoresSeparados[1]);
        Scene escenario = new Scene(root);
        stageEdit.setTitle("Modificar telefono");
        stageEdit.setScene(escenario);
        stageEdit.initModality(Modality.APPLICATION_MODAL);
        stageEdit.show();
    }

    public void recogeDatosEditTelefono (String telefono, String etiqueta){
        // LLamada al modelo para crear el objeto
        boolean added = true;

        // Actualizacion de la vista
        if(added){
            String valor = etiqueta+": "+telefono;
            cmboxTlfs.getItems().set(cmboxTlfs.getItems().indexOf(cmboxTlfs.getValue()), valor);
            cmboxTlfs.getSelectionModel().select(0);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Telefono modificado");
            alert.setHeaderText("El telefono fue modificado correctamente");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("Hubo un problema al intentar modificar el nuevo telefono");
            alert.showAndWait();
        }
    }

    @FXML
    void guardarTodo(MouseEvent event) {
        // Se guardaran el nombre y el apellido (si cambió)
        if(txtNombre.getText() != null && txtNombre.getText() != contacto.getNombre()){
            // Nombre cambiado
            contacto.setNombre(txtNombre.getText());
        }

        if(txtApellido.getText()!= null && txtApellido.getText() != contacto.getApellido()){
            // Apellido cambiado
            contacto.setApellido(txtApellido.getText());
        }
        // El resto de modificaciones se habrán hecho ya en las acciones de los botones
        Stage stage = (Stage) btnGuardar.getScene().getWindow();
        stage.close();
    }

    @FXML
    void volverAtras(MouseEvent event) {
        // Cerrará la nueva ventana
        Stage stage = (Stage) btnVolver.getScene().getWindow();
        stage.close();
    }

    public void recogeContacto(Contacto contacto){
        this.contacto = contacto;
        this.txtNombre.setText(this.contacto.getNombre());
        this.txtApellido.setText(this.contacto.getApellido());

        // TELEFONOS
        cargarTelefonos();

        // EMAILS
        cargarMails();

        // GRUPOS
        cargarGrupos();
    }

    public void cargarTelefonos () {
        ArrayList<Telefono> tlfsCont = telefonosDao.getTelefonosContacto(contacto.getId());
        ArrayList<String> auxTlf = new ArrayList<>();
        for(Telefono tlf : tlfsCont){
            int num = tlf.getNumero();
            String etiq = tlf.getEtiquetaTelefono();
            String itemBox = etiq+": "+num;
            auxTlf.add(itemBox);
        }
        ObservableList<String> telefonos = FXCollections.observableArrayList(auxTlf);
        cmboxTlfs.setItems(telefonos);
        cmboxTlfs.getSelectionModel().select(0);
    }

    public void cargarMails () {
        ArrayList<Email> mailCont = mailDao.getEmailsContacto(contacto.getId());
        ArrayList<String> auxMail = new ArrayList<>();
        for(Email mail : mailCont){
            String dir = mail.getCorreo();
            String etiq = mail.getEtiquetaEmail();
            String itemBox = etiq+": "+dir;
            auxMail.add(itemBox);
        }
        ObservableList<String> mails = FXCollections.observableArrayList(auxMail);
        cmboxMails.setItems(mails);
        cmboxMails.getSelectionModel().select(0);
    }

    public void cargarGrupos () {
        List<Grupos> grp = grupoDao.getGruposContacto(contacto.getId());
        ArrayList<String> auxGrp = new ArrayList<>();
        for(Grupos grupo : grp)
            auxGrp.add(grupo.getNombre());
        ObservableList<String> grupos = FXCollections.observableArrayList(auxGrp);
        cmboxGrupos.setItems(grupos);
        cmboxGrupos.getSelectionModel().select(0);
    }

}
