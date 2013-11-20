package Test;

import laboratorio.Cliente;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClienteTest {

    public ClienteTest() {
    }
    Cliente cliente = new Cliente();

    @Test
    public void getDniTest() {
        cliente.setDni(34915123);
        assertEquals(34915123, cliente.getDni());
    }

    @Test
    public void getNombreTest() {
        cliente.setNombre("Carlos Lagoria");
        assertEquals("Carlos Lagoria", cliente.getNombre());
    }

    @Test
    public void getDomicilioTest() {
        cliente.setDomicilio("Almagro");
        assertEquals("Almagro", cliente.getDomicilio());
    }

    @Test
    public void getTelefonoTest() {
        cliente.setTelefono(4420625);
        assertEquals(4420625, cliente.getTelefono());
    }
}
