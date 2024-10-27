package forms;

import cls.TableUtils;
import cls.services.Student;
import cls.services.StudentList;
import db.DBConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentData extends JDialog {
    private JPanel maindataPanel;
    private JPanel innerPanel;
    private JPanel hrPanel;
    private JPanel pfPanel;
    private JLabel lblName;
    private JLabel lblId;
    private JLabel lblGender;
    private JPanel subInfoPanel;
    private JLabel lblDob;
    private JTextField infoName;
    private JTextField infoId;
    private JTextField infoClass;
    private JTextField infoMajor;
    private JTextField infoGender;
    private JTextField infoDob;
    private JTextField infoGmail;
    private JTextField infoPhone;
    private JButton btnEdit;
    private JButton btnDelete;
    private JTextField infoLastname;
    private JPanel notePanel;
    private JPanel scorePanel;
    private JTable tableScoreStudent;
    private JPanel tablePanel;
    private JButton btnExit;
    private JLabel lblTotalScore;
    private JLabel lblGPA;
    private JLabel lblGrade;
    private JButton btnDev;

    private StudentList studentList;

    public StudentData(JFrame parentFrame) {
        super(parentFrame, "Student Information", true);
        this.studentList = studentList;

        infoName.setBorder(BorderFactory.createEmptyBorder());
        infoName.setBorder(new EmptyBorder(5, 5,5,5));
        infoName.setCaretColor(Color.WHITE);

        infoLastname.setBorder(BorderFactory.createEmptyBorder());
        infoLastname.setBorder(new EmptyBorder(5, 5,5,5));
        infoLastname.setCaretColor(Color.WHITE);

        infoId.setBorder(BorderFactory.createEmptyBorder());
        infoId.setBorder(new EmptyBorder(5, 5,5,5));
        infoId.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        infoId.setEditable(false);

        infoGender.setBorder(BorderFactory.createEmptyBorder());
        infoGender.setBorder(new EmptyBorder(5, 5,5,5));
        infoGender.setCaretColor(Color.WHITE);

        infoDob.setBorder(BorderFactory.createEmptyBorder());
        infoDob.setBorder(new EmptyBorder(5, 5,5,5));
        infoDob.setCaretColor(Color.WHITE);

        infoClass.setBorder(BorderFactory.createEmptyBorder());
        infoClass.setBorder(new EmptyBorder(5, 5,5,5));
        infoClass.setCaretColor(Color.WHITE);

        infoMajor.setBorder(BorderFactory.createEmptyBorder());
        infoMajor.setBorder(new EmptyBorder(5, 5,5,5));
        infoMajor.setCaretColor(Color.WHITE);

        infoGmail.setBorder(BorderFactory.createEmptyBorder());
        infoGmail.setBorder(new EmptyBorder(5, 5,5,5));
        infoGmail.setCaretColor(Color.WHITE);

        infoPhone.setBorder(BorderFactory.createEmptyBorder());
        infoPhone.setBorder(new EmptyBorder(5, 5,5,5));
        infoPhone.setCaretColor(Color.WHITE);

        btnDev.setBorderPainted(false);
        btnDev.setFocusPainted(false);

        btnEdit.setBorderPainted(false);
        btnEdit.setFocusPainted(false);
        btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveStudentData();
            }
        });

        btnDelete.setBorderPainted(false);
        btnDelete.setFocusPainted(false);
        btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        btnExit.setBorderPainted(false);
        btnExit.setFocusPainted(false);
        btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        JScrollPane scrollPane = new JScrollPane(tableScoreStudent);
        tablePanel.setLayout(new BorderLayout());
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        tablePanel.setBackground(Color.decode("#2e2e2e"));
        scrollPane.setOpaque(false);
