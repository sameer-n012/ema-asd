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
import javax.swing.JCheckBox;
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
import org.ucvts.ema.model.UserGroup;
import org.ucvts.ema.placeholders.PlaceholderJPasswordField;
import org.ucvts.ema.placeholders.PlaceholderJTextArea;
import org.ucvts.ema.placeholders.PlaceholderJTextField;

@SuppressWarnings("serial")
public class ModifyView extends JPanel {

	private JPanel profilePanel;
	private JLabel title;
    private JButton cancelButton;
    private JButton updateButton;
    private JLabel fName;
    private JLabel lName;
    private PlaceholderJTextField fNameField;
    private PlaceholderJTextField lNameField;
    private JLabel username;
    private PlaceholderJTextField usernameField;
    private JLabel password;
    private PlaceholderJPasswordField passwordField;
    private JCheckBox passwordReset;
    private JLabel passwordResetLabel;
    private JLabel salary;
    private PlaceholderJTextField salaryField;
    private PlaceholderJTextArea notesField;
    private JLabel errorMssg;
    private Border buttonBorder;
    private Border textFieldBorder;
    private Border textAreaBorder;
    private ArrayList<JLabel> shiftlabels;
    private ArrayList<JComboBox<String>> shiftboxes;
    
    private EMA ema;
	private Controller controller;

