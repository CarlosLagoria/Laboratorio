
package excepciones;

import javax.swing.JOptionPane;

public class DataAccessException extends Exception{
    public DataAccessException(String exceptionMsg) {
        JOptionPane.showMessageDialog(null, exceptionMsg);
    }
}
