package Customer.presenter;

import java.sql.SQLException;

public interface IPresenterCustomer 
{
	String buy(int product_Id,int quantity,int price,String username) throws SQLException;
	public String addcart(int product_id, String customer) throws SQLException;
	Object showcart(String customer) throws SQLException;
	public String removecart(int S_No,String customer) throws SQLException;
}

