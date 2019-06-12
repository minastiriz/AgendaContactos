package com.almasb.IGU;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class DaoGrupo {

    // INICIACIÓN DE LA CLASE
    // private JdbcTemplate jdbcTemplate;

    // @Autowired
    // public void setDataSource(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    // METODOS
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

    public boolean crearGrupo(String nombre){
        // Método para crear un grupo.
        // Recibirá como parámetro el nombre del grupo a crear.
        // Devuelve boolean dependiendo si se añadió correctamente o no.
        return true;
    }

    public boolean editarGrupo(String viejoNombre, String nuevoNombre){
        // Método para editar un grupo.
        // Recibirá como parámetro el viejo nombre que tenía y el nuevo nombre que tendrá
        // Devuelve boolean dependiendo si se editó correctamente o hubieron fallos.
        return true;
    }

    public void añadirContactoGrupo (){

    }

    public void borrarContactoGrupo (){

    }
}
