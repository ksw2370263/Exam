package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import bean.Student;

public class StudentDAO extends DAO{
	public Student search(String no, String name)
	throws Exception{
		Student student=null;

		Connection con=getConnection();


		PreparedStatement st;
		st=con.prepareStatement("select * from student where no=? and name=?");
		st.setString(1, no);
		st.setString(2, name);
		ResultSet rs=st.executeQuery();

		while (rs.next()){
			student=new Student();
			student.setNo(rs.getString("no"));
			student.setName(rs.getString("name"));
		}
		st.close();
		con.close();
		return student;
	}
}