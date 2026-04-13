package views;

import javax.swing.*;
import java.awt.*;
import models.User;
import java.util.Map;
import utils.AppFont;

public class FormularyRegistry extends JFrame {
	private JButton createBtn, returnBtn;
	private JTextField txtName;
	private JTextField txtEmail;
	private JPasswordField passwordField;
	private JTextArea textArea;
	private JTextField txtPc;
	private JCheckBox cbTerms;
	private JComboBox<String> genderComboBox;

	private JLabel lblNameError;
	private JLabel lblEmailError;
	private JLabel lblPasswordError;
	private JLabel lblAddressError;
	private JLabel lblPcError;
	private JLabel lblTermsError;
	private JLabel lblGenderError;

	public FormularyRegistry() {
		setSize(400, 700);
		setTitle("eManza - Create Account");
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		initializeComponents();
		setVisible(true);
	}

	public void initializeComponents() {
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

		JLabel title = new JLabel("Create Account");
		title.setFont(AppFont.title());
		title.setAlignmentX(Component.CENTER_ALIGNMENT);

		main.add(title);
		main.add(Box.createVerticalStrut(15));

		txtName = new JTextField();
		addField(main, "Name", txtName);
		lblNameError = addError(main);

		txtEmail = new JTextField();
		addField(main, "E-mail", txtEmail);
		lblEmailError = addError(main);

		passwordField = new JPasswordField();
		addField(main, "Password", passwordField);
		lblPasswordError = addError(main);

		textArea = new JTextArea(8, 20);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);

		JScrollPane addressScroll = new JScrollPane(textArea);
		addField(main, "Address", addressScroll);
		lblAddressError = addError(main);

		txtPc = new JTextField();
		addField(main, "Postal Code", txtPc);
		lblPcError = addError(main);

		genderComboBox = new JComboBox<>(new String[]{"Select", "Male", "Female", "Other"});
		addField(main, "Gender", genderComboBox);
		lblGenderError = addError(main);

		cbTerms = new JCheckBox("I agree to the Terms of Service.");
		cbTerms.setAlignmentX(Component.CENTER_ALIGNMENT);

		main.add(cbTerms);
		lblTermsError = addError(main);

		main.add(Box.createVerticalStrut(10));

		createBtn = new JButton("Create Account");
		returnBtn = new JButton("Return");

		createBtn.setFont(AppFont.normal());
		returnBtn.setFont(AppFont.normal());

		createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		returnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

		main.add(createBtn);
		main.add(Box.createVerticalStrut(5));
		main.add(returnBtn);

		JScrollPane scroll = new JScrollPane(main);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

		add(scroll);
	}

	private void addField(JPanel panel, String label, JComponent field) {
		JLabel l = new JLabel(label);
		l.setFont(AppFont.normal());
		l.setAlignmentX(Component.CENTER_ALIGNMENT);

		panel.add(l);

		field.setFont(AppFont.normal());
		field.setAlignmentX(Component.CENTER_ALIGNMENT);

		if (field instanceof JScrollPane) {
			field.setPreferredSize(new Dimension(250, 160));
			field.setMaximumSize(new Dimension(250, 160));
		} else {
			field.setPreferredSize(new Dimension(250, 30));
			field.setMaximumSize(new Dimension(250, 30));
		}

		panel.add(field);
		panel.add(Box.createVerticalStrut(10));
	}

	private JLabel addError(JPanel panel) {
		JLabel l = new JLabel(" ");
		l.setFont(AppFont.small());
		l.setForeground(Color.RED);
		l.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(l);
		return l;
	}

	public void setRegisterListener(java.awt.event.ActionListener l) {
		createBtn.addActionListener(l);
	}

	public void setReturnListener(java.awt.event.ActionListener l) {
		returnBtn.addActionListener(l);
	}

	public User getUserFromForm() {
		return new User(
				txtName.getText(),
				txtEmail.getText(),
				new String(passwordField.getPassword()),
				textArea.getText(),
				txtPc.getText(),
				(String) genderComboBox.getSelectedItem(),
				cbTerms.isSelected()
		);
	}

	public void clearErrors() {
		lblNameError.setText(" ");
		lblEmailError.setText(" ");
		lblPasswordError.setText(" ");
		lblAddressError.setText(" ");
		lblPcError.setText(" ");
		lblGenderError.setText(" ");
		lblTermsError.setText(" ");
	}

	public void showErrors(Map<String,String> m) {
		if(m.containsKey("name")) lblNameError.setText(m.get("name"));
		if(m.containsKey("email")) lblEmailError.setText(m.get("email"));
		if(m.containsKey("password")) lblPasswordError.setText(m.get("password"));
		if(m.containsKey("address")) lblAddressError.setText(m.get("address"));
		if(m.containsKey("postalCode")) lblPcError.setText(m.get("postalCode"));
		if(m.containsKey("gender")) lblGenderError.setText(m.get("gender"));
		if(m.containsKey("terms")) lblTermsError.setText(m.get("terms"));
	}
}
