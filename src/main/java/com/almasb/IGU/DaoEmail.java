package com.almasb.IGU;

import javax.sql.DataSource;
import java.util.ArrayList;

// @Repository
public class DaoEmail {

    // INICIACIÓN DE LA CLASE
    // private JdbcTemplate jdbcTemplate;

    // @Autowired
    // public void setDataSource(DataSource dataSource) { jdbcTemplate = new JdbcTemplate(dataSource); }

    // METODOS
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
}
