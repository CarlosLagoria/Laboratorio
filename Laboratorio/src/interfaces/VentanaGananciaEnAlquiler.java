
package interfaces;

import excepciones.DataAccessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import laboratorio.GestorRegistro;

public class VentanaGananciaEnAlquiler  extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton calcular = new JButton ("Calcular");
    JButton cancelar = new JButton ("Cancelar");
        
    JTextField Tfechainicio = new JTextField();
    JTextField Tfechafin = new JTextField();
    JTextField Tganancia = new JTextField();
    
    JLabel Ltitulo = new JLabel("INGRESE LAS FECHAS");
    JLabel Lfechainicio = new JLabel("FECHA INICIAL (aaaa-mm-dd)");
    JLabel Lfechafin = new JLabel("FECHA FINAL (aaaa-mm-dd)");
    JLabel Ltitulo2 = new JLabel("LA GANANCIA EN CONCEPTO DE ALQUILERES ES: ");
    JLabel Lganancia = new JLabel("$");

    public VentanaGananciaEnAlquiler() {

        super("Ganancia en concepto de Alquileres");
        this.setBounds(200, 200, 500, 375);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo.setBounds(100, 50, 150, 25);
        Lfechainicio.setBounds(50, 100, 200, 25);
        Tfechainicio.setBounds(300, 100, 100, 25);
        Lfechafin.setBounds(50, 150, 200, 25);
        Tfechafin.setBounds(300, 150, 100, 25);
        Ltitulo2.setBounds(100, 200, 300, 25);
        Lganancia.setBounds(280, 250, 10, 25);
        Tganancia.setBounds(300, 250, 100, 25);
        
        calcular.setBounds(225, 300, 100, 25);
        cancelar.setBounds(350, 300, 100, 25);

        
        getContentPane().add(Ltitulo);
        getContentPane().add(calcular);
        getContentPane().add(cancelar);
        getContentPane().add(Lfechainicio);
        getContentPane().add(Tfechainicio);
        getContentPane().add(Lfechafin);
        getContentPane().add(Tfechafin);
        getContentPane().add(Ltitulo2);
        getContentPane().add(Lganancia);
        getContentPane().add(Tganancia);
        
        this.repaint();
        this.setVisible(true);
        
        calcular.addActionListener(manejadorEventos);
        cancelar.addActionListener(manejadorEventos);
    }
    
    class ManejadorEventos implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == calcular) {
                try {
                    calcular();
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == cancelar) {
            dispose();
            }
        }
        
        public void calcular() throws DataAccessException {
            if (Tfechainicio.getText().isEmpty() || Tfechafin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorRegistro gestor = new GestorRegistro();

                Date fechainicio = gestor.fechaString(Tfechainicio.getText());
                Date fechafin = gestor.fechaString(Tfechafin.getText());
                float monto = gestor.sumamontos(fechainicio, fechafin);
                Tganancia.setText(Float.toString(monto));
                JOptionPane.showMessageDialog(null, "  Calculo realizado con exito  ");
            }
        }
    
    }
}
