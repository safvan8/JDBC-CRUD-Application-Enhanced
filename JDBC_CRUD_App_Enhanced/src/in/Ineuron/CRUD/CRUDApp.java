package in.Ineuron.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;



public class CRUDApp
{
	PreparedStatement preparedStatement = null;
	public static int choice; 
	Scanner input = new Scanner(System.in);
	String sname;
	Integer sage;
	String tech;
	boolean welcomeMessageFlag = false;
	// sid is auto increment, it will be assigned automatically
	
	public String acceptUserChoiceAndGetSqlQuery()
	{
		if (welcomeMessageFlag == false)
		{
			System.out.println("Welcome to JDBC : CRUD Application ");
			welcomeMessageFlag = true;
		}
		
		
		System.out.println("Avaialble Services\n************************* ");
		System.out.println("1 ->  Insert new Student details \n" + "2 ->  View All student details \n"
				+ "3 ->  Update Existing Student details based on Student Id \n"
				+ "4 ->  Delete Existing Student details using Student id \n");

		System.out.println("Enter your choice  1 to 4 :");

		 
		choice = input.nextInt();
		
		// Accepting user choice until user entering a valid choice 
		boolean isValidChoice ; 
		if (choice >0 && choice <=4)
		{
			isValidChoice = true;
		}
		else
		{
			System.out.println("\nInvalid Selection: Please select an Option from 1 to 4 !!!! \n\n ");
			acceptUserChoiceAndGetSqlQuery();
		}
        
		String query;
		if (choice == 1)
		{
			System.out.println("\nYou have Selected to INSERT a new Student entry ");
			query = performInsert();
			return query;
		} else if (choice == 2)
		{
			System.out.println("\nAll Student details\n-----------------------------------");
			query = performSelect();
			return query;
		} else if (choice == 3)
		{
			System.out.println("\nYou have selected to UPDATE existing student details");
			query = performUpadte();
			return query;
			
		} else if (choice == 4)
		{
			System.out.println("\nYou have selected to DELETE existing student details");
			query = performDelete();
			return query;
		}
		else 
		{
			System.out.println("wrong choice , please Enter a number between 1 to 4");
			return null;
		}
		
	}
	
	public String performInsert()
	{
		String insertQuery = "INSERT INTO schooldbo.student (sname,sage,tech)VALUES (?,?,?)";
		
		return insertQuery;
	}

	public String performSelect()
	{
		String selectQuery = "SELECT sid,sname,sage,tech FROM  schooldbo.student ";
		return selectQuery;
	}

	public String performUpadte()
	{
		String updateQuery = "UPDATE schooldbo.student SET sname = ?, sage=?, tech= ? WHERE  sid = ? ;";

		return updateQuery;
	}

	public String performDelete()
	{

		String deleteQuery = "DELETE FROM schooldbo.student WHERE Sid= ? ";
		return deleteQuery;
	}

	public PreparedStatement getPreparedStatement(Connection connection, String sqlQuery) 
	{
		try
		{
			preparedStatement = connection.prepareStatement(sqlQuery);
		} catch (SQLException se)
		{

			se.printStackTrace();
		}

		// if User selected INSERT operation
		if (choice == 1)
		{
			System.out.print("Enter Student Name :: ");
			sname = input.next();
			System.out.print("Enter Student Age :: ");
			sage = input.nextInt();
			System.out.print("Enter Student's  Favorite Technology :: ");
			tech = input.next();

			try
			{
				preparedStatement.setString(1, sname);
				preparedStatement.setInt(2, sage);
				preparedStatement.setString(3, tech);
			} catch (SQLException e)
			{
				e.printStackTrace();
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		} 
		// if user selected to view existing table 
		else if (choice == 2)
		{
			
		}
		else if (choice == 3) 
		{
			System.out.println("Enter id of Student to UPDATE Student details:: ");
			Integer sid = input.nextInt();

			System.out.print("Enter new Name  :: ");
			sname = input.next();
			System.out.print("Enter Student's updated Age :: ");
			sage = input.nextInt();

			System.out.print("Enter Student's  Favorite Technology to change  :: ");
			tech = input.next();
			
			try
			{
				preparedStatement.setString(1, sname);
				preparedStatement.setInt(2, sage);
				preparedStatement.setString(3, tech);
				preparedStatement.setInt(4, sid);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}	
		}
		// user selected to delete row from table using sid
		else
		{
			System.out.print("Enter the id of Student to delete ::  ");
			Integer sid = input.nextInt();
			
			try
			{
				preparedStatement.setInt(1, sid);
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		
		
		return preparedStatement;
	}
	
	
	
	public void exitOrContinueBanner()
	{
		// To perform Multiple Operations , according to user wish
		System.out.println("\n\nDo you want to perform any other Operation ? (Y/N)");
		System.out.println("Y -> YES \nAny Other key or N -> NO");
		Character userWish = input.next().trim().toUpperCase().charAt(0);
		if (userWish == 'Y')
		{
			System.out.println("\nYou have selected to perform one more Service");
			System.out.println("================================================");
			
		}
		else
		{
			System.out.println("\nBye !!! Have a nice day");
			System.exit(0);
		}
	}

}
