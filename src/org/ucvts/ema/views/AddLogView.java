package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.text.SimpleDateFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;
import org.ucvts.ema.model.UserGroup;
import org.ucvts.ema.placeholders.PlaceholderJTextArea;
import org.ucvts.ema.placeholders.PlaceholderJTextField;

@SuppressWarnings("serial")
public class AddLogView extends JPanel {

	private EMA ema;
	private Controller controller;
	private boolean adding;
	private JLabel title;
	private JLabel authorlabel;
	private JPanel logPanel;
	private JLabel errorMssg;
	private JLabel startdatelabel;
	private JLabel stopdatelabel;
	private PlaceholderJTextField stopdatefield;
	private PlaceholderJTextField startdatefield;
	private PlaceholderJTextArea descriptiontextarea;
	private JLabel commitdatelabel;
	private JCheckBox verifiedbox;
	private JLabel verifiedlabel;
	private JButton cancelButton;
	private JButton updateButton;
	private Border buttonBorder;
	private Border panelBorder;
	private Border textAreaBorder;
	private Border textFieldBorder;

	public AddLogView(Controller controller) {
		super();
		ema = EMA.getInstance();
		this.setBackground(ema.BACKGROUND_COLOR);
		this.controller = controller;

		buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
		textFieldBorder = BorderFactory.createMatteBorder(0,0,2,0, ema.FOREGROUND_COLOR);
		textAreaBorder = BorderFactory.createMatteBorder(2,2,2,2, ema.FOREGROUND_COLOR);
		panelBorder = BorderFactory.createLineBorder(ema.FOREGROUND_COLOR);

		this.initialize();

	}

	public void updateCard() {
		this.removeAll();
		
		initialize();
	}

