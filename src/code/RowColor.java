package code;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author ALEJO SALGADO
 */
public class RowColor extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused, int row, int column) {        
        Font fuente = new Font(getFont().getFontName(), Font.BOLD, 12);
        Component cell = super.getTableCellRendererComponent(table, value, selected, focused, row, column);
        if((column != -1) && (row != -1)){
            
            String valor = table.getValueAt(row, column).toString();
            
            if((column == 0) && (row == 0)) { ///si llego a agregar más columnas dejar en el como color a partir de la primer celda de la fila y la columna entera
                cell.setForeground(Color.BLACK);
                cell.setFont(getFont());
            }
            else if(valor.equals("?")) {
                cell.setForeground(Color.RED);
                cell.setFont(fuente);
            }
            else if(valor.equals("no valido")) {
                cell.setForeground(Color.BLUE);
                cell.setFont(fuente);
            }
            else {
                cell.setForeground(Color.BLACK);
                cell.setFont(getFont());
            }            
        }
        return cell;
    }
}
