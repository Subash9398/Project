package list.presenter;

import java.sql.SQLException;

import list.model.IModelList;
import list.view.IViewList;

public class PresenterList implements IPresenterList 
{
	private IModelList modellist;
	@SuppressWarnings("unused")
	private IViewList viewlist;

	//This constructor is used to initiate the view and model objects
	public PresenterList(IViewList view, IModelList model) 
	{
		this.modellist = model;
		this.viewlist = view;
	}
	
	//This method is used to view the category list
	public Object category() throws SQLException
	{ 
		return modellist.category();
	}
	
	//This method is used to view the product list
	public Object categoryS_No(String i) throws SQLException
	{
		return modellist.productlist(i);
	}
}
