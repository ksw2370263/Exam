package scoremanager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Login;
import dao.LoginDAO;
import tool.Action;

public class LoginAction extends Action{
	public String execute(
	HttpServletRequest request, HttpServletResponse response
	)throws Exception{
		HttpSession session=request.getSession();

		String login=request.getParameter("login");
		String password=request.getParameter("password");
		LoginDAO dao=new LoginDAO();
		Login Login=dao.search(login, password);

		if(Login!=null){
			session.setAttribute("login", Login);
			return "base2.jsp";
		}
		return "login-error.jsp";
	}
}