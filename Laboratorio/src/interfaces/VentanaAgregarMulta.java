
package interfaces;

import excepciones.DataAccessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import laboratorio.GestorMulta;
import laboratorio.Multa;

public class VentanaAgregarMulta extends JFrame{
    
     ActionListener manejadorEventos = new ManejadorEventos();
    
    JLabel Ltitulo2 = new JLabel("INGRESE EL MONTO DE LA MULTA RECIBIDA");
    JLabel Lmonto2 = new JLabel("MONTO DE LA MULTA                      $");
    JTextField Tmonto2 = new JTextField();
    
    JButton aceptar = new JButton ("Aceptar");
    JButton cancelar = new JButton ("Cancelar");
    
    public VentanaAgregarMulta() {

        super("Agregar Multa");
        this.setBounds(250, 200, 500, 225);
        this.setResizable(false);
        this.setLayout(null);

        getContentPane().add(Ltitulo2);
        getContentPane().add(Lmonto2);
        getContentPane().add(Tmonto2);
        getContentPane().add(aceptar);
        getContentPane().add(cancelar);

        Ltitulo2.setBounds(100, 50, 300, 25);
        Lmonto2.setBounds(50, 100, 200, 25);
        Tmonto2.setBounds(250, 100, 150, 25);
        
        aceptar.setBounds(225, 150, 100, 25);
        cancelar.setBounds(350, 150, 100, 25);

        this.repaint();
        this.setVisible(true);
        
        aceptar.addActionListener(manejadorEventos);
        cancelar.addActionListener(manejadorEventos);
    }

    class ManejadorEventos implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == aceptar) {
                try {
                    agregar();
                } catch (DataAccessException ex) {
                    //Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == cancelar) {
            dispose();
            }
        }
        
        public void agregar() throws DataAccessException {
            if (Tmonto2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                Multa multa = new Multa();
                GestorMulta gestor = new GestorMulta();

                multa.setMonto((Float.parseFloat(Tmonto2.getText())));
                multa.setFecha(gestor.fecha());
                gestor.agregarMulta(multa);
                JOptionPane.showMessageDialog(null, "  Multa agregada con exito  ");
                dispose();
            }
        }
    }
}
