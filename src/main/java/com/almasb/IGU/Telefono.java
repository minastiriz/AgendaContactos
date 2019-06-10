package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Telefono {

    private  IntegerProperty id;

    private  IntegerProperty numero;

    private  StringProperty etiqueta;

    public Telefono(){

    }

    public Telefono(int id, int numero, String etiqueta){
        this.id= new SimpleIntegerProperty(id);
        this.numero= new SimpleIntegerProperty(numero);
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


    public int getNumero() {
        return numero.get();
    }

    public void setNumero(int numero){
        this.numero.set(numero);
    }

    public IntegerProperty numeroProperty() {
        return numero;
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

