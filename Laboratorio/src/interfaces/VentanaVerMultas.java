
package interfaces;

import excepciones.DataAccessException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import laboratorio.GestorMulta;
import laboratorio.Multa;
import laboratorio.Pelicula;

public class VentanaVerMultas extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton buscar = new JButton ("Buscar");
    JButton aceptar = new JButton ("Salir");;
    
    JLabel Ltitulo2 = new JLabel("INGRESE EL INTERVALO DE FECHAS PARA VER LAS MULTAS");
    JLabel Lfecha = new JLabel("FECHA INICIO (aaaa-mm-dd)");
    JTextField Tfecha = new JTextField();
    JLabel Lfecha2 = new JLabel("FECHA FIN (aaaa-mm-dd)");
    JTextField Tfecha2 = new JTextField();

    JLabel Ltitulo1 = new JLabel("REGISTROS ENCONTRADOS");
    JTable tabla;
    DefaultTableModel modelo;

    public VentanaVerMultas() {

        super("Ver Multas");
        this.setBounds(100, 50, 500, 625);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo2.setBounds(100, 50, 350, 25);
        Lfecha.setBounds(50, 100, 250, 25);
        Tfecha.setBounds(300, 100, 125, 25);        
        Lfecha2.setBounds(50, 150, 250, 25);
        Tfecha2.setBounds(300, 150, 125, 25);       
        
        Ltitulo1.setBounds(50, 250, 200, 25);

        buscar.setBounds(225, 550, 100, 25);
        aceptar.setBounds(350, 550, 100, 25);
        
         //TABLA
        String[] columnas = {"NUMERO DE MULTA","MONTO","FECHA"}; 
        Object[][] datos = {};        
        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(250,200));//TAMAÑO
        scrollPane.setLocation(50,275);//UBICACION                
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //TABLA
        
        getContentPane().add(Ltitulo2);
        getContentPane().add(Lfecha);
        getContentPane().add(Tfecha);
        getContentPane().add(Lfecha2);
        getContentPane().add(Tfecha2);
        getContentPane().add(Ltitulo1);
        getContentPane().add(scrollPane);
        getContentPane().add(buscar);  
        getContentPane().add(aceptar);
        
        this.repaint();
        this.setVisible(true);
        
        aceptar.addActionListener(manejadorEventos);
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
            } else if (e.getSource() == aceptar) {
            dispose();
            }
        }
        
        public void buscar() throws DataAccessException {
            if (Tfecha.getText().isEmpty() || Tfecha2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorMulta gestor = new GestorMulta();
                Date fechainicio = gestor.fechaString(Tfecha.getText());
                Date fechafin = gestor.fechaString(Tfecha2.getText());
                Collection coleccion = gestor.buscarMulta(fechainicio, fechafin);
                if(coleccion.isEmpty()){
                JOptionPane.showMessageDialog(null, "NO HAY MULTAS EN ESE INTERVALO DE TIEMPO");
                }else {
                ArrayList multasbuscadas = (ArrayList) coleccion;
                Iterator<Multa> it = multasbuscadas.iterator();
                while (it.hasNext()) {
                    Multa multa = it.next();
                    
                    String num_multa = Integer.toString(multa.getNum_multa());
                    String monto = Float.toString(multa.getMonto());
                    String fecha = gestor.fechaDate(multa.getFecha());//TRANSFORMA LA FECHASQL A STRING

                    Object[] nuevaMulta = {num_multa,monto, fecha};
                    modelo.addRow(nuevaMulta);
                }
                }
            }

        }
    
    }
}
