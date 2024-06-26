package scoremanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDAO;
import tool.Action;

public class StudentListAction extends Action {
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String entYearStr = request.getParameter("f1");
        String classNum = request.getParameter("f2");
        String isAttendStr = request.getParameter("f3");
        int entYear = 0;
        boolean isAttend = false;
        List<Student> students = null;
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        StudentDAO sDao = new StudentDAO();
        ClassNumDao cNumDao = new ClassNumDao();
        Map<String, String> errors = new HashMap<>();

        if (entYearStr != null && !entYearStr.isEmpty()) {
            entYear = Integer.parseInt(entYearStr);
        }

        if (isAttendStr != null && isAttendStr.equals("true")) {
            isAttend = true;
        }

        List<String> classNumList = cNumDao.filter(teacher.getSchool());

        if (entYear != 0 && !classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
        } else if (entYear != 0 && classNum.equals("0")) {
            students = sDao.filter(teacher.getSchool(), entYear, isAttend);
        } else if (entYear == 0 && (classNum == null || classNum.equals("0"))) {
            students = sDao.filter(teacher.getSchool(), isAttend);
        } else {
            errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
            request.setAttribute("errors", errors);
            students = sDao.filter(teacher.getSchool(), isAttend);
        }

        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        request.setAttribute("f1", entYear);
        request.setAttribute("f2", classNum);
        request.setAttribute("f3", isAttendStr);
        request.setAttribute("students", students);
        request.setAttribute("class_num_set", classNumList);
        request.setAttribute("ent_year_set", entYearSet);

        request.getRequestDispatcher("student_list.jsp").forward(request, response);
    }

    private String baseSql = "select * from student where school_cd=? ";

    private List<Student> postFilter(ResultSet rSet, String school) throws SQLException {
        List<Student> list = new ArrayList<>();
        while (rSet.next()) {
            Student student = new Student();
            student.setNo(rSet.getString("no"));
            student.setName(rSet.getString("name"));
            student.setEntYear(rSet.getInt("ent_year"));
            student.setClassNum(rSet.getString("class_num"));
            student.setAttend(rSet.getBoolean("is_attend"));
            student.setSchool(school);
            list.add(student);
        }
        return list;
    }

    public List<Student> filter(String school, int entYear, String classNum, boolean isAttend) throws SQLException {
        List<Student> list = new ArrayList<>();
        String condition = "and ent_year=? and class_num=? ";
        String order = "order by no asc ";
        String conditionIsAttend = isAttend ? "and is_attend=true " : "";

        String sql = baseSql + condition + conditionIsAttend + order;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, school);
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        }
        return list;
    }

    public List<Student> filter(String school, int entYear, boolean isAttend) throws SQLException {
        List<Student> list = new ArrayList<>();
        String condition = "and ent_year=? ";
        String order = "order by no asc ";
        String conditionIsAttend = isAttend ? "and is_attend=true " : "";

        String sql = baseSql + condition + conditionIsAttend + order;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, school);
            statement.setInt(2, entYear);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        }
        return list;
    }

    public List<Student> filter(String school, boolean isAttend) throws SQLException {
        List<Student> list = new ArrayList<>();
        String condition = "";
        String order = "order by no asc ";
        String conditionIsAttend = isAttend ? "and is_attend=true " : "";

        String sql = baseSql + condition + conditionIsAttend + order;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, school);
            try (ResultSet rSet = statement.executeQuery()) {
                list = postFilter(rSet, school);
            }
        }
        return list;
    }

    public Student get(String no) throws SQLException {
        Student student = new Student();
        String sql = "select * from student where no=?";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, no);
            try (ResultSet rSet = statement.executeQuery()) {
                if (rSet.next()) {
                    student.setNo(rSet.getString("no"));
                    student.setName(rSet.getString("name"));
                    student.setEntYear(rSet.getInt("ent_year"));
                    student.setClassNum(rSet.getString("class_num"));
                    student.setAttend(rSet.getBoolean("is_attend"));
                    student.setSchool(rSet.getString("school_cd"));
                } else {
                    student = null;
                }
            }
        }
        return student;
    }

    public boolean save(Student student) throws SQLException {
        int count = 0;
        Student old = get(student.getNo());
        String sql;

        if (old == null) {
            sql = "insert into student(no, name, ent_year, class_num, is_attend, school_cd) values(?,?,?,?,?,?)";
        } else {
            sql = "update student set name=?, ent_year=?, class_num=?, is_attend=? where no=?";
        }

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, student.getNo());
            statement.setString(2, student.getName());
            statement.setInt(3, student.getEntYear());
            statement.setString(4, student.getClassNum());
            statement.setBoolean(5, student.isAttend());
            statement.setString(6, student.getSchool().getCd());
            count = statement.executeUpdate();
        }
        return count > 0;
    }
}
