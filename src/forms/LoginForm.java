package forms;

import cls.BorderRadius;
import cls.ImagePanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
    private JPanel MainPanelLoginForm;
    private JPanel overlayPanel;
    private JPanel leftSide;
    private JPanel rightSide;
    private JLabel lblProject;
    private JLabel adminIcon;
    private JTextField txtUsername;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JPasswordField txtPassword;
    private JPanel vr;
    private JPanel hr;
    private JPanel hr1;
    private JButton btnLogin;
    private JCheckBox chbox;
    private JPanel extraFeature;
    private JPanel hr2;
    private JPanel hr0;
    private JLabel lbldescription;

    public LoginForm() {
        Image backgroundImage = new ImageIcon(getClass().getResource("/images/bg.png")).getImage();
        MainPanelLoginForm = new ImagePanel(backgroundImage);
        MainPanelLoginForm.setLayout(new BorderLayout());

        overlayPanel.setOpaque(false);
        leftSide.setOpaque(false);
        rightSide.setOpaque(false);
        hr.setOpaque(false);
        hr0.setOpaque(false);
        hr1.setOpaque(false);
        hr2.setOpaque(false);
        extraFeature.setOpaque(false);
        chbox.setOpaque(false);
        MainPanelLoginForm.add(overlayPanel, BorderLayout.CENTER);

        lblProject.setText("<html><center>STUDENT MANAGEMENT<br>SYSTEM</center></html>");
        lbldescription.setText("<html><center>Streamline your educational journey<br>with our intuitive and<br>efficient management system.</center></html>");

        txtUsername.setOpaque(false);
        txtUsername.setBorder(new BorderRadius(4));
        txtUsername.setCaretColor(Color.WHITE);
        txtPassword.setOpaque(false);
        txtPassword.setBorder(new BorderRadius(4));
        txtPassword.setCaretColor(Color.WHITE);
        btnLogin.setOpaque(false);
        btnLogin.setContentAreaFilled(false);
        btnLogin.setBorder(new BorderRadius(4));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add action listener to the login button
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());

                if (username.equals("Javadeveloper") && password.equals("smsproject123")) {
                    JFrame frame = new JFrame("Home Page");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setSize(1200, 700);
                    HomePage homePage = new HomePage();
                    frame.setContentPane(homePage.getHomePanel());
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);

                    SwingUtilities.getWindowAncestor(MainPanelLoginForm).dispose();
                }
                else {
                    if (!username.equals("Javadeveloper")) {
                        JOptionPane.showMessageDialog(MainPanelLoginForm, "Incorrect Username", "Error", JOptionPane.ERROR_MESSAGE);
                    } else if (!password.equals("smsproject123")) {
                        JOptionPane.showMessageDialog(MainPanelLoginForm, "Incorrect Password", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    public JPanel getMainPanelLoginForm() {
        return MainPanelLoginForm;
    }
}
