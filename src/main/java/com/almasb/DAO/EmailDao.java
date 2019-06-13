package com.almasb.DAO;

import com.almasb.IGU.Email;

import javax.sql.DataSource;
import java.util.ArrayList;

public class EmailDao {

    public ArrayList<Email> getEmailsContacto(int id) {
        // Método para recoger todos los emails asociados a un contacto
        // Recibirá como parámetro el id de un contacto.
        // Devolverá una lista con los objetos Email correspondientes a los mails asociados al contacto
        ArrayList<Email> lista =new ArrayList<>();
        Email email = new Email(1,"boqweiybcf@test.es","uji");
        Email email2 = new Email(2, "boqweiybcf@test.es", "trabajo");
        lista.add(email);
        lista.add(email2);
        return lista;
    }

    public boolean borrarMail(int id, String dir){
        // Método que borrará un mail asociado a un usuario
        // Recibirá el id del usuario y la direccion de correo a eliminar
        // Devolverá un boolean indicando si se borró correctamente o no.
        return true;
    }

    public boolean addEmailContacto(Email mail){
        // Método para añadir un email a un contacto.
        // Email a añadir.
        // Devolverá un boolean indicando si se añadió correctamente o no.
        return true;
    }

    public boolean editEmailContacto(int id, String dirAntigua, String dirNueva, String etiqNueva){
        // Método que cambiará los datos de un email
        // Recibirá el id del contacto al que pertenece, el antiguo correo y los dos campos actualizados (direccion y etiqueta nueva)
        // Devolverá un boolean indicando si la modificación se completó correctamente.
        return true;
    }

    public boolean existeCorreo(String text) {
        return false;

    }
}
