package com.almasb.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.almasb.IGU.Contacto;
import com.almasb.IGU.GrupoContacto;
import com.almasb.IGU.Grupos;
import com.almasb.utils.JsonParser;
import com.almasb.utils.Requester;

public class GruposDao {
	
	private Requester req = new Requester();
	private ContactosDao contactosDao = new ContactosDao();
	private GrupoContacto grupoContacto = new GrupoContacto();

	public boolean existeGrupo(Grupos grupo){
	    // Método que comprobara si existe un grupo que intentemos crear o editar
        // Recibe como parámetro el grupo que deseamos comprobar si existe
        // Devuelve un boolean indicando si se creó correctamente o no
        return getGrupos().contains (grupo);
    }

    public List<Grupos> getGruposContacto(int id) {
        // Método para recoger los grupos a los que está asociado un contacto.
        // Recibirá el id de un contacto como parámetro.
        // Devolverá una lista de grupos.
        ArrayList<Grupos> lista = new ArrayList<>();
        for (Map<String, String> data: JsonParser.jsonToMapList(req.requestGet("cg/c/"+id))){
        	Grupos grupo = new Grupos();
        	grupo.setNombre(data.get("nombre"));
        	lista.add(grupo);
        }
        return lista;
    }

    public List<Grupos> getGrupos(){
        // Método que crea un listado de todos los grupos creados hasta el momento.
        // Devolverá una lista.
    	ArrayList<Grupos> lista = new ArrayList<>();
        for (Map<String, String> data: JsonParser.jsonToMapList(req.requestGet("grupo"))){
        	Grupos grupo = new Grupos();
        	grupo.setNombre(data.get("nombre"));
        	lista.add(grupo);
        }
        return lista;
    }

    public boolean borrarGrupo(String nombre){
        // Método para borrar un grupo.
        // Recibirá como parámetro el nombre del grupo en cuestión para proceder a borrarlo.
        // Devuelve boolean dependiendo si se borró correctamente o no
    	try{
            List<Contacto> listaContactos = contactosDao.getContactosNonmbreGrupo(nombre);
            for (Contacto contacto : listaContactos) {
                GrupoContacto gpc = new GrupoContacto();
                gpc.setId(contacto.getId());
                gpc.setNombre(nombre);
                borrarContactoGrupo(contacto.getId(), nombre);
            }
    		req.requestPost("grupo/" + nombre.replace(" ", "%20"), null);
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
        return true;
    }

    public boolean crearGrupo(Grupos grp){
        // Método para crear un grupo.
        // Recibirá como parámetro el grupo a crear.
        // Devuelve boolean dependiendo si se añadió correctamente o no.
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("nombre", grp.getNombre());
    	
    	req.requestPost("grupo", JsonParser.objectToJson(data));
    	return true;
    }

    public boolean editarGrupo(String viejoNombre, String nuevoNombre){
        // Método para editar un grupo.
        // Recibirá como parámetro el viejo nombre que tenía y el nuevo nombre que tendrá.
        // Devuelve boolean dependiendo si se editó correctamente o hubieron fallos.

        List<Contacto> listaContactos = contactosDao.getContactosNonmbreGrupo(viejoNombre);
        Grupos grupo = new Grupos();
        grupo.setNombre(nuevoNombre);

        if(!existeGrupo(grupo) && crearGrupo(grupo)) {
            for (Contacto contacto : listaContactos) {
                GrupoContacto gpc = new GrupoContacto();
                gpc.setId(contacto.getId());
                gpc.setNombre(nuevoNombre);
                addContactoGrupo(gpc);
                borrarContactoGrupo(contacto.getId(), viejoNombre);
            }
            if(borrarGrupo(viejoNombre))
                return true;
        }
        return false;
    }

    public boolean addContactoGrupo (GrupoContacto grupo){
        // Método para añadir un GrupoContacto.
        // Recibirá como parámetros el GrupoContacto con el atributo id de su contacto
        // Devolverá un boolean indicando si se añadió de forma correcta o no.
    	req.requestPost("cg/"+grupo.getId()+"/"+grupo.getNombre().replace(" ", "%20"), null);
        return true;
    }

    public boolean borrarContactoGrupo (int id, String grupo){
        // Método para añadir un contacto a un grupo.
        // Recibirá como parámetros el id del contacto y el grupo al que se le quiere quitar.
        // Devolverá un boolean indicando si se le eliminó de forma correcta o no.
    	req.requestPost("cg/del/"+id+"/"+grupo.replace(" ", "%20"), null);
        return true;
    }
}
