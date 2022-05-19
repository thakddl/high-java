package kr.or.ddit.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class BoardJDBCUtil {
	static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("boardDb");
		
		try {
			Class.forName(bundle.getString("driver"));
//			System.out.println("드라이버 로딩 완료");
		} catch(ClassNotFoundException e) {
//			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		}
	}
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"),
											   bundle.getString("user"),
											   bundle.getString("pass"));
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void disConnect(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		
		if(rs!=null) try{rs.close();}catch(SQLException e) {}
		if(pstmt!=null) try{pstmt.close();}catch(SQLException e) {}
		if(stmt!=null) try{stmt.close();}catch(SQLException e) {}
		if(conn!=null) try{conn.close();}catch(SQLException e) {}
		
	}
}
