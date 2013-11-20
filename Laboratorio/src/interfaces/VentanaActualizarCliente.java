
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

public class VentanaActualizarCliente extends JFrame{
      
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JLabel Ltitulo2 = new JLabel("INGRESE EL DNI DEL CLIENTE A MODIFICAR");
    JLabel Ldnibuscar = new JLabel("DNI DEL CLIENTE A MODIFICAR");
    JTextField Tdnibuscar = new JTextField();  
    
    JLabel Ltitulo = new JLabel("INGRESE LOS NUEVOS DATOS");
    JLabel Ldni = new JLabel("DNI");
    JTextField Tdni = new JTextField();
    JLabel Lnombre = new JLabel("APELLIDO Y NOMBRE");
    JTextField Tnombre = new JTextField();
    JLabel Ltelefono = new JLabel("TELEFONO");
    JTextField Ttelefono = new JTextField();
    JLabel Ldomicilio = new JLabel("DOMICILIO");
    JTextField Tdomicilio = new JTextField();

    JButton aceptar = new JButton ("Modificar");
    JButton cancelar = new JButton ("Cancelar");
    JButton buscar = new JButton ("Buscar");

    public VentanaActualizarCliente() {

        super("Modificar Cliente");
        this.setBounds(250, 200, 500, 500);
        this.setResizable(false);
        this.setLayout(null);        
                
        getContentPane().add(Ltitulo2);
        getContentPane().add(Ldnibuscar);
        getContentPane().add(Tdnibuscar);
        getContentPane().add(Ltitulo);
        getContentPane().add(Ldni);
        getContentPane().add(Tdni);
        getContentPane().add(Lnombre);
        getContentPane().add(Tnombre);
        getContentPane().add(Ltelefono);
        getContentPane().add(Ttelefono);
        getContentPane().add(Ldomicilio);
        getContentPane().add(Tdomicilio);      
        getContentPane().add(aceptar);
        getContentPane().add(cancelar);
        getContentPane().add(buscar);
        
        Ltitulo2.setBounds(100, 50, 250, 25);
        Ldnibuscar.setBounds(50, 100, 200, 25);
        Tdnibuscar.setBounds(250, 100, 175, 25);        
        Ltitulo.setBounds(100, 150, 250, 25);
        Ldni.setBounds(50, 200, 150, 25);
        Tdni.setBounds(225, 200, 200, 25);
        Lnombre.setBounds(50, 250, 150, 25);
        Tnombre.setBounds(225, 250, 200, 25);
        Ltelefono.setBounds(50, 300, 150, 25);
        Ttelefono.setBounds(225, 300, 200, 25);
        Ldomicilio.setBounds(50, 350, 150, 25);   
        Tdomicilio.setBounds(225, 350, 200, 25); 
        buscar.setBounds(170, 400, 100, 25);
        aceptar.setBounds(280, 400, 100, 25);
        cancelar.setBounds(390, 400, 100, 25);

        this.repaint();
        this.setVisible(true);
        
        buscar.addActionListener(manejadorEventos);
        aceptar.addActionListener(manejadorEventos);
        cancelar.addActionListener(manejadorEventos);
        
    }
    
    class ManejadorEventos implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == aceptar) {
                try {
                    modificar(); 
                } catch (DataAccessException ex) {          
                    Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == buscar) {
                try {
                    buscar();
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaActualizarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (e.getSource() == cancelar) {
            dispose();
            }
        }
        
        //METODO BUSCAR
        public void buscar() throws DataAccessException {
            if (Tdnibuscar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorCliente gestor = new GestorCliente();
                Cliente cliente = new Cliente();
                int dni = Integer.parseInt(Tdnibuscar.getText());
                cliente = gestor.buscarCliente(dni);
                if (cliente == null){
                  JOptionPane.showMessageDialog(null, "Error: Cliente Inexistente"); 
                }else {
                Tdni.setText(Integer.toString(cliente.getDni()));
                Tnombre.setText(cliente.getNombre());
                Ttelefono.setText(Integer.toString(cliente.getTelefono()));
                Tdomicilio.setText(cliente.getDomicilio());
                }
            }
        }
        
        //METODO MODIFICAR
        public void modificar() throws DataAccessException {
            if (Tdnibuscar.getText().isEmpty() || Tdni.getText().isEmpty() || Tnombre.getText().isEmpty() || Ttelefono.getText().isEmpty()
                    || Tdomicilio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {

                Cliente cliente = new Cliente();
                GestorCliente gestor = new GestorCliente();

                int dni = Integer.parseInt(Tdnibuscar.getText());
                cliente.setDni(Integer.parseInt(Tdni.getText()));
                cliente.setNombre(Tnombre.getText());
                cliente.setTelefono(Integer.parseInt(Ttelefono.getText()));
                cliente.setDomicilio(Tdomicilio.getText());

                gestor.actualizarCliente(cliente, dni);
                JOptionPane.showMessageDialog(null,"  Usuario modificado con exito  ");
                dispose();
            }
        }
    
    }
}
