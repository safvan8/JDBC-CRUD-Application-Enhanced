package ai.Ineuron.jdbc.utiilty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.Ineuron.CRUD.CRUDApp;


public class JdbcUtil
{
	private static Connection connection = null;
	private static ResultSet resultSet = null;

	// To load driver class. not Required from JDBC 4.0 API
	static
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	public static Connection getMysqlConnection()
	{
		String url = "jdbc:mysql://localhost:3306/schooldbo";
		String username = "root";
		String passwd = "Safvan@123";

		try
		{
			connection = DriverManager.getConnection(url, username, passwd);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return connection;
	}

	public static void executeQueryAndDisplayResult(PreparedStatement preparedStatement)
	{
		// if the user want to run SELECT query
		if (CRUDApp.choice == 2)
		{
				try
				{
					resultSet = preparedStatement.executeQuery();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
	
				System.out.println("SID \tSName \tSAge \tTech");
				try
				{
					while (resultSet.next())
					{
						Integer sid = resultSet.getInt(1);
						String sname = resultSet.getString(2);
						Integer sage = resultSet.getInt(3);
						String tech = resultSet.getString(4);
	
						System.out.println(sid + " \t" + sname + " \t" + sage + " \t" + tech);
					}
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		// if user want to perform any non- select activity
		else 
			{
				int rowsAffected = 0;
				try
				{
					rowsAffected = preparedStatement.executeUpdate();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
				System.out.println("no. of rows affected :: " + rowsAffected);
		}
	}

	public static void closeResources(Connection connection, PreparedStatement preparedStatement)
	{

		try
		{
			if (resultSet != null)
			{
				resultSet.close();
			}

			if (preparedStatement != null)
			{
				preparedStatement.close();
			}

			if (connection != null)
			{
				connection.close();
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

}
