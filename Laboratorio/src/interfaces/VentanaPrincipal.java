
package interfaces;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaPrincipal extends JFrame{

    ActionListener manejadorEventos = new ManejadorEventos();
    
    JMenuBar menubar = new JMenuBar();
    
    JMenu cliente = new JMenu ("Cliente");
    JMenu pelicula = new JMenu ("Pelicula");
    JMenu registro = new JMenu ("Registro de Alquileres");
    JMenu multa = new JMenu ("Multa");
    
    JMenuItem agregarcliente = new JMenuItem("Agregar Cliente");
    JMenuItem modificarcliente = new JMenuItem("Modificar Cliente");
    JMenuItem eliminarcliente = new JMenuItem("Eliminar Cliente");
    JMenuItem buscarcliente = new JMenuItem("Ver Datos De Un Cliente");
    JMenuItem mostrarclientes = new JMenuItem("Ver Lista De Clientes");
    
    JMenuItem agregarpelicula = new JMenuItem("Agregar Pelicula");
    JMenuItem modificarpelicula = new JMenuItem("Modificar Pelicula");
    JMenuItem eliminarpelicula = new JMenuItem("Eliminar Pelicula");
    JMenuItem buscarpelicula = new JMenuItem("Ver Datos De Una Pelicula");
    JMenuItem mostrarpeliculas = new JMenuItem("Ver Lista De Peliculas");
    
    JMenuItem agregarregistro = new JMenuItem("Agregar Registro");
    JMenuItem modificarregistro = new JMenuItem("Modificar Registro");
    JMenuItem eliminarregistro = new JMenuItem("Eliminar Registro");
    JMenuItem actualizardevolucion = new JMenuItem("Devolución");
    JMenuItem sumaregistro = new JMenuItem("Ver Ganancia En Alquileres");
    JMenuItem buscarregistro = new JMenuItem("Ver Registros De Un Cliente");
    JMenuItem mostrarregistros = new JMenuItem("Ver Lista De Registros");
    
    JMenuItem agregarmulta = new JMenuItem("Agregar Multa");
    JMenuItem sumamulta = new JMenuItem("Ver Ganancia en Multas");
    JMenuItem buscarmulta = new JMenuItem("Ver Lista De Multas En Un Intervalo De Tiempo");
    JMenuItem mostrarmultas = new JMenuItem("Ver Lista De Todas Las Multas");
    
    
    public VentanaPrincipal() {

        super("VideoClub");//NOMBRE
        this.setBounds(300, 100, 750, 500);//TAMAÑO
        this.setResizable(false);
        
        JPanel menu = new JPanel();
        
        getContentPane().add(menu, BorderLayout.WEST);//ContentPane = Panel del JFrame
        
        menu.add(menubar);
        
        menubar.add(cliente);
        menubar.add(pelicula);
        menubar.add(registro);
        menubar.add(multa);
                        
        cliente.add(agregarcliente);
        cliente.add(modificarcliente);
        cliente.add(eliminarcliente);
        cliente.add(buscarcliente);
        cliente.add(mostrarclientes);
        
        pelicula.add(agregarpelicula);
        pelicula.add(modificarpelicula);
        pelicula.add(eliminarpelicula);
        pelicula.add(buscarpelicula); 
        pelicula.add(mostrarpeliculas);

        registro.add(agregarregistro);
        registro.add(modificarregistro);
        registro.add(eliminarregistro);
        registro.add(actualizardevolucion);
        registro.add(sumaregistro);
        registro.add(buscarregistro);        
        registro.add(mostrarregistros);        
        
        multa.add(agregarmulta);
        multa.add(sumamulta);
        multa.add(buscarmulta);
        multa.add(mostrarmultas);
                
        this.repaint();
        this.setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        
        //EVENTOS-BOTONES
        agregarcliente.addActionListener(manejadorEventos);
        modificarcliente.addActionListener(manejadorEventos);
        eliminarcliente.addActionListener(manejadorEventos);
        buscarcliente.addActionListener(manejadorEventos);
        mostrarclientes.addActionListener(manejadorEventos);
        
        agregarpelicula.addActionListener(manejadorEventos);
        modificarpelicula.addActionListener(manejadorEventos);
        eliminarpelicula.addActionListener(manejadorEventos);
        buscarpelicula.addActionListener(manejadorEventos);
        mostrarpeliculas.addActionListener(manejadorEventos);
        
        agregarregistro.addActionListener(manejadorEventos);
        modificarregistro.addActionListener(manejadorEventos);
        eliminarregistro.addActionListener(manejadorEventos);
        buscarregistro.addActionListener(manejadorEventos);
        sumaregistro.addActionListener(manejadorEventos);
        actualizardevolucion.addActionListener(manejadorEventos);
        mostrarregistros.addActionListener(manejadorEventos);
        
        agregarmulta.addActionListener(manejadorEventos);
        buscarmulta.addActionListener(manejadorEventos);
        sumamulta.addActionListener(manejadorEventos);
        mostrarmultas.addActionListener(manejadorEventos);
    }
    
    
    class ManejadorEventos implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == agregarcliente) {
                VentanaAgregarCliente v = new VentanaAgregarCliente();
            } else if (e.getSource() == modificarcliente) {
                VentanaActualizarCliente v = new VentanaActualizarCliente();
            } else if (e.getSource() == eliminarcliente) {
                VentanaEliminarCliente v = new VentanaEliminarCliente();
            } else if (e.getSource() == buscarcliente) {
                VentanaBuscarCliente v = new VentanaBuscarCliente();
            } else if (e.getSource() == mostrarclientes) {
                VentanaVerTodosClientes v = new VentanaVerTodosClientes();
            }else if (e.getSource() == agregarpelicula) { 
                VentanaAgregarPelicula v = new VentanaAgregarPelicula();
            }else if (e.getSource() == modificarpelicula) { 
                VentanaActualizarPelicula v = new VentanaActualizarPelicula();
            }else if (e.getSource() == eliminarpelicula) {
                VentanaEliminarPelicula v = new VentanaEliminarPelicula();
            }else if (e.getSource() == buscarpelicula) { 
                VentanaBuscarPelicula v = new VentanaBuscarPelicula();
            } else if (e.getSource() == mostrarpeliculas) {
                VentanaVerTodasPeliculas v = new VentanaVerTodasPeliculas();
            }else if (e.getSource() == agregarregistro) {
                VentanaAgregarRegistro v = new VentanaAgregarRegistro();
            }else if (e.getSource() == modificarregistro) { 
                VentanaActualizarRegistro v = new VentanaActualizarRegistro();
            }else if (e.getSource() == eliminarregistro) { 
                VentanaEliminarRegistro v = new VentanaEliminarRegistro();
            }else if (e.getSource() == buscarregistro) {  
                VentanaBuscarRegistro v = new VentanaBuscarRegistro();
            }else if (e.getSource() == sumaregistro) {  
                VentanaGananciaEnAlquiler v = new VentanaGananciaEnAlquiler();
            }else if (e.getSource() == mostrarregistros) {  
                VentanaVerTodosRegistros v = new VentanaVerTodosRegistros();
            }else if (e.getSource() == actualizardevolucion) { 
                VentanaDevolucion v = new VentanaDevolucion();
            }else if (e.getSource() == agregarmulta) { 
                VentanaAgregarMulta v = new VentanaAgregarMulta();
            }else if (e.getSource() == buscarmulta) {  
                VentanaVerMultas v = new VentanaVerMultas();
            }else if (e.getSource() == sumamulta) {
                VentanaGananciaEnMultas v = new VentanaGananciaEnMultas();
            }else if (e.getSource() == mostrarmultas) {
                VentanaVerTodasMultas v = new VentanaVerTodasMultas();
            }
            
        }//ACTIONPERF
    }//CLASE INTERNA
}
