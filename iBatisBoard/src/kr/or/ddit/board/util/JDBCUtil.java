package kr.or.ddit.board.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class JDBCUtil {
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("db");
		
		try {
			Class.forName(bundle.getString("driver"));
//			System.out.println("성공");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
//			System.out.println("실패");
		}
		
	}
	
	public static Connection getConnection() {
		
		try {
			return DriverManager.getConnection(bundle.getString("url"), 
											   bundle.getString("user"), 
											   bundle.getString("pass"));
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	public static void disConnection(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs!=null) {try {rs.close();} catch (SQLException e) {}}
		if(pstmt!=null) {try {pstmt.close();} catch (SQLException e) {}}
		if(stmt!=null) {try {stmt.close();} catch (SQLException e) {}}
		if(conn!=null) {try {conn.close();} catch (SQLException e) {}}
		
	}
}