	private void initialize() {
		this.adding = (controller.getCurrentLog() == null);
		this.setLayout(null);
		initLogPanel();
		initCancelButton();
		initUpdateButton();
		initTitle();

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
	
	private String getStartDateField() {
		return startdatefield.getText();
	}
	
	private String getStopDateField() {
		return stopdatefield.getText();
	}
	
	private String getDescriptionTextArea() {
		return descriptiontextarea.getText();
	}
	
	private boolean getVerifiedBox() {
		return verifiedbox.isSelected();
	}

	private void initTitle() {
    	String s = null;
    	if(adding && controller.getCurrentCompany() != null) { 
    		s = controller.getCurrentCompany().getName() + ": New Log"; 
		}
    	if(!adding && controller.getCurrentLog() != null && controller.getCurrentCompany() != null) {
    		s = controller.getCurrentCompany().getName() + ": Log " + controller.getCurrentLog().getID();
    	}
    	title = new JLabel(s, SwingConstants.LEFT);
    	style(title, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TITLE_FONT, 
				20, 20, 500, 35, null);

        this.add(title);
    }

	private void initLogPanel() {

		logPanel = new JPanel();
		logPanel.setLayout(null);

		style(logPanel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, null, 
				0, 80, 600, 800, panelBorder);

		initName();
		initVerifiedBox();
		initErrorMssg();
		initName();
		initStartDate();
		initStopDate();
		initDescTextArea();
		initCommitDate();
		


		this.add(logPanel);
	}

	private void initName() {
		String name = null;
		if(adding && controller.getCurrentUser() != null) {
			name = controller.getCurrentUser().getFName() + " " + controller.getCurrentUser().getLName();
		}
		else if(!adding && controller.getCurrentLog() != null) {
			name = controller.getCurrentLog().getAuthor().getFName() + " " + controller.getCurrentLog().getAuthor().getLName();
		}
		authorlabel = new JLabel("Author: " + name, SwingConstants.LEFT);
		style(authorlabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				33, 20, 300, 35, null);

		logPanel.add(authorlabel);

	}
	
	private void initVerifiedBox() {
		verifiedbox = new JCheckBox();
		style(verifiedbox, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
   			 350, 25, 15, 25, buttonBorder);
		verifiedbox.setEnabled(false);
		
		verifiedlabel = new JLabel("(Verified)", SwingConstants.LEFT);
		style(verifiedlabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT,
				375, 20, 110, 35, null);
		
		
		
		if(controller.getCurrentUser() != null && controller.getCurrentUser().getRole() == UserGroup.EMPLOYER) {
			verifiedbox.setEnabled(true);
		}
		if(!adding && controller.getCurrentLog() != null) {
			verifiedbox.setSelected(controller.getCurrentLog().isVerified());
		}
		
		logPanel.add(verifiedbox);
		logPanel.add(verifiedlabel);
	}

	
	
	private void initStartDate() {
		

			
		startdatelabel = new JLabel("Start Time: ", SwingConstants.RIGHT);
		style(startdatelabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 80, 100, 35, null);

		startdatefield = new PlaceholderJTextField(14);
		startdatefield.setPlaceholder("DD/MM/YY hh:mm");
		startdatefield.setCaretColor(ema.FOREGROUND_COLOR);
		style(startdatefield, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				125, 85, 125, 25, textFieldBorder);


		startdatefield.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (getStartDateField().length() >= 14) {
					e.consume();  //Next line includes 0-9, :, and space 
				} else if (e.getKeyChar() < 32 || (e.getKeyChar() > 32 && e.getKeyChar() < 47) || e.getKeyChar() > 58) {
					e.consume(); 
				}
			}
		});
		
		if(adding) {
			startdatefield.setEditable(true);
		}
		else if(!adding && controller.getCurrentLog() != null) {
			startdatefield.setText(controller.getCurrentLog().getStartString());
			startdatefield.setEditable(false);
		}
		
		logPanel.add(startdatelabel);
		logPanel.add(startdatefield);
	}
	
	private void initStopDate() {
		stopdatelabel = new JLabel("Stop Time: ", SwingConstants.RIGHT);
		style(stopdatelabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				300, 80, 100, 35, null);

		stopdatefield = new PlaceholderJTextField(14);
		stopdatefield.setPlaceholder("DD/MM/YY hh:mm");
		stopdatefield.setCaretColor(ema.FOREGROUND_COLOR);
		style(stopdatefield, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				405, 85, 125, 25, textFieldBorder);

		stopdatefield.addKeyListener(new KeyAdapter() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (getStopDateField().length() >= 14) {
					e.consume();  //Next line includes 0-9, :, and space 
				} else if (e.getKeyChar() < 32 || (e.getKeyChar() > 32 && e.getKeyChar() < 47) || e.getKeyChar() > 58) {
					e.consume(); 
				}
			}
		});
		
		if(adding && controller.getCurrentUser() != null) {
			stopdatefield.setEditable(true);
		}
		else if(!adding && controller.getCurrentUser() != null) {
			stopdatefield.setText(controller.getCurrentLog().getStopString());
			stopdatefield.setEditable(false);
		}
		
		logPanel.add(stopdatelabel);
		logPanel.add(stopdatefield);

	}
	
	private void initDescTextArea() {
		descriptiontextarea = new PlaceholderJTextArea();
		descriptiontextarea.setPlaceholder("Add description here");
		descriptiontextarea.setMargin(new Insets(20,20,20,20));
		descriptiontextarea.setLineWrap(true);
		descriptiontextarea.setWrapStyleWord(true);
		style(descriptiontextarea, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				20, 150, 540, 300, textAreaBorder);
		
		if(adding) {
			descriptiontextarea.setEditable(true);
		}
		else if(!adding && controller.getCurrentLog() != null) {
			descriptiontextarea.setText(controller.getCurrentLog().getDescription());
			descriptiontextarea.setEditable(false);
		}
		
		logPanel.add(descriptiontextarea);
	}

	private void initErrorMssg() {
		errorMssg = new JLabel("", SwingConstants.CENTER);
		style(errorMssg, ema.ERROR_COLOR, ema.BACKGROUND_COLOR, ema.ERROR_FONT, 
				0, 450, 600, 35, null);

		errorMssg.setVisible(false);

		logPanel.add(errorMssg);
	}
	
	private void initCommitDate() {
		String s = null;
		if(!adding && controller.getCurrentLog() != null) {
			System.out.println("5");
			s = "Commit Date: " + controller.getCurrentLog().getCommitString();
			showErrorMessage("", false);
		}
		else { s = ""; }
		commitdatelabel = new JLabel(s, SwingConstants.CENTER);
		style(commitdatelabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				0, 450, 600, 35, null);
		
		logPanel.add(commitdatelabel);
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
                    controller.cancelLogUpdate();
                }
            }
        });
        
        this.add(cancelButton);
    }
	
    
    private void initUpdateButton() {
    	String s = null;
    	if(adding) { s = "Add"; }
    	else { s = "Update"; }
    	
    	updateButton = new JButton(s);
    	style(updateButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 40, 100, 25, buttonBorder);
    
    	updateButton.addActionListener(new ActionListener() {
    
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if(source.equals(updateButton)) {
	                if (controller.getCurrentLog() == null) {
	                	String start = getStartDateField();
	                	String stop = getStopDateField();
	                	String desc = getDescriptionTextArea();
	                	boolean ver = getVerifiedBox();
	            		Date d1 = null;
	            		Date d2 = null;
	                	
	                	try { 
	                		if(start.length() != 14 || stop.length() != 14) {
	                			throw new Exception();
	                		}
	                		
	                		d1 = new SimpleDateFormat("dd/MM/yy HH:mm").parse(start); 
	                		d2 = new SimpleDateFormat("dd/MM/yy HH:mm").parse(stop); 
	                		
	                		
	                		if(d1.compareTo(d2) >= 0) {
	                			showErrorMessage("Invalid dates", true);
	                		}
	                		else if(desc == null || desc.length() == 0) {
	                			showErrorMessage("Invalid description", true);
	                		}
	                		else {
	                			controller.addLog(d1, d2, desc, ver);
	                		}
	                		
	            		}
	                	catch (Exception ex) { showErrorMessage("Invalid dates", true); }
	                }
	                else if(controller.getCurrentLog() != null && verifiedbox.isSelected() != controller.getCurrentLog().isVerified()){
	                	controller.verifyLog(controller.getCurrentLog());
	                }
	                else {
	                	controller.gotoViewLogs();
	                }
                }
            }
        });
        
        this.add(updateButton);
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
