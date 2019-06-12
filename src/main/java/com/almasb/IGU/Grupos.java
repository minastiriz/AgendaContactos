package com.almasb.IGU;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;
import java.util.List;

public class Grupos {

    private StringProperty nombre;

    public Grupos (String nombre){
        this.nombre = new SimpleStringProperty(nombre);
    }

    public Grupos (){
        this.nombre = new SimpleStringProperty();
    }

    public static List<Grupos> getGruposContacto(int id) {
        Grupos g1 = new Grupos("Tolai" );
        ArrayList<Grupos> lista= new ArrayList<Grupos>();
        lista.add(g1);
        return lista;
    }


    public void setNombre(String nombre){ this.nombre.set(nombre); }

    public StringProperty nombreProperty(){return nombre; }

    public String getNombre() { return nombre.get(); }



    public List<String> getGrupos(){
        // Método que recoge un listado de todos los grupos creados hasta el momento
        // Servirá para poder listarlos en la app
        // Devolverá siempre una lista

        ArrayList<String> grupos = new ArrayList<>();
        grupos.add("Familia");
        grupos.add("Amigos");
        grupos.add("Trabajo");
        return grupos;
    }

    public boolean borrarGrupo(String nombre){
        // Método para borrar un grupo, el cual pasan su nombre como parámetro.
        // Devuelve boolean dependiendo si se borró correctamente o no
        return true;
    }

    public boolean crearGrupo(String nombre){
        // Método para crear un grupo, el cual pasan su nombre como parámetro.
        // Devuelve true si se creó correctamente
        // Devuelve false si NO se creó (Nombre existente o similares)
        return true;
    }

    public boolean editarGrupo(String viejoNombre, String nuevoNombre){
        // Método para editar un grupo, el cual pasan su ANTIGUO nombre y su NUEVO nombre como parámetro.
        // Devuelve true si se editó correctamente
        // Devuelve false si NO se editó (Nombre existente o similares)
        return true;
    }

    public void añadirContactoGrupo (){

    }

    public void borrarContactoGrupo (){

    }


}
