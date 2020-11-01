package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.Log;
import org.ucvts.ema.model.User;

@SuppressWarnings("serial")
public class LogView extends JPanel {
	
	private JLabel title;
    private JButton logoutButton;
    private JScrollPane logScrollList;
    private JPanel logBoxPanel;
    private JButton addButton;
    private JButton viewButton;
    private JButton deleteButton;
    private JLabel logName;
    private Border buttonBorder;
    private Border panelBorder;
    
    private EMA ema;
	private Controller controller;

    public LogView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        buttonBorder = BorderFactory.createMatteBorder(0, 0, 0, 0, ema.FOREGROUND_COLOR);
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

        initTitle();
        initLogoutButton();
        initAddButton();
        initLogList();
        
    }
    
    
    private void initTitle() {
    	String s = null;
    	if(controller.getCurrentUser() != null) { 
    		s = controller.getCurrentCompany().getName() + ": Log List"; 
		}
    	title = new JLabel(s, SwingConstants.LEFT);
    	style(title, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TITLE_FONT, 
				20, 20, 500, 35, null);

        this.add(title);
    }
    
    private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	style(logoutButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 10, 100, 25, buttonBorder);
    
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
    
    private void initAddButton() {
    	addButton = new JButton("Add");
    	style(addButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
				460, 40, 100, 25, buttonBorder);
    
    	addButton.addActionListener(new ActionListener() {
    
            /*
             * Respond when the user clicks the Login button.
             */
    
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
    
                if (source.equals(addButton)) {
                    controller.gotoAddLog(null);
                }
            }
        });
        
        this.add(addButton);
    }
    
    private void initLogList() {
    	
    	
    	
    	logBoxPanel = new JPanel();
    	logBoxPanel.setLayout(null);
    	style(logBoxPanel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				0, 80, 585, 800, null);
    	
    	
    	logScrollList = new JScrollPane(logBoxPanel);
    	style(logScrollList, null, null, null, 
				0, 80, 585, 485, panelBorder);
    	
    	logScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	logScrollList.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
    	logScrollList.getVerticalScrollBar().setUnitIncrement(20);
    	
    	//TODO try to change scrollbar thumb color
    	logScrollList.getVerticalScrollBar().setForeground(ema.BUTTON_COLOR);
    	//UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumb", ema.BUTTON_COLOR );
    	logScrollList.getVerticalScrollBar().setBackground(ema.BACKGROUND_COLOR);
    	
    	System.out.println("1");
    	try { System.out.println(controller.getCurrentCompany().getName()); } catch(Exception e) {}
    	try { System.out.println(controller.getCurrentUser().getUsername()); } catch(Exception e) {}
    	if(controller.getCurrentCompany() != null && controller.getCurrentUser() != null) {
    		System.out.println("!");
    		initLogBoxes();
    	}
    	
    	this.add(logScrollList);
    	
    }
    
    private void initLogBoxes() {
    	
    		Company c = controller.getCurrentCompany();
	    	ArrayList<Log> list = c.getLogs();
	    	
	    	Log l = null;
	    		    	
	    	
	    	for(int i = 0; i < list.size(); i++) {
	    		System.out.println("printed");

	    		l = list.get(i);
	    		
	    		logName = new JLabel("Log ID: " + l.getID(), SwingConstants.LEFT);
	    		style(logName, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
	    				40, 20+60*(i+1), 300, 30, null);
	    			
	    		viewButton = new JButton("View");
	    		style(viewButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
	    				330, 20+60*(i+1), 100, 30, buttonBorder);
	    		
	    		viewButton.setActionCommand(String.valueOf(i));
	    		
	    		viewButton.addActionListener(new ActionListener() {
	        
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    Object source = e.getSource();
	                    int a = Integer.parseInt(((JButton) source).getActionCommand());
	                    if (a >= 0 && a < list.size()) {
	                    	//TODO do controller function to move to view log screen
	                    	controller.gotoAddLog(controller.getCurrentCompany().getLog(a));
	                    }
	                }
	            });
	    		
	    		deleteButton = new JButton("Delete");
	    		style(deleteButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
	    				450, 20+60*(i+1), 100, 30, buttonBorder);
	    		
	    		deleteButton.setActionCommand(String.valueOf(i));
	    		
	    		deleteButton.addActionListener(new ActionListener() {
	    		    
	                /*
	                 * Respond when the user clicks the Login button.
	                 */
	        
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    Object source = e.getSource();
	                    int a = Integer.parseInt(((JButton) source).getActionCommand());
	                    if (a >= 0 && a < list.size()) {
	                    	Log l = list.get(a); 
	                    	controller.deleteLog(l);
	                    }
	                    
	                }
	            });
	    		
	    		JSeparator separator = new JSeparator();
	    		style(separator, null, null,null, 
	    				20, 65+60*(i+1), 540, 2, BorderFactory.createMatteBorder(50,50,50,50, ema.FOREGROUND_COLOR));
	    		separator.setSize(540, 2);
	    		separator.setOrientation(SwingConstants.HORIZONTAL);
	    		
	    		logBoxPanel.add(logName);
	    		logBoxPanel.add(viewButton);
	    		logBoxPanel.add(deleteButton);
	    		
	    		if(i!=list.size()-1) { logBoxPanel.add(separator); }
	    		
	    	}
	    	
	    	logBoxPanel.setPreferredSize(new Dimension(560, 60*(list.size()+1)));
    	
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
