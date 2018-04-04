package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class attributes {
    private String nombre;
    private String valor;
    private String expresionRegular;
    
    public attributes(String n, String v, String e_r) {
        this.nombre = n;
        this.valor = v;
        this.expresionRegular = e_r;
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
