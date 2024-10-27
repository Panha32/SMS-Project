package cls;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class GradeCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setHorizontalAlignment(CENTER);

        if (value != null) {
            String grade = value.toString();
            switch (grade) {
                case "A":
                    cell.setForeground(Color.MAGENTA);
                    break;
                case "B":
                    cell.setForeground(Color.CYAN);
                    break;
                case "C":
                    cell.setForeground(Color.ORANGE);
                    break;
                case "D":
                    cell.setForeground(Color.GREEN);
                    break;
                case "E":
                    cell.setForeground(Color.YELLOW);
                    break;
                case "F":
                    cell.setForeground(Color.RED);
                    break;
                default:
                    cell.setForeground(Color.decode("#f0f8ff"));
                    break;
            }
        }
        else {
            cell.setForeground(Color.decode("#f0f8ff"));
        }
        return cell;
    }
}

