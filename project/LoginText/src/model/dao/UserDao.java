package model.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.resource.cci.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.vo.User;


public  class UserDao {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/user?useUnicode=true&characterEncoding=utf-8&useSSL=false";
	// ���ݿ�ĵ�½�˺�����
	static final String USER = "root";
	static final String PASS = "123456";
	// �û���½
	public User login(String userName){
		Connection conn = null;
		Statement stmt = null;
		java.sql.ResultSet rs = null;
		User user = new User();
		try {
			System.out.println(conn + "----------" + stmt + "---------" + rs);
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println(conn + "----------" + stmt + "---------" + rs);
			// ִ��sql��ѯ
			stmt = conn.createStatement();
			String sql;
			System.out.println(conn + "----------" + stmt + "---------" + rs);
			sql = "select * from t_user where userName = \'" + userName + "\'";
			rs = stmt.executeQuery(sql);
			System.out.println(conn + "----------" + stmt + "---------" + rs);
			 //4.�������ݿ�ķ��ؽ��(ʹ��ResultSet��)
	        while(rs.next()){
//	        	System.out.println(rs.getString("user_name")+" "
//	                          +rs.getString("password"));
	        	// ����ѯ�����ݷ���User���󷵻�
	        	user.setUserName(rs.getString("userName"));
	        	user.setRole(rs.getString("role"));
	        	user.setName(rs.getString("chrName"));
	        	user.setPassword(rs.getString("password"));
	        }
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			//�ر���Դ
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return user;
	}
	

	

}
