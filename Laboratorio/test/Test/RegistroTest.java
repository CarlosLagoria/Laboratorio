package Test;

import excepciones.DataAccessException;
import laboratorio.GestorRegistro;
import laboratorio.Registro;
import org.junit.Test;
import static org.junit.Assert.*;

public class RegistroTest {

    public RegistroTest() {}
    Registro registro = new Registro();

    @Test
    public void getDniTest() {
        registro.setDni(34911255);
        assertEquals(34911255, registro.getDni());        
    }

    @Test
    public void getCodigoTest() {
        registro.setCodigo(10);
        assertEquals(10, registro.getCodigo());
    }

    @Test
    public void getDevolucionTest() {
        registro.setDevolucion("Si");
        assertEquals("Si", registro.getDevolucion());
    }

    @Test
    public void getFechaDevolucionTest() throws DataAccessException {
        GestorRegistro gestor = new GestorRegistro();
        Registro registro = new Registro();
        registro.setFechadevolucion(gestor.fechaString("2010-05-05"));
        assertEquals(gestor.fechaString("2010-05-05"), registro.getFechadevolucion());
    }

    @Test
    public void getFechaEntregaTest() throws DataAccessException {
        GestorRegistro gestor = new GestorRegistro();
        Registro registro = new Registro();
        registro.setFechaentrega(gestor.fechaString("2010-05-05"));
        assertEquals(gestor.fechaString("2010-05-05"),registro.getFechaentrega());
    }
}
