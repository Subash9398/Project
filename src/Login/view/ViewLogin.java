package Login.view;

import java.sql.SQLException;
import java.util.Scanner;

import Login.model.ModelLogin;
import Login.presenter.IPresenterLogin;
import Login.presenter.PresenterLogin;

public class ViewLogin implements IViewLogin 
{
	private IPresenterLogin presenterlogin; 
	
	public static void main(String args[]) 
	{
		//create the view class object
		ViewLogin view = new ViewLogin();
		
		//call the setPresenter method and then
		//Argument of create the PresenterLogin class object
		//PresenterLogin class object Argument of view class object and ModelLogin class object
		view.setPresenter(new PresenterLogin(view,new ModelLogin()));
	}
	public static Object  scannerObject()
	{
		Scanner scannerObject1= new Scanner(System.in);
		 return scannerObject1;
	}
	
	public void setPresenter(IPresenterLogin presenter) 
	{
		this.presenterlogin = presenter;
		homepage();
	}

	//Home page method to call sign in or sign up or account delete method
	public void homepage()
	{
		try {
		Object scannerObject= scannerObject();
		System.out.println("Home Page :");
		System.out.println("Sign in Press ---> 1");
		System.out.println("Sign Up Press ---> 2");
		System.out.println("Account Delete Press ---> 3");
		System.out.println("Exit Application Press ---> 4");
		int option = ((Scanner) scannerObject).nextInt();

		switch(option)
		{
			case 1 : userlogin(); break;
			case 2 : try 
					{
						signup();
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					} break;
			case 3 : try 
					{
						delete_account();
					} 
					catch (SQLException e) 
					{
						e.printStackTrace();
					} break;
			case 4 : System.out.println("You had been successfully Exited"); System.exit(0); break;
			default : 
					{
						System.out.println("\nPlease Enter Valid Number");
						homepage();
					}
		}
	}
		catch(Exception e) 
		{
		System.out.println("Enter the valid option");
		homepage();
		}
	}

	//UserLogin method to get UserName and Password 
	//call the Interface presenterlogin methods
	public void userlogin() 
	{
		System.out.println("\nSign In Page :");
        Object scannerObject= scannerObject();

		System.out.println("Enter Your Username :");
		String username = ((Scanner) scannerObject).nextLine();
		System.out.println("Enter Your Password :");
		String password = ((Scanner) scannerObject).nextLine();
		
		presenterlogin.pass(username,password);
		presenterlogin.login();
	}

	//Sign up method to create new account 
	public void signup() throws SQLException 
	{
		System.out.println("\nSign Up Page :");
        Object scannerObject= scannerObject();

		System.out.println("Enter the Username :");
		String username = ((Scanner) scannerObject).nextLine();
		System.out.println("Enter the Password :");
		String password = ((Scanner) scannerObject).nextLine();
		
		String output = presenterlogin.signup(username,password);
		if("success".equals(output))
		{
			System.out.println("Sign Up Successfully");
			homepage();
		}
		else
		{
			System.out.println("Please Re-enter UserName and Password:");
			signup();
		}
		
	}
	
	//Delete account method to delete exist account 
	public void delete_account() throws SQLException 
	{
		System.out.println("\nAccount Delete Page :");
        Object scannerObject = scannerObject();

		System.out.println("Enter Your Username :");
		String username = ((Scanner) scannerObject).nextLine();
		System.out.println("Enter Your Password :");
		String password = ((Scanner) scannerObject).nextLine();
		
		String result = presenterlogin.delete_account(username,password);
		if("true".equals(result))
		{
			System.out.println("Account Delete Successfully");
			homepage();
		}
		else
		{
			System.out.println("Incorrect Username or Password");
			homepage();
		}
	}
	
	public void updateStatusLabel(String result) 
	{
		System.out.println(result);
		homepage();
	}
}