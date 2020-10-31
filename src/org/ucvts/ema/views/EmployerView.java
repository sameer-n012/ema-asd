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
import org.ucvts.ema.model.User;

@SuppressWarnings("serial")
public class EmployerView extends JPanel {

    private JLabel title;
    private JButton logoutButton;
    private JScrollPane employeeScrollList;
    private JPanel employeeBoxPanel;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JLabel empName;
    private Border buttonBorder;
    private Border panelBorder;
    
    private EMA ema;
	private Controller controller;

    public EmployerView(Controller controller) {
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
        initEmployeeList();
    }
    
    
    private void initTitle() {
    	String s = null;
    	if(controller.getCurrentUser() != null) { 
    		s = controller.getCurrentCompany().getName() + ": Employee List"; 
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
                    controller.modifyAddEmployee(null);
                }
            }
        });
        
        this.add(addButton);
    }
    
    private void initEmployeeList() {
    	
    	
    	
    	employeeBoxPanel = new JPanel();
    	employeeBoxPanel.setLayout(null);
    	style(employeeBoxPanel, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
				0, 80, 585, 800, null);
    	
    	
    	employeeScrollList = new JScrollPane(employeeBoxPanel);
    	style(employeeScrollList, null, null, null, 
				0, 80, 585, 485, panelBorder);
    	
    	employeeScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	employeeScrollList.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
    	employeeScrollList.getVerticalScrollBar().setUnitIncrement(20);
    	
    	//TODO try to change scrollbar thumb color
    	employeeScrollList.getVerticalScrollBar().setForeground(ema.BUTTON_COLOR);
    	//UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumb", ema.BUTTON_COLOR );
    	employeeScrollList.getVerticalScrollBar().setBackground(ema.BACKGROUND_COLOR);
    	
    	
    	
    	if(controller.getCurrentCompany() != null && controller.getCurrentUser() != null) {
    		initEmployeeBoxes();
    	}
    	
    	this.add(employeeScrollList);
    	
    }
    
    private void initEmployeeBoxes() {
    	
    		Company c = controller.getCurrentCompany();
	    	ArrayList<User> list = c.employeeList();
	    	
	    	User u = null;
	    	String name = null;
	    	
	    	//scrollPanel.setBounds(0, 80, 600, 20+60*list.size());
	    	
	    	
	    	for(int i = -1; i < list.size(); i++) {
	    		
	    		if(i == -1) { u = c.getEmployer(); }
	    		else { u = list.get(i); }
	    		
	    		name = u.getFName() + " " + u.getLName();
	    		if(i == -1) { name = name + " *"; }
	    		
	    		empName = new JLabel(name, SwingConstants.LEFT);
	    		style(empName, ema.FOREGROUND_COLOR, ema.BACKGROUND_COLOR, ema.TEXT_FONT, 
	    				40, 20+60*(i+1), 300, 30, null);
	    			
	    		modifyButton = new JButton("Modify");
	    		style(modifyButton, ema.FOREGROUND_COLOR, ema.BUTTON_COLOR, ema.TEXT_FONT, 
	    				330, 20+60*(i+1), 100, 30, buttonBorder);
	    		
	    		modifyButton.setActionCommand(String.valueOf(i));
	    		
	    		modifyButton.addActionListener(new ActionListener() {
	    		    
	                /*
	                 * Respond when the user clicks the Login button.
	                 */
	        
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    Object source = e.getSource();
	                    int a = Integer.parseInt(((JButton) source).getActionCommand());
	                    if (a >= -1 && a < list.size()) {
	                    	User u = null;
	                    	if(a==-1) { u = c.getEmployer(); }
	                    	else { u = list.get(a); }
	                    	controller.modifyAddEmployee(u);
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
	                    if (a >= -1 && a < list.size()) {
	                    	User u = null;
	                    	if(a==-1) { u = c.getEmployer(); }
	                    	else { u = list.get(a); }
	                    	controller.deleteEmployee(u);
	                    	//updateCard();
	                    	//initEmployeeList();
	                    }
	                }
	            });
	    		
	    		JSeparator separator = new JSeparator();
	    		style(separator, null, null,null, 
	    				20, 65+60*(i+1), 540, 2, BorderFactory.createMatteBorder(50,50,50,50, ema.FOREGROUND_COLOR));
	    		separator.setSize(540, 2);
	    		separator.setOrientation(SwingConstants.HORIZONTAL);
	    		
	    		employeeBoxPanel.add(empName);
	    		employeeBoxPanel.add(modifyButton);
	    		employeeBoxPanel.add(deleteButton);
	    		
	    		if(i!=list.size()-1) { employeeBoxPanel.add(separator); }
	    		
	    	}
	    	
	    	employeeBoxPanel.setPreferredSize(new Dimension(560, 60*(list.size()+1)));
    	
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