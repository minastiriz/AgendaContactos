package com.almasb.DAO;

import com.almasb.IGU.Telefono;

import java.util.ArrayList;

public class TelefonosDao {
    public ArrayList<Telefono> getTelefonosContacto(int id) {
        // Metodo para recoger los telefonos de un contacto
        // Recibirá el id de un contacto
        // Devolverá un listado con los telefonos que pertenecen a ese contacto. En caso de no tener devuelve lista vacía.
        return new ArrayList<Telefono>();
    }

    public boolean existeTelefono(int numero) {
        // Método para comprobar si un teléfono existe
        // Recibirá como parametro el numero en cuestion
        // Devolvera un boolean indicando si existe o no
        return false;
    }

    public boolean addTelefono(Telefono tlf) {
        // Método para añadir un nuevo telefono a un contacto
        // Recibirá como parámetros el id del contacto y el telefono a añadir en cuestion
        // Devolvera un boolean dependiendo si se añade correctamente
        return true;
    }

    public boolean borrarTelefono(int id, String telefono) {
        // Método para borrar un telefono a un contacto
        // Recibirá como parámetros el id del contacto y el telefono en cuestion
        // Devolverá un boolean dependiendo de si se ha borrado correctamente
        return  true;
    }

    public boolean editarTelefono(int id, String antiguoNum, String nuevoNum, String nuevaEtiqueta) {
        // Método para editar un telefono perteneciente a un contacto
        // Recibirá como parámetros el id del contacto, el antiguo numero y los nuevos datos
        // Devolverá un boolean dependiendo de si se ha modificado correctamente
        return true;
    }
}
