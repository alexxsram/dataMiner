package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class attributes {
    private String nombre;
    private String valor;
    private String expresionRegular;
    
    public attributes(String nombre, String valor, String expresionRegular) {
        this.nombre = nombre;
        this.valor = valor;
        this.expresionRegular = expresionRegular;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setValor(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
    
    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }
    
    public String getExpresionRegular() {
        return expresionRegular;
    }
}
