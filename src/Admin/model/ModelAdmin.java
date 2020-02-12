package Admin.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Login.model.ModelLogin;

public class ModelAdmin implements IModelAdmin
{
	public String addcategory(String categoryname) throws SQLException
	{
		Statement statementObject1 = ModelLogin.connection();
		statementObject1.executeUpdate("insert into Category_List values ('"+categoryname+"')");
		
		return "category added sucessfully";
	}
	public String removecategory(String categoryName) throws SQLException
	{
		Statement statementObject1 = ModelLogin.connection();
		statementObject1.executeUpdate("delete from Category_List where Category_Name='"+categoryName+"'");
		statementObject1.executeUpdate("delete from ProductsDetails where Category_Name='"+categoryName+"'");
		return "Category removed sucessfully";
	}	
	public ArrayList categoryList() throws SQLException
	{
		ArrayList category=new ArrayList();
		Statement statementObject1 = ModelLogin.connection();
		ResultSet resultSetObject = statementObject1.executeQuery("select * from Category_List");
		
		while (resultSetObject.next())
		{
	     category.add(resultSetObject.getString("category_name"));
		}
		return category;
	}
	@SuppressWarnings("rawtypes")
	public String adddetails(Object details) throws SQLException
	{
		ArrayList arrayListObject = new ArrayList();
		arrayListObject = (ArrayList) details;
		
		Statement statementObject1 = ModelLogin.connection();
		statementObject1.execute("insert into ProductsDetails (Product_Name,Category_Name,Product_Description,Qty,Price) values ('"+arrayListObject.get(0).toString()+"','"+arrayListObject.get(1).toString()+"','"+arrayListObject.get(2).toString()+"',"+arrayListObject.get(3).toString()+","+arrayListObject.get(4).toString()+")");
		return "product added successfully";	
	}
	
	public String removefromtable(int S_No) throws SQLException
	{
		Statement statementObject1 = ModelLogin.connection();
		statementObject1.executeUpdate("delete from ProductsDetails where S_No="+S_No);
		return "Product removed successfully";		
	}
}
