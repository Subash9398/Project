package Login.model;

import java.sql.SQLException;

public interface IModelLogin 
{
	void set(String username,String password);
	String login() throws Exception;
	String signup(String username, String password) throws SQLException;
	String delete_account(String username, String password) throws SQLException;
}