package com.almasb.DAO;

import java.util.ArrayList;
import java.util.List;

public class GruposDao {

    public ArrayList<String> getGrupos(){
        // Método que recoge un listado de todos los grupos creados hasta el momento
        // Servirá para poder listarlos en la app
        // Devolverá siempre una lista

        ArrayList<String> grupos = new ArrayList<>();
        grupos.add("Familia");
        grupos.add("Amigos");
        grupos.add("Trabajo");
        return grupos;
    }




}
