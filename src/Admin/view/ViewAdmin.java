package Admin.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import Admin.presenter.IPresenterAdmin;
import Login.model.ModelLogin;
import Login.presenter.PresenterLogin;
import Login.view.ViewLogin;
import list.model.ModelList;
import list.presenter.PresenterList;
import list.view.IViewList;
import list.view.ViewList;

public class ViewAdmin implements IViewAdmin
{
	private IPresenterAdmin presenteradmin; 
	
	public void setPresenter(IPresenterAdmin prese) throws SQLException 
	{
		this.presenteradmin = prese;
		showdetails();
	}
	
	
	// show details
	void showdetails() throws SQLException
	{
        Object scannerObject= ViewLogin.scannerObject();

		while(true)
		{
			System.out.println("\nMain Menu :");
			System.out.println("Add products Press ---> 1");
			System.out.println("Remove products Press ---> 2");
			System.out.println("View products Press ---> 3");
			System.out.println("logout Press ---> 4");
		int option = ((Scanner) scannerObject).nextInt();
		switch(option)
		{
			case 1:addproducts();break;
			case 2:removeproducts();break;
			case 3:viewproducts();break;
			case 4:
			{
				ViewLogin view = new ViewLogin();
				view.setPresenter(new PresenterLogin(view,new ModelLogin()));
			}
			default :continue;
		}
		}
	}
	public String isDigit()
	{
		int count=0;
		 Object scannerObject= ViewLogin.scannerObject();
		 String value=((Scanner) scannerObject).nextLine();
		 for(int i=0;i<value.length();i++)
		 {
		if(Character.isDigit(value.charAt(i)))
		count++;
		 }
		 if(count==value.length())
		  return value;
		 System.out.println("Please entre a digit");
		 isDigit();
		 return "";
	}
	public ArrayList categoryList() throws SQLException
	{
		return presenteradmin.categoryList();
	}
	public String addcategory(String categoryname) throws SQLException
	{
		return presenteradmin.addcategory(categoryname);
	}
	// add products
	public void addproducts() throws SQLException
	{
        Object scannerObject= ViewLogin.scannerObject();
        int categoryListS_No=0;
		ArrayList<String> arrayListObject= new ArrayList<String>();
		ArrayList category=categoryList();
		System.out.println("\ncategory List");
		while(true)
		{
			for(int i=0,j=1;i<category.size();i++,j++)
			{
				System.out.println(j+" "+category.get(i).toString());
		    }
		while(true) 
		{
			System.out.println("Please enter the category press ---> 1 \nAdd new category press ---> 2 \nMain menu press ---> 3");
			int value=((Scanner) scannerObject).nextInt();
			switch(value)
			{
			case 1:
				System.out.println("please select the Category No if you interest");
				categoryListS_No=((Scanner) scannerObject).nextInt();
				 
					Object scannerObjectone= ViewLogin.scannerObject();
					System.out.println("Enter the Product_Name");
					arrayListObject.add(((Scanner) scannerObjectone).nextLine());
					arrayListObject.add(category.get(categoryListS_No-1).toString());
					System.out.println("Enter the Product_Description");
					arrayListObject.add(((Scanner) scannerObjectone).nextLine());
					System.out.println("Enter the Qty");
					arrayListObject.add(isDigit());
					System.out.println("Enter the price");
					arrayListObject.add(isDigit());
					boolean flag=true;
					
					while(flag)
					{
					System.out.println("Please select option \nPrint Press ---> 1\nStore Press ---> 2 \nRe-enter Press ---> 3 \nMainmenu Press ---> 4 ");
					int data = ((Scanner) scannerObject).nextInt();
					
					switch(data)
					{
						case 1:
						{
						    System.out.println(arrayListObject);
						    break;
						}
						case 2:
							try 
							{
									System.out.println(presenteradmin.addproducts(arrayListObject));
							} 
							catch (SQLException e) 
							{
								e.printStackTrace();	
							}
							break;
					    case 3:
					    		arrayListObject.removeAll(arrayListObject);
						    	addproducts();break;
					    case 4:showdetails();	break;
					    default: flag=true;
				}
				flag=false;
				}break;
		case 2:
			 Object scannerObjecttwo= ViewLogin.scannerObject();
			System.out.println("Please enter the category name");
			System.out.println(presenteradmin.addcategory(((Scanner) scannerObjecttwo).nextLine()));
			addproducts();
			break;
		case 3:
			showdetails();
			break;
		default:
			{System.out.println("Please enter the valid S_No");
			continue;} 
		}}}
		
		}
	public void removeproducts() throws SQLException
	{
		while(true)
		{
		System.out.println("\nEnter the options: \nRemove category press ---> 1 \nRemove product press ---> 2 \nMain menu press ---> 3");
		 Object scannerObject= ViewLogin.scannerObject();
		switch(((Scanner) scannerObject).nextInt())
        {
        	case 1:
        		int categoryListS_No=0;
        		ArrayList<String> arrayListObject= new ArrayList<String>();
        		ArrayList category=categoryList();
        		System.out.println("\ncategory List");
        		for(int i=0,j=1;i<category.size();i++,j++)
        		{
        			System.out.println(j+" "+category.get(i).toString());
        		}
        		System.out.println("\nPlease Enter remove category no:");
        		categoryListS_No=((Scanner) scannerObject).nextInt();
        		System.out.println(presenteradmin.removecategory(category.get(categoryListS_No-1).toString()));
        		break;
        	case 2:
		        IViewList viewlist = new ViewList();
		        viewlist.setPresenter(new PresenterList(viewlist,new ModelList()));
		        int S_No = viewlist.checkTheS_No();
		        System.out.println(presenteradmin.removeProducts(S_No));
		        break;
        	case 3: showdetails(); break;
        	default:continue;
        }
		}
	}
	
	public void viewproducts() throws SQLException
	{
		IViewList viewlist = new ViewList();
		viewlist.setPresenter(new PresenterList(viewlist,new ModelList()));
		showdetails();
	}
	
}
