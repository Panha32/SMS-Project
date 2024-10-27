package cls;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StatusCellRenderer extends DefaultTableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setHorizontalAlignment(CENTER);

        if (value instanceof String) {
            String status = (String) value;
            switch (status) {
                case "P":
                    cell.setForeground(Color.decode("#32CD32"));
                    break;
                case "L":
                    cell.setForeground(Color.decode("#ffa40a"));
                    break;
                case "E":
                    cell.setForeground(Color.decode("#7c7c7c"));
                    break;
                case "A":
                    cell.setForeground(Color.decode("#FF4500"));
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
