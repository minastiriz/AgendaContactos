package com.almasb.IGU;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Grupos {

    private StringProperty nombre;

    // CONSTRUCTORES
    public Grupos (){
        this.nombre = new SimpleStringProperty();
    }
    public Grupos (String nombre){
        this.nombre = new SimpleStringProperty(nombre);
    }


    // PROPIEDADES
    public StringProperty nombreProperty(){return nombre; }

    // SETTERS

    public void setNombre(String nombre){ this.nombre.set(nombre); }

    // GETTERS
    public String getNombre() { return nombre.get(); }

}
