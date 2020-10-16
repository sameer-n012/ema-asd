package org.ucvts.ema.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.ucvts.ema.EMA;
import org.ucvts.ema.app.Controller;

public class EmployerView extends JPanel {

	private Controller controller;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField companyField;
    private JButton loginButton;
    private JButton createCompanyButton;
    private JLabel errorMssg;
    
    private EMA ema;
    

    public EmployerView(Controller controller) {
        super();
        ema = EMA.getInstance();
        this.controller = controller;
        this.initialize();
    }

    private void initialize() {
        this.setLayout(null);

        
    }
    

    
}