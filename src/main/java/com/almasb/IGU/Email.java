package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;


public class Email implements Comparable<Email>{

    private IntegerProperty id;
    private  StringProperty correo;
    private  StringProperty etiquetaEmail;

    public Email(){
        this.id = new SimpleIntegerProperty();
        this.correo= new SimpleStringProperty();
        this.etiquetaEmail= new SimpleStringProperty();
    }

    public Email(int id, String correo, String etiqueta){
        this.id= new SimpleIntegerProperty(id);
        this.correo= new SimpleStringProperty(correo);
        this.etiquetaEmail= new SimpleStringProperty(etiqueta);
    }

    // PROPIEDADES
    public IntegerProperty idProperty() {
        return id;
    }
    public StringProperty numeroProperty() {
        return correo;
    }
    public StringProperty etiquetaEmailProperty() {
        return etiquetaEmail;
    }

    // SETTERS
    public void setId(int id) { this.id.set(id); }
    public void setCorreo(String correo){
        this.correo.set(correo);
    }
    public void setEtiquetaEmail(String etiqueta) { this.etiquetaEmail.set(etiqueta); }

    // GETTERS
    public int getId() {
        return id.get();
    }
    public String getCorreo() {
        return correo.get();
    }
    public String getEtiquetaEmail() {
        return etiquetaEmail.get();
    }

    @Override
    public int compareTo(Email o) {
        if(this.getCorreo().equals(o.getCorreo()) /*&& this.getEtiquetaEmail().equals(o.getEtiquetaEmail())*/)
            return -1;
        return 0;
    }
}


