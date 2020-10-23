package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

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
    private Border buttonBorder;
    
    private EMA ema;
    

    public LoginView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
        this.controller = controller;
        this.initialize();
    }
    
    public void updateCard() {
    	
    	this.removeAll();
    	initialize();
    }

    public String getUsernameTextField() {
        return usernameField.getText();
    }

    public String getPasswordTextField() {
        return new String(passwordField.getPassword());
    }
    
    public String getCompanyNameTextField() {
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
        label.setBounds(50, 60, 500, 35);
        label.setFont(ema.TITLE_FONT);
        label.setForeground(ema.FOREGROUND_COLOR);

        this.add(label);
    }

    private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        errorMssg.setBounds(50, 275, 500, 35);
        errorMssg.setFont(ema.ERROR_FONT);
        errorMssg.setForeground(ema.ERROR_COLOR);
        errorMssg.setVisible(false);

        this.add(errorMssg);
    }

    private void initUsernameField() {
        JLabel label = new JLabel("Username:", SwingConstants.RIGHT);
        label.setForeground(ema.FOREGROUND_COLOR);
        label.setBounds(100, 160, 95, 35);
        label.setLabelFor(usernameField);
        label.setFont(ema.TEXT_FONT);

        usernameField = new JTextField(20);
        usernameField.setBounds(205, 165, 200, 25);
        usernameField.setFont(ema.TEXT_FONT);
        usernameField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR));
        usernameField.setBackground(ema.BACKGROUND_COLOR);
        usernameField.setForeground(ema.FOREGROUND_COLOR);
        usernameField.setCaretColor(ema.FOREGROUND_COLOR);
        
        usernameField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (getUsernameTextField().length() >= 20) {
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
        label.setForeground(ema.FOREGROUND_COLOR);
        label.setLabelFor(passwordField);
        label.setFont(ema.TEXT_FONT);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(205, 205, 200, 25);
        passwordField.setBackground(ema.BACKGROUND_COLOR);
        passwordField.setFont(ema.TEXT_FONT);
        passwordField.setForeground(ema.FOREGROUND_COLOR);
        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
        
        passwordField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR));
        passwordField.addKeyListener(new KeyAdapter() {


            @Override //TODO Most likely remove (no bounds on password characters
            public void keyTyped(KeyEvent e) {
                if (getPasswordTextField().length() >= 20) {
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
        label.setForeground(ema.FOREGROUND_COLOR);
        label.setFont(ema.TEXT_FONT);

        companyField = new JTextField(20);
        companyField.setBounds(205, 245, 200, 25);
        companyField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR));
        companyField.setFont(ema.TEXT_FONT);
        companyField.setBackground(ema.BACKGROUND_COLOR);
        companyField.setForeground(ema.FOREGROUND_COLOR);
        companyField.setCaretColor(ema.FOREGROUND_COLOR);

        companyField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (companyField.getText().length() >= 20) {
                    e.consume();  //Next line includes upper and lowercase letters and 0-9 
                } else if (e.getKeyChar() < 32 || (e.getKeyChar() > 32 && e.getKeyChar() < 48) || (e.getKeyChar() < 65 && e.getKeyChar() > 57) || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
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
        loginButton.setForeground(ema.FOREGROUND_COLOR);
        loginButton.setBorder(buttonBorder);
        loginButton.setBackground(ema.BUTTON_COLOR);
        loginButton.setFont(ema.TEXT_FONT);
        
    
        loginButton.addActionListener(new ActionListener() {
    
            /*
             * Respond when the user clicks the Login button.
             */
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(loginButton)) {
                    String username = getUsernameTextField();
                    String password = getPasswordTextField();

                    controller.login(username, password);
                }
            }
        });
        this.add(loginButton);
        
        
    }
    
    private void initCreateCompanyButton() {
    	createCompanyButton = new JButton("Create Company");
    	createCompanyButton.setBounds(205, 360, 200, 35);
    	createCompanyButton.setForeground(ema.FOREGROUND_COLOR);
    	createCompanyButton.setBorder(buttonBorder);
    	createCompanyButton.setBackground(ema.BUTTON_COLOR);
    	createCompanyButton.setFont(ema.TEXT_FONT);
    	
    	createCompanyButton.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			String username = getUsernameTextField();
    			String password = getPasswordTextField();
    			String companyName = getCompanyNameTextField();
    			controller.createCompany(username, password, companyName);
    		}
    	});
        this.add(createCompanyButton);
    }
}