package Admin.view;

import java.sql.SQLException;
import java.util.ArrayList;

import Admin.presenter.IPresenterAdmin;

public interface IViewAdmin 
{
	public void setPresenter(IPresenterAdmin prese) throws SQLException;
	void addproducts() throws SQLException;
	void removeproducts() throws SQLException;
	void viewproducts() throws SQLException;
	public ArrayList categoryList() throws SQLException;
	public String addcategory(String categoryname) throws SQLException;
	public String isDigit();
}
