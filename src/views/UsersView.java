package views;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import tablemodels.UserTableModel;
import utils.AppFont;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.JTableHeader;

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

        styleTable();

        add(panelButtons, BorderLayout.NORTH);
    }

    public void styleTable() {
        table.setRowHeight(35);
        table.setShowGrid(true);
        table.setGridColor(new Color(30, 30, 30));
        table.setBackground(Color.WHITE);
        table.setForeground(Color.BLACK);
        table.setFont(AppFont.normal());

        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);

        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(44, 62, 80));
        header.setForeground(Color.WHITE);
        header.setFont(AppFont.title());
        header.setPreferredSize(new Dimension(0, 40));
        header.setReorderingAllowed(false);

        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table,
                    Object value,
                    boolean isSelected,
                    boolean hasFocus,
                    int row,
                    int column) {

                Component c = super.getTableCellRendererComponent(
                        table,
                        value,
                        isSelected,
                        hasFocus,
                        row,
                        column);

                if(column == 1) {
                    c.setFont(AppFont.normal());
                    if(!isSelected) {
                        //c.setForeground(new Color(41, 128, 185));
                    }
                } else {
                    c.setFont(AppFont.normal());
                }
                return c;
            }
        });
    }

    public void setTableModel(UserTableModel model) {
        table.setModel(model);

        if(table.getColumnCount() >= 1) {
            table.getColumnModel().getColumn(0).setPreferredWidth(80);
        }

        if(table.getColumnCount() >= 2) {
            table.getColumnModel().getColumn(1).setPreferredWidth(200);
        }

        if(table.getColumnCount() >= 3) {
            table.getColumnModel().getColumn(2).setPreferredWidth(50);
        }

        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(SwingConstants.CENTER);

        if(table.getColumnCount() >= 1) {
            table.getColumnModel().getColumn(0).setCellRenderer(center);
        }
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
