
package basededatos;

import laboratorio.Multa;
import excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class MultaDAO {
    
    public MultaDAO() {}
    
      //METODO QUE DEVUELTE TODOS LOS REGISTROS DE LA DB
    public Collection findAll() throws DataAccessException{       
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("SELECT * FROM multa ORDER BY num_multa");            
            Multa multa = null;
            ArrayList array = new ArrayList();
            while (result.next())  {
                multa = new Multa();
                multa.setNum_multa(result.getInt("num_multa"));
                multa.setMonto(result.getFloat("monto"));
                multa.setFecha(result.getDate("fecha"));
                array.add(multa);
            }
            result.close();
            smt.close();
            return array;
        } catch (Exception ex) {
            throw new DataAccessException("Error en MultaDAO.findAll() "+ex);
        }        
    }
    
    //METODO INSERT EN LA DB
    public void agregar (Multa insertRecord) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("INSERT INTO multa (monto, fecha) values (?,?)");
            smt.setFloat(1,insertRecord.getMonto());
            smt.setDate(2,insertRecord.getFecha());
            smt.execute();            
        } catch (Exception ex) {
            throw new DataAccessException("Error en MultaDAO.agregar() "+ex);
        }
    }
    
    
    //METODO SELECT EN LA DB(SELECCIONA TODOS LOS REGISTROS ENTRE 2 FECHAS) 
    public Collection buscar (Date fechaincio, Date fechafin) throws DataAccessException{
        try{
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("SELECT * FROM multa WHERE fecha >= '"+fechaincio+"' and fecha <= '"+fechafin+"'");            
            Multa multa = null;
            ArrayList array = new ArrayList();
            while (result.next())  {
                multa = new Multa();
                multa.setNum_multa(result.getInt("num_multa"));
                multa.setMonto(result.getFloat("monto"));
                multa.setFecha(result.getDate("fecha"));
                array.add(multa);
            }
            result.close();
            smt.close();
            return array;
        }catch (Exception ex){throw new DataAccessException("Error en MultaDAO.buscar() "+ex);}
    }
    
}
