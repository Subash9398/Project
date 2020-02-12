package list.view;

import java.sql.SQLException;

import list.presenter.IPresenterList;

public interface IViewList
{
	void setPresenter(IPresenterList prese) throws SQLException;
	public int checkTheS_No();
}
