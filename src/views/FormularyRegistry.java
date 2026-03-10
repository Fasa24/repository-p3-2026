package views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import utils.AppFont;

@SuppressWarnings("serial")
public class FormularyRegistry extends JFrame {
	private JButton createAccountBtn;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JTextArea textArea;
	private JTextField txtPc;
	private JCheckBox cbTerms;
	private JLabel lblNameError;
	private JLabel lblEmailError;
	private JLabel lblPasswordError;
	private JLabel lblAddressError;
	private JLabel lblPcError;
	private JLabel lblTermsError;
	private JLabel lblGenderError;
	private JButton returnLoginBtn;
	private JComboBox<String> genderComboBox;
	
	
    public FormularyRegistry() {
        setSize(350, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("eManza - Create Account");
        setLocationRelativeTo(null);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image icono = tk.getImage("src/img/appleLogo.jpg");
        setIconImage(icono);

        initializeComponents();
        verification();

        setVisible(true);
    }

    public void initializeComponents() {
        // TITLE
        JLabel lblTitulo = new JLabel("Create Account");
        lblTitulo.setFont(AppFont.title());
        lblTitulo.setForeground(UIManager.getColor("Label.foreground"));
        
        // CENTER TITLE
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setOpaque(false);
        titlePanel.add(lblTitulo);
        
        add(titlePanel, BorderLayout.NORTH);
        
        // MAIN PANEL w/ BOXLAYOUT
        JPanel panelComponentes = new JPanel();
        panelComponentes.setLayout(new BoxLayout(panelComponentes, BoxLayout.Y_AXIS));
        panelComponentes.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelComponentes.setOpaque(false);

        // FORM FIELDS
        addField(panelComponentes, "Name", txtName = new JTextField());
        addLabel(panelComponentes, lblNameError = new JLabel());
        addField(panelComponentes, "E-mail", txtEmail = new JTextField());
        addLabel(panelComponentes, lblEmailError = new JLabel());
        addPasswordField(panelComponentes, "Password");
        addLabel(panelComponentes, lblPasswordError = new JLabel());
        addTextAreaField(panelComponentes, "Address", 5, 20);
        addLabel(panelComponentes, lblAddressError = new JLabel());
        addField(panelComponentes, "Postal Code", txtPc = new JTextField());
        addLabel(panelComponentes, lblPcError = new JLabel());
        
        // GENDER SELECTION COMBO BOX
        addGenderField(panelComponentes);
        addLabel(panelComponentes, lblGenderError = new JLabel());
        genderComboBox.addActionListener(e -> verification());

        // ToS CHECKBOX
        cbTerms = new JCheckBox("I agree to the Terms of Service.");
        cbTerms.setAlignmentX(CENTER_ALIGNMENT);
        panelComponentes.add(cbTerms);
        addLabel(panelComponentes, lblTermsError = new JLabel());
        panelComponentes.add(Box.createVerticalStrut(10));
        cbTerms.addActionListener(e -> verification());
        
        // ACCOUNT BUTTON
        createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBackground(new Color(0, 128, 0));
        createAccountBtn.setForeground(Color.WHITE);
        createAccountBtn.setAlignmentX(CENTER_ALIGNMENT);
        createAccountBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelComponentes.add(createAccountBtn);
        
        createAccountBtn.addActionListener(e-> register());
        
        // RETURN TO LOGIN BUTTON
        returnLoginBtn = new JButton("Return");
        returnLoginBtn.setBackground(new Color(0, 128, 0));
        returnLoginBtn.setForeground(Color.WHITE);
        returnLoginBtn.setAlignmentX(CENTER_ALIGNMENT);
        returnLoginBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        returnLoginBtn.addActionListener(e-> {
        	int option = JOptionPane.showConfirmDialog(this, "Are you sure?");
        	
        	if(option == JOptionPane.YES_OPTION) {
        		new LoginWindow();
        		dispose();
        	}
        });
        
        assignListeners();
        
        panelComponentes.add(returnLoginBtn);

        // Add scroll pane to the panel
        JScrollPane scroll = new JScrollPane(panelComponentes);
        scroll.setHorizontalScrollBar(null);
        add(scroll, BorderLayout.CENTER);
    }

    private void addField(JPanel panel, String label, JTextField textField) {
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lbl);

        textField.setPreferredSize(new Dimension(250, 30));
        textField.setMaximumSize(new Dimension(250, 30));
        panel.add(textField);
        panel.add(Box.createVerticalStrut(10));
    }
    
    private void addLabel(JPanel panel, JLabel lbl) {
    	lbl.setAlignmentX(CENTER_ALIGNMENT);
    	lbl.setForeground(Color.RED);
    	lbl.setText(" ");
    	lbl.setVisible(false);
    	panel.add(lbl);
    }

