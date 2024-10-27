package cls;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;

public class TableUtils {

    public static void setRowHeight(JTable table, int rowHeight) {
        table.setRowHeight(rowHeight);
    }

    public static void setColumnWidths(JTable table, int[] columnWidths) {
        TableColumn column;
        for (int i = 0; i < columnWidths.length; i++) {
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(columnWidths[i]);
        }
    }

    public static void setColumnPadding(JTable table, int padding) {
        DefaultTableCellRenderer paddingRenderer = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    ((JLabel) c).setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
                }
                return c;
            }
        };

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(paddingRenderer);
        }
    }

    public static void setColumnTextAlignment(JTable table, int[] columns, int alignment) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(alignment);

        for (int column : columns) {
            table.getColumnModel().getColumn(column).setCellRenderer(centerRenderer);
        }
    }
}
