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
import laboratorio.Cliente;
import laboratorio.GestorCliente;

public class VentanaAgregarCliente extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton aceptar = new JButton ("Aceptar");
    JButton cancelar = new JButton ("Cancelar");
    
    JTextField Tdni = new JTextField();
    JTextField Tnombre = new JTextField();
    JTextField Ttelefono = new JTextField();
    JTextField Tdomicilio = new JTextField();
    
    JLabel Ltitulo = new JLabel("INGRESE LOS DATOS");
    JLabel Ldni = new JLabel("DNI");
    JLabel Lnombre = new JLabel("APELLIDO Y NOMBRE");
    JLabel Ltelefono = new JLabel("TELEFONO");
    JLabel Ldomicilio = new JLabel("DOMICILIO");

    public VentanaAgregarCliente() {

        super("Agregar Cliente");
        this.setBounds(200, 200, 500, 400);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo.setBounds(100, 50, 150, 25);
        Ldni.setBounds(50, 100, 150, 25);
        Tdni.setBounds(225, 100, 200, 25);
        Lnombre.setBounds(50, 150, 150, 25);
        Tnombre.setBounds(225, 150, 200, 25);
        Ltelefono.setBounds(50, 200, 150, 25);
        Ttelefono.setBounds(225, 200, 200, 25);
        Ldomicilio.setBounds(50, 250, 150, 25);   
        Tdomicilio.setBounds(225, 250, 200, 25);
        
        aceptar.setBounds(225, 300, 100, 25);
        cancelar.setBounds(350, 300, 100, 25);

        
        getContentPane().add(Ltitulo);
        getContentPane().add(aceptar);
        getContentPane().add(cancelar);
        getContentPane().add(Tdni);
        getContentPane().add(Tnombre);
        getContentPane().add(Ttelefono);
        getContentPane().add(Tdomicilio);
        getContentPane().add(Ldni);
        getContentPane().add(Lnombre);
        getContentPane().add(Ltelefono);
        getContentPane().add(Ldomicilio);
        
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
            if (Tdni.getText().isEmpty() || Tnombre.getText().isEmpty() || Ttelefono.getText().isEmpty()
                    || Tdomicilio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            }else {
            Cliente cliente = new Cliente();
            GestorCliente gestor = new GestorCliente();

            cliente.setDni(Integer.parseInt(Tdni.getText()));
            cliente.setNombre(Tnombre.getText());
            cliente.setTelefono(Integer.parseInt(Ttelefono.getText()));
            cliente.setDomicilio(Tdomicilio.getText());

            gestor.agregarCliente(cliente);
            JOptionPane.showMessageDialog(null,"  Usuario agregado con exito  ");
            dispose();
            }
        }
    
    }
}
