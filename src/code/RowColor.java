package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ALEJO SALGADO
 */
public class RowColor extends DefaultTableCellRenderer {
    ArrayList<Attribute> attributeList = new ArrayList<Attribute>();
    
    public void pasarLista(ArrayList<Attribute> attributeList) {
        this.attributeList = attributeList;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {        
        Font fuente = new Font(getFont().getFontName(), Font.BOLD, 13);
        Component cell = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        if(column > 0){
            String valor = table.getValueAt(row, column).toString();
            if(Pattern.matches("[?]", valor)) {
                cell.setForeground(Color.RED);
                cell.setFont(fuente);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            else if(!Pattern.matches(attributeList.get(column - 1).getExpresionRegular(), valor)) {
                cell.setForeground(Color.BLUE);
                cell.setFont(fuente);
                setHorizontalAlignment(SwingConstants.CENTER);
            }
            else {                
                cell.setForeground(Color.BLACK);
                cell.setFont(getFont());
                setHorizontalAlignment(SwingConstants.LEFT);
            }
        }
        else {
            cell.setForeground(Color.BLACK);
            cell.setFont(getFont());
            setHorizontalAlignment(SwingConstants.LEFT);
        }
        return cell;
    }
}
