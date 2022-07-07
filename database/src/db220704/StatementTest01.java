package db220704;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utils.JDBCUtil;

public class StatementTest01 {

	public static void main(String[] args) throws Exception {
		
		Connection connection = JDBCUtil.getConnection();
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery("select * from student_info;");
		while (resultSet.next()) {
			int stu_no = resultSet.getInt("stu_no");
			String stu_name = resultSet.getString("stu_name");
			int stu_age = resultSet.getInt("stu_age");
			Date birthday = resultSet.getDate("birthday");
			System.out.println(stu_no+stu_name+stu_age+birthday);
			
		}
	}
}
