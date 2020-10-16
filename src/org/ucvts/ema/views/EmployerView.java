package org.ucvts.ema.views;

import java.awt.Color;
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
    
    private EMA ema;
	private Controller controller;

    public EmployerView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.controller = controller;
        this.initialize();
    }

    private void initialize() {
        this.setLayout(null);

        initTitle();
        initLogoutButton();
        initAddButton();
        initEmployeeList();
    }
    
    private void initTitle() {
    	String s = controller.getCurrentCompany().getName() + ": Employee List";
    	//String s = "First Inc: Employee List";
    	title = new JLabel(s, SwingConstants.CENTER);
    	title.setBounds(50, 20, 500, 35);
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
    	logoutButton = new JButton("Add");
    	logoutButton.setBounds(460, 40, 100, 25);
    
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
    
    private void initEmployeeList() {
    	
    	employeeList = new JScrollPane();
    	employeeList.setBounds(0, 80, 600, 600);
    	employeeList.setBorder(BorderFactory.createLineBorder(Color.black));
    	
    	initEmployeeBoxes();
    	
    	this.add(employeeList);
    	
    }
    
    private void initEmployeeBoxes() {
    	Company c = controller.getCurrentCompany();
    	ArrayList<User> list = c.employeeList();
    	
    	User u = null;
    	String name = null;
    	
    	for(int i = 0; i < list.size(); i++) {
    		u = list.get(i);
    		name = u.getFName() + " " + u.getLName();
    		JLabel label = new JLabel(name, SwingConstants.LEFT);
    		label.setBounds(10, 90 + 40*i, 150, 30);
    		label.setFont(new Font("Verdana", Font.BOLD, 14));
    		
    		JButton button = new JButton("Modify");
    		button.setBounds(460, 90 + 40*i, 150, 30);
    		
    		JSeparator separator = new JSeparator();
    		separator.setOrientation(SwingConstants.HORIZONTAL);
    		
    		employeeList.add(label);
    		employeeList.add(button);
    		if(i!=list.size()-1) { employeeList.add(separator); }
    		
    	}
    	
    	
    }
    

    
}