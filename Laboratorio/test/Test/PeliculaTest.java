package Test;

import laboratorio.Pelicula;
import org.junit.Test;
import static org.junit.Assert.*;

public class PeliculaTest {

    public PeliculaTest() {
    }
    Pelicula peli = new Pelicula();

    @Test
    public void getCodigoTest() {
        peli.setCodigo(10);
        assertEquals(10, peli.getCodigo());
    }

    @Test
    public void getAutorTest() {
        peli.setAutor("Lagoria");
        assertEquals("Lagoria", peli.getAutor());
    }

    @Test
    public void getTituloTest() {
        peli.setTitulo("El señor de los anillos");
        assertEquals("El señor de los anillos", peli.getTitulo());
    }

    @Test
    public void getEjemplaresTest() {
        peli.setEjemplares(10);
        assertEquals(10, peli.getEjemplares());
    }
}
