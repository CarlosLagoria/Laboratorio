
package laboratorio;

import basededatos.PeliculaDAO;
import excepciones.DataAccessException;
import java.util.ArrayList;
import java.util.Collection;

public class GestorPelicula {
    
    private ArrayList peliculas = new ArrayList();
    private PeliculaDAO peliculaDAO = new PeliculaDAO();
    
    public GestorPelicula() throws DataAccessException {
        peliculas=(ArrayList)peliculaDAO.findAll();
    }
    
    //AGREGAR
    public void agregarPelicula (Pelicula pelicula) throws DataAccessException{
        peliculaDAO.agregar(pelicula);
        peliculas.add(pelicula);
    }
        
    //ACTUALIZAR
    public void actualizarPelicula (Pelicula pelicula, int codigo) throws DataAccessException{
        peliculaDAO.actualizar(pelicula, codigo);
    }
    
    //ELIMINAR
    public void eliminarPelicula (int codigo) throws DataAccessException{
        peliculaDAO.eliminar(codigo);
    }
     
    //BUSCAR UNA PELICULA ESPECIFICA
    public Pelicula buscarPelicula (int codigo) throws DataAccessException{
        Pelicula buscada = peliculaDAO.buscar(codigo);
        return buscada;
    }
    
    //MOSTRAR TODOAS LAS PELICULAS
    public Collection mostrarPeliculas() throws DataAccessException {
        Collection peliculas = peliculaDAO.findAll();
        return peliculas;
    }
    
    //METODO QUE TOMA EL VALOR DE NUMERO DE EJEMPLARES DE UNA PELICULA
    public int ejemplares(int codigo) throws DataAccessException {
        int ejemplar = peliculaDAO.ejemplares(codigo);
        return ejemplar;
    }
    
    //ACTUALIZA EL VALOR DE NUMERO DE EJEMPLARES DE UNA PELICULA
    public void actualizarEjemplares(int ejemplar, int codigo) throws DataAccessException {
        peliculaDAO.actualizarEjemplares(ejemplar,codigo);
    }
}
