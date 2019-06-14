package com.almasb.controladores;

import com.almasb.DAO.GruposDao;
import com.almasb.IGU.Grupos;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GruposController implements Initializable {

    private GruposDao grupoDao = new GruposDao();

    public void setGrupoDao(GruposDao grupoDao) { this.grupoDao = grupoDao; }

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
    void borrarGrupo(MouseEvent event) {
        //Hace referencia al boton de borrar, borrará un grupo seleccionado en el box
        boolean borrado = grupoDao.borrarGrupo(gruposBox.getValue());
        if(borrado && gruposBox.getItems().indexOf(gruposBox.getValue()) >= 0){
            //GESTION PARA BORRARLO
            gruposBox.getItems().remove(gruposBox.getValue());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Grupo borrado");
            alert.setHeaderText("El grupo fue borrado correctamente");
            alert.showAndWait();
        }else{
            // NO SE PUDO BORRAR, MENSAJE DE ERROR
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al borrar grupo");
            alert.setHeaderText("Hubo un problema al intentar borrar el grupo");
            alert.showAndWait();
        }
    }

    @FXML
    void crearGrupo(MouseEvent event) {
        //Hace referencia al boton de crear, creará un grupo con el nombre en el txtfield
        Grupos grp = new Grupos();
        grp.setNombre(txtCrearGrupo.getText());
        if(txtCrearGrupo != null && !txtCrearGrupo.getText().equals("") && grupoDao.existeGrupo(grp)){
            boolean res = grupoDao.crearGrupo(grp);
            if(res) {
                gruposBox.getItems().add(txtCrearGrupo.getText());
                txtCrearGrupo.setText("");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Grupo creado");
                alert.setHeaderText("El grupo se creó correctamente");
                alert.showAndWait();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al crear");
            alert.setHeaderText("El grupo no se pudo crear");
            alert.showAndWait();
        }
    }

    @FXML
    void editarGrupo(MouseEvent event) throws IOException {
        //Hace referencia al boton modificar, llevará a una ventana en la que podrá cambiar el nombre al grupo seleccionado en el box
        if(gruposBox.getItems().indexOf(gruposBox.getValue()) >= 0){
            Stage stageEdit = new Stage();
            FXMLLoader loader = new FXMLLoader();
            AnchorPane root = (AnchorPane) loader.load(getClass().getResource("/view/modificarGrupo.fxml").openStream());
            EditGrupoController editController = (EditGrupoController) loader.getController();
            editController.recibeNombre(this, gruposBox.getValue());
            Scene escenario = new Scene(root);
            stageEdit.setTitle("Editar Grupo");
            stageEdit.setScene(escenario);
            stageEdit.initModality(Modality.APPLICATION_MODAL);
            stageEdit.show();
        } else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("El grupo seleccionado no puede ser modificado");
            alert.showAndWait();
        }

    }

    @FXML
    public void realizaModificacion(String nombre){
        // Este metodo recibe el nombre modificado de parte de la vista de modificacion

        boolean res = grupoDao.editarGrupo(gruposBox.getValue(), nombre);
        if(res){
            gruposBox.getItems().set(gruposBox.getItems().indexOf(gruposBox.getValue()), nombre);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Grupo modificado");
            alert.setHeaderText("El grupo fue modificado correctamente");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error al modificar");
            alert.setHeaderText("Hubo un problema al intentar modificar el grupo");
            alert.showAndWait();
        }
        gruposBox.getSelectionModel().select(0);
    }

    @FXML
    void volverAnterior(MouseEvent event) {
        //Hace referencia al botón volver, llevará a la ventana anterior en la que se encontraba el usuario
        Stage stage = (Stage) botonVolver.getScene().getWindow();
        stage.close();
    }

    @FXML
    private ObservableList<String> getItemsBox(){
        // Este metodo permitirá comunicar al controlador con el modelo para recoger los datos de los grupos
        List<Grupos> nombresGrupos = grupoDao.getGrupos();
        ArrayList<String> aux = new ArrayList<String>();
        for(Grupos grupo : nombresGrupos) aux.add(grupo.getNombre());
        ObservableList<String> itemsBox = FXCollections.observableArrayList(aux);
        return itemsBox;
    }

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Al iniciar la vista se cargaran los datos de la base de datos para el combobox con todos los grupos creados
        gruposBox.setItems(getItemsBox());
        gruposBox.getSelectionModel().select(0);
    }
}