package views;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import components.*;
import models.User;
import java.util.Map;
import utils.AppFont;

public class UserFormDialog extends JDialog {
    private RoundedButton createBtn, returnBtn;
    private RoundedField  txtName;
    private RoundedField txtEmail;
    private RoundedPasswordField passwordField;
    private RoundedTextArea textArea;
    private RoundedField  txtPc;
    private JCheckBox cbTerms;
    private RoundedComboBox genderComboBox;

    private JLabel lblNameError;
    private JLabel lblEmailError;
    private JLabel lblPasswordError;
    private JLabel lblAddressError;
    private JLabel lblPcError;
    private JLabel lblTermsError;
    private JLabel lblGenderError;

    private final Border defaultBorder = new RoundedBorder(10, Color.GRAY, 2f);
    private final Border focusBorder = new RoundedBorder(10, new Color(65, 116, 255), 3f);
    private final Border errorBorder = new RoundedBorder(10, Color.RED, 3f);

    private final ImageIcon warningIcon = ScaleIcon.scale("src/img/warningIcon.png", 16, 16);

    private boolean saved = false;
    private User user;

    public UserFormDialog(JFrame parent, User user) {
        super(parent, true);
        this.user = user;

        setSize(400, 700);
        setTitle(user == null ? "eManza - Add user" : "eManza - Edit user");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        initializeComponents();
        loadData();

    }

    public void initializeComponents() {
        UIManager.put("ComboBox.background", Color.WHITE);
        UIManager.put("ComboBox.foreground", Color.BLACK);
        UIManager.put("ComboBox.selectionBackground", new Color(200, 220, 255));
        UIManager.put("ComboBox.selectionForeground", Color.BLACK);
        UIManager.put("List.background", Color.WHITE);
        UIManager.put("List.foreground", Color.BLACK);

        JPanel main = new JPanel();
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel();
        if(user == null){
            title.setText("Add user");
        } else{
            title.setText("Edit user");
        }

        title.setFont(AppFont.title());
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(title);
        main.add(Box.createVerticalStrut(15));

        txtName = new RoundedField(10);
        txtName.setCaretColor(Color.BLACK);
        addField(main, "Name", txtName);
        lblNameError = addError(main);

        applyFocusBorder(txtName, lblNameError);

        txtEmail = new RoundedField(10);
        txtEmail.setCaretColor(Color.BLACK);
        addField(main, "E-mail", txtEmail);
        lblEmailError = addError(main);

        applyFocusBorder(txtEmail, lblEmailError);

        passwordField = new RoundedPasswordField(10);
        passwordField.setCaretColor(Color.BLACK);
        addField(main, "Password", passwordField);
        lblPasswordError = addError(main);

        applyFocusBorder(passwordField, lblPasswordError);

        textArea = new RoundedTextArea(10);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setCaretColor(Color.BLACK);
        textArea.setBackground(Color.WHITE);
        textArea.setForeground(Color.BLACK);
        textArea.setBorder(defaultBorder);

        JScrollPane addressScroll = new JScrollPane(textArea);
        addressScroll.setOpaque(false);
        addressScroll.getViewport().setOpaque(false);
        addressScroll.setBorder(null);

        main.add(Box.createVerticalStrut(8));
        JLabel l = new JLabel("Address");
        l.setFont(AppFont.normal());
        l.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(l);
        addressScroll.setAlignmentX(Component.CENTER_ALIGNMENT);
        addressScroll.setPreferredSize(new Dimension(250, 160));
        addressScroll.setMaximumSize(new Dimension(250, 160));
        main.add(Box.createVerticalStrut(4));
        main.add(addressScroll);
        main.add(Box.createVerticalStrut(10));

        lblAddressError = addError(main);

        applyFocusBorder(textArea, lblAddressError);

        txtPc = new RoundedField(10);
        txtPc.setCaretColor(Color.BLACK);
        addField(main, "Postal Code", txtPc);
        lblPcError = addError(main);

        applyFocusBorder(txtPc, lblPcError);

        genderComboBox = new RoundedComboBox(new String[]{"Select", "Male", "Female", "Other"}, 10);
        addField(main, "Gender", genderComboBox);
        lblGenderError = addError(main);

        applyFocusBorder(genderComboBox, lblGenderError);

        cbTerms = new JCheckBox("I agree to the Terms of Service.");
        cbTerms.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(Box.createVerticalStrut(8));

        if(user == null) {
            main.add(cbTerms);
        }
        else{
            cbTerms.setSelected(true);
        }

        main.add(Box.createVerticalStrut(8));
        lblTermsError = addError(main);

        main.add(Box.createVerticalStrut(10));

        createBtn = new RoundedButton("Save", 10);
        createBtn.setFont(AppFont.normal());
        createBtn.setForeground(Color.WHITE);
        createBtn.setPreferredSize(new Dimension(120, 35));
        createBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        returnBtn = new RoundedButton("Cancel", 10);
        returnBtn.setFont(AppFont.normal());
        returnBtn.setPreferredSize(new Dimension(120, 35));
        returnBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        createBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        returnBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        main.add(Box.createVerticalStrut(8));
        main.add(createBtn);
        main.add(Box.createVerticalStrut(5));
        main.add(returnBtn);

        JScrollPane scroll = new JScrollPane(main);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getVerticalScrollBar().setUnitIncrement(25);

        setHoverEffect();

        add(scroll);
    }