    public ModifyView(Controller controller) {
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
        initCancelButton();
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
    
    public Double getSalaryTextField() {
    	double a;
    	try{ a = Double.parseDouble(salaryField.getText()); }
    	catch(Exception e) {a = 0;}
    	return a;
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
    	 style(fName, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
    			 20, 20, 95, 35, null);
    	 
    	 fNameField = new PlaceholderJTextField(20);
    	 fNameField.setPlaceholder("First Name");
    	 fNameField.setCaretColor(ema.FOREGROUND_COLOR);
    	 style(fNameField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
    			 125, 25, 125, 25, textFieldBorder);
    	 
    	 if(controller.getModifiedUser() != null) {
    		 fNameField.setText(controller.getModifiedUser().getFName()); 
		 }
    	 
    	 if(controller.getModifiedUser() != controller.getCurrentUser() && controller.getModifiedUser() != null) {
    		 fNameField.setEditable(false);
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
		
		if(controller.getModifiedUser() != null) {
			lNameField.setText(controller.getModifiedUser().getLName()); 
		}
		
		if(controller.getModifiedUser() != controller.getCurrentUser() && controller.getModifiedUser() != null) {
			lNameField.setEditable(false);
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
        usernameField.setPlaceholder("Username");
        usernameField.setCaretColor(ema.FOREGROUND_COLOR);
        style(usernameField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
     			 125, 145, 125, 25, textFieldBorder);
        
        if(controller.getModifiedUser() != null) { 
        	usernameField.setText(controller.getModifiedUser().getUsername()); 
        	usernameField.setEditable(false);
    	}
        else { 
        	usernameField.setEditable(true);
    	}
        


        profilePanel.add(username);
        profilePanel.add(usernameField);
        
    }	
    
    private void initPassword() {
        password = new JLabel("Password:", SwingConstants.RIGHT);
        style(password, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 200, 95, 35, null);

        if(controller.getCurrentUser() == controller.getModifiedUser()) {
        	password.setLabelFor(passwordField);
        	
	        passwordField = new PlaceholderJPasswordField(20);
	        passwordField.setPlaceholder("******");
	        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
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
	        profilePanel.add(passwordField);
        }
        else {
        	
        	passwordReset = new JCheckBox();
        	style(passwordReset, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
        			 125, 205, 15, 25, buttonBorder);
        	
        	passwordResetLabel = new JLabel("(Reset)", SwingConstants.LEFT);
        	style(passwordResetLabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
       			 140, 200, 110, 35, buttonBorder);
        	
        	profilePanel.add(passwordReset);
        	profilePanel.add(passwordResetLabel);
        }

        profilePanel.add(password);
        
    }
    
    private void initSalary() {
    	salary = new JLabel("Salary ($/h):", SwingConstants.RIGHT);
    	style(salary, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 260, 95, 35, null);
        
    	salaryField = new PlaceholderJTextField(20);
    	salaryField.setPlaceholder("0.00");
    	salaryField.setCaretColor(ema.FOREGROUND_COLOR);
    	style(salaryField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
   			 125, 265, 125, 25, textFieldBorder);
        
        if(controller.getModifiedUser() != null) { 
        	salaryField.setText(Double.toString(controller.getModifiedUser().getSalary()));
        }

        salaryField.addKeyListener(new KeyAdapter() {

        		@Override
                public void keyTyped(KeyEvent e) {
                  //Next line allows 0-9, and period
                    if (e.getKeyChar() < 46 || (e.getKeyChar() < 48 && e.getKeyChar() > 46) || e.getKeyChar() > 57) {
                        e.consume();
                    }
                }
            });

        profilePanel.add(salary);
        profilePanel.add(salaryField);
    }
    
	
    private void initTitle() {
    	String s = null;
    	if(controller.getCurrentCompany() != null && controller.getModifiedUser() != null) { 
    		s = controller.getCurrentCompany().getName() + ": " + 
    			controller.getModifiedUser().getFName() + " " + controller.getModifiedUser().getLName(); 
		}
    	else if(controller.getCurrentCompany() != null) {
    		s = controller.getCurrentCompany().getName() + ": Add User";
    	}
    	title = new JLabel(s, SwingConstants.LEFT);
    	style(title, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TITLE_FONT, 
				20, 20, 450, 35, null);
    	
        this.add(title);
    }
    
	private void initShifts() { 
		
		UIManager.put("ComboBox.background", ema.BACKGROUND_COLOR);
		UIManager.put("ComboBox.foreground", ema.FOREGROUND_COLOR);
    	
		String[] shifts = { "None", "Morning", "Afternoon", "Evening", "Night" };
		String[] daysOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };

    	
    	if(controller.getCurrentUser() != null) {
    	
    	
	    	shiftlabels = new ArrayList<JLabel>();
	    	shiftboxes = new ArrayList<JComboBox<String>>();
	    	
	    	
	    	for(int i = 0; i < 7; i++) {
	    		
	    		JLabel l = new JLabel(daysOfWeek[i] + ":", SwingConstants.RIGHT);
	    		style(l, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
	    				300, 20+60*i, 95, 35, null);
	    		
		    	JComboBox<String> cb = new JComboBox<String>(shifts);
		    	try {
			    	//System.out.println(shiftToString(controller.getModifiedUser().getShifts()[i]));
		    		cb.setSelectedItem(shiftToString(controller.getModifiedUser().getShifts()[i]));
		    	} catch(Exception e) {
		    		//System.out.println("N/A");
		    		cb.setSelectedItem("None");
		    	}
				style(cb, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
	    				405, 25+60*i, 125, 25, null);
				
				shiftlabels.add(l);
				shiftboxes.add(cb);
				
				profilePanel.add(l);
				profilePanel.add(cb);
	    		
	    	}
    	}
		
    	
    }
	
	private void initNotesField() {
		notesField = new PlaceholderJTextArea();
		notesField.setPlaceholder("Add notes here");
		if(controller.getModifiedUser() != null) {
			notesField.setText(controller.getModifiedUser().getNotes());
		}
		
		notesField.setMargin(new Insets(20,20,20,20));
		notesField.setCaretColor(ema.FOREGROUND_COLOR);
		notesField.setLineWrap(true);
		notesField.setWrapStyleWord(true);
		style(notesField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 320, 230, 120, textAreaBorder);
		
		profilePanel.add(notesField);
	}
	
	private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        style(errorMssg, ema.ERROR_COLOR, ema.BACKGROUND_COLOR, ema.ERROR_FONT, 
				20, 440, 600, 35, null);
        
        errorMssg.setVisible(false);

        profilePanel.add(errorMssg);
    }
	
	private void initCancelButton() {
    	cancelButton = new JButton("Cancel");
    	style(cancelButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 10, 100, 25, buttonBorder);
    
    	cancelButton.addActionListener(new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(cancelButton)) {
                    controller.cancelUpdate();
                }
            }
        });
        
        this.add(cancelButton);
    }
	
    
    private void initUpdateButton() {
    	String s = null;
    	if(controller.getCurrentUser() == null) { s = "Add"; }
    	else { s = "Update"; }
    	updateButton = new JButton(s);
    	style(updateButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 40, 100, 25, buttonBorder);
    
    	updateButton.addActionListener(new ActionListener() {
    
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(updateButton)) {
                	
                	
                	
                	String fname = getFNameTextField();
                	String lname = getLNameTextField();
                	String uname = getUsernameTextField();
                	String notes = getNotesTextArea();
                	double salary = getSalaryTextField();
            		Shift[] shift = new Shift[7];
            		for(int i = 0; i < 7; i++) {
            			shift[i] = stringToShift(shiftboxes.get(i).getSelectedItem().toString());
            		}
            		
            		if(controller.getModifiedUser() == null) {
                		int cId = controller.getCurrentUser().getCID();
                		controller.addProfileInformation(fname, lname, uname, UserGroup.EMPLOYEE, shift, cId, notes, salary);
                	} else if(controller.getModifiedUser() == controller.getCurrentUser()) {
                		String pass = getPasswordTextField();
                		controller.updateProfileInformation(fname, lname, pass, notes, shift, salary);
                	} else {
                		boolean passreset = passwordReset.isSelected();
                		controller.updateProfileInformation(passreset, shift, getSalaryTextField(), notes);
                	}                   
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
    
    private Shift stringToShift(String s) {
    	switch(s.toLowerCase()) {
			case "morning":
				return Shift.MORNING;
			case "afternoon":
				return Shift.AFTERNOON;
			case "evening":
				return Shift.EVENING;
			case "night":
				return Shift.NIGHT;
			case "none":
				return Shift.NONE;
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
