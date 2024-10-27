import forms.LoginForm;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Login Form");
        LoginForm loginForm = new LoginForm();
        frame.setContentPane(loginForm.getMainPanelLoginForm());
        frame.setSize(800, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
