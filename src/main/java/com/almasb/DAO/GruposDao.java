package com.almasb.DAO;

import com.almasb.IGU.Grupos;
import javafx.beans.property.StringProperty;

import java.util.ArrayList;

public class GruposDao {

    public List<Grupos> getGruposContacto(int id) {
        // Método para recoger los grupos a los que está asociado un contacto.
        // Recibirá el id de un contacto como parámetro.
        // Devolverá una lista de grupos.
        ArrayList<Grupos> lista = new ArrayList<>();
        Grupos g1 = new Grupos("Familia" );
        lista.add(g1);
        return lista;
    }

    public List<String> getGrupos(){
        // Método que crea un listado de todos los grupos creados hasta el momento.
        // Devolverá una lista.
        ArrayList<String> grupos = new ArrayList<>();
        grupos.add("Familia");
        grupos.add("Amigos");
        grupos.add("Trabajo");
        return grupos;
    }

    public boolean borrarGrupo(String nombre){
        // Método para borrar un grupo.
        // Recibirá como parámetro el nombre del grupo en cuestión para proceder a borrarlo.
        // Devuelve boolean dependiendo si se borró correctamente o no
        return true;
    }

    public boolean crearGrupo(Grupos grp){
        // Método para crear un grupo.
        // Recibirá como parámetro el grupo a crear.
        // Devuelve boolean dependiendo si se añadió correctamente o no.
        return true;
    }

    public boolean editarGrupo(String viejoNombre, String nuevoNombre){
        // Método para editar un grupo.
        // Recibirá como parámetro el viejo nombre que tenía y el nuevo nombre que tendrá.
        // Devuelve boolean dependiendo si se editó correctamente o hubieron fallos.
        return true;
    }

    public boolean addContactoGrupo (int id, String grupo){
        // Método para añadir un contacto a un grupo.
        // Recibirá como parámetros el id del contacto y el grupo al que se le quiere meter.
        // Devolverá un boolean indicando si se añadió de forma correcta o no.
        return true;
    }

    public boolean borrarContactoGrupo (int id, String grupo){
        // Método para añadir un contacto a un grupo.
        // Recibirá como parámetros el id del contacto y el grupo al que se le quiere quitar.
        // Devolverá un boolean indicando si se le eliminó de forma correcta o no.
        return true;
    }
}
