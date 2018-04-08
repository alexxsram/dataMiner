package code;

/**
 *
 * @author ALEJO SALGADO
 */
public class data {
    private String[] data;
    
    public data(String[] data) {
        this.data = data;
    }
    
    public void setData(String[] data) {
        this.data = data;
    }
    
    public void setData(String aux, int index) {
        this.data[index] = aux;
    }
    
    public String getData(int index) {
        return data[index];
    }
}
