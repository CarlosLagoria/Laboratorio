package laboratorio;

import basededatos.MultaDAO;
import excepciones.DataAccessException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GestorMulta {

    private ArrayList multas = new ArrayList();
    private MultaDAO multaDAO = new MultaDAO();

    public GestorMulta() throws DataAccessException {
        multas = (ArrayList) multaDAO.findAll();
    }

    //AGREGAR
    public void agregarMulta(Multa multa) throws DataAccessException {
        multaDAO.agregar(multa);
        multas.add(multa);
    }

    //BUSCAR, SIRVE PARA VER LOS VALORES DE LAS MULTAS EN UN INTERVALO DE TIEMPO
    public Collection buscarMulta(Date fechaincio, Date fechafin) throws DataAccessException {
        Collection multasbuscadas = multaDAO.buscar(fechaincio, fechafin);
        return multasbuscadas;
    }

    //BUSCAR, SIRVE PARA VER TODAS LAS MULTAS
    public Collection buscarTodo() throws DataAccessException {
        Collection multasbuscadas = multaDAO.findAll();
        return multasbuscadas;
    }

    //FECHA ACTUAL
    public Date fecha() {
        java.util.Date utilDate = new java.util.Date();
        long lnMilisegundos = utilDate.getTime();
        java.sql.Date sqlDate = new java.sql.Date(lnMilisegundos);
        return sqlDate;
    }

    //METODO DE LA FECHA FIN - TRANSFORMA STRING A DATE (EN MULTA SIRVE PARA EL INTERVALO DE TIEMPO DE LA BUSQUEDA PARA LA SUMA DE MULTAS)
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

    //METODO QUE SUMA LAS MULTAS EN UN INTERVALO DE TIEMPO
    public float sumamultas(Date fechainicio, Date fechafin) throws DataAccessException {
        float resultado = 0;
        ArrayList array = (ArrayList) multaDAO.buscar(fechainicio, fechafin);
        Iterator<Multa> it = array.iterator();
        while (it.hasNext()) {
            Multa mul = it.next();
            resultado = mul.getMonto() + resultado;
        }
        return resultado;
    }
}
