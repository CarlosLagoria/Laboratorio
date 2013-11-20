
package interfaces;

import excepciones.DataAccessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import laboratorio.GestorRegistro;
import laboratorio.Registro;

public class VentanaBuscarRegistro extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton buscar = new JButton ("Buscar");
    JButton cancelar = new JButton ("Cancelar");;
    
    JLabel Ltitulo2 = new JLabel("INGRESE DNI DEL CLIENTE Y PRESIONE EL BOTON BUSCAR");
    JLabel Ldni2 = new JLabel("DNI DEL CLIENTE");
    JTextField Tdni2 = new JTextField();
    JLabel Ltitulo1 = new JLabel("REGISTROS ENCONTRADOS");
    JTable tabla;
    DefaultTableModel modelo;
    
    public VentanaBuscarRegistro() {

        super("Buscar Registro");
        this.setBounds(100, 50, 800, 425);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo2.setBounds(100, 50, 500, 25);
        Ldni2.setBounds(50, 100, 100, 25);
        Tdni2.setBounds(175, 100, 125, 25); 
        Ltitulo1.setBounds(300, 150, 500, 25);
        
        //TABLA
        String[] columnas = {"Numero Registro","DNI Cliente","CODIGO Pelicula","Monto","Fecha de Alquiler","Fecha de Devolucion", "Devolucion"}; 
        Object[][] datos = {};        
        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(700,100));
        scrollPane.setLocation(50,200);                
        
        //TABLA
        buscar.setBounds(525, 350, 100, 25);
        cancelar.setBounds(650, 350, 100, 25);

        getContentPane().add(Ltitulo2);
        getContentPane().add(Ldni2);
        getContentPane().add(Tdni2);
        getContentPane().add(Ltitulo1);
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
                    //Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == cancelar) {
                dispose();
            }
        }

        public void buscar() throws DataAccessException {
            if (Tdni2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorRegistro gestor = new GestorRegistro();
                int dni = Integer.parseInt(Tdni2.getText());
                Collection coleccion = gestor.buscarRegistro(dni);
                if (coleccion.isEmpty()){
                 JOptionPane.showMessageDialog(null, "Error: DNI INCORRECTO");   
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
                }
            }
        }//METODO BUSCAR
    }//CLASE INTERNA MANEJADOR DE EVENTOS
}//CLASE VENTANA

