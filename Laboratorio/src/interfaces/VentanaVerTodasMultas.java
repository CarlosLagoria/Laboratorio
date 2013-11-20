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
import laboratorio.GestorMulta;
import laboratorio.Multa;

public class VentanaVerTodasMultas extends JFrame {

    ActionListener manejadorEventos = new ManejadorEventos();
    JButton buscar = new JButton("Buscar");
    JButton aceptar = new JButton("Salir");
    ;
    
    JLabel Ltitulo2 = new JLabel("REGISTRO DE TODAS LAS MULTAS");
    JTable tabla;
    DefaultTableModel modelo;

    public VentanaVerTodasMultas() {

        super("Registro de Multas");
        this.setBounds(100, 50, 400, 400);
        this.setResizable(false);
        this.setLayout(null);

        Ltitulo2.setBounds(50, 50, 200, 25);
        buscar.setBounds(50, 325, 100, 25);
        aceptar.setBounds(175, 325, 100, 25);

        //TABLA
        String[] columnas = {"NUMERO DE MULTA", "MONTO", "FECHA"};
        Object[][] datos = {};
        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(300, 200));//TAMAÑO
        scrollPane.setLocation(50, 100);//UBICACION                
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //TABLA

        getContentPane().add(Ltitulo2);
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

            GestorMulta gestor = new GestorMulta();
            Collection coleccion = gestor.buscarTodo();
            if (coleccion.isEmpty()) {
                JOptionPane.showMessageDialog(null, "NO HAY MULTAS");
            } else {
                ArrayList multasbuscadas = (ArrayList) coleccion;
                Iterator<Multa> it = multasbuscadas.iterator();
                while (it.hasNext()) {
                    Multa multa = it.next();

                    String num_multa = Integer.toString(multa.getNum_multa());
                    String monto = Float.toString(multa.getMonto());
                    String fecha = gestor.fechaDate(multa.getFecha());//TRANSFORMA LA FECHASQL A STRING

                    Object[] nuevaMulta = {num_multa, monto, fecha};
                    modelo.addRow(nuevaMulta);
                }
            }

        }
    }
}
