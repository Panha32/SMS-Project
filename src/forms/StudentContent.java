package forms;

import cls.BorderRadius;
import cls.TableUtils;
import cls.crud.Search;
import cls.services.Student;
import cls.services.StudentList;
import cls.services.TotalStudents;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentContent {
    private JPanel secondPanel;
    private JPanel topPanel;
    private JPanel listPanel;
    private JButton btnAdd;
    private JButton btnSearch;
    private JButton btnSort;
    private JTable tableStudent;
    private JPanel hrPanel;
    private JTextField searchField;
    private JPanel hrPanel1;
    private JPanel bottomPanel;
    private JPanel hrPanel2;
    private JLabel lblTotalStu;
    private JLabel lblTotalFemale;
    private JButton btnView;
    private JTextField txtSearchDetail;

    private StudentList studentList;
    private Search searchFor;
    private TotalStudents totalStudents;

    public StudentContent() {

        studentList = new StudentList();
        searchFor = new Search();
        totalStudents = new TotalStudents();

        secondPanel.setPreferredSize(new Dimension(950, 700));

        topPanel.setOpaque(false);
        hrPanel.setOpaque(false);
        hrPanel1.setOpaque(false);
        hrPanel2.setOpaque(false);
        listPanel.setBackground(Color.decode("#2e2e2e"));
        listPanel.setOpaque(true);
        bottomPanel.setOpaque(false);

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

        txtSearchDetail.setBorder(BorderFactory.createEmptyBorder());
        txtSearchDetail.setBorder(new BorderRadius(5));
        txtSearchDetail.setCaretColor(Color.WHITE);
        btnView.setBorderPainted(false);
        btnView.setFocusPainted(false);
        btnView.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JScrollPane scrollPane = new JScrollPane(tableStudent);
        listPanel.setLayout(new BorderLayout());
        listPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);

        tableStudent.setBackground(Color.decode("#2e2e2e"));
        tableStudent.setOpaque(false);
        tableStudent.setForeground(Color.decode("#d1d1d1"));
        tableStudent.setFont(new Font("Arial", Font.BOLD, 12));

        loadStudentData(null, null);
        int totalFemale = totalStudents.getTotalFemaleStudents();
        lblTotalFemale.setText("Total Female Students: " + totalFemale);
        int totalStudent = totalStudents.getTotalStudents();
        lblTotalStu.setText("Total Students: " + totalStudent);

        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim();
                String studentId = null;
                String studentName = null;

                if (searchText.matches("\\d+")) {
                    studentId = searchText;
                }
                else {
                    studentName = searchText;
                }
                loadStudentData(studentId, studentName);
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(secondPanel);
                AddStudent dialog = new AddStudent(parentFrame);
                dialog.pack();
                dialog.setLocationRelativeTo(parentFrame);
                dialog.setVisible(true);
            }
        });
        btnView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = txtSearchDetail.getText().trim();
                if (!studentId.isEmpty()) {
                    Student student = studentList.fetchStudentById(studentId);
                    if (student != null) {
                        StudentData dialog = new StudentData((JFrame) SwingUtilities.getWindowAncestor(secondPanel));
                        dialog.setStudentInfo(student);
                        dialog.setLocationRelativeTo(null);
                        dialog.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(secondPanel, "No student found with ID: " + studentId);
                    }
                } else {
                    JOptionPane.showMessageDialog(secondPanel, "Please enter a student ID.");
                }
            }
        });

    }

    private void loadStudentData(String studentId, String studentName) {
        String[] columnNames = {
                "ID", "First Name", "Last Name",
                "Gender", "Date of Birth", "Email",
                "Phone", "Department", "Class"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        ResultSet rs;
        if (studentId == null && studentName == null) {
            rs = studentList.fetchAllStudents();
        }
        else {
            rs = searchFor.searchStudents(studentId, studentName);
        }

        try {
            while (rs != null && rs.next()) {
                Object[] row = {
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("gender"),
                        rs.getDate("date_of_birth"),
                        rs.getString("email"),
                        rs.getString("phone_number"),
                        rs.getString("department_name"),
                        rs.getString("class_name")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        tableStudent.setModel(tableModel);

        TableUtils.setRowHeight(tableStudent, 25);
        int[] columnWidths = {40, 150, 150, 80, 120, 200, 100, 80, 80};
        TableUtils.setColumnWidths(tableStudent, columnWidths);
        TableUtils.setColumnPadding(tableStudent, 10);
        int[] columnsToCenter = {0, 3, 4, 6, 7, 8};
        TableUtils.setColumnTextAlignment(tableStudent, columnsToCenter, SwingConstants.CENTER);
    }

    public JPanel getSecondPanel() {
        return secondPanel;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Student List");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(950, 700);
//        StudentContent studentContent = new StudentContent();
//        frame.setContentPane(studentContent.secondPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
