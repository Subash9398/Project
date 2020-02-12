package list.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Login.view.ViewLogin;
import list.presenter.IPresenterList;

public class ViewList implements IViewList 
{
	private IPresenterList presenterlist;
	private ArrayList<String> productS_No = new ArrayList<String>();

	//This method is used to check the product S_No number
	//from customer and admin
	public int checkTheS_No() 
	{
        Object scannerObject = ViewLogin.scannerObject();
		System.out.println("Please enter the S_No if You want");
		int number = ((Scanner) scannerObject).nextInt();
		for (int i = 0; i < this.productS_No.size(); i++) 
		{
			if (number == (int)Integer.parseInt(this.productS_No.get(i).toString()))
			{
				return number;
			}
		}
		System.err.println("Please enter the correct S_No");
		checkTheS_No();
		return 0;
	}

	//This method is used to view the category list and product list
	public void category() throws SQLException 
	{
		try 
		{
			Object categoryLostObject = presenterlist.category();
			ArrayList<String> category_name = new ArrayList<String>();
			System.out.println("-----------------------");
			System.out.println("S_No " + "Category_Name ");
			System.out.println("-----------------------");
			int j=1;
			while (((ResultSet) categoryLostObject).next())
			{
				String Category_Name = ((ResultSet) categoryLostObject).getString("Category_Name");
				category_name.add(Category_Name);
				System.out.println(j + "    " + Category_Name);j++;
			}
			System.out.println("-----------------------");

			System.out.println("\nPlease select the S_No if you interest");
	        Object scannerObject= ViewLogin.scannerObject();
			int category = ((Scanner) scannerObject).nextInt();
			
			int count = 0;
			if (category_name.size() >= category && category > 0)
			{
				count++;
			}
			else 
			{
				System.err.println("Please select correct S_No");
				category();
			}
			
			if(count == 1)
			{
				Object productlistObject = presenterlist.categoryS_No(category_name.get(category - 1).toString());
				System.out.println("\nProduct Details :");
				System.out.println("-------------------------------------------------");
				System.out.println("S_No " + "Product_Name  " + "Product_Description  " + "Price ");
				System.out.println("-------------------------------------------------");

				while (((ResultSet) productlistObject).next()) 
				{
					String S_No = ((ResultSet) productlistObject).getString("S_No");
					String Product_Name = ((ResultSet) productlistObject).getString("Product_Name");
					String Product_Description = ((ResultSet) productlistObject).getString("Product_Description");
					String Price = ((ResultSet) productlistObject).getString("Price");
					System.out.println(S_No + "   " + Product_Name + "        " + Product_Description + "       " + Price);
					this.productS_No.add(S_No);
				}
				System.out.println("-------------------------------------------------");
			}
		}
		
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
	}
	
	//This method is set the presenter object from customer and admin class
	public void setPresenter(IPresenterList prese) throws SQLException
	{
		this.presenterlist = prese;
		category();
	}
}
