
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
import laboratorio.GestorRegistro;
import laboratorio.Registro;

public class VentanaVerTodosRegistros extends JFrame{
                
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton buscar = new JButton ("Buscar");
    JButton cancelar = new JButton ("Cancelar");    
    
    JLabel Ltitulo = new JLabel("LISTA DE TODOS LOS REGISTROS DE ALQUILERES");
    JTable tabla;
    DefaultTableModel modelo;
    
    public VentanaVerTodosRegistros() {

        super("Lista De Registros De Alquileres");
        this.setBounds(100, 50, 900, 425);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo.setBounds(300, 50, 400, 25);
        buscar.setBounds(625, 350, 100, 25);
        cancelar.setBounds(750, 350, 100, 25);
        
        //TABLA
        String[] columnas = {"NUMERO DE REGISTRO","DNI CLIENTE","CODIGO PELICULA","MONTO","FECHA ENTREGA", "FECHA DEVOLUCION", "DEVOLUCION"}; 
        Object[][] datos = {};        
        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(800,225));//TAMAÑO
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
            GestorRegistro gestor = new GestorRegistro();
            Collection coleccion = gestor.buscarTodo();
            if(coleccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "NO HAY REGISTROS");
            }else{
            ArrayList registrobuscados = (ArrayList) coleccion;
            Iterator<Registro> it = registrobuscados.iterator();
            while (it.hasNext()) {
                Registro registro = it.next();
                //AQUI TENGO QUE MANDAR ESTE REGISTRO AL JTABLE
                String num_registro = Integer.toString(registro.getNum_registro());
                String dnii = Integer.toString(registro.getDni());
                String codigo = Integer.toString(registro.getCodigo());
                String monto = Float.toString(registro.getMonto());
                String fechaentrega = gestor.fechaDate(registro.getFechaentrega());
                String fechadevolucion = gestor.fechaDate(registro.getFechadevolucion());
                String devolucion = registro.getDevolucion();

                Object[] nuevoRegistro = {num_registro,dnii, codigo, monto, fechaentrega, fechadevolucion, devolucion};
                modelo.addRow(nuevoRegistro);
            }
            }//METODO BUSCAR
        }//CLASE INTERNA MANEJADOR DE EVENTOS
    }
}
