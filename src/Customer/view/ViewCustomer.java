package Customer.view;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Customer.presenter.IPresenterCustomer;
import Login.model.ModelLogin;
import Login.presenter.PresenterLogin;
import Login.view.ViewLogin;
import list.model.ModelList;
import list.presenter.PresenterList;
import list.view.IViewList;
import list.view.ViewList;

public class ViewCustomer implements IViewCustomer
{
	@SuppressWarnings("rawtypes")
	private ArrayList cartS_No=new ArrayList();
	private IPresenterCustomer presentercustomer; 
	private String username;
	private int number;
	
	public ViewCustomer(String username)
	{
		this.username = username;
	} 
	
	public int checkTheS_No() 
	{
        Object scannerObject = ViewLogin.scannerObject();
		this.number = ((Scanner) scannerObject).nextInt();
		for (int i = 0; i < this.cartS_No.size(); i++) 
		{
			if (number == (int)Integer.parseInt(this.cartS_No.get(i).toString()))
			{
				return this.number;
				
			}
		}
		System.err.println("Please enter the corrct S_No");
		checkTheS_No();
		return 0;
	}
	
	public void setPresenter(IPresenterCustomer presenetr) throws SQLException 
	{
		this.presentercustomer = presenetr;
		showdetails();
	}
	
	public void showdetails() throws SQLException
	{
        Object scannerObject = ViewLogin.scannerObject();
		System.out.println("\nMain Menu :");
		System.out.println("View product list Press ---> 1 \nView cart Press ---> 2 \nLogout Press ---> 3");
		switch(((Scanner) scannerObject).nextInt())
		{
			case 1: customer(); break; 
			case 2: cartdetails(); break; 
			case 3: {
						System.out.println("Thank You!\n");
						ViewLogin view = new ViewLogin();
						view.setPresenter(new PresenterLogin(view,new ModelLogin())); break;
			        }
			default : System.out.println("\nPlease Enter Valid Number");
					  showdetails();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void cartdetails() throws SQLException
	{   
        Object scannerObject = ViewLogin.scannerObject();
		String customer = this.username;int S_No=0;
		Object cart = presentercustomer.showcart(customer);
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("S_No "+"Product_Name "+"Category_name "+" Product_Description "+" Price");
		System.out.println("---------------------------------------------------------------------------");
		while(((ResultSet) cart).next())
		{       
			    String i=((ResultSet) cart).getString("S_No");
			    cartS_No.add(i);
		        System.out.print(i+"    ");
		        System.out.print(((ResultSet) cart).getString("Product_Name")+"       ");
		        System.out.print(((ResultSet) cart).getString("Category_Name")+"       ");
		        System.out.print(((ResultSet) cart).getString("Product_Description")+"    ");
		        System.out.print(((ResultSet) cart).getString("Price")+"  ");
		        System.out.println();
		 }
		 System.out.println("---------------------------------------------------------------------------");
		 while(true)
		 {
		 System.out.println("\nRemove from cart Press ---> 1\nBuy Press ---> 2\nMain Menu Press ---> 3");
		 
		 switch(((Scanner) scannerObject).nextInt())
		 {
			 case 1:{
					 System.out.println("Enter the S_No if you want to remove");
//					 System.out.println("\nMain Menu Press ---> 3");
//					 if(scannerObject.nextInt() == 3)
//					 {
//						 showdetails();
//					 }
					 S_No = checkTheS_No() ;
					 
					 System.out.println(presentercustomer.removecart(S_No,this.username)); break;
				    }
			 case 2:{
					 System.out.println("Enter the S_No if you want to buy");
//					 System.out.println("\nMain Menu Press ---> 3");
//					 if(scannerObject.nextInt() == 3)
//					 {
//						 showdetails();
//					 }
					 S_No = checkTheS_No() ;
					 
					 
					 buy(S_No);break;
					 }
			  case 3: 
					 showdetails();break;
			  default :continue;
		 }
	}  
	}
	
	public  void customer() throws SQLException
	{   
        System.out.println("\nThe following category products are avilable");
		IViewList viewlist = new ViewList();
		viewlist.setPresenter(new PresenterList(viewlist,new ModelList()));
		
		
	        Object scannerObject= ViewLogin.scannerObject();
			int product_id = viewlist.checkTheS_No();
			
			while(true)
			{
				System.out.println("\nOptions :");
				System.out.println("Buy Press ---> 1 \nAdd to cart Press ---> 2 \nMain Menu Press ---> 3");
			
				switch(((Scanner) scannerObject).nextInt())
				{
					case 1:buy( product_id);break;
					case 2:addToCart(product_id);break;
					case 3:showdetails();break;
					default:System.out.println("\nPlease Enter Valid Number");
			  		  		continue;
				}
			}
	}
	
	public  void buy(int product_Id) throws SQLException 
	{
	     Object scannerObject= ViewLogin.scannerObject();
		 System.out.println("\nBuy Page :");
		 System.out.println("Please enter the Quantity");
		 int quantity = ((Scanner) scannerObject).nextInt();
		 System.out.println("Enter the Amount");
		 int givenPrice = ((Scanner) scannerObject).nextInt();
		
		System.out.println(presentercustomer.buy(product_Id, quantity, givenPrice,this.username));
        System.out.println("Thank You "+this.username);
        showdetails();
	}
	
	public void addToCart(int product_id) throws SQLException
	{
		String customer = this.username;
		System.out.println(presentercustomer.addcart(product_id,customer));
		while(true)
		{
			System.out.println("\nIf you want to view \ncategoryList Press ---> 1 \nShow cart Press ---> 2 \nMain Menu Press ---> 3");
	        Object scannerObject = ViewLogin.scannerObject();

			switch(((Scanner) scannerObject).nextInt())
			{
				case 2: cartdetails();break;
				case 3: showdetails();break;
				default : System.out.println("\nPlease Enter Valid Number");
				  		  continue;
			}	
		}
	}
}