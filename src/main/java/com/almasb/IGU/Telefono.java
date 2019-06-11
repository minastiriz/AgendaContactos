package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Telefono {

    private  IntegerProperty id;

    private  IntegerProperty numero;

    private  StringProperty etiquetaTelefono;

    public Telefono(){

    }

    public Telefono(int id, int numero, String etiqueta){
        this.id= new SimpleIntegerProperty(id);
        this.numero= new SimpleIntegerProperty(numero);
        this.etiquetaTelefono= new SimpleStringProperty(etiqueta);
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



    public String getEtiquetaTelefono() {
        return etiquetaTelefono.get();
    }

    public void setEtiquetaTelefono(String etiqueta) {
        this.etiquetaTelefono.set(etiqueta);
    }

    public StringProperty etiquetaTelefonoProperty() {
        return etiquetaTelefono;
    }



    public  static ArrayList<Telefono> getTelefonosContacto(int id){

        ArrayList<Telefono> lista =new ArrayList<>();
        Telefono telefono1 = new Telefono(1,567890,"casa");
        Telefono telefono = new Telefono(2, 45678, "trabajo");
        lista.add(telefono1);
        lista.add(telefono);
        return lista;
    }

}

