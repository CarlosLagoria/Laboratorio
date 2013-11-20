
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
import laboratorio.GestorPelicula;
import laboratorio.GestorRegistro;
import laboratorio.Registro;

public class VentanaDevolucion extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton buscar = new JButton ("Buscar");
    JButton aceptar = new JButton ("Aceptar");
    JButton cancelar = new JButton ("Cancelar");
    
    JLabel Ltitulo2 = new JLabel("INGRESE EL NUMERO DE REGISTRO DEL QUE DESEA ACTUALIZAR SU DEVOLUCION");
    JLabel Lregistro2 = new JLabel("NUMERO DE REGISTRO: ");
    JTextField Tregistro2 = new JTextField();

    JLabel Ltitulo1 = new JLabel("SU REGISTRO ES:");
    
    JTextField Tdni = new JTextField();
    JTextField Tcodigo = new JTextField();
    JTextField Tmonto = new JTextField();
    JTextField Tfechaentrega = new JTextField();
    JTextField Tfechadevolucion = new JTextField();
    JTextField Tdevolucion = new JTextField();

    JLabel Ldni = new JLabel("DNI");
    JLabel Lcodigo = new JLabel("CODIGO");
    JLabel Lmonto = new JLabel("MONTO");
    JLabel Lfechaentrega = new JLabel("FECHA ENTREGA (aaaa-mm-dd)");
    JLabel Lfechadevolucion = new JLabel("FECHA DEVOLUCION (aaaa-mm-dd)");
    JLabel Ldevolucion = new JLabel("DEVOLUCION (SI/NO)");

    public VentanaDevolucion() {

        super("Actualizar Devolución");
        this.setBounds(100, 50, 500, 625);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo2.setBounds(10, 50, 490, 25);
        Lregistro2.setBounds(50, 100, 250, 25);
        Tregistro2.setBounds(300, 100, 125, 25);      
        
        Ltitulo1.setBounds(150, 175, 200, 25);
        Ldni.setBounds(50, 250, 150, 25);
        Tdni.setBounds(250, 250, 175, 25);
        Lcodigo.setBounds(50, 300, 150, 25);
        Tcodigo.setBounds(250, 300, 175, 25);  
        Lmonto.setBounds(50, 350, 150, 25);
        Tmonto.setBounds(250, 350, 175, 25); 
        Lfechaentrega.setBounds(50, 400, 200, 25);
        Tfechaentrega.setBounds(250, 400, 175, 25);
        Lfechadevolucion.setBounds(50, 450, 200, 25);
        Tfechadevolucion.setBounds(250, 450, 175, 25);
        Ldevolucion.setBounds(50, 500, 150, 25);   
        Tdevolucion.setBounds(250, 500, 175, 25);        
        
        buscar.setBounds(170, 550, 100, 25);
        aceptar.setBounds(280, 550, 100, 25);
        cancelar.setBounds(390, 550, 100, 25);

        getContentPane().add(Ltitulo2);
        getContentPane().add(Lregistro2);
        getContentPane().add(Tregistro2);
        getContentPane().add(Ltitulo1);
        getContentPane().add(buscar);
        getContentPane().add(aceptar);
        getContentPane().add(cancelar);
        
        getContentPane().add(Ldni);
        getContentPane().add(Lcodigo);   
        getContentPane().add(Lmonto);  
        getContentPane().add(Lfechaentrega);
        getContentPane().add(Lfechadevolucion);
        getContentPane().add(Ldevolucion);
        
        getContentPane().add(Tcodigo);
        getContentPane().add(Tdni);
        getContentPane().add(Tmonto);  
        getContentPane().add(Tfechaentrega);
        getContentPane().add(Tfechadevolucion);
        getContentPane().add(Tdevolucion);

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
                    actualizardevolucion();
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == buscar) {
                try {
                    buscar();
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaActualizarRegistro.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (e.getSource() == cancelar) {
            dispose();
            }
        }
        
        //BUSCAR
        public void buscar() throws DataAccessException {
            if (Tregistro2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorRegistro gestor = new GestorRegistro();
                Registro reg = new Registro();
                reg = gestor.buscar(Integer.parseInt(Tregistro2.getText()));
                if(reg == null){
                JOptionPane.showMessageDialog(null, "Error: REGISTRO INEXISTENTE");
                }else{
                Tdni.setText(Integer.toString(reg.getDni()));
                Tcodigo.setText(Integer.toString(reg.getCodigo()));
                Tmonto.setText(Float.toString(reg.getMonto()));
                Tfechaentrega.setText(gestor.fechaDate(reg.getFechaentrega()));
                Tfechadevolucion.setText(gestor.fechaDate(reg.getFechadevolucion()));
                Tdevolucion.setText(reg.getDevolucion());
                }
            }
        }
        
        public void actualizardevolucion() throws DataAccessException {
            if (Tregistro2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                
                GestorPelicula gestorpeli = new GestorPelicula();
                int stock = gestorpeli.ejemplares(Integer.parseInt(Tcodigo.getText()));
                int stocknuevo = stock + 1;
                gestorpeli.actualizarEjemplares(stocknuevo, Integer.parseInt(Tcodigo.getText()));
                
                GestorRegistro gestor = new GestorRegistro();
                int Tregistrob = Integer.parseInt(Tregistro2.getText());
                gestor.actualizarDevolucion(Tregistrob);
                JOptionPane.showMessageDialog(null,"  Devolución registrada con exito  ");
                dispose();
            }
        }
    
    }
}
