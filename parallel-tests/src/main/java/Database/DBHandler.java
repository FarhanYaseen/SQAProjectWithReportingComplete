package Database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

public class DBHandler {
	Connection conn1 = null, conn2 = null;

	public Connection openConnection() throws SQLException {

		String url = "jdbc:jtds:sqlserver://f484c3af-958f-447d-ae3a-a4b800aa7288.sqlserver.sequelizer.com/dbf484c3af958f447dae3aa4b800aa7288;instance=SQLEXPRESS";
		// hostname/dbname;
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String userName = "pybkvmuuowliyhqs";
		String password = "fMgDfrEnp4Yb5GWqYNQJFZouBM7HAs6mWtZWLJ6uAYk5MV28emDsHdmoyUczs6U5";
		try {
			Class.forName(driver);
			conn1 = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn1;
	}

}