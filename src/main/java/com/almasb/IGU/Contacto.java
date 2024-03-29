package com.almasb.IGU;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.*;

public class Contacto {

    private IntegerProperty id;

    private StringProperty nombre;

    private StringProperty apellido;

    private ArrayList<Telefono> telefonosContacto;

    private ArrayList<Email> emailsContacto;

    private ArrayList<String> gruposContacto;

    public Contacto() {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.apellido = new SimpleStringProperty();
    }

    public Contacto(int id, String nombre, String apellido) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);


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

    public void setNombre(String nombre) {
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


    public ArrayList<Telefono> getTelefonosContacto() {
        return telefonosContacto;
    }

    public void setTelefonosContacto(ArrayList<Telefono> telefonosContacto) {
        this.telefonosContacto = telefonosContacto;
    }

    public ArrayList<Email> getEmailsContacto() {
        return emailsContacto;
    }

    public void setEmailsContacto(ArrayList<Email> emailsContacto) {
        this.emailsContacto = emailsContacto;
    }

    public ArrayList<String> getGruposContacto() {
        return gruposContacto;
    }

    public void setGruposContacto(ArrayList<String> gruposContacto) {
        this.gruposContacto = gruposContacto;
    }

    /*
    public static ArrayList<Contacto> getContactos(){

    }

    public static ArrayList<Contacto> getContactos(String buscar, String tipo){
        //Devuelve todos los contactos
        //return null;
        //Buscar por tipo==Apellidos o sino Nombre
        /*
         ArrayList<Contacto> lista =new ArrayList<>();
        Contacto contacto1 = new Contacto(1, "Miguel", "López");
        Contacto contacto2 = new Contacto(2, "Carlos", "Juan");
        lista.add(contacto1);
        lista.add(contacto2);
        return lista;


        Contacto contacto2 = new Contacto(2, "Carlos", "Juan");
        ArrayList<Contacto> lista = new ArrayList<Contacto>();
        lista.add(contacto2);
        return lista;

    }

    public  Contacto getContacto(int id){

        return null;
    }

    public static List<Contacto> getContactosEtiquetaTelefono(String textFieldContenido) {
        //Devuelve una lista de contactos con la etiqueta telefono indicada
        return new ArrayList<Contacto>();
    }

    public static List<Contacto> getContactosEtiquetaEmail(String textFieldContenido) {
        // Devuelve una lista de contactos con la etiqueta Email indicada
        return new ArrayList<Contacto>();
    }

    public static List<Contacto> getContactosNonmbreGrupo(String textFieldContenido) {
        // Devuelve una lista de contactos con el nombre de grupo que paso como parametro
        return new ArrayList<Contacto>();
    }

     */
}
