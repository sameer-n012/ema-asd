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
    	title = new JLabel(s, SwingConstants.CENTER);
    	title.setBounds(30, 20, 500, 35);
    	title.setFont(new Font("Verdana", Font.BOLD, 18));

        this.add(title);
    }
    
    private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(460, 10, 100, 25);
    
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
    	
    	
    	employeeScrollList = new JScrollPane(employeeBoxPanel);
    	employeeScrollList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	employeeScrollList.getVerticalScrollBar().setPreferredSize(new Dimension(15, Integer.MAX_VALUE));
    	employeeScrollList.getVerticalScrollBar().setUnitIncrement(20);
    	employeeScrollList.setBounds(0, 80, 585, 485);
    	
    	employeeBoxPanel.setLayout(null);
    	employeeScrollList.setBorder(BorderFactory.createLineBorder(Color.black));
    	
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
	    		empName.setFont(new Font("Verdana", Font.BOLD, 14));
	    		
	    		modifyButton = new JButton("Modify");
	    		modifyButton.setBounds(330, 20 + 60*(i+1), 100, 30);
	    		
	    		deleteButton = new JButton("Delete");
	    		deleteButton.setBounds(450, 20 + 60*(i+1), 100, 30);
	    		
	    		modifyButton.setActionCommand(String.valueOf(i));
	    		
	    		modifyButton.addActionListener(new ActionListener() {
	    		    
	                /*
	                 * Respond when the user clicks the Login button.
	                 */
	        
	                @Override
	                public void actionPerformed(ActionEvent e) {
	                    Object source = e.getSource();
	                    int a = Integer.parseInt(((JButton) source).getActionCommand());
	        
	                    if (source.equals(modifyButton)) {
	                    	User u = null;
	                    	if(a==-1) { u = c.getEmployer(); }
	                    	else { u = list.get(a); }
	                    	controller.modifyAddEmployee(u);
	                    }
	                }
	            });
	    		
	    		JSeparator separator = new JSeparator();
	    		separator.setBounds(20, 65 + 60*(i+1), 540, 1);
	    		separator.setBorder(BorderFactory.createMatteBorder(50,50,50,50, Color.black));
	    		separator.setSize(540, 2);
	    		separator.setOrientation(SwingConstants.HORIZONTAL);
	    		
	    		employeeBoxPanel.add(empName);
	    		employeeBoxPanel.add(modifyButton);
	    		employeeBoxPanel.add(deleteButton);
	    		if(i!=list.size()-1) { employeeBoxPanel.add(separator); }
	    		
	    	}
	    	
	    	employeeBoxPanel.setPreferredSize(new Dimension(560, 60*(list.size()+1)));
    	}
    	catch(Exception e) {e.printStackTrace();}
    	
    }
    

    
}