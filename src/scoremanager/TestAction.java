package scoremanager;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import dao.SubjectDAO;
import dao.TestDAO;
import tool.Action;

public class TestAction extends Action {
    public String execute(
            HttpServletRequest request, HttpServletResponse response
            ) throws Exception {
        HttpSession session = request.getSession();
        HttpSession session2 = request.getSession();

        String keyword = request.getParameter("keyword");
        String keyword2 = request.getParameter("keyword2");
        String keyword3 = request.getParameter("keyword3");
        if (keyword == null) keyword = "";
        if (keyword2 == null) keyword2 = "";
        if (keyword3 == null) keyword3 = "";

        TestDAO dao = new TestDAO();
        List<Student> list;

        SubjectDAO dao1 = new SubjectDAO();
        List<Subject> list1;

        // キーワードが空の場合は全件検索、それ以外は条件に基づいて検索
        if (keyword.isEmpty() && keyword2.isEmpty() && keyword3.isEmpty()) {
            list = dao.getAllStudents();
            list1 = dao1.getAllStudents();
        } else {
            list = dao.search(keyword, keyword2);
            list1 = dao1.search(keyword3);
        }

        session.setAttribute("list", list);
        session.setAttribute("list1", list1);
        session.setAttribute("keyword", keyword);
        session.setAttribute("keyword2", keyword2);
        session2.setAttribute("keyword3", keyword3);

        return "test.jsp";
    }
}
