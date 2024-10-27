package forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {
    private JPanel homePanel;
    private JPanel menuPanel;
    private JPanel contentPanel;
    private JPanel titleProject;
    private JPanel menuItem;
    private JLabel lblProject;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton btnSetting;
    private JButton btnMode;

    public HomePage() {

        homePanel.setOpaque(true);
        homePanel.setBackground(Color.decode("#242424"));
        menuPanel.setOpaque(true);
        menuPanel.setBackground(Color.decode("#242424"));
        contentPanel.setOpaque(true);
        contentPanel.setBackground(Color.decode("#242424"));

        button1.setOpaque(false);
        button1.setBorderPainted(false);
        button1.setFocusPainted(false);
        button1.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button2.setOpaque(false);
        button2.setBorderPainted(false);
        button2.setFocusPainted(false);
        button2.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button3.setOpaque(false);
        button3.setBorderPainted(false);
        button3.setFocusPainted(false);
        button3.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button4.setOpaque(false);
        button4.setBorderPainted(false);
        button4.setFocusPainted(false);
        button4.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSetting.setOpaque(false);
        btnSetting.setBorderPainted(false);
        btnSetting.setFocusPainted(false);
        btnSetting.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMode.setOpaque(false);
        btnMode.setBorderPainted(false);
        btnMode.setFocusPainted(false);
        btnMode.setCursor(new Cursor(Cursor.HAND_CURSOR));

        HomeContent homeContent = new HomeContent();
        StudentContent studentContent = new StudentContent();
        CourseContent courseContent = new CourseContent();
        AttendanceContent attendanceContent = new AttendanceContent();

        contentPanel.add(homeContent.getFirstPanel(), "Home");
        contentPanel.add(studentContent.getSecondPanel(), "Students");
        contentPanel.add(courseContent.getThirdPanel(), "Courses");
        contentPanel.add(attendanceContent.getForthPanel(), "Attendances");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Home");
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Students");
            }
        });
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Courses");
            }
        });
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCard("Attendances");
            }
        });
    }

    private void showCard(String cardName) {
        CardLayout cl = (CardLayout) (contentPanel.getLayout());
        cl.show(contentPanel, cardName);
        System.out.println("Switched to card: " + cardName);
    }

    public JPanel getHomePanel() {
        return homePanel;
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Home Page");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1200, 700);
//        HomePage homePage = new HomePage();
//        frame.setContentPane(homePage.getHomePanel());
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//    }
}
