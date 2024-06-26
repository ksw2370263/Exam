package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import dao.TestDAO;
import tool.Action;

public class TestAction extends Action{
	public String execute(
			HttpServletRequest request, HttpServletResponse response
			)throws Exception{
		HttpSession session=request.getSession();

		String keyword=request.getParameter("keyword");
		String keyword2=request.getParameter("keyword2");
		if(keyword==null)keyword="";

		TestDAO dao=new TestDAO();
		List<Student> list=dao.search(keyword,keyword2);

		session.setAttribute("list", list);

		return "test.jsp";
	}
}
