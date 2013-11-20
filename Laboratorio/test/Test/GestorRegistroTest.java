package Test;

import excepciones.DataAccessException;
import java.sql.Date;
import laboratorio.GestorRegistro;
import org.junit.Test;
import static org.junit.Assert.*;

public class GestorRegistroTest {

    public GestorRegistroTest() {
    }

    @Test
    public void fechaTest() throws DataAccessException {
        GestorRegistro gestor = new GestorRegistro();
        assertEquals(gestor.fechaString("2013-11-10"), gestor.fecha());//DIA ACTUAL
    }

    @Test
    public void fechaStringTest() throws DataAccessException {
        GestorRegistro gestor = new GestorRegistro();
        assertEquals(gestor.fecha(), gestor.fechaString("2013-11-10"));
    }

    @Test
    public void fechaDateTest() throws DataAccessException {
        GestorRegistro gestor = new GestorRegistro();
        assertEquals("2013-11-10", gestor.fechaDate(gestor.fechaString("2013-11-10")));
    }

    @Test
    public void fechadevolucionTest() throws DataAccessException {
        GestorRegistro gestor = new GestorRegistro();
        assertEquals(gestor.fechaString("2013-11-17"), gestor.fechadevolucion());
    }
}
