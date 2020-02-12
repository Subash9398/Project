package Login.model;

import java.sql.Statement;

import Admin.model.ModelAdmin;
import Admin.presenter.PresenterAdmin;
import Admin.view.IViewAdmin;
import Admin.view.ViewAdmin;
import Customer.model.ModelCustomer;
import Customer.presenter.PresenterCustomer;
import Customer.view.IViewCustomer;
import Customer.view.ViewCustomer;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;

public class ModelLogin implements IModelLogin
{
	  private String username;
	  private String password;
	  
		public void set(String username,String password) 
	    {
	        this.username = username;
	        this.password = password;
	    } 
		
		static public Statement connection() throws SQLException
		{
			Connection connectionObject = DriverManager.getConnection("jdbc:sqlserver://106.51.1.63; database = {fresher_ecom_task}","ecomfresher","Change@Fresher");
			Statement statementObject = connectionObject.createStatement();
			return statementObject;
		}
		
		
		public String login() throws SQLException
		{
			Statement statementObject1 = ModelLogin.connection();
			
			ResultSet resultSetObject = statementObject1.executeQuery("select * from login");
		
			String result = "Please re-enter correct username or password :";

			while(resultSetObject.next())
			{
				String name  = resultSetObject.getString("User_Name");
				String password = resultSetObject.getString("Password");
			
				//check the given username and password is correct or not
				//Already username and password is stored in database
			    if(name.equals(this.username) && password.equals(this.password)) 
			    {
			    	System.out.println("Login Successfully");
				    if(username.contains("@admin"))
				    {
				    	IViewAdmin viewadmin = new ViewAdmin();
						viewadmin.setPresenter(new PresenterAdmin(viewadmin,new ModelAdmin()));
				    }
				    else
				    {
						IViewCustomer view = new ViewCustomer(username);
						view.setPresenter(new PresenterCustomer(view,new ModelCustomer()));
				    }
			    	result = "Thank You!";
				   break;
			    }
		  	}
			return result;
		}
		
		//Sign up the Account 
		public String signup(String username, String password) throws SQLException 
		{

			Statement statementObject1 = ModelLogin.connection();
			
			String value1 = check_username(username);
			if("fail" == value1)
			{
				System.out.println("This UserName is alreaty Used");
				return "fail";
			}
			
			String value2 = check_password(password);
			if("Password is valid" != value2)
			{
				System.out.println("Password Not Valid : \n* Password must be 8 to 15 characters \n* At least one Capital letter "
						+ "\n* At least one small letter \n* At least one Number \n* At least one Special character");
				return "fail";
			}
		
				statementObject1.executeUpdate("insert into login values ('"+ username + "','" + password+"')");
				return "success";
		}

		
		private String check_username(String username) throws SQLException
		{
			Statement statementObject1 = ModelLogin.connection();
			
			ResultSet resultSetObject = statementObject1.executeQuery("select User_Name from login");
			
			while(resultSetObject.next())
			{
				if( username.equals(resultSetObject.getString("User_Name").toString()) )
				{
					return "fail";
				}
			}
			return "success";
		}

		private String check_password(String password) 
		{
			int length = password.length();
			
			if(length >= 8 && length <= 15)
			{
				int small_alphabet = 0, capital_alphabet = 0;
				int numeric = 0, special_character = 0;
				
				for(int i=0;i<length;i++)
				{
					char ch = password.charAt(i);
					
					if(ch>='a' && ch<='z')
						small_alphabet++;
					else if(ch>='A' && ch<='Z')
						capital_alphabet++;
					else if(ch>='0' && ch<='9')
						numeric++;
					else
						special_character++;
				}
				if(small_alphabet>=1 && capital_alphabet>=1 && numeric>=1 && special_character>=1)
					return "Password is valid";
				else
					return "Password Not Valid";
			}
			else
				return "Password Not Valid";
		}

		//Delete exist account 
		public String delete_account(String username, String password) throws SQLException 
		{
			Statement statementObject1 = ModelLogin.connection();
			
			ResultSet resultSetObject = statementObject1.executeQuery("select * from login");
			
			String result = "false";
			while(resultSetObject.next())
			{
				String name  = resultSetObject.getString("User_Name");
				String pass = resultSetObject.getString("Password");
				
				//Username and password is correct means delete account
			    if(name.equals(username) && pass.equals(password))
			    {
					Connection connectionObject2 = DriverManager.getConnection("jdbc:sqlserver://106.51.1.63; database = {fresher_ecom_task}","ecomfresher","Change@Fresher");
					Statement statementObject2 = connectionObject2.createStatement();
					statementObject2.executeUpdate("DELETE FROM login WHERE User_Name ='"+ username +"' AND Password ='"+ password +"'");
					result = "true";break;
			    }
			}
			return result;
		}
}