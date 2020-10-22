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
import javax.swing.border.Border;
import javax.swing.JPasswordField;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

public class EmployeeView extends JPanel {

	private JPanel profilePanel;
	private JLabel title;
    private JButton logoutButton;
    private JLabel fName;
    private JLabel lName;
    private JTextField fNameField;
    private JTextField lNameField;
    private JLabel username;
    private JTextField usernameField;
    private JLabel password;
    private JPasswordField passwordField;
    private JLabel salary;
    private JTextField salaryField;
    private Border buttonBorder;
    private Border textFieldBorder;
    
    private EMA ema;
	private Controller controller;

    public EmployeeView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
        textFieldBorder = BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR);
        this.controller = controller;
        this.initialize();
    }
    
    
    public void updateCard() {
    	this.setLayout(null);
        initLogoutButton();
        initTitle();
        initProfilePanel();
    }

    private void initialize() {
    	this.setLayout(null);
        initLogoutButton();
        initTitle();
        initProfilePanel();
        
    }
    
    public String getUsernameTextField() {
        return usernameField.getText();
    }
    
    public String getFNameTextField() {
    	return fNameField.getText();
    }
    
    public String getLNameTextField() {
    	return lNameField.getText();
    }
    
    public String getSalaryTextField() {
    	return salaryField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }
    
    private void initProfilePanel() {
    	
    	profilePanel = new JPanel();
    	profilePanel.setLayout(null);
    	profilePanel.setBounds(0, 80, 600, 800);
    	profilePanel.setBackground(ema.BACKGROUND_COLOR);
    	profilePanel.setBorder(BorderFactory.createLineBorder(ema.FOREGROUND_COLOR));
    	
    	initFName();
    	initLName();
    	initUsername();
    	initPassword();
    	initSalary();
    	
    	
    	this.add(profilePanel);
    }
    
    private void initFName() {
    	 fName = new JLabel("First Name:", SwingConstants.RIGHT);
    	 fName.setForeground(ema.FOREGROUND_COLOR);
    	 fName.setBounds(20, 80, 95, 35);
    	 fName.setLabelFor(fNameField);
    	 fName.setFont(ema.TEXT_FONT);
    	 
    	 fNameField = new JTextField(20);
    	 fNameField.setBounds(125, 85, 125, 25);
    	 fNameField.setFont(ema.TEXT_FONT);
    	 fNameField.setBorder(textFieldBorder);
    	 fNameField.setBackground(ema.BACKGROUND_COLOR);
    	 fNameField.setForeground(ema.FOREGROUND_COLOR);
    	 fNameField.setCaretColor(ema.FOREGROUND_COLOR);
    	 try{fNameField.setText(controller.getCurrentUser().getFName()); System.out.println(controller.getCurrentUser().getFName() + "!!!"); }
    	 catch(Exception e) { e.printStackTrace(); System.out.println(System.nanoTime());};
    	 
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
    	 
    	 profilePanel.add(fName);
    	 profilePanel.add(fNameField);

    }
    
    private void initLName() {
    	 
		lName = new JLabel("Last Name:", SwingConstants.RIGHT);
		lName.setForeground(ema.FOREGROUND_COLOR);
		lName.setBounds(20, 120, 95, 35);
		lName.setLabelFor(lNameField);
		lName.setFont(ema.TEXT_FONT);
		 
		lNameField = new JTextField(20);
		lNameField.setBounds(125, 125, 125, 25);
		lNameField.setFont(ema.TEXT_FONT);
		lNameField.setBorder(textFieldBorder);
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
	   	profilePanel.add(lName);
	   	profilePanel.add(lNameField);
    }
    
    private void initUsername() {
    	username = new JLabel("Username:", SwingConstants.RIGHT);
        username.setForeground(ema.FOREGROUND_COLOR);
        username.setBounds(20, 160, 95, 35);
        username.setLabelFor(usernameField);
        username.setFont(ema.TEXT_FONT);
        
        usernameField = new JTextField(20);
        usernameField.setBounds(125, 165, 125, 25);
        usernameField.setBackground(ema.BACKGROUND_COLOR);
        usernameField.setFont(ema.TEXT_FONT);
        usernameField.setForeground(ema.FOREGROUND_COLOR);
        usernameField.setCaretColor(ema.FOREGROUND_COLOR);
        
        usernameField.setBorder(textFieldBorder);
        
        try{ 
        	String a = controller.getCurrentUser().getUsername();
        	System.out.println(a + "!!!");
        	usernameField.setText(a);
    	}
    	catch(Exception e) { System.out.println("caught");};
        usernameField.setEditable(false);


        profilePanel.add(username);
        profilePanel.add(usernameField);
    }	
    
    private void initPassword() {
        password = new JLabel("Password:", SwingConstants.RIGHT);
        password.setBounds(20, 200, 95, 35);
        password.setForeground(ema.FOREGROUND_COLOR);
        password.setLabelFor(passwordField);
        password.setFont(ema.TEXT_FONT);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(125, 205, 125, 25);
        passwordField.setBackground(ema.BACKGROUND_COLOR);
        passwordField.setFont(ema.TEXT_FONT);
        passwordField.setForeground(ema.FOREGROUND_COLOR);
        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
        
        passwordField.setBorder(textFieldBorder);
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

        profilePanel.add(password);
        profilePanel.add(passwordField);
    }
    
    private void initSalary() {
    	salary = new JLabel("Salary:", SwingConstants.RIGHT);
    	salary.setForeground(ema.FOREGROUND_COLOR);
    	salary.setBounds(20, 240, 95, 35);
    	salary.setLabelFor(salaryField);
    	salary.setFont(ema.TEXT_FONT);
        
    	salaryField = new JTextField(20);
    	salaryField.setBounds(125, 245, 125, 25);
    	salaryField.setBackground(ema.BACKGROUND_COLOR);
    	salaryField.setFont(ema.TEXT_FONT);
    	salaryField.setForeground(ema.FOREGROUND_COLOR);
    	salaryField.setCaretColor(ema.FOREGROUND_COLOR);
        
    	salaryField.setBorder(textFieldBorder);
        
        try{ salaryField.setText("$" + Double.toString(controller.getCurrentUser().getSalary())); }
    	catch(Exception e) {};
    	salaryField.setEditable(false);


        profilePanel.add(salary);
        profilePanel.add(salaryField);
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
    	logoutButton.setBorder(buttonBorder);
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
//                System.out.println(source.toString());
//                System.out.println(logoutButton.toString());
                if (source.toString().equals(logoutButton.toString())) {
                	controller.logout();
                }
            }
        });
        this.add(logoutButton);
    }
    

}
