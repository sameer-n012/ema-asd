package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;
import org.ucvts.ema.model.Company;
import org.ucvts.ema.model.User;

public class EmployerView extends JPanel {

    private JLabel title;
    private JButton logoutButton;
    private JScrollPane employeeScrollList;
    private JPanel employeeBoxPanel;
    private JButton addButton;
    private JButton modifyButton;
    private JButton deleteButton;
    private JLabel empName;
    
    private EMA ema;
	private Controller controller;

    public EmployerView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.setBackground(ema.BACKGROUND_COLOR);
        this.controller = controller;
        this.initialize();
    }
    
    public void updateCard() {
    	
    	this.removeAll();
    	this.setLayout(null);

        initTitle();
        initLogoutButton();
        initAddButton();
        initEmployeeList();
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
    	try { s = controller.getCurrentCompany().getName() + ": Employee List"; }
    	catch(Exception e) {};
    	//String s = "First Inc: Employee List";
    	title = new JLabel(s, SwingConstants.LEFT);
    	title.setBounds(20, 20, 500, 35);
    	title.setFont(ema.TITLE_FONT);
    	title.setForeground(ema.FOREGROUND_COLOR);

        this.add(title);
    }
    
    private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(460, 10, 100, 25);
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
    
                if (source.equals(logoutButton)) {
                    controller.logout();
                }
            }
        });
        
        this.add(logoutButton);
    }
    
    private void initAddButton() {
    	addButton = new JButton("Add");
    	addButton.setBounds(460, 40, 100, 25);
    	addButton.setForeground(ema.FOREGROUND_COLOR);
    	addButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, ema.FOREGROUND_COLOR));
    	addButton.setBackground(ema.BUTTON_COLOR);
    	addButton.setFont(ema.TEXT_FONT);
    
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
    	employeeBoxPanel.setBounds(0, 80, 585, 800);
    	employeeBoxPanel.setBackground(ema.BACKGROUND_COLOR);
    	
    	
    	employeeScrollList = new JScrollPane(employeeBoxPanel);
    	employeeScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	employeeScrollList.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
    	employeeScrollList.getVerticalScrollBar().setUnitIncrement(20);
    	//TODO try to change scrollbar thumb color
    	employeeScrollList.getVerticalScrollBar().setForeground(ema.BUTTON_COLOR);
    	//UIManager.getLookAndFeelDefaults().put( "ScrollBar.thumb", ema.BUTTON_COLOR );
    	employeeScrollList.getVerticalScrollBar().setBackground(ema.BACKGROUND_COLOR);
    	employeeScrollList.setBounds(0, 80, 585, 485);
    	
    	employeeBoxPanel.setLayout(null);
    	employeeScrollList.setBorder(BorderFactory.createLineBorder(ema.FOREGROUND_COLOR));
    	
    	initEmployeeBoxes();
    	
    	this.add(employeeScrollList);
    	
    }
    
    private void initEmployeeBoxes() {
    	try { 
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
	    		empName.setBounds(40, 20 + 60*(i+1), 300, 30);
	    		empName.setFont(ema.TEXT_FONT);
	    		empName.setForeground(ema.FOREGROUND_COLOR);
	    		
	    		modifyButton = new JButton("Modify");
	    		modifyButton.setBounds(330, 20 + 60*(i+1), 100, 30);
	    		modifyButton.setForeground(ema.FOREGROUND_COLOR);
	    		modifyButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, ema.FOREGROUND_COLOR));
	    		modifyButton.setBackground(ema.BUTTON_COLOR);
	    		modifyButton.setFont(ema.TEXT_FONT);
	    		
	    		
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
	    		deleteButton.setBounds(450, 20 + 60*(i+1), 100, 30);
	    		deleteButton.setForeground(ema.FOREGROUND_COLOR);
	    		deleteButton.setBorder(BorderFactory.createMatteBorder(0,0,0,0, ema.FOREGROUND_COLOR));
	    		deleteButton.setBackground(ema.BUTTON_COLOR);
	    		deleteButton.setFont(ema.TEXT_FONT);
	    		
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
	                    	System.out.println("1");
	                    	updateCard();
	                    }
	                }
	            });
	    		
	    		JSeparator separator = new JSeparator();
	    		separator.setBounds(20, 65 + 60*(i+1), 540, 1);
	    		separator.setBorder(BorderFactory.createMatteBorder(50,50,50,50, ema.FOREGROUND_COLOR));
	    		separator.setSize(540, 2);
	    		separator.setOrientation(SwingConstants.HORIZONTAL);
	    		
	    		employeeBoxPanel.add(empName);
	    		employeeBoxPanel.add(modifyButton);
	    		employeeBoxPanel.add(deleteButton);
	    		if(i!=list.size()-1) { employeeBoxPanel.add(separator); }
	    		
	    	}
	    	
	    	employeeBoxPanel.setPreferredSize(new Dimension(560, 60*(list.size()+1)));
    	}
    	catch(Exception e) {}
    	
    }
    

    
}