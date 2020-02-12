package list.model;

import java.sql.Statement;

import Login.model.ModelLogin;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ModelList implements IModelList 
{

	 public Object category() throws SQLException
	 {
		Statement statementObject1 = ModelLogin.connection();
		ResultSet resultSetObject = statementObject1.executeQuery("select * from Category_List");
		return resultSetObject;
	 }
	 

	 public Object productlist(String i) throws SQLException 
	 {
		Statement statementObject1 = ModelLogin.connection();
		ResultSet resultSetObject = statementObject1.executeQuery("select * from ProductsDetails where Category_Name='"+i+"'");
		return resultSetObject;
	 }
}