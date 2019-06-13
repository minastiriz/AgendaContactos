package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GrupoContacto {

    private IntegerProperty Id;
    private StringProperty nombre;

    // CONSTRUCTORES
    public GrupoContacto (){
        this.nombre = new SimpleStringProperty();
        this.Id = new SimpleIntegerProperty();
    }


    public GrupoContacto (String nombre, int id) {
        this.nombre = new SimpleStringProperty(nombre);
        this.Id = new SimpleIntegerProperty(id);
    }


    // PROPIEDADES
    public StringProperty nombreProperty(){return nombre; }
    public IntegerProperty idProperty() { return Id;  }

    // SETTERS

    public void setNombre(String nombre){ this.nombre.set(nombre); }
    public void setId(int id){ this.Id.set(id); }

    // GETTERS
    public String getNombre() { return nombre.get(); }
    public int getId(){ return Id.get(); }




}
