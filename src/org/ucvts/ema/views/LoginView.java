package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;
import org.ucvts.ema.placeholders.PlaceholderJPasswordField;
import org.ucvts.ema.placeholders.PlaceholderJTextField;

@SuppressWarnings("serial")
public class LoginView extends JPanel {

	private Controller controller;
    private PlaceholderJTextField usernameField;
    private PlaceholderJPasswordField passwordField;
    private PlaceholderJTextField companyField;
    private JButton loginButton;
    private JButton createCompanyButton;
    private JLabel errorMssg;
    private Border buttonBorder;
    private Border textFieldBorder;
    
    private EMA ema;
    

    public LoginView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
        textFieldBorder = BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR);
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
        JLabel title = new JLabel("Employee Management Application", SwingConstants.CENTER);
        style(title, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TITLE_FONT, 
				50, 60, 500, 35, null);

        this.add(title);
    }

    private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        style(errorMssg, ema.ERROR_COLOR, ema.BACKGROUND_COLOR, ema.ERROR_FONT, 
				50, 275, 500, 35, null);
        
        errorMssg.setVisible(false);

        this.add(errorMssg);
    }

    private void initUsernameField() {
        JLabel label = new JLabel("Username:", SwingConstants.RIGHT);
        style(label, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				100, 160, 95, 35, null);

        usernameField = new PlaceholderJTextField(20);
        usernameField.setPlaceholder("Username");
        usernameField.setCaretColor(ema.FOREGROUND_COLOR);
        style(usernameField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				205, 165, 200, 25, textFieldBorder);        
        
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
        style(label, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				100, 200, 95, 35, null);

        passwordField = new PlaceholderJPasswordField(20);
        passwordField.setCaretColor(ema.FOREGROUND_COLOR);
        style(passwordField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				205, 205, 200, 25, textFieldBorder);   

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
        
        passwordField.addKeyListener(new KeyAdapter() {

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 10 || e.getKeyChar() == 13) {
                    loginButton.doClick(); 
                }
            }
        });

        this.add(label);
        this.add(usernameField);

        this.add(label);
        this.add(passwordField);
    }
    
    private void initCompanyField() {
        JLabel label = new JLabel("Company:", SwingConstants.RIGHT);
        style(label, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				100, 240, 95, 35, null);

        companyField = new PlaceholderJTextField(20);
        companyField.setPlaceholder("Company Name (Optional)");
        companyField.setCaretColor(ema.FOREGROUND_COLOR);
        style(companyField, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				205, 245, 200, 25, textFieldBorder);

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
    	style(loginButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				205, 320, 200, 35, buttonBorder);
        
    
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
    	style(createCompanyButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				205, 360, 200, 35, buttonBorder);
    	
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