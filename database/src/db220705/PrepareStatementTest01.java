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
			connection.setAutoCommit(false);// ���ùر��Զ��ύ

			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, "С��");
			prepareStatement.setInt(2, 10005);
			prepareStatement.executeUpdate();
			
			prepareStatement = connection.prepareStatement(sql);
			prepareStatement.setString(1, "Сmao");
			prepareStatement.setInt(2, 10003);
			prepareStatement.executeUpdate();
			connection.commit();//�ύs
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			connection.rollback();//�ع�
		} finally {
			JDBCUtil.closeAllResource(connection, prepareStatement, null);
		}

	}
}
