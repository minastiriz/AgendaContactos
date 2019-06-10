package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Contacto {

    private  IntegerProperty id;

    private  StringProperty nombre;

    private  StringProperty apellido;

    public Contacto(){

    }

    public Contacto(int id, String nombre, String apellido){
        this.id= new SimpleIntegerProperty(id);
        this.nombre= new SimpleStringProperty(nombre);
        this.apellido= new SimpleStringProperty(apellido);
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


    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre){
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }



    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }





}
