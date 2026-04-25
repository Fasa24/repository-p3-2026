package views;

import java.awt.*;
import javax.swing.*;
import tablemodels.UserTableModel;

public class UsersView extends JPanel {
    private JTable table;
    private JButton btnEdit;
    private JButton btnAdd;
    private JButton btnDelete;


    public UsersView() {
        setLayout(new BorderLayout());
        table = new JTable();
        table.setDefaultEditor(Object.class, null);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        btnAdd = new JButton("Add");
        btnEdit = new JButton("Edit");
        btnDelete = new JButton("Delete");

        panelButtons.add(btnAdd);
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);

        add(panelButtons, BorderLayout.NORTH);
    }

    public void setTableModel(UserTableModel model) {
        table.setModel(model);
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }

    public JButton getBtnEdit() {
        return btnEdit;
    }

    public JButton getBtnDelete() {
        return btnDelete;
    }

    public int getSelectedRow() {
        return table.getSelectedRow();
    }
}
