package org.ucvts.ema.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

public class EmployeeView extends JPanel {

	private JLabel title;
    private JButton logoutButton;
    private JLabel fName;
    private JLabel lName;
    private JTextField fNameField;
    private JTextField lNameField;
    private JLabel username;
    private JLabel password;
    private JPasswordField passwordField;
    
    private EMA ema;
	private Controller controller;

    public EmployeeView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        this.controller = controller;
        this.initialize();
    }
    
    
    public void updateCard() {
    	this.setLayout(null);
        initLogoutButton();
        initTitle();
        initEmployeeName();
        initUsername();
        initPasswordField();
    }

    private void initialize() {
    	this.setLayout(null);
        initLogoutButton();
        initTitle();
        initEmployeeName();
        initPasswordField();
    }
    
    private void initEmployeeName() {
    	 fName = new JLabel("First Name:", SwingConstants.RIGHT);
    	 fName.setForeground(ema.FOREGROUND_COLOR);
    	 fName.setBounds(10, 60, 95, 35);
    	 fName.setLabelFor(fNameField);
    	 fName.setFont(ema.TEXT_FONT);
    	 
    	 fNameField = new JTextField(20);
    	 fNameField.setBounds(110, 65, 125, 25);
    	 fNameField.setFont(ema.TEXT_FONT);
    	 fNameField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR));
    	 fNameField.setBackground(ema.BACKGROUND_COLOR);
    	 fNameField.setForeground(ema.FOREGROUND_COLOR);
    	 fNameField.setCaretColor(ema.FOREGROUND_COLOR);
    	 try{fNameField.setText(controller.getCurrentUser().getFName());}
    	 catch(Exception e) {};
    	 
    	 fNameField.addKeyListener(new KeyAdapter() {

             @Override
             public void keyTyped(KeyEvent e) {
                 if (controller.getCurrentUser().getFName().length() >= 20) {
                     e.consume();  //Next line includes upper and lowercase letters and 0-9 
                 } else if (e.getKeyChar() < 65 || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                     e.consume(); 
                 }
             }
         });
    	 
    	 lName = new JLabel("Last Name:", SwingConstants.RIGHT);
    	 lName.setForeground(ema.FOREGROUND_COLOR);
    	 lName.setBounds(10, 90, 95, 35);
    	 lName.setLabelFor(lNameField);
    	 lName.setFont(ema.TEXT_FONT);
    	 
    	 lNameField = new JTextField(20);
    	 lNameField.setBounds(110, 95, 125, 25);
    	 lNameField.setFont(ema.TEXT_FONT);
    	 lNameField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR));
    	 lNameField.setBackground(ema.BACKGROUND_COLOR);
    	 lNameField.setForeground(ema.FOREGROUND_COLOR);
    	 lNameField.setCaretColor(ema.FOREGROUND_COLOR);
    	 try{lNameField.setText(controller.getCurrentUser().getLName()); }
    	 catch(Exception e) {};
    	 
    	 lNameField.addKeyListener(new KeyAdapter() {

             @Override
             public void keyTyped(KeyEvent e) {
                 if (controller.getCurrentUser().getLName().length() >= 20) {
                     e.consume();  //Next line includes upper and lowercase letters and 0-9 
                 } else if (e.getKeyChar() < 65 || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                     e.consume(); 
                 }
             }
         });
    	 
    	 this.add(fName);
    	 this.add(fNameField);
    	 this.add(lName);
    	 this.add(lNameField);
    }
    
    private void initUsername() {
    	username  = new JLabel("", SwingConstants.RIGHT);
        username.setForeground(ema.FOREGROUND_COLOR);
        username.setBounds(20, 120, 95, 35);
        username.setLabelFor(null);
        username.setFont(ema.TEXT_FONT);
        try{username.setText("Username: " + controller.getCurrentUser().getUsername());}
    	catch(Exception e) {};

        this.add(username);
    }	
    
    private void initPasswordField() {
        password = new JLabel("Password:", SwingConstants.RIGHT);
        password.setBounds(10, 150, 95, 35);
        password.setForeground(ema.FOREGROUND_COLOR);
        password.setLabelFor(passwordField);
        password.setFont(ema.TEXT_FONT);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(110, 155, 125, 25);
        passwordField.setBackground(ema.BACKGROUND_COLOR);
        passwordField.setFont(ema.TEXT_FONT);
        passwordField.setForeground(ema.FOREGROUND_COLOR);
        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
        
        passwordField.setBorder(BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR));
        passwordField.addKeyListener(new KeyAdapter() {

        //TODO I cannot for the life of me get this to function, I've tried doing the same thing as LoginView, I've tried moving outside of a function, I've tried different methods to convert the char array, it will not work.  I don't know why.
        String pass = String.valueOf(passwordField.getPassword());
            @Override //TODO Most likely remove (no bounds on password characters
            public void keyTyped(KeyEvent e) {
            	System.out.print(pass);
                if (pass.length() >= 20) {
                    e.consume(); //Next line allows upper and lowercase letters, 0-9, and special characters
                    System.out.print("a");
                }else if (e.getKeyChar() < 33 || (e.getKeyChar() < 64 && e.getKeyChar() > 57) || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                    e.consume();
                }
            }
        });

        this.add(password);
        this.add(passwordField);
    }
    
	
    private void initTitle() {
    	String s = null;
    	try { s = controller.getCurrentCompany().getName() + ": " + controller.getCurrentUser().getFName() + " " + controller.getCurrentUser().getLName(); }
    	catch(Exception e) {};
    	title = new JLabel(s, SwingConstants.LEFT);
    	title.setBounds(20, 20, 450, 35);
    	title.setFont(ema.TITLE_FONT);
    	title.setForeground(ema.FOREGROUND_COLOR);
    	
    	
        this.add(title);
    }
    
    
    private void initLogoutButton() { //TODO does not work. I don't know why
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(460, 25, 100, 25);
    	logoutButton.setForeground(ema.FOREGROUND_COLOR);
    	logoutButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, ema.FOREGROUND_COLOR));
    	logoutButton.setBackground(ema.BUTTON_COLOR);
    	logoutButton.setFont(ema.TEXT_FONT);
    
    	logoutButton.addActionListener(new ActionListener() {
    
            /*
             * Respond when the user clicks the Login button.
             */
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                //Would not work normally, works like this
                if (source.toString().equals(logoutButton.toString())) {
                	controller.logout();
                }
            }
        });
        this.add(logoutButton);
    }
    

}
