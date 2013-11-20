
package basededatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import excepciones.DataAccessException;
import java.util.ArrayList;
import java.util.Collection;
import laboratorio.Pelicula;

public class PeliculaDAO {

    
    public PeliculaDAO() {}
    
    //METODO QUE BUSCA EL REGISTRO PELICULA EN LA DB MEDIANTE LA CLAVE PRIMARIA
    public Pelicula findByPK(int codigo) throws DataAccessException {
         try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("SELECT * FROM pelicula WHERE codigo='"+codigo+"'");            
            Pelicula pelicula = null;
            while (result.next())  {
                pelicula = new Pelicula();
                pelicula.setCodigo(result.getInt("codigo"));
                pelicula.setGenero(result.getString("genero"));
                pelicula.setTitulo(result.getString("titulo"));
                pelicula.setFormato(result.getString("formato"));
                pelicula.setAutor(result.getString("autor"));
                pelicula.setEjemplares(result.getInt("ejemplares"));
                pelicula.setPrecio(result.getFloat("precio"));
            }
            result.close();
            smt.close();
            return pelicula;
        } catch (Exception ex) {
            throw new DataAccessException("Error en PeliculaDAO.findByPK() "+ex);
        }
    }
    
    //METODO QUE DEVUELTE TODOS LOS REGISTROS DE LA DB
    public Collection findAll() throws DataAccessException{       
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("SELECT * FROM pelicula ORDER BY codigo");            
            Pelicula pelicula = null;
            ArrayList array = new ArrayList();
            while (result.next())  {
                pelicula = new Pelicula();
                pelicula.setCodigo(result.getInt("codigo"));
                pelicula.setGenero(result.getString("genero"));
                pelicula.setTitulo(result.getString("titulo"));
                pelicula.setFormato(result.getString("formato"));
                pelicula.setAutor(result.getString("autor"));
                pelicula.setEjemplares(result.getInt("ejemplares"));
                pelicula.setPrecio(result.getFloat("precio"));
                array.add(pelicula);
            }
            result.close();
            smt.close();
            return array;
        } catch (Exception ex) {
            throw new DataAccessException("Error en PeliculaDAO.findAll() "+ex);
        }        
    }
    
    //METODO AGREGAR
    public void agregar(Pelicula insertRecord) throws DataAccessException {

        try {
            Pelicula existe = findByPK(insertRecord.getCodigo());
            if (existe != null) {
                throw new DataAccessException("Pelicula existente");
            }
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("INSERT INTO pelicula (codigo, genero, titulo, formato, autor, ejemplares, precio) VALUES (?,?,?,?,?,?,?)");
            ps.setInt(1, insertRecord.getCodigo());
            ps.setString(2, insertRecord.getGenero());
            ps.setString(3, insertRecord.getTitulo());
            ps.setString(4, insertRecord.getFormato());
            ps.setString(5, insertRecord.getAutor());
            ps.setInt(6, insertRecord.getEjemplares());
            ps.setFloat(7, insertRecord.getPrecio());
            ps.execute();
            
        } catch (Exception ex) {
            throw new DataAccessException("Error en PeliculaDAO.agregar() " + ex);
        }
    }
    
    //METODO ACTUALIZAR
    public void actualizar (Pelicula pelicula, int codigo) throws DataAccessException{
    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE pelicula SET codigo=?, genero=?, titulo=?, formato=?, autor=?, ejemplares=?, precio=? WHERE codigo='"+codigo+"'");
            ps.setInt(1, pelicula.getCodigo());
            ps.setString(2, pelicula.getGenero());
            ps.setString(3, pelicula.getTitulo());         
            ps.setString(4, pelicula.getFormato());
            ps.setString(5, pelicula.getAutor());
            ps.setInt(6, pelicula.getEjemplares());
            ps.setFloat(7, pelicula.getPrecio());
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en PeliculaDAO.actualizar() "+ex);}
    }
            
     //METODO ELIMINAR
     public void eliminar(int codigo) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            smt.executeUpdate("DELETE FROM pelicula WHERE codigo='" + codigo + "'");
            smt.close();
        } catch (Exception ex) {
            throw new DataAccessException("Error en PeliculaDAO.eliminar() " + ex);
        }
    }
            
    //METODO BUSCAR
    public Pelicula buscar (int codigo) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pelicula WHERE codigo='"+codigo+"'");
            Pelicula peli = null;
            while(rs.next())
            {
                peli = new Pelicula();
                peli.setCodigo(rs.getInt("codigo"));
                peli.setGenero(rs.getString("genero"));
                peli.setTitulo(rs.getString("titulo"));
                peli.setFormato(rs.getString("formato"));
                peli.setAutor(rs.getString("autor"));
                peli.setEjemplares(rs.getInt("ejemplares"));
                peli.setPrecio(rs.getFloat("precio"));
            }
            rs.close();
            st.close();
            return peli;
        }catch (Exception ex){throw new DataAccessException("Error en PeliculaDAO.buscar() "+ex);}
    }
                
    //METODO QUE TOMA EL VALOR DE NUMERO DE EJEMPLARES DE UNA PELICULA
    public int ejemplares(int codigo) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT ejemplares FROM pelicula WHERE codigo='" + codigo + "'");
            int ejemplar;
            rs.next();
            ejemplar = rs.getInt("ejemplares");
            rs.close();
            st.close();
            return ejemplar;
        } catch (Exception ex) {
            throw new DataAccessException("Error en PeliculaDAO.ejemplares() " + ex);
        }
    }
    
    //METODO ACTUALIZAR EL NUMERO DE PELICULAS
    public void actualizarEjemplares (int ejemplar, int codigo) throws DataAccessException{    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE pelicula SET ejemplares=? WHERE codigo='"+codigo+"'");
            ps.setInt(1, ejemplar);
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en PeliculaDAO.actualizarEjemplares() "+ex);}
    }
}
