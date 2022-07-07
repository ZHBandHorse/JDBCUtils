package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public abstract class JDBCUtil<T> {
	
	private static Connection connection = null;
	/**
	 * 	�������ļ��ֻ�ȡ�����ݿ������
	 * @return
	 */
	public static Connection getConnection() {
		String url = null;
		String username = null;
		String password = null;
		String driver = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("src\\jdbc.properties"));
			url = properties.getProperty("url");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			driver = properties.getProperty("driver");
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
		
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	/**
	  *    �ر�������Դ
	 * @param connection
	 * @param prepareStatement
	 * @param resultSet
	 */
	public static void closeAllResource(Connection connection,PreparedStatement prepareStatement,ResultSet resultSet) {
		if (resultSet!=null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (prepareStatement!=null) {
			try {
				prepareStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 	��ɾ�Ĳ����
	 * @param sql
	 * @param param
	 * @return
	 */
	public static boolean executeUpdate(String sql,Object...param) {
		int infLines = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			connection.setAutoCommit(false);
			if (param!=null) {
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i+1, param);
				}
			}
			infLines = ps.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return infLines>0 ? true : false;
	}
	
	/**
	 * 	��ѯ
	 * @param sql SQL���
	 * @param param	SQL���Ĳ���
	 * @return	����һ����װ�õ�һ������
	 */
	public List<T> exeUpdateQuery(String sql,Object...param) {
		List<T> list = new ArrayList<T>(); 
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			connection.setAutoCommit(false);
			if (param!=null) {
				for (int i = 0; i < param.length; i++) {
					ps.setObject(i+1, param);
				}
			}
			ResultSet resultSet = ps.executeQuery();
			while (resultSet.next()) {
				T t = get(resultSet);//����д�˷���������һ����װ�õ�����
				list.add(t);
			}
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return list;

	}
	/**
	 * 	����д�˷���������һ����װ�õ�����
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public abstract T get(ResultSet resultSet) throws SQLException;
	
	
}