    private void addField(JPanel panel, String label, JComponent field) {
        JLabel l = new JLabel(label);
        l.setFont(AppFont.normal());
        l.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(Box.createVerticalStrut(8));
        panel.add(l);
        panel.add(Box.createVerticalStrut(4));

        field.setFont(AppFont.normal());
        field.setAlignmentX(Component.CENTER_ALIGNMENT);
        field.setBorder(defaultBorder);
        field.setForeground(Color.BLACK);
        field.setBackground(Color.WHITE);

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
        if(m.containsKey("name")){
            lblNameError.setText(m.get("name"));
            lblNameError.setIcon(warningIcon);
            txtName.setBorder(errorBorder);
        }else{
            txtName.setBorder(defaultBorder);
            lblNameError.setIcon(null);
        }

        if(m.containsKey("email")){
            lblEmailError.setText(m.get("email"));
            lblEmailError.setIcon(warningIcon);
            txtEmail.setBorder(errorBorder);
        }else{
            txtEmail.setBorder(defaultBorder);
            lblEmailError.setIcon(null);
        }

        if(m.containsKey("password")){
            lblPasswordError.setText(m.get("password"));
            lblPasswordError.setIcon(warningIcon);
            passwordField.setBorder(errorBorder);
        }else{
            passwordField.setBorder(defaultBorder);
            lblPasswordError.setIcon(null);
        }

        if(m.containsKey("address")){
            lblAddressError.setText(m.get("address"));
            lblAddressError.setIcon(warningIcon);
            textArea.setBorder(errorBorder);
        }else{
            textArea.setBorder(defaultBorder);
            lblAddressError.setIcon(null);
        }

        if(m.containsKey("postalCode")){
            lblPcError.setText(m.get("postalCode"));
            lblPcError.setIcon(warningIcon);
            txtPc.setBorder(errorBorder);
        }else{
            txtPc.setBorder(defaultBorder);
            lblPcError.setIcon(null);
        }

        if(m.containsKey("gender")){
            lblGenderError.setText(m.get("gender"));
            lblGenderError.setIcon(warningIcon);
            genderComboBox.setBorder(errorBorder);
        }else{
            genderComboBox.setBorder(defaultBorder);
            lblGenderError.setIcon(null);
        }

        if(m.containsKey("terms")){
            lblTermsError.setText(m.get("terms"));
            lblTermsError.setIcon(warningIcon);
        }else{
            lblTermsError.setIcon(null);
        }
    }

    public void setHoverEffect(){
        Color createNormal = new Color(30, 58, 138);
        Color createHover  = new Color(23, 45, 110);
        Color returnNormal = new Color(200, 200, 200);
        Color returnHover = new Color(170, 170, 170);

        createBtn.setBackground(createNormal);
        returnBtn.setBackground(returnNormal);
        returnBtn.setForeground(Color.BLACK);

        createBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                createBtn.setBackground(createHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                createBtn.setBackground(createNormal);
            }
        });

        returnBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                returnBtn.setBackground(returnHover);
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                returnBtn.setBackground(returnNormal);
            }
        });
    }

    private void applyFocusBorder(JComponent field, JLabel errorLabel) {
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (errorLabel.getIcon() == null) {
                    field.setBorder(focusBorder);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (errorLabel.getIcon() == null) {
                    field.setBorder(defaultBorder);
                }
            }
        });
    }

    private void loadData() {
        if(user != null) {
            txtName.setText(user.getName());
            txtEmail.setText(user.getEmail());
            passwordField.setText((user.getPassword()));
            textArea.setText(user.getAddress());
            txtPc.setText(user.getPostalCode());
            genderComboBox.setSelectedItem(user.getGender());

        }
    }

    private void save() {
        String name = txtName.getText();
        String email = txtEmail.getText();
        String password = new String(passwordField.getPassword());
        String address = textArea.getText();
        String postalCode = txtPc.getText();
        String gender = (String) genderComboBox.getSelectedItem();

        if(user == null) {
            user = new User(name, email, password, address, postalCode, gender, true);
        }else {
            user.setName(name);
            user.setEmail(email);
            user.setPassword(password);
            user.setAddress(address);
            user.setPostalCode(postalCode);
            user.setGender(gender);
        }

        saved = true;
        dispose();
    }

    public boolean isSaved() {
        return saved;
    }
    public User getUser() {
        return user;
    }
}
