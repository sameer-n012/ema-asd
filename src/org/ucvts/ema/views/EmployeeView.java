package org.ucvts.ema.views;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;
import org.ucvts.ema.model.Shift;

public class EmployeeView extends JPanel {

	private JPanel profilePanel;
	private JLabel title;
    private JButton logoutButton;
    private JButton updateButton;
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
    private JTextArea notesField;
    private JLabel errorMssg;
    private Border buttonBorder;
    private Border textFieldBorder;
    private Border textAreaBorder;
    private JLabel sun;
    private JLabel mon;
    private JLabel tue;
    private JLabel wed;
    private JLabel thu;
    private JLabel fri;
    private JLabel sat;
    private JComboBox<String> suncb;
    private JComboBox<String> moncb;
    private JComboBox<String> tuecb;
    private JComboBox<String> wedcb;
    private JComboBox<String> thucb;
    private JComboBox<String> fricb;
    private JComboBox<String> satcb;
    
    private EMA ema;
	private Controller controller;

    public EmployeeView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
        textFieldBorder = BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR);
        textAreaBorder = BorderFactory.createMatteBorder(2,2,2,2, ema.FOREGROUND_COLOR);
        this.controller = controller;
        this.initialize();
    }
    
    
    public void updateCard() {
    	this.removeAll();
    	initialize();
    }

    private void initialize() {
    	this.setLayout(null);
        initLogoutButton();
        initUpdateButton();
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

    public String getPasswordTextField() {
        return new String(passwordField.getPassword());
    }
    
    public String getNotesTextArea() {
    	return notesField.getText();
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
    	initShifts();
    	initNotesField();
    	initErrorMssg();
    	
    	
    	this.add(profilePanel);
    }
    
    private void initFName() {
    	 fName = new JLabel("First Name:", SwingConstants.RIGHT);
    	 fName.setForeground(ema.FOREGROUND_COLOR);
    	 fName.setBounds(20, 20, 95, 35);
    	 fName.setLabelFor(fNameField);
    	 fName.setFont(ema.TEXT_FONT);
    	 
    	 fNameField = new JTextField(20);
    	 fNameField.setBounds(125, 25, 125, 25);
    	 fNameField.setFont(ema.TEXT_FONT);
    	 fNameField.setBorder(textFieldBorder);
    	 fNameField.setBackground(ema.BACKGROUND_COLOR);
    	 fNameField.setForeground(ema.FOREGROUND_COLOR);
    	 fNameField.setCaretColor(ema.FOREGROUND_COLOR);
    	 if(controller.getCurrentUser() != null) {
    		 fNameField.setText(controller.getCurrentUser().getFName()); 
		 }
    	 
    	 fNameField.addKeyListener(new KeyAdapter() {

             @Override
             public void keyTyped(KeyEvent e) {
                 if (getFNameTextField().length() >= 20) {
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
		lName.setBounds(20, 80, 95, 35);
		lName.setLabelFor(lNameField);
		lName.setFont(ema.TEXT_FONT);
		 
		lNameField = new JTextField(20);
		lNameField.setBounds(125, 85, 125, 25);
		lNameField.setFont(ema.TEXT_FONT);
		lNameField.setBorder(textFieldBorder);
		lNameField.setBackground(ema.BACKGROUND_COLOR);
		lNameField.setForeground(ema.FOREGROUND_COLOR);
		lNameField.setCaretColor(ema.FOREGROUND_COLOR);
		if(controller.getCurrentUser() != null) {
			lNameField.setText(controller.getCurrentUser().getLName()); 
		}
		 
		lNameField.addKeyListener(new KeyAdapter() {
		
		        @Override
		        public void keyTyped(KeyEvent e) {
		            if (getLNameTextField().length() >= 20) {
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
        username.setBounds(20, 140, 95, 35);
        username.setLabelFor(usernameField);
        username.setFont(ema.TEXT_FONT);
        
        usernameField = new JTextField(20);
        usernameField.setBounds(125, 145, 125, 25);
        usernameField.setBackground(ema.BACKGROUND_COLOR);
        usernameField.setFont(ema.TEXT_FONT);
        usernameField.setForeground(ema.FOREGROUND_COLOR);
        usernameField.setCaretColor(ema.FOREGROUND_COLOR);
        
        usernameField.setBorder(textFieldBorder);
        
        if(controller.getCurrentUser() != null) { 
        	usernameField.setText(controller.getCurrentUser().getUsername()); 
    	}
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
        passwordField.setText("******");
        passwordField.setBounds(125, 205, 125, 25);
        passwordField.setBackground(ema.BACKGROUND_COLOR);
        passwordField.setFont(ema.TEXT_FONT);
        passwordField.setForeground(ema.FOREGROUND_COLOR);
        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
        
        passwordField.setBorder(textFieldBorder);
        passwordField.addKeyListener(new KeyAdapter() {

        //TODO I cannot for the life of me get this to function, I've tried doing the same thing as LoginView, I've tried moving outside of a function, I've tried different methods to convert the char array, it will not work.  I don't know why.
            @Override //TODO Most likely remove (no bounds on password characters
            public void keyTyped(KeyEvent e) {
                if (getPasswordTextField().length() >= 20) {
                    e.consume(); //Next line allows upper and lowercase letters, 0-9, and special characters
                }else if (e.getKeyChar() < 33 || (e.getKeyChar() < 64 && e.getKeyChar() > 57) || (e.getKeyChar() < 97 && e.getKeyChar() > 90) || e.getKeyChar() > 122) {
                    e.consume();
                }
            }
        });

        profilePanel.add(password);
        profilePanel.add(passwordField);
    }
    
    private void initSalary() {
    	salary = new JLabel("Salary($):", SwingConstants.RIGHT);
    	salary.setForeground(ema.FOREGROUND_COLOR);
    	salary.setBounds(20, 260, 95, 35);
    	salary.setLabelFor(salaryField);
    	salary.setFont(ema.TEXT_FONT);
        
    	salaryField = new JTextField(20);
    	salaryField.setBounds(125, 265, 125, 25);
    	salaryField.setBackground(ema.BACKGROUND_COLOR);
    	salaryField.setFont(ema.TEXT_FONT);
    	salaryField.setForeground(ema.FOREGROUND_COLOR);
    	salaryField.setCaretColor(ema.FOREGROUND_COLOR);
        
    	salaryField.setBorder(textFieldBorder);
        
        if(controller.getCurrentUser() != null) { 
        	salaryField.setText(Double.toString(controller.getCurrentUser().getSalary()));
        }
    	salaryField.setEditable(false);


        profilePanel.add(salary);
        profilePanel.add(salaryField);
    }
    
	
    private void initTitle() {
    	String s = null;
    	if(controller.getCurrentCompany() != null && controller.getCurrentUser() != null) { 
    		s = controller.getCurrentCompany().getName() + ": " + 
    			controller.getCurrentUser().getFName() + " " + controller.getCurrentUser().getLName(); 
		}
    	title = new JLabel(s, SwingConstants.LEFT);
    	title.setBounds(20, 20, 450, 35);
    	title.setFont(ema.TITLE_FONT);
    	title.setForeground(ema.FOREGROUND_COLOR);
    	
    	
        this.add(title);
    }
    
	private void initShifts() {
    	
    	String[] shifts = { "None", "Morning", "Afternoon", "Evening", "Night", };

    	
    	if(controller.getCurrentUser() != null) {
			UIManager.put("ComboBox.disabledBackground", ema.BACKGROUND_COLOR);
			UIManager.put("ComboBox.disabledForeground", ema.FOREGROUND_COLOR);
    		
	    	sun = new JLabel("Sunday:", SwingConstants.RIGHT);
	    	sun.setForeground(ema.FOREGROUND_COLOR);
	    	sun.setBounds(300, 20, 95, 35);
	    	sun.setLabelFor(fNameField);
	    	sun.setFont(ema.TEXT_FONT);
			suncb = new JComboBox<String>(shifts);
			suncb.setBounds(405, 25, 125, 25);
			suncb.setFont(ema.TEXT_FONT);
			suncb.setBackground(ema.BACKGROUND_COLOR);
			suncb.setForeground(ema.FOREGROUND_COLOR);
			suncb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[0]));
			suncb.setEnabled(false);
			profilePanel.add(sun);
			profilePanel.add(suncb);
			
			
			mon = new JLabel("Monday:", SwingConstants.RIGHT);
			mon.setForeground(ema.FOREGROUND_COLOR);
			mon.setBounds(300, 80, 95, 35);
			mon.setLabelFor(fNameField);
			mon.setFont(ema.TEXT_FONT);
			moncb = new JComboBox<String>(shifts);
			moncb.setBounds(405, 85, 125, 25);
			moncb.setFont(ema.TEXT_FONT);
			moncb.setBackground(ema.BACKGROUND_COLOR);
			moncb.setForeground(ema.FOREGROUND_COLOR);
			moncb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[1]));
			moncb.setEnabled(false);
			profilePanel.add(mon);
			profilePanel.add(moncb);
			
			
			tue = new JLabel("Tuesday:", SwingConstants.RIGHT);
			tue.setForeground(ema.FOREGROUND_COLOR);
			tue.setBounds(300, 140, 95, 35);
			tue.setLabelFor(fNameField);
			tue.setFont(ema.TEXT_FONT);
			tuecb = new JComboBox<String>(shifts);
			tuecb.setBounds(405, 145, 125, 25);
			tuecb.setFont(ema.TEXT_FONT);
			tuecb.setBackground(ema.BACKGROUND_COLOR);
			tuecb.setForeground(ema.FOREGROUND_COLOR);
			tuecb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[2]));
			tuecb.setEnabled(false);
			profilePanel.add(tue);
			profilePanel.add(tuecb);
			
			
			wed = new JLabel("Wednesday:", SwingConstants.RIGHT);
	    	wed.setForeground(ema.FOREGROUND_COLOR);
	    	wed.setBounds(300, 200, 95, 35);
	    	wed.setLabelFor(fNameField);
	    	wed.setFont(ema.TEXT_FONT); 
			wedcb = new JComboBox<String>(shifts);
			wedcb.setBounds(405, 205, 125, 25);
			wedcb.setFont(ema.TEXT_FONT);
			wedcb.setBackground(ema.BACKGROUND_COLOR);
			wedcb.setForeground(ema.FOREGROUND_COLOR);
			wedcb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[3]));
			wedcb.setEnabled(false);
			profilePanel.add(wed);
			profilePanel.add(wedcb);
			
			
			thu = new JLabel("Thursday:", SwingConstants.RIGHT);
	    	thu.setForeground(ema.FOREGROUND_COLOR);
	    	thu.setBounds(300, 260, 95, 35);
	    	thu.setLabelFor(fNameField);
	    	thu.setFont(ema.TEXT_FONT);
			thucb = new JComboBox<String>(shifts);
			thucb.setBounds(405, 265, 125, 25);
			thucb.setFont(ema.TEXT_FONT);
			thucb.setBackground(ema.BACKGROUND_COLOR);
			thucb.setForeground(ema.FOREGROUND_COLOR);
			thucb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[4]));
			thucb.setEnabled(false);
			profilePanel.add(thu);
			profilePanel.add(thucb);
			
			
			fri = new JLabel("Friday:", SwingConstants.RIGHT);
	    	fri.setForeground(ema.FOREGROUND_COLOR);
	    	fri.setBounds(300, 320, 95, 35);
	    	fri.setLabelFor(fNameField);
	    	fri.setFont(ema.TEXT_FONT);
			fricb = new JComboBox<String>(shifts);
			fricb.setBounds(405, 325, 125, 25);
			fricb.setFont(ema.TEXT_FONT);
			fricb.setBackground(ema.BACKGROUND_COLOR);
			fricb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[5]));
			fricb.setEnabled(false);
			profilePanel.add(fri);
			profilePanel.add(fricb);
			
			
			sat = new JLabel("Saturday:", SwingConstants.RIGHT);
	    	sat.setForeground(ema.FOREGROUND_COLOR);
	    	sat.setBounds(300, 380, 95, 35);
	    	sat.setLabelFor(fNameField);
	    	sat.setFont(ema.TEXT_FONT);
			satcb = new JComboBox<String>(shifts);
			satcb.setBounds(405, 385, 125, 25);
			satcb.setFont(ema.TEXT_FONT);
			satcb.setBackground(ema.BACKGROUND_COLOR);
			satcb.setForeground(ema.FOREGROUND_COLOR);
			satcb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[6]));
			satcb.setEnabled(false);
			profilePanel.add(sat);
			profilePanel.add(satcb);
    	}
    	
    }
	
	private void initNotesField() {
		notesField = new JTextArea();
		if(controller.getCurrentUser() != null) {
			notesField.setText(controller.getCurrentUser().getNotes());
		}
		
		notesField.setForeground(ema.FOREGROUND_COLOR);
		notesField.setBackground(ema.BACKGROUND_COLOR);
		notesField.setMargin(new Insets(20,20,20,20));
		notesField.setBounds(20, 320, 230, 120);
		notesField.setFont(ema.TEXT_FONT);
		notesField.setBorder(textAreaBorder);
		notesField.setCaretColor(ema.FOREGROUND_COLOR);
		notesField.setEditable(false);
		
		profilePanel.add(notesField);
	}
	
	private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        errorMssg.setBounds(20, 440, 600, 35);
        errorMssg.setFont(ema.ERROR_FONT);
        errorMssg.setForeground(ema.ERROR_COLOR);
        errorMssg.setVisible(false);

        profilePanel.add(errorMssg);
    }
	
	private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(460, 10, 100, 25);
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
    
                if (source.equals(logoutButton)) {
                    controller.logout();
                }
            }
        });
        
        this.add(logoutButton);
    }
    
    private void initUpdateButton() {
    	updateButton = new JButton("Update");
    	updateButton.setBounds(460, 40, 100, 25);
    	updateButton.setForeground(ema.FOREGROUND_COLOR);
    	updateButton.setBorder(buttonBorder);
    	updateButton.setBackground(ema.BUTTON_COLOR);
    	updateButton.setFont(ema.TEXT_FONT);
    
    	updateButton.addActionListener(new ActionListener() {
    
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(updateButton)) {
                	String fname = getFNameTextField();
                	String lname = getLNameTextField();
                	String password = getPasswordTextField();
                	String notes = getNotesTextArea();
                	
                    controller.updateProfileInformation(fname, lname, password, notes);
                }
            }
        });
        
        this.add(updateButton);
    }
    
    private String shiftToString(Shift s) {
    	switch(s) {
    		case MORNING:
    			return "Morning";
    		case AFTERNOON:
    			return "Afternoon";
    		case EVENING:
    			return "Evening";
    		case NIGHT:
    			return "Night";
    		case NONE:
    			return "None";
    		default:
    			return null;
    		
    	}
    }
    

}
