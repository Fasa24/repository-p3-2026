package views;

import java.awt.*;
import javax.swing.*;

import utils.AppFont;

@SuppressWarnings("serial")
public class FormularyRegistry extends JFrame {
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
        addField(panelComponentes, "Name", new JTextField());
        addField(panelComponentes, "E-mail", new JTextField());
        addPasswordField(panelComponentes, "Password");
        addTextAreaField(panelComponentes, "Address", 5, 20);
        addField(panelComponentes, "Postal Code", new JTextField());

        // GENDER SELECTION COMBO BOX
        addGenderField(panelComponentes);

        // ToS CHECKBOX
        JCheckBox cbTerms = new JCheckBox("I agree to the Terms of Service.");
        cbTerms.setAlignmentX(CENTER_ALIGNMENT);
        panelComponentes.add(cbTerms);
        panelComponentes.add(Box.createVerticalStrut(10));
        
        // ACCOUNT BUTTON
        JButton createAccountBtn = new JButton("Create Account");
        createAccountBtn.setBackground(new Color(0, 128, 0));
        createAccountBtn.setForeground(Color.WHITE);
        createAccountBtn.setAlignmentX(CENTER_ALIGNMENT);
        panelComponentes.add(createAccountBtn);

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

    private void addPasswordField(JPanel panel, String label) {
        JLabel lbl = new JLabel(label);
        lbl.setAlignmentX(CENTER_ALIGNMENT);
        JPasswordField passwordField = new JPasswordField();
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

        JTextArea textArea = new JTextArea(rows, cols);
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

        String[] genders = {"Male", "Female", "Other", "Prefer not to say"};
        JComboBox<String> genderComboBox = new JComboBox<>(genders);
        genderComboBox.setPreferredSize(new Dimension(250, 30));
        genderComboBox.setMaximumSize(new Dimension(250, 30));
        panel.add(genderComboBox);
        panel.add(Box.createVerticalStrut(10));
    }
}
