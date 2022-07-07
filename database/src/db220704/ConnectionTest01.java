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
//		//实例化一个驱动Driver
//		Class clazz = Class.forName(driverName);
//		Driver driver = (Driver)clazz.newInstance();		
//		//注册驱动
//		DriverManager.registerDriver(driver);
//		//获取连接
//		Connection connection = DriverManager.getConnection(url, user, password);
//		System.out.println(connection);
		/*
		 * 简化版本
		 */
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection);
		
		
	}
}









