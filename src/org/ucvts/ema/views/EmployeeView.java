package org.ucvts.ema.views;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

public class EmployeeView extends JPanel {

	private JLabel title;
	private JLabel subtitle;
    private JButton logoutButton;
    private JScrollPane employeeList;
    
    private EMA ema;
	private Controller controller;

    public EmployeeView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.controller = controller;
        this.initialize();
    }
    
    public void updateCard() {
    	this.setLayout(null);
        initLogoutButton();
        initTitle();
    	
    }

    private void initialize() {
    	this.setLayout(null);
        initLogoutButton();
        initTitle();
    }
	
    private void initTitle() {
    	String s = null;
    	String s2 = null;
    	try { s = controller.getCurrentCompany().getName(); }
    	catch(Exception e) {};
    	try { s2 = controller.getCurrentUser().getFName() + " " + controller.getCurrentUser().getLName(); }
    	catch(Exception e) {};
    	title = new JLabel(s, SwingConstants.CENTER);
    	title.setBounds(30, 10, 500, 35);
    	title.setFont(new Font("Verdana", Font.BOLD, 20));
    	
    	subtitle = new JLabel(s2, SwingConstants.CENTER);
    	subtitle.setBounds(30, 30, 500, 35);
    	subtitle.setFont(new Font("Verdana", Font.BOLD, 14));
    	
        this.add(title);
        this.add(subtitle);
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
                //Would not work normally, works like this
                if (source.toString().equals(logoutButton.toString())) {
                	controller.logout();
                }
            }
        });
        this.add(logoutButton);
    }
}
