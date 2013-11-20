
package laboratorio;

import basededatos.ClienteDAO;
import excepciones.DataAccessException;
import java.util.ArrayList;
import java.util.Collection;

public class GestorCliente {
        
    private ArrayList clientes = new ArrayList();
    private ClienteDAO clienteDAO = new ClienteDAO();
    
    public GestorCliente() throws DataAccessException {//en el array clientes voy a tener todos los registros de la db
        clientes=(ArrayList)clienteDAO.findAll();
    }
    
    //AGREGAR
    public void agregarCliente (Cliente cliente) throws DataAccessException{
        clienteDAO.agregar(cliente);
        clientes.add(cliente);
    }
        
    //ACTUALIZAR
    public void actualizarCliente (Cliente cliente, int dni) throws DataAccessException{
        clienteDAO.actualizar(cliente, dni);
    }
    
    //ELIMINAR
    public void eliminarCliente (int dni) throws DataAccessException{
        clienteDAO.eliminar(dni);
    }
     
    //BUSCAR UN CLIENTE ESPECIFICO
    public Cliente buscarCliente (int dni) throws DataAccessException{
        Cliente buscado = clienteDAO.buscar(dni);
        return buscado;
    }
    
    //MOSTRAR TODOS LOS CLIENTES
    public Collection mostrarClientes() throws DataAccessException {
        Collection clientes = clienteDAO.findAll();
        return clientes;
    }
}
