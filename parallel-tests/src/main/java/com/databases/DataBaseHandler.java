package com.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseHandler {

	Connection spConnection = null;
	Connection mobileArenaConnection = null;

	public void openSocialPediaConnection() {

		String url = "jdbc:jtds:sqlserver://ce86a3aa-576e-47e8-96ca-a57e00c4549d.sqlserver.sequelizer.com/dbce86a3aa576e47e896caa57e00c4549d;instance=SQLEXPRESS";
		// hostname/dbname;
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String userName = "ckcyyrvwjemwccaz";
		String password = "MJ4CbbuepSso7MZJqxRoSa8KyAjdJkr3r6mRQS2edaQovK6Ss4LQuUkLKseGZrCd";
		try {
			Class.forName(driver);
			spConnection = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// return conn1;
	}

	public void openMobileArenaConnection() {

		String url = "jdbc:jtds:sqlserver://94077f87-c281-4713-a3cf-a4b800cd9043.sqlserver.sequelizer.com/db94077f87c2814713a3cfa4b800cd9043;instance=SQLEXPRESS";
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String userName = "ncdfqqmnfdoeeewj";
		String password = "XhZtdVN8s8asYyihuBY5rd8GxThZ55rJMcx8eSaSzi2NMmRW7zXQt6dUir5sNWcw";
		try {
			Class.forName(driver);
			mobileArenaConnection = DriverManager.getConnection(url, userName,
					password);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void closeSocialPediaConnection() {

		if (spConnection != null) {
			try {
				spConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void closeMobileArenaConnection() {
		if (mobileArenaConnection != null) {
			try {
				mobileArenaConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public boolean isUserExist(String userName, String password) {
		ResultSet rs = null;
		Statement stmt = null;
		openSocialPediaConnection();
		try {

			stmt = spConnection.createStatement();
			String strSelect = "select * from user" + " where name = '"
					+ userName + "' and password = '" + password + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null)
				System.out.println("In database.....Test Passed");
			else {
				System.out.println("not in Database.....Test Failed");
			}
			rs.close();
			closeSocialPediaConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeSocialPediaConnection();
		return false;
	}

	// Social Pedia
	public boolean isPostExist(String userName, String postText) {

		ResultSet rs = null;
		Statement stmt = null;
		openSocialPediaConnection();
		try {

			stmt = spConnection.createStatement();
			String strSelect = "select * from Post" + " where userName = '"
					+ userName + "' and postText = '" + postText + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null)
				System.out.println("In database.....Test Passed");
			else {
				System.out.println("not in Database.....Test Failed");
			}
			// conn1.close();
			rs.close();
			closeSocialPediaConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeSocialPediaConnection();
		return false;
	}

	public boolean isCommentExist(String commentFrom, String Content) {

		ResultSet rs = null;
		Statement stmt = null;
		openSocialPediaConnection();
		try {

			stmt = spConnection.createStatement();
			String strSelect = "select * from Comment"
					+ " where commentFrom = '" + commentFrom
					+ "' and Content = '" + Content + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null)
				System.out.println("In database.....Test Passed");
			else {
				System.out.println("not in Database.....Test Failed");
			}
			// conn1.close();
			rs.close();
			closeSocialPediaConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeSocialPediaConnection();
		return false;
	}

	// MobileArena
	public boolean isFeedBackExist(String name, String email, String message,
			String contact) {
		ResultSet rs = null;
		Statement stmt = null;
		openMobileArenaConnection();
		try {
			stmt = mobileArenaConnection.createStatement();
			String strSelect = "select * from FeedBack " + "where name = '"
					+ name + "' and email = '" + email + "' and Message = '"
					+ message + "' and Contact = '" + contact + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null)
				System.out.println("In database.....Test Passed");
			else {
				System.out.println("not in Database.....Test Failed");
			}

			rs.close();
			closeMobileArenaConnection();
			return true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeMobileArenaConnection();
		return false;

	}

	public boolean isCommentInMAExist(String commentFrom, String Content) {

		ResultSet rs = null;
		Statement stmt = null;
		openMobileArenaConnection();
		try {

			stmt = mobileArenaConnection.createStatement();
			String strSelect = "select * from Comment" + " where Email = '"
					+ commentFrom + "' and Comment = '" + Content + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null)
				System.out.println("In database.....Test Passed");
			else {
				System.out.println("not in Database.....Test Failed");
			}
			// conn1.close();
			rs.close();
			closeSocialPediaConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		closeMobileArenaConnection();
		return false;
	}

	public boolean isUserExistInMA(String email, String password) {

		ResultSet rs = null;
		Statement stmt = null;
		openMobileArenaConnection();
		try {

			stmt = mobileArenaConnection.createStatement();
			String strSelect = "select * from dbo.Users" + " where email = '"
					+ email + "' and Password = '" + password + "';";
			rs = stmt.executeQuery(strSelect);
			if (rs != null)
				System.out.println("In database.....Test Passed");
			else {
				System.out.println("not in Database.....Test Failed");
			}

			rs.close();
			closeMobileArenaConnection();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
}
