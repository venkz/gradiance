package ncsu.csc.db.beans;

import java.awt.BorderLayout;
import java.awt.Container;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ModelJTable extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	DefaultTableModel resultSetToTableModel(DefaultTableModel model,
			ResultSet row) throws SQLException {
		ResultSetMetaData meta = row.getMetaData();
		if (model == null)
			model = new DefaultTableModel();
		String cols[] = new String[meta.getColumnCount()];
		for (int i = 0; i < cols.length; ++i) {
			cols[i] = meta.getColumnLabel(i + 1);
		}

		model.setColumnIdentifiers(cols);

		while (row.next()) {
			Object data[] = new Object[cols.length];
			for (int i = 0; i < data.length; ++i) {
				data[i] = row.getObject(i + 1);
			}
			model.addRow(data);
		}
		return model;
	}

	private DefaultTableModel model;
	private JTable table;

	public ModelJTable(ResultSet row) {
		super();
		model = new DefaultTableModel();
		try {
			model = resultSetToTableModel(model, row);
			table = new JTable(model);

			
			Container container = getContentPane();
			container.add(new JScrollPane(table), BorderLayout.CENTER);
			container.add(table.getTableHeader(), BorderLayout.NORTH);

			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			setSize(400, 300);
			setVisible(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
