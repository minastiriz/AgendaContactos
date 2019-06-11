package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Email {

    private IntegerProperty id;

    private  StringProperty correo;

    private  StringProperty etiquetaEmail;

    public Email(){

    }

    public Email(int id, String correo, String etiqueta){
        this.id= new SimpleIntegerProperty(id);
        this.correo= new SimpleStringProperty(correo);
        this.etiquetaEmail= new SimpleStringProperty(etiqueta);
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



    public String getEtiquetaEmail() {
        return etiquetaEmail.get();
    }

    public void setEtiquetaEmail(String etiqueta) {
        this.etiquetaEmail.set(etiqueta);
    }

    public StringProperty etiquetaEmailProperty() {
        return etiquetaEmail;
    }


    public  static ArrayList<Email> getEmailsContacto(int id) {
        ArrayList<Email> lista =new ArrayList<>();
        Email email = new Email(1,"boqweiybcf@test.es","uji");
        Email email2 = new Email(2, "boqweiybcf@test.es", "trabajo");
        lista.add(email);
        lista.add(email2);
        return lista;
    }



}


