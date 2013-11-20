
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
import laboratorio.Pelicula;

public class VentanaAgregarPelicula extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton aceptar = new JButton ("Aceptar");
    JButton cancelar = new JButton ("Cancelar");
    
    JTextField Tcodigo = new JTextField();
    JTextField Tgenero = new JTextField();
    JTextField Ttitulo = new JTextField();
    JTextField Tformato = new JTextField();
    JTextField Tautor = new JTextField();
    JTextField Tejemplares = new JTextField();
    JTextField Tprecio = new JTextField();
    
    JLabel Ltitulo1 = new JLabel("INGRESE LOS DATOS");
    JLabel Lcodigo = new JLabel("CODIGO (numerico)");
    JLabel Lgenero = new JLabel("GENERO");
    JLabel Ltitulo = new JLabel("TITULO");
    JLabel Lformato = new JLabel("FORMATO");
    JLabel Lautor = new JLabel("AUTOR");
    JLabel Lejemplares = new JLabel("EJEMPLARES (numerico)");
    JLabel Lprecio = new JLabel("PRECIO");

    public VentanaAgregarPelicula() {

        super("Agregar Pelicula");
        this.setBounds(100, 100, 500, 525);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo1.setBounds(100, 50, 150, 25);
        Lcodigo.setBounds(50, 100, 150, 25);
        Tcodigo.setBounds(225, 100, 200, 25);
        Lgenero.setBounds(50, 150, 150, 25);
        Tgenero.setBounds(225, 150, 200, 25);
        Ltitulo.setBounds(50, 200, 150, 25);
        Ttitulo.setBounds(225, 200, 200, 25);
        Lformato.setBounds(50, 250, 150, 25);   
        Tformato.setBounds(225, 250, 200, 25);        
        Lautor.setBounds(50, 300, 150, 25);
        Tautor.setBounds(225, 300, 200, 25);
        Lejemplares.setBounds(50, 350, 150, 25);
        Tejemplares.setBounds(225, 350, 200, 25);
        Lprecio.setBounds(50, 400, 150, 25);
        Tprecio.setBounds(225, 400, 200, 25);
        
        aceptar.setBounds(225, 450, 100, 25);
        cancelar.setBounds(350, 450, 100, 25);

        
        getContentPane().add(Ltitulo1);
        getContentPane().add(aceptar);
        getContentPane().add(cancelar);
        
        getContentPane().add(Lcodigo);
        getContentPane().add(Lgenero);
        getContentPane().add(Ltitulo);
        getContentPane().add(Lformato);
        getContentPane().add(Lautor);
        getContentPane().add(Lejemplares);
        getContentPane().add(Lprecio);
        
        getContentPane().add(Tcodigo);
        getContentPane().add(Tgenero);
        getContentPane().add(Ttitulo);
        getContentPane().add(Tformato);
        getContentPane().add(Tautor);
        getContentPane().add(Tejemplares);
        getContentPane().add(Tprecio);
        
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
            if (Tcodigo.getText().isEmpty() || Tgenero.getText().isEmpty() || Ttitulo.getText().isEmpty() || Tformato.getText().isEmpty()
                    || Tautor.getText().isEmpty() || Tejemplares.getText().isEmpty() || Tprecio.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                Pelicula pelicula = new Pelicula();
                GestorPelicula gestor = new GestorPelicula();

                pelicula.setCodigo(Integer.parseInt(Tcodigo.getText()));
                pelicula.setGenero(Tgenero.getText());
                pelicula.setTitulo(Ttitulo.getText());
                pelicula.setFormato(Tformato.getText());
                pelicula.setAutor(Tautor.getText());
                pelicula.setEjemplares(Integer.parseInt(Tejemplares.getText()));
                pelicula.setPrecio(Float.parseFloat(Tprecio.getText()));

                gestor.agregarPelicula(pelicula);
                JOptionPane.showMessageDialog(null, "  Pelicula agregada con exito  ");
                dispose();
            }
        }
    
    }
}
