package draft220707;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import utils.JDBCUtil;

public class UtilsTest extends JDBCUtil<Student>{


	@Override
	public Student get(ResultSet resultSet) throws SQLException {
		return new Student(resultSet.getString("stu_no"),resultSet.getString("name"),resultSet.getInt("stu_age"),resultSet.getString("adr"));
	}

	public static void main(String[] args) {
		
		UtilsTest test = new UtilsTest();
		test.getConnection();
		String sql = "SELECT * FROM `student`";
		List<Student> list = test.exeUpdateQuery(sql,null);
		for (Student student : list) {
			System.out.println(student);
		}
		
	}
}
