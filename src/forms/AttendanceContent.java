package forms;

import cls.BorderRadius;
import cls.StatusCellRenderer;
import cls.TableUtils;
import cls.services.DataFetcher;
import db.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AttendanceContent {
    private JPanel forthPanel;
    private JPanel topPanel;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnSort;
    private JTextField searchField;
    private JPanel hrPanel1;
    private JPanel hrPanel;
    private JPanel listPanel;
    private JTable tableAtt;
    private JPanel hrPanel2;

    public AttendanceContent() {
        forthPanel.setPreferredSize(new Dimension(950, 700));

        btnAdd.setBorderPainted(false);
        btnSearch.setBorderPainted(false);
        btnSort.setBorderPainted(false);

        btnAdd.setFocusPainted(false);
        btnSearch.setFocusPainted(false);
        btnSort.setFocusPainted(false);

        btnAdd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSearch.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSort.setCursor(new Cursor(Cursor.HAND_CURSOR));

        searchField.setBorder(BorderFactory.createEmptyBorder());
        searchField.setBorder(new BorderRadius(5));
        searchField.setCaretColor(Color.WHITE);
        try {
            Connection conn = DBConnection.getConnection();
            DataFetcher dataFetcher = new DataFetcher(conn);
            ResultSet rs = dataFetcher.fetchAttendanceData();
            tableAtt = createTableFromResultSet(rs);
            listPanel.add(new JScrollPane(tableAtt));

            listPanel.setBackground(Color.decode("#2e2e2e"));

            tableAtt.setBackground(Color.decode("#2e2e2e"));
            tableAtt.setOpaque(false);
            tableAtt.setForeground(Color.decode("#d1d1d1"));
            tableAtt.setFont(new Font("Arial", Font.BOLD, 12));

            TableUtils.setRowHeight(tableAtt, 25);
            int[] columnWidths = {50, 200, 150, 60, 60, 60, 60, 60, 80, 80, 80};
            TableUtils.setColumnWidths(tableAtt, columnWidths);
            TableUtils.setColumnPadding(tableAtt, 10);
            int[] columnsToCenter = {0, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            TableUtils.setColumnTextAlignment(tableAtt, columnsToCenter, SwingConstants.CENTER);
            tableAtt.getColumnModel().getColumn(3).setCellRenderer(new StatusCellRenderer());
            tableAtt.getColumnModel().getColumn(4).setCellRenderer(new StatusCellRenderer());
            tableAtt.getColumnModel().getColumn(5).setCellRenderer(new StatusCellRenderer());
            tableAtt.getColumnModel().getColumn(6).setCellRenderer(new StatusCellRenderer());
            tableAtt.getColumnModel().getColumn(7).setCellRenderer(new StatusCellRenderer());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static JTable createTableFromResultSet(ResultSet rs) throws SQLException {
        String[] columnNames = {"#", "Name", "Date", "Java", "HTML5", "JS", "Python", "DSA", "Late", "Absent", "Excused"};

        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        while (rs.next()) {
            Object[] row = new Object[columnNames.length];
            row[0] = rs.getInt("student_id");
            row[1] = rs.getString("student_name");
            row[2] = rs.getDate("attendance_date");
            row[3] = rs.getString("Java");
            row[4] = rs.getString("HTML_CSS");
            row[5] = rs.getString("JavaScript");
            row[6] = rs.getString("Python");
            row[7] = rs.getString("DSA");
            row[8] = rs.getInt("total_late");
            row[9] = rs.getInt("total_absent");
            row[10] = rs.getInt("total_excused");
            model.addRow(row);
        }
        return new JTable(model);
    }


    public JPanel getForthPanel() {
        return forthPanel;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Test HomeContent");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(950, 700);
//        AttendanceContent attendanceContent = new AttendanceContent();
//        frame.setContentPane(attendanceContent.forthPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
