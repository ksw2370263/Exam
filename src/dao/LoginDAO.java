package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Login;

public class LoginDAO extends DAO{
	public Login search(String login, String password)
	throws Exception{
		Login Login=null;

		Connection con=getConnection();

		PreparedStatement st;
		st=con.prepareStatement("select * from login where login=? and password=?");
		st.setString(1, login);
		st.setString(2, password);
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			Login=new Login();
			Login.setId(rs.getInt("id"));
			Login.setLogin(rs.getString("login"));
			Login.setPassword(rs.getString("password"));
		}
		st.close();
		con.close();
		return Login;
	}
}