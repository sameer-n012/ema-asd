package ems.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import ems.app.Controller;

public class LoginView extends JPanel {

	private Controller controller;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createCompanyButton;
    private JLabel errorMssg;

    public LoginView() {
        super();

        this.initialize();
    }

    public JTextField getUsernameField() {
        return usernameField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public void clear() {
        usernameField.setText("");
        passwordField.setText("");
    }


    private void initialize() {
        this.setLayout(null);

        initTitle();
        initErrorMssg("");
        initUsernameField();
        initPasswordField();
        initLoginButton();
    }

    private void initTitle() {
        JLabel label = new JLabel("Employee Management Application", SwingConstants.CENTER);
        label.setBounds(0, 20, 500, 35);
        label.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(label);
    }

    private void initErrorMssg(String mssg) {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        errorMssg.setBounds(0, 110, 500, 35);
        errorMssg.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMssg.setForeground(Color.RED);

        this.add(errorMssg);
    }

    private void initUsernameField() {
        JLabel label = new JLabel("Username:", SwingConstants.RIGHT);
        label.setBounds(100, 160, 95, 35);
        label.setLabelFor(usernameField);
        label.setFont(new Font("DialogInput", Font.BOLD, 14));

        usernameField = new JTextField(20);
        usernameField.setBounds(205, 160, 200, 35);

        usernameField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (usernameField.getText().length() >= 8) {
                    e.consume();
                } else if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume(); //TODO change accepted keys for usernames
                }
            }
        });

        this.add(label);
        this.add(usernameField);
    }

    private void initPasswordField() {
        JLabel label = new JLabel("Password:", SwingConstants.RIGHT);
        label.setBounds(100, 200, 95, 35);
        label.setLabelFor(passwordField);
        label.setFont(new Font("DialogInput", Font.BOLD, 14));

        passwordField = new JPasswordField(20);
        passwordField.setBounds(205, 200, 200, 35);

        passwordField.addKeyListener(new KeyAdapter() {


            @Override //TODO Most likely remove (no bounds on password characters
            public void keyTyped(KeyEvent e) {
                if (passwordField.getPassword().length >= 4) {
                    e.consume();
                } else if (e.getKeyChar() < 48 || e.getKeyChar() > 57) {
                    e.consume();
                }
            }
        });

        this.add(label);
        this.add(passwordField);
    }

    private void initLoginButton() {
    	loginButton = new JButton("Login");
        loginButton.setBounds(205, 260, 200, 35);
    
        loginButton.addActionListener(new ActionListener() {
    
            /*
             * Respond when the user clicks the Login button.
             */
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(loginButton)) {
                    String accountNumber = usernameField.getText();
                    char[] pin = passwordField.getPassword();
                        
                    controller.login(accountNumber, pin);
                }
            }
        });
    
        this.add(loginButton);
    }
}