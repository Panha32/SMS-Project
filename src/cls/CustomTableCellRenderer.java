package cls;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Top, Left, Bottom, Right padding

        if (column == 0) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        else if (column == 3) {
            label.setHorizontalAlignment(SwingConstants.CENTER);
        }
        else {
            label.setHorizontalAlignment(SwingConstants.LEFT);
        }
        return label;
    }
}
