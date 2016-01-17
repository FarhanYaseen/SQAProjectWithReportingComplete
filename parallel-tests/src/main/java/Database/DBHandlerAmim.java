package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHandlerAmim {
	Connection conn1 = null, conn2 = null;
	public Connection ExamPortaralConnection() throws SQLException {
        
        String url = "jdbc:jtds:sqlserver://e357cb4b-750e-425a-977e-a4bb0082feea.sqlserver.sequelizer.com/dbe357cb4b750e425a977ea4bb0082feea";
						//hostname/dbname;
		String driver = "net.sourceforge.jtds.jdbc.Driver";
        String userName = "jyqofgamlvvwvyfh";
        String password = "uHvEVywVW8pVycYWweAwDLCbM7SAUYziyE76cKP7HZUoamdxacCUEXWReuZWN4a6";
        try {
            Class.forName(driver);
            conn1 = DriverManager.getConnection(url, userName, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn1;
    }
	
	/*
	public void bloodDonorCloseConnection() {
		try {
			conn1.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
	
	public Connection coffeeHubConnection() throws SQLException {

        String url = "jdbc:jtds:sqlserver://67134f60-4761-4830-b80b-a4bb0056f788.sqlserver.sequelizer.com/db67134f6047614830b80ba4bb0056f788;instance=SQLEXPRESS";
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        String userName = "uicnkgmfdcyfbnzw";
        String password = "qyjUuk8xgae4UR3zgXe7hhDBfENjivFkSr4bJQqMuQxxjHzymFgnB6Ui3QPXEWoT";
        try {
            Class.forName(driver);
            conn2 = DriverManager.getConnection(url, userName, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return conn2;
    }
	
	public void coffeeHubCloseConnection(){
		try {
			conn2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection mobileArenaConnection() throws SQLException {

        String url = "jdbc:jtds:sqlserver://94077f87-c281-4713-a3cf-a4b800cd9043.sqlserver.sequelizer.com/db94077f87c2814713a3cfa4b800cd9043;instance=SQLEXPRESS";
        String driver = "net.sourceforge.jtds.jdbc.Driver";
        String userName = "ncdfqqmnfdoeeewj";
        String password = "XhZtdVN8s8asYyihuBY5rd8GxThZ55rJMcx8eSaSzi2NMmRW7zXQt6dUir5sNWcw";
        try {
            Class.forName(driver);
            conn2 = DriverManager.getConnection(url, userName, password);
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return conn2;
    }
	
	public void mobileArenaCloseConnection(){
		try {
			conn2.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	*/
	
}