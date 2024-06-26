package tool;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            String path = request.getServletPath().substring(1);
            String name = path.replace(".action", "Action").replace('/', '.');
            System.out.println("★ servlet path -> " + request.getServletPath());
            System.out.println("★ class name -> " + name);
            // クラス名をログに出力
            System.out.println("Attempting to load class: " + name);
            Action action = (Action) Class.forName(name).getDeclaredConstructor().newInstance();
            String url = action.execute(request, response);
            request.getRequestDispatcher(url).forward(request, response);
        } catch (ClassNotFoundException e) {
            out.println("Class not found: " + e.getMessage());
            e.printStackTrace(out);
            System.out.println("Class not found: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
