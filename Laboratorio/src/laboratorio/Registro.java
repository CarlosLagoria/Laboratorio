
package laboratorio;

import java.sql.Date;

public class Registro {
    
    private int dni;
    private int codigo;
    private float monto;
    private Date fechaentrega;//dia de alquiler
    private Date fechadevolucion;//dia que tiene que devolver
    private String devolucion;//SI O NO, POR DEFECTO ES NO. 
    private int num_registro;

    public Registro() {}

    
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    
    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }
    
    public Date getFechaentrega() {
        return fechaentrega;
    }

    public void setFechaentrega(Date fechaentrega) {
        this.fechaentrega = fechaentrega;
    }

    public Date getFechadevolucion() {
        return fechadevolucion;
    }

    public void setFechadevolucion(Date fechadevolucion) {
        this.fechadevolucion = fechadevolucion;
    }
    
    public String getDevolucion() {
        return devolucion;
    }

    public void setDevolucion(String devolucion) {
        this.devolucion = devolucion;
    }

    public int getNum_registro() {
        return num_registro;
    }

    public void setNum_registro(int num_registro) {
        this.num_registro = num_registro;
    }

}
