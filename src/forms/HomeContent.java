package forms;

import cls.services.TotalStudents;
import cls.services.TotalCourses;
import cls.services.TotalDepartments;
import cls.services.TotalClasses;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class HomeContent {
    private JPanel firstPanel;
    private JPanel adminPanel;
    private JLabel lblProfile;
    private JLabel lblAdminName;
    private JLabel lblEmailAdmin;
    private JPanel profilePanel;
    private JPanel infoStuPanel;
    private JLabel lblFeature1;
    private JPanel hrPanel;
    private JPanel blankPanel1;
    private JPanel blankPanel2;
    private JPanel onePanel;
    private JPanel twoPanel;
    private JPanel threePanel;
    private JPanel fourPanel;
    private JPanel hrSpace;
    private JLabel lblMajor;
    private JLabel lblClass;
    private JLabel lblStudent;
    private JLabel lblCourse;
    private JPanel archievePanel;
    private JPanel hrSpace1;
    private JPanel hrSpace2;
    private JPanel gpaPanel;
    private JPanel principalPanel;
    private JPanel academicPanel;
    private JPanel newsPanel;
    private JPanel hrSpace3;
    private JPanel hrSpace4;
    private JPanel updatePanel;
    private JPanel update1Panel;
    private JPanel update2Panel;

    public HomeContent() {
        adminPanel.setOpaque(false);
        profilePanel.setOpaque(false);
        infoStuPanel.setOpaque(false);
        blankPanel1.setOpaque(false);
        blankPanel2.setOpaque(false);
        hrSpace.setOpaque(false);
        hrSpace1.setOpaque(false);
        hrSpace2.setOpaque(false);
        hrSpace3.setOpaque(false);
        hrSpace4.setOpaque(false);
        archievePanel.setOpaque(false);
        newsPanel.setOpaque(false);

        onePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        twoPanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        threePanel.setBorder(new EmptyBorder(10, 0, 10, 0));
        fourPanel.setBorder(new EmptyBorder(10, 0, 10, 0));

        firstPanel.setPreferredSize(new Dimension(950, 700));

        TotalStudents totalStudents = new TotalStudents();
        TotalCourses totalCourses = new TotalCourses();
        TotalDepartments totalDepartments = new TotalDepartments();
        TotalClasses totalClasses = new TotalClasses();

        lblStudent.setText("" + totalStudents.getTotalStudents());
        lblCourse.setText("" + totalCourses.getTotalCourses());
        lblMajor.setText("" + totalDepartments.getTotalDepartments());
        lblClass.setText("" + totalClasses.getTotalClasses());
    }



    public JPanel getFirstPanel() {
        return firstPanel;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Test HomeContent");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(950, 700);
//        HomeContent homeContent = new HomeContent();
//        frame.setContentPane(homeContent.firstPanel);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
