package com.almasb.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.almasb.IGU.Email;
import com.almasb.utils.JsonParser;
import com.almasb.utils.Requester;

public class EmailDao {
	
	private Requester req = new Requester();

    public ArrayList<Email> getEmailsContacto(int id) {
        // Método para recoger todos los emails asociados a un contacto
        // Recibirá como parámetro el id de un contacto.
        // Devolverá una lista con los objetos Email correspondientes a los mails asociados al contacto
        ArrayList<Email> lista = new ArrayList<>();
        for (Map<String, String> data: JsonParser.jsonToMapList(req.requestGet("email/" + id))){
			Email email = new Email();
			email.setCorreo(data.get("correo"));
			email.setEtiquetaEmail(data.get("etiqueta"));
			email.setId(Integer.parseInt(data.get("id")));
			lista.add(email);
		}
        return lista;
    }

    public boolean borrarMail(int id, String dir){
        // Método que borrará un mail asociado a un usuario
        // Recibirá el id del usuario y la direccion de correo a eliminar
        // Devolverá un boolean indicando si se borró correctamente o no.
    	try{
    		req.requestPost("email/" + dir, null);
    	}catch(Exception e){
    		e.printStackTrace();
    		return false;
    	}
        return true;
    }

    public boolean addEmailContacto(Email mail){
        // Método para añadir un email a un contacto.
        // Email a añadir.
        // Devolverá un boolean indicando si se añadió correctamente o no.
    	Map<String, String> data = new HashMap<String, String>();
    	data.put("id", mail.getId()+"");
    	data.put("correo", mail.getCorreo());
    	data.put("etiqueta", mail.getEtiquetaEmail());
    	
       	req.requestPost("email", JsonParser.objectToJson(data));
        return true;
    }

    public boolean existeCorreo(String text) {
        return false;

    }
}
