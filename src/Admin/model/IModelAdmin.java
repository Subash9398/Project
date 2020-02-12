package Admin.model;

import java.sql.SQLException;
import java.util.ArrayList;

public interface IModelAdmin 
{
	public String adddetails(Object details) throws SQLException;
	public String removefromtable(int S_No) throws SQLException;
	public ArrayList categoryList() throws SQLException;
	public String addcategory(String categoryname) throws SQLException;
	public String removecategory(String categoryName) throws SQLException;
}
