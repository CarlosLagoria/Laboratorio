
package laboratorio;

import basededatos.RegistroDAO;
import excepciones.DataAccessException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;

public class GestorRegistro {
  
    private ArrayList registros = new ArrayList();
    private RegistroDAO registroDAO = new RegistroDAO();
    
    public GestorRegistro() throws DataAccessException {
        registros=(ArrayList)registroDAO.findAll();
    }
    
    //AGREGAR
    public void agregarRegistro (Registro registro) throws DataAccessException{
        registroDAO.agregar(registro);
        registros.add(registro);
    }
    
    //ACTUALIZAR
    public void actualizarRegistro (Registro registro, int num_registro) throws DataAccessException{
        registroDAO.actualizar(registro, num_registro);
    }
    
    //ELIMINAR
    public void eliminarRegistro (int num_registro) throws DataAccessException{
        registroDAO.eliminar(num_registro);
    }
    
     //BUSCAR - MUESTRA EL REGISTRO ESPECIFICO
    public Registro buscar(int num_registro) throws DataAccessException {
        Registro reg = registroDAO.buscar(num_registro);
        return reg;
    }
    
    //BUSCAR -MUESTRA REGISTROS DE UN CLIENTE ESPECIFICO
    public Collection buscarRegistro(int dni) throws DataAccessException {
        Collection coleccion = registroDAO.buscarRegistro(dni);
        return coleccion;
    }
    
    //BUSCAR -MUESTRA TODOS LOS REGISTROS
    public Collection buscarTodo() throws DataAccessException {
        Collection coleccion = registroDAO.findAll();
        return coleccion;
    }
    
    //CARGAR EL MONTO (ES EL PRECIO DE LA PELICULA QUE SE ENVIA POR PARAMETRO A LA TABLA REGISTRO)
    public float cargamonto(int codigo) throws DataAccessException {
        float monto = registroDAO.monto(codigo);
        return monto;
    }    
      
    //CARGAR FECHA ACTUAL 
    public Date fecha() {
        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
        return sqlDate;
        //System.out.println("util.Date: " + utilDate);
        //System.out.println("sql.Date: " + sqlDate);
    }
    
    //METODO DE LA FECHA - TRANSFORMA STRING A DATE SQL
    public Date fechaString(String fec) {
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
        String strFecha = fec;
        java.util.Date fecha = null;
        try {
            fecha = (java.util.Date) formatoDelTexto.parse(strFecha);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
        return sqlDate;
    }
    
    //METODO QUE TRANSFORMA DATESQL A STRING - SIRVE PARA MOSTRARLO EN LA JTABLE
    public String fechaDate(Date fecha) {
        java.util.Date Utilfecha = new java.util.Date(fecha.getTime());
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        String fechaString = sdf.format(Utilfecha);
        return fechaString;
    }    
    
    //CARGAR FECHA DEVOLUCION , 7 DIAS DSP DE LA ENTREGA
    public java.sql.Date fechadevolucion() {
        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);

        int dias = 7;//7 dias para devolver la pelicula

        Calendar cal = new GregorianCalendar();
        cal.setTimeInMillis(sqlDate.getTime());
        cal.add(Calendar.DATE, dias);
        return new java.sql.Date(cal.getTimeInMillis());
    }
    
    //METODO QUE SUMA LOS MONTOS DE LOS ALQUILERES EN UN INTERVALO DE TIEMPO PARA CONOCER GANANCIA
    public float sumamontos(Date fechainicio, Date fechafin) throws DataAccessException{
        float resultado=0;
        ArrayList array =(ArrayList)registroDAO.suma(fechainicio, fechafin);
        Iterator<Registro> it = array.iterator();
        while (it.hasNext()) {
            Registro reg = it.next();
            resultado=reg.getMonto()+resultado;
        }
        return resultado;
    }
    
    //ACTUALIZAR DEVOLUCION (PASA DE NO A SI)
    public void actualizarDevolucion (int num_registro) throws DataAccessException{
        registroDAO.actualizardevolucion(num_registro);
    }  
}
