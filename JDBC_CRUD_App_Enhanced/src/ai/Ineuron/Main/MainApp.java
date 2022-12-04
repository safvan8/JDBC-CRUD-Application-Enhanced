package ai.Ineuron.Main;

import in.Ineuron.CRUD.CRUDApp;
import ai.Ineuron.jdbc.utiilty.JdbcUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class MainApp
{
	private Connection connection = null;
	private String sqlQuery;
	private PreparedStatement preparedStatement = null;
	private CRUDApp crudApp = new CRUDApp();
	private static MainApp mainApp = new MainApp();
	Scanner input = new Scanner(System.in);

	public static void main(String[] args)
	{
		mainApp.runJDBCApplication();
	}

	public void runJDBCApplication()
	{

		connection = JdbcUtil.getMysqlConnection();

		if (connection != null)
		{
			boolean wantToPerformAnyoperation = true;

			while (wantToPerformAnyoperation)
			{
				sqlQuery = crudApp.acceptUserChoiceAndGetSqlQuery();
				preparedStatement = crudApp.getPreparedStatement(connection, sqlQuery);

				if (preparedStatement != null)
				{

					JdbcUtil.executeQueryAndDisplayResult(preparedStatement);

					// To perform Multiple Operations , according to user wish
					System.out.println("\n\nDo you want to perform any other Operation ? (Y/N)");
					System.out.println("Y -> YES \nAny Other key or N -> NO");
					Character userWish = input.next().trim().toUpperCase().charAt(0);
					if (userWish == 'Y')
					{
						System.out.println("\nPlease Select Your next Oparation from the menu\n");

					} else
					{
						System.out.println("\nBye !!! Have a nice day");
						wantToPerformAnyoperation = false;
					}

				}
			}
			// ResultSet will be closed automatically when we call this method
			JdbcUtil.closeResources(connection, preparedStatement);
		}
	}
}
