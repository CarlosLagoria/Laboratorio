
package interfaces;

import excepciones.DataAccessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import laboratorio.Cliente;
import laboratorio.GestorCliente;

public class VentanaVerTodosClientes extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton buscar = new JButton ("Buscar");
    JButton cancelar = new JButton ("Cancelar");   
    JLabel Ltitulo = new JLabel("LISTA DE CLIENTES");
    JTable tabla;
    DefaultTableModel modelo;
    
    public VentanaVerTodosClientes() {

        super("Clientes");
        this.setBounds(100, 50, 600, 425);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo.setBounds(250, 50, 150, 25);
        buscar.setBounds(325, 350, 100, 25);
        cancelar.setBounds(450, 350, 100, 25);
        
        //TABLA
        String[] columnas = {"DNI","NOMBRE","TELEFONO","DOMICILIO"}; 
        Object[][] datos = {};     
        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(500,225));//TAMAÑO
        scrollPane.setLocation(50,100);//UBICACION         
        
        getContentPane().add(Ltitulo);  
        getContentPane().add(buscar);  
        getContentPane().add(cancelar);
        getContentPane().add(scrollPane);
        
        this.repaint();
        this.setVisible(true);
        
        cancelar.addActionListener(manejadorEventos);
        buscar.addActionListener(manejadorEventos);
    }
    
    class ManejadorEventos implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == buscar) {
                try {
                    buscar();
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == cancelar) {
                dispose();
            }
        }

        public void buscar() throws DataAccessException {
            GestorCliente gestor = new GestorCliente();
            Collection coleccion = gestor.mostrarClientes();
            if(coleccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "NO HAY CLIENTES");
            }else{
            ArrayList registrobuscados = (ArrayList) coleccion;
             Iterator<Cliente> it = registrobuscados.iterator();
             while (it.hasNext()) {
             Cliente cliente = it.next();
             //EL JTABLE MUESTRA STRING POR ESO HICE ESTO.
             String dni = Integer.toString(cliente.getDni());
             String nombre = cliente.getNombre();
             String telefono = Integer.toString(cliente.getTelefono());
             String domicilio = cliente.getDomicilio();
             //AQUI TENGO QUE MANDAR ESTE CLIENTE AL JTABLE
                 Object[] nuevoCliente = {dni,nombre,telefono,domicilio};
                 modelo.addRow(nuevoCliente);
             }
             }
        }//METODO BUSCAR
    }//CLASE INTERNA MANEJADOR DE EVENTOS
}
