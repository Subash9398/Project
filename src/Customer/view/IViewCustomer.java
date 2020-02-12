package Customer.view;

import java.sql.SQLException;

import Customer.presenter.IPresenterCustomer;

public interface IViewCustomer 
{
	public int checkTheS_No();
	void setPresenter(IPresenterCustomer presenetr) throws SQLException;
	void buy(int product_Id) throws SQLException;
	public void addToCart(int product_id) throws SQLException;
	public void cartdetails() throws SQLException;
}

