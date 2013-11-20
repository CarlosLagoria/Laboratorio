
package laboratorio;

import java.sql.Date;

public class Multa {
    
    private float monto;
    private Date fecha;
    private int num_multa;
    
    public Multa () {}

    
    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNum_multa() {
        return num_multa;
    }

    public void setNum_multa(int num_multa) {
        this.num_multa = num_multa;
    }

}
