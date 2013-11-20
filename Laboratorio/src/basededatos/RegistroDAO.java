
package basededatos;

import laboratorio.Registro;
import excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;

public class RegistroDAO {

       public RegistroDAO() {}
    
    
    //METODO QUE DEVUELTE TODOS LOS REGISTROS DE LA DB
    public Collection findAll() throws DataAccessException{       
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
                ResultSet result = smt.executeQuery("SELECT * FROM registro ORDER BY num_registro");            
            Registro registro = null;
            ArrayList array = new ArrayList();
            while (result.next())  {
                registro = new Registro();
                registro.setDni(result.getInt("dni"));
                registro.setCodigo(result.getInt("codigo"));
                registro.setMonto(result.getFloat("monto"));
                registro.setFechaentrega(result.getDate("fechaentrega"));
                registro.setFechadevolucion(result.getDate("fechadevolucion"));
                registro.setDevolucion(result.getString("devolucion"));
                registro.setNum_registro(result.getInt("num_registro"));
                array.add(registro);
            }
            result.close();
            smt.close();
            return array;
        } catch (Exception ex) {
            throw new DataAccessException("Error en RegistroDAO.findAll() "+ex);
        }        
    }
    
    //METODO INSERT EN LA DB()
    public void agregar (Registro insertRecord) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("INSERT INTO registro (dni,codigo,monto,fechaentrega,fechadevolucion, devolucion) VALUES (?,?,?,?,?,?)");
            smt.setInt(1,insertRecord.getDni());
            smt.setInt(2,insertRecord.getCodigo());
            smt.setFloat(3,insertRecord.getMonto());
            smt.setDate(4,insertRecord.getFechaentrega());
            smt.setDate(5,insertRecord.getFechadevolucion());
            smt.setString(6, "NO");
            smt.execute();            
        } catch (Exception ex) {
            throw new DataAccessException("Error en RegistroDAO.agregar() "+ex);
        }
    }
    
    //METODO ACTUALIZAR - ACTUALIZA TODO EL REGISTRO
    public void actualizar (Registro registro, int num_registro) throws DataAccessException{    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE registro SET dni=?, codigo=?, monto=?, fechaentrega=?, fechadevolucion=?, devolucion=? WHERE num_registro='"+num_registro+"'");
            ps.setInt(1, registro.getDni());
            ps.setInt(2, registro.getCodigo());
            ps.setFloat(3, registro.getMonto());         
            ps.setDate(4, registro.getFechaentrega());
            ps.setDate(5, registro.getFechadevolucion());
            ps.setString(6, registro.getDevolucion());
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en RegistroDAO.actualizar() "+ex);}
    }
            
     //METODO ELIMINAR
     public void eliminar(int num_registro) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            smt.executeUpdate("DELETE FROM registro WHERE num_registro='" + num_registro + "'");
            smt.close();
        } catch (Exception ex) {
            throw new DataAccessException("Error en RegistroDAO.eliminar() " + ex);
        }
    }
     
     //METODO BUSCAR, DEVUELVE UN REGISTRO
    public Registro buscar (int num_registro) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registro WHERE num_registro='" + num_registro + "'");
            Registro registro = null;
            while(rs.next()){          
                registro = new Registro();
                registro.setDni(rs.getInt("dni"));
                registro.setCodigo(rs.getInt("codigo"));
                registro.setMonto(rs.getFloat("monto"));
                registro.setFechaentrega(rs.getDate("fechaentrega"));
                registro.setFechadevolucion(rs.getDate("fechadevolucion"));
                registro.setDevolucion(rs.getString("devolucion")); 
                registro.setNum_registro(rs.getInt("num_registro"));
            }
            rs.close();
            st.close();
            return registro;
        }catch (Exception ex){throw new DataAccessException("Error en RegistroDAO.buscar() "+ex);}
    }
     
    //METODO BUSCAR, DEVUELVE UNA COLECCION DE LOS REGISTRO DE UN CLIENTE
    public Collection buscarRegistro (int dni) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registro WHERE dni='" + dni + "' ORDER BY num_registro");
            Registro registro = null;
            ArrayList array = new ArrayList();
            while(rs.next())
            {
                registro = new Registro();
                registro.setDni(rs.getInt("dni"));
                registro.setCodigo(rs.getInt("codigo"));
                registro.setMonto(rs.getFloat("monto"));
                registro.setFechaentrega(rs.getDate("fechaentrega"));
                registro.setFechadevolucion(rs.getDate("fechadevolucion"));
                registro.setDevolucion(rs.getString("devolucion"));
                registro.setNum_registro(rs.getInt("num_registro"));
                array.add(registro);
            }
            rs.close();
            st.close();
            return array;
        }catch (Exception ex){throw new DataAccessException("Error en RegistroDAO.buscarRegistro() "+ex);}
    }
    
    //METODO QUE DEVUELVE EL VALOR DE LA PELICULA PARA PODER CARGAR EN LA COLUMNA MONTO DE LA TABLA REGISTRO
    public float monto(int codigo) throws DataAccessException{
          try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT precio FROM pelicula WHERE codigo='"+codigo+"'");
            float monto = 0;
            while(rs.next()){
            monto = rs.getFloat("precio");
           }
            rs.close();
            st.close();
            return monto;
        }catch (Exception ex){throw new DataAccessException("Error en RegistroDAO.monto() "+ex);}
    }
    
    
    //METODO QUE DEVUELVE UN ARRAY DE TODOS LOS REGISTROS COMPRENDIDO EN UN INTERVALO DE TIEMPO
    public Collection suma (Date fechainicio, Date fechafin) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM registro WHERE fechaentrega>='" + fechainicio + "' and fechaentrega<='"+fechafin+"'");
            Registro registro = null;
            ArrayList array = new ArrayList();
            while(rs.next())
            {
                registro = new Registro();
                registro.setDni(rs.getInt("dni"));
                registro.setCodigo(rs.getInt("codigo"));
                registro.setMonto(rs.getFloat("monto"));
                registro.setFechaentrega(rs.getDate("fechaentrega"));
                registro.setFechadevolucion(rs.getDate("fechadevolucion"));
                registro.setDevolucion(rs.getString("devolucion"));
                array.add(registro);
            }
            rs.close();
            st.close();
            return array;
        }catch (Exception ex){throw new DataAccessException("Error en RegistroDAO.suma() "+ex);}
    }
    
    //METODO QUE ACTUALIZA DEVOLUCION, DE NO A SI.
    public void actualizardevolucion (int num_registro) throws DataAccessException{    
        try{
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE registro SET devolucion=? WHERE num_registro='"+num_registro+"'");
            ps.setString(1, "SI");//CAMBIA DE NO A SI.          
            ps.execute();
        }catch(Exception ex){throw new DataAccessException("Error en RegistroDAO.actualizardevolucion() "+ex);}
    }
        
}