    private void addPasswordField(JPanel panel, String label) {
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(250, 30));
        passwordField.setMaximumSize(new Dimension(250, 30));
        panel.add(lbl);
        panel.add(passwordField);
        panel.add(Box.createVerticalStrut(10));
    }
    
    private void addTextAreaField(JPanel panel, String label, int rows, int cols) {
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lbl);

        textArea = new JTextArea(rows, cols);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setAlignmentX(CENTER_ALIGNMENT);

        JScrollPane scrollArea = new JScrollPane(textArea);
        scrollArea.setPreferredSize(new Dimension(250, 100));
        scrollArea.setMaximumSize(new Dimension(250, 100));

        panel.add(scrollArea);
        panel.add(Box.createVerticalStrut(10));
    }

    private void addGenderField(JPanel panel) {
        JLabel lblGender = new JLabel("Gender");
        lblGender.setAlignmentX(CENTER_ALIGNMENT);
        panel.add(lblGender);

        String[] genders = {"Select" ,"Male", "Female", "Other", "Prefer not to say"};
        genderComboBox = new JComboBox<>(genders);
        
        
        genderComboBox.setPreferredSize(new Dimension(250, 30));
        genderComboBox.setMaximumSize(new Dimension(250, 30));
        panel.add(genderComboBox);
        panel.add(Box.createVerticalStrut(10));
    }
    
    private void register() {
    	if(verification()) {
    		JOptionPane.showMessageDialog(null, "Registration successful!", "eManza", JOptionPane.INFORMATION_MESSAGE);
    	}
    }
    
    private boolean verificateGender() {
    	if(genderComboBox.getSelectedIndex() == 0) {
    		lblGenderError.setText("A gender is required");
			lblGenderError.setVisible(true);
    		return false;
    	}
    	else {
    		lblGenderError.setVisible(false);
    		return true;
    	}
    }
    
    private void assignListeners() {
    	txtName.getDocument().addDocumentListener(new DocumentListener() {
    		@Override
    		public void removeUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void insertUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void changedUpdate(DocumentEvent e) {
    			verification();
    		}
    	});
    	
    	txtEmail.getDocument().addDocumentListener(new DocumentListener() {
    		@Override
    		public void removeUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void insertUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void changedUpdate(DocumentEvent e) {
    			verification();
    		}
    	});
    	
    	passwordField.getDocument().addDocumentListener(new DocumentListener() {
    		@Override
    		public void removeUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void insertUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void changedUpdate(DocumentEvent e) {
    			verification();
    		}
    	});
    	
    	textArea.getDocument().addDocumentListener(new DocumentListener() {
    		@Override
    		public void removeUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void insertUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void changedUpdate(DocumentEvent e) {
    			verification();
    		}
    	});
    	
    	txtPc.getDocument().addDocumentListener(new DocumentListener() {
    		@Override
    		public void removeUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void insertUpdate(DocumentEvent e) {
    			verification();
    		}
    		
    		@Override
    		public void changedUpdate(DocumentEvent e) {
    			verification();
    		}
    	});
    }
    
    private boolean verification() {
    	int cont = 0;
		
		// NAME
		if(txtName.getText().trim().isEmpty()) {
			lblNameError.setText("Name is required.");
			lblNameError.setVisible(true);
		}
		else {
			lblNameError.setVisible(false);
			cont++;
		}
		// EMAIL
		if(txtEmail.getText().trim().isEmpty()) {
			lblEmailError.setText("email is required.");
			lblEmailError.setVisible(true);
		}
		else {
			lblEmailError.setVisible(false);
			cont++;
		}
		
		// PASSWORD
		if(String.valueOf(passwordField.getPassword()).trim().isEmpty()) {
			lblPasswordError.setText("A password is required.");
			lblPasswordError.setVisible(true);
		}
		else {
			lblPasswordError.setVisible(false);
			cont++;
		}
		
		// ADDRESS
		if(textArea.getText().trim().isEmpty()) {
			lblAddressError.setText("Address is required.");
			lblAddressError.setVisible(true);
		}
		else {
			lblAddressError.setVisible(false);
			cont++;
		}
		
		// POSTAL CODE
		if(txtPc.getText().trim().isEmpty()) {
			lblPcError.setText("A postal code is required.");
			lblPcError.setVisible(true);
		}
		else {
			lblPcError.setVisible(false);
			cont++;
		}
		
		// TERMS
		if(!cbTerms.isSelected()) {
			lblTermsError.setText("You must accept the Terms and Conditions.");
			lblTermsError.setVisible(true);
		}
		else {
			lblTermsError.setVisible(false);
			cont++;
		}
		
		boolean genderValid = verificateGender();
		if(cont == 6 && genderValid) {
			return true;
		}
		else {
			return false;
		}
    }
}
