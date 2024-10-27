package forms;

import cls.GradeCellRenderer;
import cls.RankCellRenderer;
import cls.TableUtils;
import cls.services.ResultFetcher;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseContent {

    private JPanel thirdPanel;
    private JPanel topPanel;
    private JPanel hrPanel;
    private JPanel listPanel;
    private JTable tableResult;
    private JLabel lblTotal;
    private JLabel lblA;
    private JLabel lblB;
    private JLabel lblC;
    private JLabel lblD;
    private JLabel lblE;
    private JLabel lblF;

    public CourseContent() {
        thirdPanel.setPreferredSize(new Dimension(950, 700));
        loadTableData();
        listPanel.add(new JScrollPane(tableResult), BorderLayout.CENTER);

        listPanel.setBackground(Color.decode("#2e2e2e"));
        tableResult.setBackground(Color.decode("#2e2e2e"));
        tableResult.setOpaque(false);
        tableResult.setForeground(Color.decode("#d1d1d1"));
        tableResult.setFont(new Font("Arial", Font.BOLD, 12));
    }

    private void loadTableData() {
        ResultFetcher fetcher = new ResultFetcher();
        try (ResultSet rs = fetcher.fetchExamResults()) {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("#");
            model.addColumn("ID");
            model.addColumn("Name");
            model.addColumn("Gender");
            model.addColumn("Java");
            model.addColumn("HTML5");
            model.addColumn("JS");
            model.addColumn("Python");
            model.addColumn("DSA");
            model.addColumn("Total");
            model.addColumn("Average");
            model.addColumn("Grade");
            model.addColumn("Rank");

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("orderNum"),
                        rs.getInt("student_id"),
                        rs.getString("name"),
                        rs.getString("gender"),
                        rs.getString("Java"),
                        rs.getString("HTML5"),
                        rs.getString("JS"),
                        rs.getString("Python"),
                        rs.getString("DSA"),
                        rs.getString("total_score"),
                        rs.getString("average"),
                        rs.getString("grade"),
                        rs.getInt("Rankstu")
                });
            }
            tableResult.setModel(model);
            TableUtils.setRowHeight(tableResult, 25);
            int[] columnWidths = {30, 60, 150, 70, 60, 60, 60, 60, 60, 100, 100, 70, 70};
            TableUtils.setColumnWidths(tableResult, columnWidths);
            TableUtils.setColumnPadding(tableResult, 10);
            int[] columnsToCenter = {0, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
            TableUtils.setColumnTextAlignment(tableResult, columnsToCenter, SwingConstants.CENTER);

            tableResult.getColumnModel().getColumn(11).setCellRenderer(new GradeCellRenderer()); // Grade column
            tableResult.getColumnModel().getColumn(12).setCellRenderer(new RankCellRenderer());  // Rank column
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        updateCounts();
    }

    private void updateCounts() {
        ResultFetcher fetcher = new ResultFetcher();
        lblTotal.setText("Total Students : " + fetcher.getTotalStudentCount() + " and female : " + fetcher.getTotalWomenCount());
        lblA.setText("Grade A : " + fetcher.getGradeCount("A"));
        lblB.setText("Grade B : " + fetcher.getGradeCount("B"));
        lblC.setText("Grade C : " + fetcher.getGradeCount("C"));
        lblD.setText("Grade D : " + fetcher.getGradeCount("D"));
        lblE.setText("Grade E : " + fetcher.getGradeCount("E"));
        lblF.setText("Grade F : " + fetcher.getGradeCount("F"));
    }

    public JPanel getThirdPanel() {
        return thirdPanel;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Student List");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(950, 700);
//        CourseContent courseContent = new CourseContent();
//        frame.setContentPane(courseContent.getThirdPanel());
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
