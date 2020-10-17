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
    private JScrollPane employeeList;
    private JPanel scrollPanel;
    private JButton addButton;
    
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
                    
                }
            }
        });
        
        this.add(addButton);
    }
    
    private void initEmployeeList() {
    	
    	scrollPanel = new JPanel();
    	employeeList = new JScrollPane(scrollPanel);
    	employeeList.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    	employeeList.getVerticalScrollBar().setPreferredSize(new Dimension(30, Integer.MAX_VALUE));
    	employeeList.setBounds(0, 80, 600, 300);
    	//scrollPanel.setBounds(0, 80, 600, 300);
    	scrollPanel.setLayout(null);
    	employeeList.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	initEmployeeBoxes();
    	
    	this.add(employeeList);
    	
    }
    
    private void initEmployeeBoxes() {
    	try { 
    		Company c = controller.getCurrentCompany();
	    	ArrayList<User> list = c.employeeList();
	    	
	    	User u = null;
	    	String name = null;
	    	
	    	for(int i = 0; i < list.size(); i++) {
	    		u = list.get(i);
	    		name = u.getFName() + " " + u.getLName();
	    		System.out.println(name);
	    		JLabel label = new JLabel(name, SwingConstants.LEFT);
	    		label.setBounds(40, 20 + 60*i, 100, 30);
	    		label.setFont(new Font("Verdana", Font.BOLD, 14));
	    		
	    		JButton button = new JButton("Modify");
	    		button.setBounds(450, 20 + 60*i, 100, 30);
	    		System.out.println(button.getBounds().toString());
	    		
	    		JSeparator separator = new JSeparator();
	    		separator.setBounds(20, 65 + 60*i, 540, 1);
	    		separator.setBorder(BorderFactory.createMatteBorder(50,50,50,50, Color.black));
	    		separator.setSize(540, 2);
	    		separator.setOrientation(SwingConstants.HORIZONTAL);
	    		
	    		scrollPanel.add(label);
	    		scrollPanel.add(button);
	    		if(i!=list.size()-1) { scrollPanel.add(separator); }
	    		
	    	}
    	}
    	catch(Exception e) {e.printStackTrace();}
    	
    	System.out.println(scrollPanel.getBounds().toString());
    	System.out.println(employeeList.getBounds().toString());
    }
    

    
}