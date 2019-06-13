package com.almasb.DAO;

import com.almasb.IGU.Contacto;
import com.almasb.utils.JsonParser;
import com.almasb.utils.Requester;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContactosDao {
	
	private Requester req = new Requester();

    public void anadirContacto(Contacto contacto) {
        // Método para crear un contacto nuevo
        // Recibe al contacto como parámetro, en el cual se encuentra su información, tanto de usuario como de telefonos, emails y grupos
    }

    public boolean borrarContacto (Contacto contacto) {
        // Método para borrar un contacto existente
        // Recibe como parámetro un contacto en el cual se encuentra su información
        // Devolverá un boolean dependiendo de si se borra correctamente o no
    	try{
    		req.requestPost("contacto/" + contacto.getId(), null);
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
        return true;
    }

    public List<Contacto> getContactos(String nombre, String tipo) {
        // Método para recoger un listado de los contactos los cuales coincide su nombre o apellido
        // Recibe como parámetro el nombre o apellido a buscar y el tipo de busqueda, si se trata de buscar por apellido o nombre
        // Devolverá una lista con los contactos que coincidan. En caso de no haber coincidencias devolverá una lista vacía.
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactosNonmbreGrupo(String nombre) {
        // Método para encontrar los contactos que pertenecen a un determinado grupo
        // Recibe como parámetro el nombre del grupo a buscar
        // Devolverá una lista con los contactos que coincidan con la busqueda. En caso de no haber coincidencias devolverá lista vacía.
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactosEtiquetaEmail(String etiqueta) {
        // Método para buscar contactos por etiqueta de mail
        // Recibirá como parámetro el nombre de la etiqueta a buscar
        // Devolverá una lista con los contactos que coincidan con la busqueda. En caso de no haber coincidencias devolverá lista vacía.
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactosEtiquetaTelefono(String etiqueta) {
        // Método para buscar contactos por etiqueta de telefono
        // Recibirá como parámetro el nombre de la etiqueta a buscar
        // Devolverá una lista con los contactos que coincidan con la busqueda. En caso de no haber coincidencias devolverá lista vacía.
        return new ArrayList<Contacto>();
    }

    public List<Contacto> getContactos() {
        // Método para buscar todos los contactos registrados hasta el momento.
        // Devolverá una lista con los contactos. En caso de no haber coincidencias devolverá lista vacía.
    	ArrayList<Contacto> lista =new ArrayList<Contacto>();
        for (Map<String, String> c: JsonParser.jsonToMapList(req.requestGet("contacto"))){
			Contacto contacto = new Contacto();
			contacto.setApellido(c.get("apellido"));
			contacto.setNombre(c.get("name"));
			contacto.setId(Integer.parseInt(c.get("id")));
			lista.add(contacto);
		}
        return lista;
    }

    public boolean editarContacto (Contacto contacto){
        // Método para editar un contacto sus campos nombre y apellidos
        // Recibirá como parámetros un objeto contacto con la información guardada
        // Devolverá un boolean indicando si la modificación se realizó con exito.
        return true;
    }
}
