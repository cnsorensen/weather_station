import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class GraphStatsTableModel extends AbstractTableModel
{
	private String[] columnNames = {"Type","Stat"};
	private Object[][] data = 	{	{"Mean Temperature", ""},
									{"High/Low Temperature", ""},
									{"Mean Wind Speed", ""},
									{"Max Wind Speed", ""},
									{"Prevailing Wind Direction", ""},
									{"Rainfall", ""}
								};
	
	public int getColumnCount()
	{
		return columnNames.length;
	}

	public int getRowCount()
	{
		return data.length;
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	
	public Object getValueAt(int row, int col)
	{
		return data[row][col];
	}

	public void setValueAt(Object value, int row, int col)
	{
		data[row][col] = value;
		fireTableCellUpdated(row, col);
	}
}
