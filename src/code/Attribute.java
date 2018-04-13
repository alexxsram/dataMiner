package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class Attribute {
    private String nombreAtributo;
    private String tipoDato;
    private String expresionRegular;
    
    public Attribute(String nombreAtributo, String tipoDato, String expresionRegular) {
        this.nombreAtributo = nombreAtributo;
        this.tipoDato = tipoDato;
        this.expresionRegular = expresionRegular;
    }
    
    public void setNombreAtributo(String nombreAtributo) {
        this.nombreAtributo = nombreAtributo;
    }
    
    public String getNombreAtributo() {
        return nombreAtributo;
    }
    
    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }
    
    public String getTipoDato() {
        return tipoDato;
    }
    
    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }
    
    public String getExpresionRegular() {
        return expresionRegular;
    }
}
