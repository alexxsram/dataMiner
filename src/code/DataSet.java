package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class DataSet {
    private String comentarios;
    private String nombre;
    
    public DataSet() {
        this.comentarios = "";
        this.nombre = "";
    }
    
    ///% comentarios 
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getComentarios() {
        return comentarios;
    }
    
    ///@relation
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
}
