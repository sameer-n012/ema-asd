package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;
import org.ucvts.ema.model.Shift;
import org.ucvts.ema.placeholders.PlaceholderJPasswordField;
import org.ucvts.ema.placeholders.PlaceholderJTextArea;
import org.ucvts.ema.placeholders.PlaceholderJTextField;

@SuppressWarnings("serial")
public class EmployeeView extends JPanel {

	private JPanel profilePanel;
	private JLabel title;
    private JButton logoutButton;
    private JButton updateButton;
    private JButton addLogButton;
    private JLabel fName;
    private JLabel lName;
    private PlaceholderJTextField fNameField;
    private PlaceholderJTextField lNameField;
    private JLabel username;
    private PlaceholderJTextField usernameField;
    private JLabel password;
    private PlaceholderJPasswordField passwordField;
    private JLabel salary;
    private PlaceholderJTextField salaryField;
    private PlaceholderJTextArea notesField;
    private JLabel errorMssg;
    private Border buttonBorder;
    private Border textFieldBorder;
    private Border textAreaBorder;
    private Border panelBorder;
    private ArrayList<JLabel> shiftlabels;
    private ArrayList<JComboBox<String>> shiftboxes;
    
    private EMA ema;
	private Controller controller;

    public EmployeeView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
        textFieldBorder = BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR);
        textAreaBorder = BorderFactory.createMatteBorder(2,2,2,2, ema.FOREGROUND_COLOR);
        panelBorder = BorderFactory.createLineBorder(ema.FOREGROUND_COLOR);
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
    	
    	style(profilePanel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, null, 
    			0, 80, 600, 800, panelBorder);
    	
    	initFName();
    	initLName();
    	initUsername();
    	initPassword();
    	initSalary();
    	initShifts();
    	initNotesField();
    	initErrorMssg();
    	initAddLogButton();
    	
    	
    	this.add(profilePanel);
    }
    
    private void initFName() {
    	 fName = new JLabel("First Name:", SwingConstants.RIGHT);
    	 style(fName, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
    			 20, 20, 95, 35, null);
    	 
    	 fNameField = new PlaceholderJTextField(20);
    	 fNameField.setPlaceholder("First Name");
    	 fNameField.setCaretColor(ema.FOREGROUND_COLOR);
    	 style(fNameField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
    			 125, 25, 125, 25, textFieldBorder);
    	 
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
		style(lName, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 80, 95, 35, null);
		 
		lNameField = new PlaceholderJTextField(20);
		lNameField.setPlaceholder("Last Name");
		lNameField.setCaretColor(ema.FOREGROUND_COLOR);
		style(lNameField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
   			 125, 85, 125, 25, textFieldBorder);
		
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
    	style(username, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 140, 95, 35, null);
        
        usernameField = new PlaceholderJTextField(20);
        usernameField.setCaretColor(ema.FOREGROUND_COLOR);
        style(usernameField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
      			 125, 145, 125, 25, textFieldBorder);
        
        if(controller.getCurrentUser() != null) { 
        	usernameField.setPlaceholder(controller.getCurrentUser().getUsername()); 
    	}
        usernameField.setEditable(false);


        profilePanel.add(username);
        profilePanel.add(usernameField);
        
    }	
    
    private void initPassword() {
        password = new JLabel("Password:", SwingConstants.RIGHT);
        style(password, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 200, 95, 35, null);

        passwordField = new PlaceholderJPasswordField(20);
        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
        passwordField.setPlaceholder("******");
        style(passwordField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
     			 125, 205, 125, 25, textFieldBorder);
        
        passwordField.addKeyListener(new KeyAdapter() {

        	@Override
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
    	salary = new JLabel("Salary ($/h):", SwingConstants.RIGHT);
    	style(salary, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 260, 95, 35, null);
        
    	salaryField = new PlaceholderJTextField(20);
    	salaryField.setCaretColor(ema.FOREGROUND_COLOR);
    	style(salaryField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
    			 125, 265, 125, 25, textFieldBorder);
                
        if(controller.getCurrentUser() != null) { 
        	salaryField.setPlaceholder(Double.toString(controller.getCurrentUser().getSalary()));
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
    	style(title, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TITLE_FONT, 
				20, 20, 450, 35, null);
    	
        this.add(title);
    }
    
	private void initShifts() {
    	
    	String[] shifts = { "None", "Morning", "Afternoon", "Evening", "Night" };
    	String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    	
    	if(controller.getCurrentUser() != null) {
			UIManager.put("ComboBox.disabledBackground", ema.BACKGROUND_COLOR);
			UIManager.put("ComboBox.disabledForeground", ema.FOREGROUND_COLOR);
			
			shiftlabels = new ArrayList<JLabel>();
	    	shiftboxes = new ArrayList<JComboBox<String>>();
	    	
	    	
	    	for(int i = 0; i < 7; i++) {
	    		
	    		JLabel l = new JLabel(daysOfWeek[i] + ":", SwingConstants.RIGHT);
	    		style(l, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
	    				300, 20+60*i, 95, 35, null);

		    	JComboBox<String> cb = new JComboBox<String>(shifts);
		    	cb.setSelectedItem(shiftToString(controller.getCurrentUser().getShifts()[i]));
				cb.setEnabled(false);
		    	style(cb, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
	    				405, 25+60*i, 125, 25, null);

				
				
				profilePanel.add(l);
				profilePanel.add(cb);
				
				shiftlabels.add(l);
				shiftboxes.add(cb);
	    		
	    	}
    	}
    	
    }
	
	private void initAddLogButton() {
		addLogButton = new JButton("Add Log");
		style(addLogButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				405, 440, 100, 25, buttonBorder);
		
		addLogButton.addActionListener(new ActionListener() {
		    
		    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(addLogButton)) {
                    controller.gotoAddLog(null);
                }
            }
        });
		
		profilePanel.add(addLogButton);
		
	}
	
	private void initNotesField() {
		notesField = new PlaceholderJTextArea();
		notesField.setPlaceholder("There are no notes");
		if(controller.getCurrentUser() != null) {
			notesField.setText(controller.getCurrentUser().getNotes());
		}
		
		style(notesField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 320, 230, 120, textAreaBorder);
		
		notesField.setMargin(new Insets(20,20,20,20));
		notesField.setCaretColor(ema.FOREGROUND_COLOR);
		notesField.setEditable(false);
		notesField.setLineWrap(true);
		notesField.setWrapStyleWord(true);
		
		profilePanel.add(notesField);
	}
	
	private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        style(errorMssg, ema.ERROR_COLOR, ema.BACKGROUND_COLOR, ema.ERROR_FONT, 
				20, 440, 600, 35, null);

        errorMssg.setVisible(false);

        profilePanel.add(errorMssg);
    }
	
	private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	style(logoutButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 10, 100, 25, buttonBorder);
    
    	logoutButton.addActionListener(new ActionListener() {
    
    
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
    	style(updateButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 40, 100, 25, buttonBorder);
    
    	updateButton.addActionListener(new ActionListener() {
    
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(updateButton)) {
                	String fname = getFNameTextField();
                	String lname = getLNameTextField();
                	String password = getPasswordTextField();
                	String notes = getNotesTextArea();
                	Shift[] emptyArr = new Shift[0];
                	
                    controller.updateProfileInformation(fname, lname, password, notes, emptyArr, -1);
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
    
    private void style(JComponent obj, Color foreground, Color background, Font font, int x, int y, int w, int h, Border border) {
    	try {
    		if(foreground != null) {
    			obj.setForeground(foreground);
    		}
    		if(background != null) {
    			obj.setBackground(background);
    		}
    		if(font != null) {
    			obj.setFont(font);
    		}
    		if(x >= 0 && y >= 0 && w >= 0 && h >= 0) {
    			obj.setBounds(x, y, w, h);
    		}
    		if(border != null) {
    			obj.setBorder(border);
    		}
    	}
    	catch(Exception e) { e.printStackTrace(); }
    }
    

}
