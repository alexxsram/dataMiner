package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class data_set {
    private String comentarios;
    private String nombre;
    
    public data_set() {
        this.comentarios = "";
        this.nombre = "";
    }
    
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    
    public String getComentarios() {
        return comentarios;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
}
