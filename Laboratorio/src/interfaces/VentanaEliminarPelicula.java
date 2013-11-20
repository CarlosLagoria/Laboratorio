
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

public class VentanaEliminarPelicula extends JFrame{
    
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JLabel Ltitulo2 = new JLabel("INGRESE EL CODIGO DE LA PELICULA A ELIMINAR");
    JLabel Lcodigo2 = new JLabel("CODIGO DE LA PELICULA");
    JTextField Tcodigo2 = new JTextField();
    
    JTextField Tcodigo = new JTextField();
    JTextField Tgenero = new JTextField();
    JTextField Ttitulo = new JTextField();
    JTextField Tformato = new JTextField();
    JTextField Tautor = new JTextField();
    JTextField Tejemplares = new JTextField();
    JTextField Tprecio = new JTextField();
    
    JLabel Ltitulo1 = new JLabel("PELICULA:");
    
    JLabel Lcodigo = new JLabel("CODIGO (numerico)");
    JLabel Lgenero = new JLabel("GENERO");
    JLabel Ltitulo = new JLabel("TITULO");
    JLabel Lformato = new JLabel("FORMATO");
    JLabel Lautor = new JLabel("AUTOR");
    JLabel Lejemplares = new JLabel("EJEMPLARES (numerico)");
    JLabel Lprecio = new JLabel("PRECIO");
    
    JButton buscar = new JButton ("Buscar");
    JButton aceptar = new JButton ("Eliminar");
    JButton cancelar = new JButton ("Cancelar");
    
    public VentanaEliminarPelicula() {

        super("Eliminar Pelicula");
        this.setBounds(250, 50, 500, 625);
        this.setResizable(false);
        this.setLayout(null);

        getContentPane().add(Ltitulo2);
        getContentPane().add(Lcodigo2);
        getContentPane().add(Tcodigo2);        
        getContentPane().add(Ltitulo1);
        
        getContentPane().add(buscar);
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
        
        Ltitulo2.setBounds(100, 50, 300, 25);
        Lcodigo2.setBounds(50, 100, 150, 25);
        Tcodigo2.setBounds(225, 100, 100, 25);

        Ltitulo1.setBounds(100, 150, 200, 25);
        Lcodigo.setBounds(50, 200, 150, 25);
        Tcodigo.setBounds(225, 200, 200, 25);
        Lgenero.setBounds(50, 250, 150, 25);
        Tgenero.setBounds(225, 250, 200, 25);
        Ltitulo.setBounds(50, 300, 150, 25);
        Ttitulo.setBounds(225, 300, 200, 25);
        Lformato.setBounds(50, 350, 150, 25);
        Tformato.setBounds(225, 350, 200, 25);
        Lautor.setBounds(50, 400, 150, 25);
        Tautor.setBounds(225, 400, 200, 25);
        Lejemplares.setBounds(50, 450, 150, 25);
        Tejemplares.setBounds(225, 450, 200, 25);
        Lprecio.setBounds(50, 500, 150, 25);
        Tprecio.setBounds(225, 500, 200, 25);  
        
        buscar.setBounds(170, 550, 100, 25);
        aceptar.setBounds(280, 550, 100, 25);
        cancelar.setBounds(390, 550, 100, 25);

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
                    eliminar(); 
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == buscar) {
                try {
                    buscar();
                } catch (DataAccessException ex) {
                    Logger.getLogger(VentanaEliminarPelicula.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else if (e.getSource() == cancelar) {
            dispose();
            }
        }
        
         public void buscar() throws DataAccessException {
            if (Tcodigo2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorPelicula gestor = new GestorPelicula();
                Pelicula pelicula = new Pelicula();
                int codigo2 = (Integer.parseInt(Tcodigo2.getText()));
                pelicula = gestor.buscarPelicula(codigo2);
                if(pelicula == null){
                JOptionPane.showMessageDialog(null, "Error: PELICULA INEXISTENTE");
                }else {
                Tcodigo.setText(Integer.toString(pelicula.getCodigo()));
                Tgenero.setText(pelicula.getGenero());
                Ttitulo.setText(pelicula.getTitulo());
                Tformato.setText(pelicula.getFormato());
                Tautor.setText(pelicula.getAutor());
                Tejemplares.setText(Integer.toString(pelicula.getEjemplares()));
                Tprecio.setText(Float.toString(pelicula.getPrecio()));
                }
            }
        }
        
        public void eliminar() throws DataAccessException {
            if (Tcodigo2.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Error: Campo vacio");
            } else {
                GestorPelicula gestor = new GestorPelicula();
                int codigo = (Integer.parseInt(Tcodigo2.getText()));
                gestor.eliminarPelicula(codigo);
                JOptionPane.showMessageDialog(null,"  Pelicula eliminada con exito  ");
                dispose();
            }
        }
    
    }
}
