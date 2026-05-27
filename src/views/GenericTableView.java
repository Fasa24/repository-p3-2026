package views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import utils.AppFont;

public class GenericTableView extends JPanel {
	private JTable table;
	private DefaultTableModel model;

	public GenericTableView() {
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(10, 10, 10, 10));

		model = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) { return false; }
		};

		table = new JTable(model);
		table.setFont(AppFont.normal());
		table.setRowHeight(30);
		table.setShowHorizontalLines(true);
		table.putClientProperty("FlatLaf.style", "selectionBackground: $Component.hoverBackground");

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		add(scrollPane, BorderLayout.CENTER);
	}

	public void setHeaders(String[] headers) {
		model.setColumnIdentifiers(headers);
	}

	public void clearData() {
		model.setRowCount(0);
	}

	public void addRow(Object[] rowData) {
		model.addRow(rowData);
	}
}
