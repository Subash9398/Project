package Admin.presenter;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IPresenterAdmin 
{
	public String addproducts(Object details) throws SQLException;
	public String removeProducts(int S_No) throws SQLException;
	public ArrayList categoryList() throws SQLException;
	public String addcategory(String categoryname) throws SQLException;
	public String removecategory(String categoryName) throws SQLException;

}
