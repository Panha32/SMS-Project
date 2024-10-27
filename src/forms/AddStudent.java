package forms;

import cls.crud.Add;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStudent extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JPanel inputPanel;
    private JPanel hrPanel;
    private JTextField txtFirstname;
    private JTextField txtLastname;
    private JComboBox genderBox;
    private JTextField txtEmail;
    private JTextField txtDob;
    private JTextField txtPhone;
    private JTextField txtDepartment;
    private JTextField txtClass;
    private JButton btnSave;
    private JButton btnCancel;

    public AddStudent(JFrame parentFrame) {
        super(parentFrame, "Add New Student", true);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        txtFirstname.setBorder(BorderFactory.createEmptyBorder());
        txtFirstname.setBorder(new EmptyBorder(5, 5,5,5));
        txtFirstname.setCaretColor(Color.WHITE);

        txtLastname.setBorder(BorderFactory.createEmptyBorder());
        txtLastname.setBorder(new EmptyBorder(5, 5,5,5));
        txtLastname.setCaretColor(Color.WHITE);

        genderBox.setBorder(BorderFactory.createEmptyBorder());
        genderBox.setUI(new BasicComboBoxUI() {
            @Override
            protected void installDefaults() {
                super.installDefaults();
                comboBox.setBorder(BorderFactory.createEmptyBorder()); // Remove focus border
            }
        });
        genderBox.setUI(new BasicComboBoxUI() {
            @Override
            protected Insets getInsets() {
                return new Insets(5, 5, 5, 5); // Top, Left, Bottom, Right
            }
        });

        txtDob.setBorder(BorderFactory.createEmptyBorder());
        txtDob.setBorder(new EmptyBorder(5, 5,5,5));
        txtDob.setCaretColor(Color.WHITE);

        txtPhone.setBorder(BorderFactory.createEmptyBorder());
        txtPhone.setBorder(new EmptyBorder(5, 5,5,5));
        txtPhone.setCaretColor(Color.WHITE);

        txtEmail.setBorder(BorderFactory.createEmptyBorder());
        txtEmail.setBorder(new EmptyBorder(5, 5,5,5));
        txtEmail.setCaretColor(Color.WHITE);

        txtDepartment.setBorder(BorderFactory.createEmptyBorder());
        txtDepartment.setBorder(new EmptyBorder(5, 5,5,5));
        txtDepartment.setCaretColor(Color.WHITE);

        txtClass.setBorder(BorderFactory.createEmptyBorder());
        txtClass.setBorder(new EmptyBorder(5, 5,5,5));
        txtClass.setCaretColor(Color.WHITE);

        btnSave.setBorderPainted(false);
        btnSave.setFocusPainted(false);
        btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnCancel.setBorderPainted(false);
        btnCancel.setFocusPainted(false);
        btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String firstName = txtFirstname.getText().trim();
                String lastName = txtLastname.getText().trim();
                String gender = genderBox.getSelectedItem().toString();
                String dateOfBirth = txtDob.getText().trim();
                String email = txtEmail.getText().trim();
                String phoneNumber = txtPhone.getText().trim();
                String departmentName = txtDepartment.getText().trim();
                String className = txtClass.getText().trim();

                Add addStudent = new Add();
                boolean success = addStudent.addStudent(firstName, lastName, gender, dateOfBirth, email, phoneNumber, departmentName, className);

                if (success) {
                    JOptionPane.showMessageDialog(AddStudent.this, "Student added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddStudent.this, "Failed to add student. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame();
//        AddStudent dialog = new AddStudent(frame);
//        dialog.pack();
//        dialog.setLocationRelativeTo(null);
//        dialog.setVisible(true);
//        System.exit(0);
//    }
}
