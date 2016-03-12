import java.sql.BatchUpdateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BatchErrorTest {

	public static final SimpleDateFormat yyyyMMDD = new SimpleDateFormat(
			"yyyyMMdd");

	public static long longdate = 20160201;

	public static void main(String[] args) {

		Connection connection = null;
		int successCount = 0;
		int failCount = 0;
		int notAavailable = 0;
		PreparedStatement preparedStatement = null;
		int[] a = new int[100];
		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			connection = DriverManager.getConnection(
					"jdbc:hsqldb:hsql://localhost/", "SA", "");
			preparedStatement = connection
					.prepareStatement("insert into testerror (id,per,dt) values (?,?,?)");

			for (int i = 1; i <= 30; i++) {
				preparedStatement.setInt(1, i);
				preparedStatement.setFloat(2, 1.0f);
				if (i == 22) {
					preparedStatement.setDate(3, new java.sql.Date(yyyyMMDD
							.parse(String.valueOf(null)).getTime()));
				} else {
					preparedStatement.setDate(3, new java.sql.Date(yyyyMMDD
							.parse(String.valueOf(longdate)).getTime()));
				}
				preparedStatement.addBatch();

				if (i % 10 == 0) {
					a = preparedStatement.executeBatch();
				}

			}
			// a=preparedStatement.executeBatch();
		} catch (BatchUpdateException buex) {
			buex.printStackTrace();
			buex.printStackTrace();
			int[] updateCounts = buex.getUpdateCounts();
			for (int i = 0; i < updateCounts.length; i++) {
				if (updateCounts[i] >= 0) {
					successCount++;

				} else if (updateCounts[i] == Statement.SUCCESS_NO_INFO) {
					notAavailable++;

				} else if (updateCounts[i] == Statement.EXECUTE_FAILED) {
					failCount++;
					System.out.println("Failed Record index : " + i);

				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				preparedStatement.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
