package basededatos;

import laboratorio.Cliente;
import excepciones.DataAccessException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

public class ClienteDAO {

    public ClienteDAO() {}

    //METODO QUE BUSCA EL REGISTRO CLIENTE EN LA DB MEDIANTE LA CLAVE PRIMARIA(SIRVE PARA VER SI ES QUE YA EXISTE EL ELEMENTO INSERTADO)
    public Cliente findByPK(int dni) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("Select * from cliente where dni='" + dni + "'");
            Cliente cliente = null;
            while (result.next()) {
                cliente = new Cliente();
                cliente.setDni(result.getInt("dni"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setTelefono(result.getInt("telefono"));
                cliente.setDomicilio(result.getString("domicilio"));
            }
            result.close();
            smt.close();
            return cliente;
        } catch (Exception ex) {
            throw new DataAccessException("Error en ClienteDAO.findByPK() " + ex);
        }
    }

    //METODO QUE DEVUELTE TODOS LOS REGISTROS DE LA DB
    public Collection findAll() throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            ResultSet result = smt.executeQuery("Select * from cliente order by dni");
            Cliente cliente = null;
            ArrayList array = new ArrayList();
            while (result.next()) {
                cliente = new Cliente();
                cliente.setDni(result.getInt("dni"));
                cliente.setNombre(result.getString("nombre"));
                cliente.setTelefono(result.getInt("telefono"));
                cliente.setDomicilio(result.getString("domicilio"));
                array.add(cliente);
            }
            result.close();
            smt.close();
            return array;
        } catch (Exception ex) {
            throw new DataAccessException("Error en ClienteDAO.findAll() " + ex);
        }
    }

    //METODO INSERT EN LA DB
    public void agregar(Cliente insertRecord) throws DataAccessException {
        try {
            Cliente existe = findByPK(insertRecord.getDni());
            if (existe != null) {
                throw new DataAccessException("Cliente existente");
            }
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement smt = con.prepareStatement("Insert into cliente (dni,nombre,telefono,domicilio) values (?,?,?,?)");
            smt.setInt(1, insertRecord.getDni());
            smt.setString(2, insertRecord.getNombre());
            smt.setInt(3, insertRecord.getTelefono());
            smt.setString(4, insertRecord.getDomicilio());
            smt.execute();

        } catch (Exception ex) {
            throw new DataAccessException("Error en ClienteDAO.agregar() " + ex);
        }
    }

    //METODO UPDATE EN LA DB
    public void actualizar(Cliente cliente, int dni) throws DataAccessException {

        try {
            Connection con = BaseDeDatos.getInstance();
            PreparedStatement ps = con.prepareStatement("UPDATE cliente SET dni=?,nombre=?,telefono=?,domicilio=? WHERE dni='" + dni + "'");
            ps.setInt(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setInt(3, cliente.getTelefono());
            ps.setString(4, cliente.getDomicilio());
            ps.execute();
        } catch (Exception ex) {
            throw new DataAccessException("Error en ClienteDAO.actualizar() " + ex);
        }
    }

    //METODO DELETE EN LA DB        
    public void eliminar(int dni) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement smt = con.createStatement();
            smt.executeUpdate("Delete from cliente where dni='" + dni + "'");
            smt.close();
        } catch (Exception ex) {
            throw new DataAccessException("Error en ClienteDAO.eliminar() " + ex);
        }
    }

    //METODO SELECT EN LA DB 
    public Cliente buscar(int dni) throws DataAccessException {
        try {
            Connection con = BaseDeDatos.getInstance();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE dni='" + dni + "'");
            Cliente cliente = null;
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setDni(rs.getInt("dni"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setTelefono(rs.getInt("telefono"));
                cliente.setDomicilio(rs.getString("domicilio"));

            }
            rs.close();
            st.close();
            return cliente;
        } catch (Exception ex) {
            throw new DataAccessException("Error en ClienteDAO.buscar() " + ex);
        }
    }
}
