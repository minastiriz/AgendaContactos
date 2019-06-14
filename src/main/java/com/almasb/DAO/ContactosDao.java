package com.almasb.DAO;

import com.almasb.IGU.*;
import com.almasb.utils.JsonParser;
import com.almasb.utils.Requester;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactosDao {
	
	private Requester req = new Requester();

    public void anadirContacto(Contacto contacto) {
        // Método para crear un contacto nuevo
        // Recibe al contacto como parámetro, en el cual se encuentra su información, tanto de usuario como de telefonos, emails y grupos
        TelefonosDao telefonoDao = new TelefonosDao();
        EmailDao emailDao = new EmailDao();
        GruposDao grupoDao = new GruposDao();

        Map<String, String> data = new HashMap<String, String>();
        data.put("id", ""+contacto.getId());
        data.put("apellido", contacto.getApellido());
        data.put("name", contacto.getNombre());
        contacto.setId(Integer.parseInt(req.requestPost("contacto", JsonParser.objectToJson(data))));
        for (Telefono tlf: contacto.getTelefonosContacto()){
            tlf.setId(contacto.getId());
            if(!telefonoDao.existeTelefono(tlf.getNumero())) telefonoDao.addTelefono(tlf);
        }
        for (Email email: contacto.getEmailsContacto()){
            email.setId(contacto.getId());
            if(!emailDao.existeCorreo(email.getCorreo())) emailDao.addEmailContacto(email);

        }
        for (String nombreGrupo: contacto.getGruposContacto()){
            Grupos grupo = new Grupos();
            grupo.setNombre(nombreGrupo);
            GrupoContacto gc = new GrupoContacto();
            gc.setId(contacto.getId());
            gc.setNombre(grupo.getNombre());
            grupoDao.addContactoGrupo(gc);
        }

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
        List<Contacto> contactos = getContactos();
        List<Contacto> contactosFiltrados = new ArrayList<>();

        switch (tipo){
            case "Nombre":
                for(Contacto contacto : contactos)
                    if(contacto.getNombre().length() >= nombre.length()){
                        if(contacto.getNombre().substring(0, nombre.length()).toLowerCase().equals(nombre.toLowerCase())){
                            contactosFiltrados.add(contacto);
                        }

                    }
                    break;
            case "Apellidos":
                for(Contacto contacto : contactos)
                    if(contacto.getApellido().length() >= nombre.length()) {
                        if (contacto.getApellido().substring(0, nombre.length()).toLowerCase().equals(nombre.toLowerCase())) {
                            contactosFiltrados.add(contacto);
                        }
                    }
                break;
        }

        return contactosFiltrados;
    }

    public List<Contacto> getContactosNonmbreGrupo(String nombre) {
        // Método para encontrar los contactos que pertenecen a un determinado grupo
        // Recibe como parámetro el nombre del grupo a buscar
        // Devolverá una lista con los contactos que coincidan con la busqueda. En caso de no haber coincidencias devolverá lista vacía.
        ArrayList<Contacto> lista = new ArrayList<>();
        for (Map<String, String> data: JsonParser.jsonToMapList(req.requestGet("cg/g/"+nombre.replace(" ", "%20")))){
            Contacto contacto = new Contacto();
            contacto.setApellido(data.get("apellido"));
            contacto.setNombre(data.get("name"));
            contacto.setId(Integer.parseInt(data.get("id")));
            lista.add(contacto);
        }
        return lista;
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
        Map<String, String> data = new HashMap<String, String>();
        data.put("id", ""+contacto.getId());
        data.put("apellido", contacto.getApellido());
        data.put("name", contacto.getNombre());
        try{
            req.requestPost("contacto/upd", JsonParser.objectToJson(data));
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
