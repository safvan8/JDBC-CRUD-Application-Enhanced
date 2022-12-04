
package ai.Ineuron.Main;
import java.sql.Statement;
import java.util.Scanner;
import in.Ineuron.CRUD.CRUDApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainApp
{
	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
	Scanner scanner = null;
	CRUDApp crudApp = new CRUDApp();
	static MainApp mainApp = new MainApp();
	
	public static void main(String[] args)
	{
		mainApp.runJDBCApplication();
	}
	public void runJDBCApplication() 
	{

		// Loading and Registering Driver is Automated
		
		try
		{	// Step 1: Creating Object of class which implements Connection interface
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/schooldbo", "Safvan","password");
			if (connection != null)
			{
				// Step 2: Creating Object of class which implements Statement, using method  inside Connection interface
				statement = connection.createStatement();

				if (statement != null)
				{
					try
					{
						boolean wantToPerformAnyoperation = true; 
						while(wantToPerformAnyoperation)
						{		// accepting user input 
								String sqlQuery = crudApp.acceptUserChoice();
								
								// if the user want to run SELECT query
								if (CRUDApp.choice == 2)
								{
									resultSet = statement.executeQuery(sqlQuery);
		
									System.out.println("SID \tSName \tSAge \tTech");
									while (resultSet.next())
									{
										Integer sid = resultSet.getInt(1);
										String sname = resultSet.getString(2);
										Integer sage = resultSet.getInt(3);
										String tech = resultSet.getString(4);
		
										System.out.println(sid + " \t" + sname + " \t" + sage + " \t" + tech);
									}
								} 
								else // if user want to perform any non- select activity
								{
									int rowsAffected = statement.executeUpdate(sqlQuery);
									System.out.println("no. of rows affected :: " + rowsAffected);
								}
							
								// To perform Multiple Operations , according to user wish
								scanner = new Scanner(System.in);
								System.out.println("\n\nDo you want to perform any other Operation ? (Y/N)");
								System.out.println("Y -> YES \nAny Other key or N -> NO");
								Character userWish = scanner.next().trim().toUpperCase().charAt(0);
								if (userWish == 'Y')
								{
									System.out.println("\nYou have selected to perform one more Service");
									System.out.println("================================================");
								}
								else
								{
									wantToPerformAnyoperation = false;
									System.out.println("\nBye !!! Have a nice day");
								}
									
							
						}
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
					catch (Exception e) 
					{
						e.printStackTrace();
					}
				}
			}

		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			if (resultSet != null)
				try
				{
					resultSet.close();
				} catch (SQLException e)
				{
					
					e.printStackTrace();
				}
			
			if (statement != null)
				try
				{
					statement.close();
				}
			catch (SQLException e)
				{
					
					e.printStackTrace();
				}
			
			if (connection != null)
				try
				{
					connection.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}

		}
	}
	
	
}
