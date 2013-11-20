
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
import laboratorio.GestorPelicula;
import laboratorio.GestorRegistro;
import laboratorio.Registro;

public class VentanaAgregarRegistro extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton aceptar = new JButton ("Salir");
    JButton agregar = new JButton ("Agregar");
    
    JTextField Tdni = new JTextField();
    JTextField Tcodigo = new JTextField();
    
    JLabel Ltitulo = new JLabel("INGRESE LOS DATOS");
    JLabel Ldni = new JLabel("DNI CLIENTE");
    JLabel Lcodigo = new JLabel("CODIGO PELICULA");

    public VentanaAgregarRegistro() {

        super("Agregar Registro");
        this.setBounds(200, 200, 500, 275);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo.setBounds(100, 50, 150, 25);
        Ldni.setBounds(50, 100, 150, 25);
        Tdni.setBounds(225, 100, 200, 25);
        Lcodigo.setBounds(50, 150, 150, 25);
        Tcodigo.setBounds(225, 150, 200, 25);
        
        agregar.setBounds(225, 200, 100, 25);
        aceptar.setBounds(350, 200, 100, 25);

        
        getContentPane().add(Ltitulo);
        getContentPane().add(aceptar);
        getContentPane().add(Tdni);
        getContentPane().add(Tcodigo);
        getContentPane().add(Ldni);
        getContentPane().add(Lcodigo);
        getContentPane().add(agregar);        
        
        this.repaint();
        this.setVisible(true);
        
        aceptar.addActionListener(manejadorEventos);
        agregar.addActionListener(manejadorEventos);
    }
    
    class ManejadorEventos implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == agregar) {
                try {
                    agregar(); 
                } catch (DataAccessException ex) {
                    //Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == aceptar) {
            dispose();
            }
        }

        public void agregar() throws DataAccessException {

            if (Tdni.getText().isEmpty() || Tcodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorPelicula gestorpeli = new GestorPelicula();
                int stock = gestorpeli.ejemplares(Integer.parseInt(Tcodigo.getText()));
                
                if (stock == 0) {
                    JOptionPane.showMessageDialog(null, "  No hay Stock de esa película  ");
                    dispose();
                } else {
                    int stocknuevo = stock - 1;
                    gestorpeli.actualizarEjemplares(stocknuevo, Integer.parseInt(Tcodigo.getText()));

                    Registro registro = new Registro();
                    GestorRegistro gestor = new GestorRegistro();

                    registro.setDni(Integer.parseInt(Tdni.getText()));
                    registro.setCodigo(Integer.parseInt(Tcodigo.getText()));
                    registro.setMonto(gestor.cargamonto(Integer.parseInt(Tcodigo.getText())));
                    registro.setFechaentrega(gestor.fecha());
                    registro.setFechadevolucion(gestor.fechadevolucion());
                    gestor.agregarRegistro(registro);
                    JOptionPane.showMessageDialog(null, "  Registro agregado con exito  ");
                    dispose();
                }
            }
        }
    
    }
}
