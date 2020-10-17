package org.ucvts.ema.views;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

public class EmployeeView extends JPanel {

	private JLabel title;
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
    	
    }

    private void initialize() {
        this.setLayout(null);

    }
	
}
