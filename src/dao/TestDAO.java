package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Student;

public class TestDAO extends DAO {

    public List<Student> search(String keyword, String keyword2) throws Exception {
        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        // SQLクエリを修正して LOWER 関数を追加
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM student WHERE LOWER(ent_Year) LIKE LOWER(?) AND class_Num = ?");
        st.setString(1, "%" + keyword + "%");
        st.setString(2, keyword2);

        // デバッグログ
        System.out.println("Executing query: SELECT * FROM student WHERE LOWER(ent_Year) LIKE LOWER('%" + keyword + "%') AND class_Num = '" + keyword2 + "'");

        ResultSet rs = st.executeQuery();

        // デバッグログ: クエリの実行結果を確認
        int count = 0;
        while (rs.next()) {
            count++;
            Student p = new Student();
            p.setEntYear(rs.getInt("ENT_YEAR"));
            p.setClassNum(rs.getString("CLASS_NUM"));
            p.setNo(rs.getString("NO"));
            p.setName(rs.getString("NAME"));
            list.add(p);
            // 各レコードの内容をログ出力
            System.out.println("Found student: " + p.getName() + ", " + p.getClassNum() + ", " + p.getNo());
        }

        // クエリ結果の総数をログ出力
        System.out.println("Total number of students found: " + count);

        st.close();
        con.close();
        return list;
    }

    public List<Student> getAllStudents() throws Exception {
        List<Student> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM student");

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Student p = new Student();
            p.setEntYear(rs.getInt("ENT_YEAR"));
            p.setClassNum(rs.getString("CLASS_NUM"));
            p.setNo(rs.getString("NO"));
            p.setName(rs.getString("NAME"));
            list.add(p);
        }

        st.close();
        con.close();
        return list;
    }

    public int insert(Student student) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO STUDENT (NO, NAME, ENT_YEAR, CLASS_NUM, IS_ATTEND, SCHOOL_CD) VALUES (?, ?, ?, ?, ?, ?)");
        st.setString(1, student.getNo());
        st.setString(2, student.getName());
        st.setInt(3, student.getEntYear());
        st.setString(4, student.getClassNum());
        st.setBoolean(5, student.isAttend());
        st.setString(6, student.getCD());

        // デバッグログ
        System.out.println("Inserting student: " + student.getNo() + ", " + student.getName() + ", " + student.getEntYear() + ", " + student.getClassNum() + ", " + student.isAttend() + ", " + student.getCD());

        int line = st.executeUpdate();

        st.close();
        con.close();
        return line;
    }
}
