package db220704;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;


public class ConnectionTest01 {

	public static void main(String[] args) throws Exception {
		
		String url = "jdbc:mysql://192.168.37.1:3306/myschool";
		String user = "root";
		String password = "";
//		String driverName = "com.mysql.jdbc.Driver";
//		
//		//ʵ����һ������Driver
//		Class clazz = Class.forName(driverName);
//		Driver driver = (Driver)clazz.newInstance();		
//		//ע������
//		DriverManager.registerDriver(driver);
//		//��ȡ����
//		Connection connection = DriverManager.getConnection(url, user, password);
//		System.out.println(connection);
		/*
		 * �򻯰汾
		 */
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
		
		
	}
}









