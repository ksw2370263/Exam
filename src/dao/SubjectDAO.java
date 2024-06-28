package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Subject;

public class SubjectDAO extends DAO {

    public List<Subject> search(String keyword3) throws Exception {
        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        // SQLクエリを修正して LOWER 関数を追加
        PreparedStatement st = con.prepareStatement(
            "SELECT * FROM subject WHERE LOWER(name) LIKE LOWER(?)");
        st.setString(1, "%" + keyword3 + "%");

        // デバッグログ
        System.out.println("Executing query: SELECT * FROM subject WHERE LOWER(name) LIKE LOWER('%" + keyword3 + "%'");

        ResultSet rs = st.executeQuery();

        // デバッグログ: クエリの実行結果を確認
        int count = 0;
        while (rs.next()) {
            count++;
            Subject p = new Subject();
            p.setCd(rs.getString("CD"));
            p.setSchool_CD(rs.getString("SCHOOL_CD"));
            p.setName(rs.getString("NAME"));
            list.add(p);
            // 各レコードの内容をログ出力
            System.out.println("Found student: " + p.getName() + ", " + p.getSchool_CD() + ", " + p.getCd());
        }

        // クエリ結果の総数をログ出力
        System.out.println("Total number of subjects found: " + count);

        st.close();
        con.close();
        return list;
    }

    public List<Subject> getAllStudents() throws Exception {
        List<Subject> list = new ArrayList<>();

        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement("SELECT * FROM subject");

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            Subject p = new Subject();
            p.setCd(rs.getString("CD"));
            p.setSchool_CD(rs.getString("SCHOOL_CD"));
            p.setName(rs.getString("NAME"));
            list.add(p);
        }

        st.close();
        con.close();
        return list;
    }

    public int insert(Subject subject) throws Exception {
        Connection con = getConnection();

        PreparedStatement st = con.prepareStatement(
            "INSERT INTO STUDENT (CD,SCHOOL_CD,NAME) VALUES (?, ?, ?)");
        st.setString(1, subject.getCd());
        st.setString(2, subject.getSchool_CD());
        st.setString(3, subject.getName());

        // デバッグログ
        System.out.println("Inserting subject: " + subject.getCd() + ", " + subject.getSchool_CD() + ", " + subject.getName());

        int line = st.executeUpdate();

        st.close();
        con.close();
        return line;
    }
}
