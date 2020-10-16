package org.ucvts.ema.views;

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

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

public class LoginView extends JPanel {

	private Controller controller;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField companyField;
    private JButton loginButton;
    private JButton createCompanyButton;
    private JLabel errorMssg;
    
    private EMA ema;
    

    public LoginView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.controller = controller;
        this.initialize();
    }

    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    public String getCompanyName() {
    	return companyField.getText();
    }

    public void clear() {
        usernameField.setText("");
        passwordField.setText("");
    }

    public void showErrorMessage(String mssg, boolean show){
    	if(show) {
    		errorMssg.setText(mssg);
    		errorMssg.setVisible(true);
    	}else {
    		errorMssg.setText("");
    		errorMssg.setVisible(false);
    	}
    }

    private void initialize() {
        this.setLayout(null);

        initTitle();
        initErrorMssg();
        initUsernameField();
        initPasswordField();
        initCompanyField();
        initLoginButton();
        initCreateCompanyButton();
    }
    

    private void initTitle() {
        JLabel label = new JLabel("Employee Management Application", SwingConstants.CENTER);
        label.setBounds(0, 20, 500, 35);
        label.setFont(new Font("DialogInput", Font.BOLD, 21));

        this.add(label);
    }

    private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        errorMssg.setBounds(50, 275, 500, 35);
        errorMssg.setFont(new Font("DialogInput", Font.ITALIC, 12));
        errorMssg.setForeground(Color.RED);
        errorMssg.setVisible(false);

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
                if (getUsername().length() >= 20) {
                    e.consume();  //Next line includes upper and lowercase letters and 0-9 
                } else if (e.getKeyChar() < 48 || (e.getKeyChar() < 65 && e.getKeyChar() > 57) || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                    e.consume(); 
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
                if (getPassword().length() >= 20) {
                    e.consume(); //Next line allows upper and lowercase letters, 0-9, and special characters
                }else if (e.getKeyChar() < 33 || (e.getKeyChar() < 64 && e.getKeyChar() > 57) || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                    e.consume();
                }
            }
        });

        this.add(label);
        this.add(passwordField);
    }
    
    private void initCompanyField() {
        JLabel label = new JLabel("Company:", SwingConstants.RIGHT);
        label.setBounds(100, 240, 95, 35);
        label.setLabelFor(companyField);
        label.setFont(new Font("DialogInput", Font.BOLD, 14));

        companyField = new JTextField(20);
        companyField.setBounds(205, 240, 200, 35);

        companyField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (companyField.getText().length() >= 20) {
                    e.consume();  //Next line includes upper and lowercase letters and 0-9 
                } else if (e.getKeyChar() < 48 || (e.getKeyChar() < 65 && e.getKeyChar() > 57) || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                    e.consume(); 
                }
            }
        });

        this.add(label);
        this.add(companyField);
    }

    private void initLoginButton() {
    	loginButton = new JButton("Login");
        loginButton.setBounds(205, 320, 200, 35);
    
        loginButton.addActionListener(new ActionListener() {
    
            /*
             * Respond when the user clicks the Login button.
             */
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(loginButton)) {
                    String username = getUsername();
                    String password = getPassword();

                    controller.login(username, password);
                }
            }
        });
        this.add(loginButton);
        
        
    }
    
    private void initCreateCompanyButton() {
    	createCompanyButton = new JButton("Create Company");
    	createCompanyButton.setBounds(205, 360, 200, 35);
    	
    	createCompanyButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String username = getUsername();
    			String password = getPassword();
    			String companyName = getCompanyName();
    			controller.createCompany(username, password, companyName);
    		}
    	});
        this.add(createCompanyButton);
    }
}