package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

@SuppressWarnings("serial")
public class AddLogView extends JPanel {
	
    private EMA ema;
	private Controller controller;
	private boolean adding;
	private JLabel authorlabel;
	private JPanel logPanel;
	private JLabel errorMssg;
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
        
        initProfilePanel();

        //TODO call initialize methods here
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
    
	private void initErrorMssg() {
        errorMssg = new JLabel("", SwingConstants.CENTER);
        style(errorMssg, ema.ERROR_COLOR, ema.BACKGROUND_COLOR, ema.ERROR_FONT, 
				20, 440, 600, 35, null);

        errorMssg.setVisible(false);

        logPanel.add(errorMssg);
    }
    
    private void initProfilePanel() {
    	
    	logPanel = new JPanel();
    	logPanel.setLayout(null);
    	
    	style(logPanel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, null, 
    			0, 80, 600, 800, panelBorder);
    	
    	initName();
    	initErrorMssg();
    	
    	
    	this.add(logPanel);
    }
    
    private void initName() {
    	String name = null;
    	if(adding && controller.getCurrentUser() != null) {
    		name = controller.getCurrentUser().getFName() + " " + controller.getCurrentUser().getLName();
    	}
    	else if(!adding && controller.getCurrentUser() != null) {
    		name = controller.getCurrentLog().getAuthor().getFName() + " " + controller.getCurrentLog().getAuthor().getLName();
    	}
    	authorlabel = new JLabel(name + ":", SwingConstants.RIGHT);
    	style(authorlabel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
    			20, 20, 200, 35, null);

    	logPanel.add(authorlabel);

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
