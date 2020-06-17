package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleDB{
	
	Connection conn; //������ ���̽��� ������ ���� ��ü 
	PreparedStatement pstmt; //sql ���� �����ͺ��̽��� ������ ���� ��ü 
	ResultSet rs;
	
	public OracleDB() { //DB ���� 
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "scott";
		String password = "tiger";
		
		
		try {
			Class.forName(driver); //jdbc ����̹� �ε�
			System.out.println("jdbc driver �ε� ����");
			conn =DriverManager.getConnection(url,user,password); //connection ���� 
			System.out.println("����Ŭ ���� ����");
		}catch(ClassNotFoundException e) {
			System.out.println("jdbc driver �ε� ����");
		}catch(SQLException e) {
			System.out.println("����Ŭ ���� ����");
		}	
		
	}

	public ResultSet executeQuery(String sql) {
		try { 
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		}catch(SQLException e) {
		System.out.println("conn");
		System.out.println(pstmt);
		}
		return rs;
	}
	
	public int executeUpdateQuery(String sql) {
		int result =0;
		try {
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		}catch(SQLException e) {
			
		}
		return result;
	}
	

		

	
	
	public void close() { //�ڿ� ����
		try {
			conn.close();
		}catch(SQLException e) {
			
		}
	}
	
	
	
	
	
	
}