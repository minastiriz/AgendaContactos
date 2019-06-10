package com.almasb.IGU;

import java.util.ArrayList;
import java.util.List;

public class Grupos {


    public List<String> getGrupos(){
        // Método que recoge un listado de todos los grupos creados hasta el momento
        // Servirá para poder listarlos en la app
        // Devolverá siempre una lista (deberá siempre tener los 3 básicos mostrados en el ejemplo)

        ArrayList<String> lista = new ArrayList<String>();
        lista.add("Familia");
        lista.add("Amigos");
        lista.add("Trabajo");
        return lista;
    }

    public void borrarGrupo(String nombre){
        // Método para borrar un grupo, el cual pasan su nombre como parámetro.
    }

    public boolean crearGrupo(String nombre){
        // Método para crear un grupo, el cual pasan su nombre como parámetro.
        // Devuelve true si se creó correctamente
        // Devuelve false si NO se creó

        return true;
    }

    public boolean editarGrupo(String viejoNombre, String nuevoNombre){
        // Método para editar un grupo, el cual pasan su ANTIGUO nombre y su NUEVO nombre como parámetro.
        // Devuelve true si se editó correctamente
        // Devuelve false si NO se editó

        return true;
    }

    public void añadirContactoGrupo (){

    }

    public void borrarContactoGrupo (){

    }
}
