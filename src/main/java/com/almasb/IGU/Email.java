package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Email {

    private IntegerProperty id;

    private  StringProperty correo;

    private  StringProperty etiqueta;

    public Email(){

    }

    public Email(int id, String correo, String etiqueta){
        this.id= new SimpleIntegerProperty(id);
        this.correo= new SimpleStringProperty(correo);
        this.etiqueta= new SimpleStringProperty(etiqueta);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }


    public String getCorreo() {
        return correo.get();
    }

    public void setNumero(String correo){
        this.correo.set(correo);
    }

    public StringProperty numeroProperty() {
        return correo;
    }



    public String getEtiqueta() {
        return etiqueta.get();
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta.set(etiqueta);
    }

    public StringProperty etiquetaProperty() {
        return etiqueta;
    }





}


