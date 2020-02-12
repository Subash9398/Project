package Customer.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Login.model.ModelLogin;

public class ModelCustomer implements IModelCustomer
{
	public String buy(int product_Id, int givenQuantity, int givenPrice,String customer) throws SQLException 
	{
		int price = 0, quantity = 0;

		 Statement statementObject1 = ModelLogin.connection();
		 ResultSet ss = statementObject1.executeQuery("select * from ProductsDetails where S_No=" + product_Id);
		
		while (ss.next()) 
		{
			price = Integer.parseInt(ss.getString("Price").toString());
			quantity = Integer.parseInt(ss.getString("Qty").toString());
		}

		if (givenPrice >= price * givenQuantity)
		{
			if (quantity >= givenQuantity) 
			{
				quantity = quantity - givenQuantity;
				statementObject1.executeUpdate("UPDATE ProductsDetails SET Qty =" + quantity + " WHERE S_No=" + product_Id);
				statementObject1.executeUpdate("delete from cart where S_No=" + product_Id+" AND Customer_Name='"+customer+"'");
				return "Buy product successfully "+(givenPrice-(price*givenQuantity))+" refunded";
			} 
			else
				return "Only " + quantity + " are avilable";
		} 
		else
			return  "Low balance "+(price-(givenPrice*givenQuantity))+" amount needed";
	}

	public String addcart(int product_id, String Customer_name) throws SQLException 
	{
		String price = null;
		String Category_Name = null;
		String Product_Name = null;
		String Product_Description = null;
		Statement statementObject1 = ModelLogin.connection();
		ResultSet ss = statementObject1.executeQuery("select * from ProductsDetails where S_No=" + product_id);
		
		while (ss.next()) 
		{
			price = ss.getString("Price").toString();
			Category_Name = ss.getString("Category_Name").toString();
			Product_Name = ss.getString("Product_Name").toString();
			Product_Description = ss.getString("Product_Description").toString();
		}
		
		statementObject1.execute("insert into cart values (" + product_id + ",'" + Customer_name + "','" +  Product_Name + "','"
				+ Category_Name + "','" + Product_Description + "'," + price + ")");
		return "Product added successfully";
		
	}

	public Object showcart(String Customer_Name) throws SQLException 
	{
		Statement statementObject1 = ModelLogin.connection();
		ResultSet ss = statementObject1.executeQuery("select * from cart where Customer_name='" + Customer_Name + "'");
		return ss;
	}

	public String removecart(int S_No,String customer) throws SQLException 
	{
		Statement statementObject1 = ModelLogin.connection();
		statementObject1.executeUpdate("delete from cart where S_No=" + S_No+" AND Customer_Name='"+customer+"'");
		return "product removed sucessfully";
	}
	
}
