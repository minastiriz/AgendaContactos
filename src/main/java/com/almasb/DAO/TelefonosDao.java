package com.almasb.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.almasb.IGU.Telefono;
import com.almasb.utils.JsonParser;
import com.almasb.utils.Requester;

public class TelefonosDao {
	
	private Requester req = new Requester();
	
    public ArrayList<Telefono> getTelefonosContacto(int id) {
        // Metodo para recoger los telefonos de un contacto
        // Recibirá el id de un contacto
        // Devolverá un listado con los telefonos que pertenecen a ese contacto. En caso de no tener devuelve lista vacía.
    	ArrayList<Telefono> lista = new ArrayList<>();
    	for (Map<String, String> data: JsonParser.jsonToMapList(req.requestGet("telefono/"+id))){
    		Telefono telefono = new Telefono();
    		telefono.setId(id);
    		telefono.setEtiquetaTelefono(data.get("etiqueta"));
    		telefono.setNumero(Integer.parseInt(data.get("numero")));
    		lista.add(telefono);
    	}
    	return lista;
    }

    public boolean existeTelefono(int numero) {
        // Método para comprobar si un teléfono existe
        // Recibirá como parametro el numero en cuestion
        // Devolvera un boolean indicando si existe o no
    	for (Map<String, String> data: JsonParser.jsonToMapList(req.requestGet("telefono/all"))){
    		if (data.get("numero").equals(numero)){
    			return true;
    		}
    	}
    	return false;
    }

    public boolean addTelefono(Telefono tlf) {
        // Método para añadir un nuevo telefono a un contacto
        // Recibirá como parámetros el id del contacto y el telefono a añadir en cuestion
        // Devolvera un boolean dependiendo si se añade correctamente
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("id", ""+tlf.getId());
    	data.put("numero", ""+tlf.getNumero());
    	data.put("etiqueta", tlf.getEtiquetaTelefono());
    	
    	req.requestPost("telefono", JsonParser.objectToJson(data));
        return true;
    }

    public boolean borrarTelefono(int id, String telefono) {
        // Método para borrar un telefono a un contacto
        // Recibirá como parámetros el id del contacto y el telefono en cuestion
        // Devolverá un boolean dependiendo de si se ha borrado correctamente
    	try{
    		req.requestPost("telefono/" + telefono, null);
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
        return true;
    }

    public boolean editarTelefono(int id, String antiguoNum, String nuevoNum, String nuevaEtiqueta) {
        // Método para editar un telefono perteneciente a un contacto
        // Recibirá como parámetros el id del contacto, el antiguo numero y los nuevos datos
        // Devolverá un boolean dependiendo de si se ha modificado correctamente
    	Telefono telefono = new Telefono();
    	telefono.setEtiquetaTelefono(nuevaEtiqueta);
    	telefono.setNumero(Integer.parseInt(nuevoNum));
    	telefono.setId(id);
    	borrarTelefono(id, antiguoNum);
    	addTelefono(telefono);
        return true;
    }

    public Set<String> etiquetasTelefonosDisponibles() {
    	Set<String> s = new HashSet<String>();
    	String l = req.requestGet("email/etq");
    	l = l.substring(1, l.length()-1);
    	l = l.replaceAll("\"", "");
    	for (String etiqueta: l.split(",")){
    		s.add(etiqueta);
    	}
    	return s;
    }
}
