package views;

import java.awt.BorderLayout;
import javax.swing.*;
import tablemodels.UserTableModel;

public class UsersView extends JPanel {
    private JTable table;

    public UsersView() {
        setLayout(new BorderLayout());
        table = new JTable();
        table.setDefaultEditor(Object.class, null);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setTableModel(UserTableModel model) {
        table.setModel(model);
    }
}
