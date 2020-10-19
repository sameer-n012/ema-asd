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
    private JButton logoutButton;
    
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
    	try { s = controller.getCurrentCompany().getName() + ": " + controller.getCurrentUser().getFName() + " " + controller.getCurrentUser().getLName();; }
    	catch(Exception e) {};
    	title = new JLabel(s, SwingConstants.LEFT);
    	title.setBounds(20, 20, 450, 35);
    	title.setFont(new Font("Verdana", Font.BOLD, 20));
    	
        this.add(title);
    }
    
    
    private void initLogoutButton() {
    	logoutButton = new JButton("Logout");
    	logoutButton.setBounds(460, 25, 100, 25);
    
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
