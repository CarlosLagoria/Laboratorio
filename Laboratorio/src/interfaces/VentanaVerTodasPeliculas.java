
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
import laboratorio.GestorPelicula;
import laboratorio.Pelicula;

public class VentanaVerTodasPeliculas extends JFrame{
        
    ActionListener manejadorEventos = new ManejadorEventos();
    
    JButton buscar = new JButton ("Buscar");
    JButton cancelar = new JButton ("Cancelar");    
    
    JLabel Ltitulo = new JLabel("LISTA DE PELICULAS");
    JTable tabla;
    DefaultTableModel modelo;
    
    public VentanaVerTodasPeliculas() {

        super("Lista de Peliculas");
        this.setBounds(100, 50, 900, 425);
        this.setResizable(false);
        this.setLayout(null);
        
        Ltitulo.setBounds(400, 50, 150, 25);
        buscar.setBounds(625, 350, 100, 25);
        cancelar.setBounds(750, 350, 100, 25);
        
        //TABLA
        String[] columnas = {"CODIGO","GENERO","TITULO","FORMATO", "AUTOR", "EJEMPLARES", "PRECIO"}; 
        Object[][] datos = {};        
        modelo = new DefaultTableModel(datos, columnas);
        tabla = new JTable(modelo);
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(new Rectangle(800,225));//TAMAÑO
        scrollPane.setLocation(50,100);//UBICACION          
        //TABLA
        getContentPane().add(Ltitulo);  
        getContentPane().add(buscar);  
        getContentPane().add(cancelar);
        getContentPane().add(scrollPane);
        
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
                    Logger.getLogger(VentanaAgregarCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (e.getSource() == cancelar) {
                dispose();
            }
        }

        public void buscar() throws DataAccessException {
            GestorPelicula gestor = new GestorPelicula();
            Collection coleccion = gestor.mostrarPeliculas();
            if(coleccion.isEmpty()){
            JOptionPane.showMessageDialog(null, "NO HAY PELICULAS");
            }else{
            ArrayList registrobuscados = (ArrayList) coleccion;
             Iterator<Pelicula> it = registrobuscados.iterator();
             while (it.hasNext()) {
             Pelicula pelicula = it.next();
                          
             String codigo = Integer.toString(pelicula.getCodigo());
             String genero = pelicula.getGenero();
             String titulo = pelicula.getTitulo();
             String formato = pelicula.getFormato();
             String autor = pelicula.getAutor();
             String ejemplares = Integer.toString(pelicula.getEjemplares());
             String precio = Float.toString(pelicula.getPrecio());
             //AQUI MANDO LA PELICULA AL JTABLE
                 Object[] nuevoCliente = {codigo,genero,titulo,formato,autor,ejemplares,precio};
                 modelo.addRow(nuevoCliente);
             }
             }
        }//METODO BUSCAR
    }//CLASE INTERNA MANEJADOR DE EVENTOS
}
