
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

public class VentanaBuscarCliente extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JLabel Ltitulo2 = new JLabel("INGRESE EL DNI DEL CLIENTE A BUSCAR");
    JLabel Ldnibuscar = new JLabel("DNI DEL CLIENTE A BUSCAR");
    JTextField Tdnibuscar = new JTextField();  
    
    JLabel Ltitulo = new JLabel("DATOS DEL CLIENTE");
    JLabel Ldni = new JLabel("DNI");
    JTextField Tdni = new JTextField();
    JLabel Lnombre = new JLabel("APELLIDO Y NOMBRE");
    JTextField Tnombre = new JTextField();
    JLabel Ltelefono = new JLabel("TELEFONO");
    JTextField Ttelefono = new JTextField();
    JLabel Ldomicilio = new JLabel("DOMICILIO");
    JTextField Tdomicilio = new JTextField();

    JButton cancelar = new JButton ("Cancelar");
    JButton buscar = new JButton ("Buscar");
    
    public VentanaBuscarCliente() {

        super("Buscar Cliente");
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
        getContentPane().add(buscar);
        getContentPane().add(cancelar);
        
        Ltitulo2.setBounds(150, 50, 250, 25);
        Ldnibuscar.setBounds(50, 100, 200, 25);
        Tdnibuscar.setBounds(250, 100, 175, 25);        
        Ltitulo.setBounds(150, 150, 250, 25);
        Ldni.setBounds(50, 200, 150, 25);
        Tdni.setBounds(225, 200, 200, 25);
        Lnombre.setBounds(50, 250, 150, 25);
        Tnombre.setBounds(225, 250, 200, 25);
        Ltelefono.setBounds(50, 300, 150, 25);
        Ttelefono.setBounds(225, 300, 200, 25);
        Ldomicilio.setBounds(50, 350, 150, 25);   
        Tdomicilio.setBounds(225, 350, 200, 25);
        buscar.setBounds(250,400,100,25);
        cancelar.setBounds(375, 400, 100, 25);

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
            if (Tdnibuscar.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorCliente gestor = new GestorCliente();
                Cliente cliente = new Cliente();
                int dni = Integer.parseInt(Tdnibuscar.getText());
                cliente = gestor.buscarCliente(dni);
                if (cliente == null){
                JOptionPane.showMessageDialog(null, "Error: Cliente Inexistente");
                }else{
                Tdni.setText(Integer.toString(cliente.getDni()));
                Tnombre.setText(cliente.getNombre());
                Ttelefono.setText(Integer.toString(cliente.getTelefono()));
                Tdomicilio.setText(cliente.getDomicilio());
                }
            }
        }
    
    }
}
