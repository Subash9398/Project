package Login.presenter;

import java.sql.SQLException;

import Login.model.IModelLogin;
import Login.view.IViewLogin;

public class PresenterLogin implements IPresenterLogin 
{
	private IModelLogin modellogin;
	private IViewLogin viewlogin;

	public PresenterLogin(IViewLogin view, IModelLogin model)
	{
		this.modellogin = model;
		this.viewlogin = view;
	}
	
	//Pass method to call modellogin interface method of set with argument of Username and password
	public void pass(String username,String password)
	{
		modellogin.set(username,password);
	}
	
	//login method to call modellogin interface method of login 
	public void login() 
	{
	   try 
	   {
		String result =  modellogin.login();
		viewlogin.updateStatusLabel(result);
	   }
	   catch (Exception e)
	   {
		e.printStackTrace();
	   }
	}

	//signup method to call modellogin interface method of signup
	public String signup(String username, String password) throws SQLException 
	{
		return modellogin.signup(username,password);
	}
	
	//delete_account method to call modellogin interface method of delete_account
	public String delete_account(String username, String password) throws SQLException 
	{
		String result = modellogin.delete_account(username,password);
		return result;
	}
}