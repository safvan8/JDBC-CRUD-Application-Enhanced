package in.Ineuron.CRUD;
import java.util.Scanner;

public class CRUDApp
{
	public static int choice; 
	Scanner input = new Scanner(System.in);
	String sname;
	Integer sage;
	String tech;
	// sid is auto increment, it will be assigned automatically

	public String performInsert()
	{
		System.out.println("Enter Student Name :: ");
		sname = input.next();
		System.out.println("Enter Student Age :: ");
		sage = input.nextInt();
		System.out.println("Enter Student's  Favorite Technology :: ");
		tech = input.next();

		String insertQuery = String.format("INSERT INTO schooldbo.student (sname,sage,tech)VALUES ('%s',%d,'%s')",
				sname, sage, tech);

		return insertQuery;
	}

	public String performSelect()
	{
		String selectQuery = "SELECT sid,sname,sage,tech FROM  schooldbo.student ";
		return selectQuery;
	}

	public String performUpadte()
	{
		System.out.println("Enter id of Student to UPDATE Student details:: ");
		Integer sid = input.nextInt();

		System.out.println("Enter new Name  :: ");
		sname = input.next();
		System.out.println("Enter Student's updated Age :: ");
		sage = input.nextInt();

		System.out.println("Enter Student's  Favorite Technology to change  :: ");
		tech = input.next();
		String updateQuery = String.format(
				"UPDATE schooldbo.student SET `sname` = '%s',`sage`=%d,`tech`= '%s' " + "WHERE  `sid` = %d ;", sname,
				sage, tech, sid);

		return updateQuery;
	}

	public String performDelete()
	{
		System.out.println("Enter the id of Student to delete ::  ");
		Integer sid = input.nextInt();

		String deleteQuery = String.format("DELETE FROM schooldbo.student WHERE Sid=%d", sid);
		return deleteQuery;
	}

	public String acceptUserChoice()
	{
		System.out.println("Welcome to JDBC : CRUD Application ");

		System.out.println("Avaialble Services\n************************* ");
		System.out.println("1 ->  Insert new Student details \n" + "2 ->  View All student details \n"
				+ "3 ->  Update Existing Student details based on Student Id \n"
				+ "4 -> Delete Existing Student details using Student id \n");

		System.out.println("Enter your choice  1 to 4 :");

		 
		choice = input.nextInt();
		
		boolean isValidChoice ; 
		if (choice >0 && choice <=4)
		{
			isValidChoice = true;
		}
		else
		{
			System.out.println("\nInvalid Selection: Please select an Option from 1 to 4 !!!! \n\n ");
			acceptUserChoice();
		}
        
		String query;
		if (choice == 1)
		{
			System.out.println("\nYou have Selected to enter new Student entry ");
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

}