//        scrollPane.setBorder(null);
        scrollPane.getViewport().setOpaque(false);

        tableScoreStudent.setBackground(Color.decode("#242424"));
        tableScoreStudent.setOpaque(false);
        tableScoreStudent.setForeground(Color.decode("#f0f8ff"));
        tableScoreStudent.setFont(new Font("Arial", Font.BOLD, 12));

        this.add(maindataPanel);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 650));
        pack();
    }

    public void setStudentInfo(Student student) {
        infoName.setText(student.getFirstName());
        infoLastname.setText(student.getLastName());
        infoId.setText(student.getId());
        infoClass.setText(student.getClassName());
        infoMajor.setText(student.getDepartment());
        infoGender.setText(student.getGender());
        infoDob.setText(student.getDob().toString());
        infoGmail.setText(student.getEmail());
        infoPhone.setText(student.getPhone());

        fetchStudentScores();
    }

    private void saveStudentData() {
        String id = infoId.getText();
        String name = infoName.getText();
        String lastname = infoLastname.getText();
        String gender = infoGender.getText();
        String dob = infoDob.getText();
        String studentClass = infoClass.getText();
        String major = infoMajor.getText();
        String email = infoGmail.getText();
        String phoneNumber = infoPhone.getText();

        Connection con = DBConnection.getConnection();
        if (con != null) {
            String query = "UPDATE students SET first_name = ?, last_name = ? , gender = ?, date_of_birth = ?, class_id = ?, department_id = ?, email = ?, phone_number = ? WHERE student_id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, name);
                pstmt.setString(2, lastname);
                pstmt.setString(3, gender);
                pstmt.setDate(4, java.sql.Date.valueOf(dob));
                pstmt.setString(5, studentClass);
                pstmt.setString(6, major);
                pstmt.setString(7, email);
                pstmt.setString(8, phoneNumber);
                pstmt.setInt(9, Integer.parseInt(id));
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Student data updated successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating student data.");
            }
        }
    }

    private void fetchStudentScores() {
        String studentId = infoId.getText();
        if (studentId.isEmpty()) {
            return;
        }

        Connection con = DBConnection.getConnection();
        if (con != null) {
            String query = "SELECT sms.courses.course_name, sms.scores.score, sms.scores.grade " +
                    "FROM sms.scores " +
                    "JOIN sms.courses ON sms.scores.course_id = sms.courses.course_id " +
                    "WHERE student_id = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setInt(1, Integer.parseInt(studentId));
                ResultSet rs = pstmt.executeQuery();

                String[] columnNames = {"Course", "Score", "Grade"};
                DefaultTableModel model = new DefaultTableModel(columnNames, 0);

                int totalScore = 0;
                int subjectCount = 0;
                while (rs.next()) {
                    String courseName = rs.getString("course_name");
                    int score = rs.getInt("score");
                    String grade = rs.getString("grade");

                    totalScore += score;
                    subjectCount++;

                    Object[] row = {courseName, score, grade};
                    model.addRow(row);
                }

                tableScoreStudent.setModel(model);

                TableUtils.setRowHeight(tableScoreStudent, 25);
                int[] columnWidths = {150, 70, 70};
                TableUtils.setColumnWidths(tableScoreStudent, columnWidths);
                TableUtils.setColumnPadding(tableScoreStudent, 7);
                int[] columnsToCenter = {1, 2};
                TableUtils.setColumnTextAlignment(tableScoreStudent, columnsToCenter, SwingConstants.CENTER);

                if (subjectCount > 0) {
                    double gpa = (double) totalScore / subjectCount / 100 * 4.0; // Normalize to 4.0 scale
                    lblGPA.setText(String.format("GPA: %.2f", gpa));
                    lblTotalScore.setText("Total Score: " + totalScore);

                    String grade;
                    if (gpa >= 3.7) {
                        grade = "Excellent";
                    } else if (gpa >= 3.0) {
                        grade = "Good";
                    } else if (gpa >= 2.0) {
                        grade = "Medium";
                    } else {
                        grade = "Fail";
                    }
                    lblGrade.setText("Ranking: " + grade);
                } else {
                    lblGPA.setText("GPA: N/A");
                    lblTotalScore.setText("Total Score: N/A");
                    lblGrade.setText("Ranking: N/A");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error fetching student scores.");
            }
        }
    }


    private void deleteStudent() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String id = infoId.getText();

            Connection con = DBConnection.getConnection();
            if (con != null) {
                String query = "DELETE FROM students WHERE student_id = ?";
                try (PreparedStatement pstmt = con.prepareStatement(query)) {
                    pstmt.setInt(1, Integer.parseInt(id));
                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(this, "Student deleted successfully.");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "No student found with the provided ID.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, "Error deleting student.");
                }
            }
        }
    }

    private int getClassId(String className) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            String query = "SELECT class_id FROM classes WHERE class_name = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, className);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("class_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    private int getDepartmentId(String departmentName) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            String query = "SELECT department_id FROM departments WHERE department_name = ?";
            try (PreparedStatement pstmt = con.prepareStatement(query)) {
                pstmt.setString(1, departmentName);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return rs.getInt("department_id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        StudentData dialog = new StudentData(frame);
//        dialog.setLocationRelativeTo(null);
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
