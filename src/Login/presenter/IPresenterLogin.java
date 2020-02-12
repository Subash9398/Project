package Login.presenter;

import java.sql.SQLException;

public interface IPresenterLogin 
{
	void login();
	void pass(String username,String password);
	String signup(String username, String password) throws SQLException;
	String delete_account(String username, String password) throws SQLException;
}