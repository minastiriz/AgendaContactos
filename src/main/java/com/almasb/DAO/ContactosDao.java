package com.almasb.DAO;

import com.almasb.IGU.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ContactosDao {

    public void anadirContacto(Contacto contacto) {
    }

    public List<Contacto> getContactos(String textFieldContenido, String nombre) {
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactosNonmbreGrupo(String textFieldContenido) {
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactosEtiquetaEmail(String textFieldContenido) {
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactosEtiquetaTelefono(String textFieldContenido) {
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactos() {
        //Devuelve todos los contactos
        //return null;
        ArrayList<Contacto> lista =new ArrayList<>();
        Contacto contacto1 = new Contacto(1, "Miguel", "López");
        Contacto contacto2 = new Contacto(2, "Carlos", "Juan");
        lista.add(contacto1);
        lista.add(contacto2);
        return lista;
    }
}
