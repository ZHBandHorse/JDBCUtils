package db220705;

import java.sql.Connection;
import java.sql.PreparedStatement;

import utils.JDBCUtil;

public class PrepareStatementTest01 {
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		String sql = "UPDATE student SET name = ? WHERE stu_no = ?";
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		try {
			connection = JDBCUtil.getConnection();
			connection.setAutoCommit(false);// 设置关闭自动提交

			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, "小蓝");
			prepareStatement.setInt(2, 10005);
			prepareStatement.executeUpdate();
			
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, "小mao");
			prepareStatement.setInt(2, 10003);
			prepareStatement.executeUpdate();
			connection.commit();//提交s
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection.rollback();//回滚
		} finally {
			JDBCUtil.closeAllResource(connection, prepareStatement, null);
		}

	}
}
