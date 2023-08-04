package common;

import java.sql.*;

public class JDBCTemplate {
	
	private static JDBCTemplate instance;
	private static Connection conn;
	
	// 싱글톤패턴.. 
	private JDBCTemplate() {}
	
	public static JDBCTemplate getInstance() {
		if(instance == null) {
			instance = new JDBCTemplate();
		} 
		return instance;
	}
	
	// 연결을 생성해주는 코드 
	public Connection createConnection() {
		String driverName = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "SALIMING";
		String password = "SALIMING";
		try {
			if(conn == null || conn.isClosed()) {
				Class.forName(driverName);
				conn = DriverManager.getConnection(url, user, password);
				conn.setAutoCommit(false); // 오토커밋 해제 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		return conn;
	}
	/* 
	 * 연결 해제
	 */
	public void close(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/* 
	 * 커밋하기
	 */
	public void commit(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/* 
	 *롤백하기
	 */
	public void rollback(Connection conn) {
		if(conn != null) {
			try {
				if(!conn.isClosed()) conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
