package com.course.dao;

import com.course.model.CourseSecond;
import com.point.ConProvider;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDao {
    private Connection connection;
    private PreparedStatement ps = null;
    ResultSet rs = null;
    Statement statement = null;

    public void checkCourse(CourseSecond course) {
        try {
            try {
                this.connection = ConProvider.getConnection();
                PreparedStatement ps = this.connection.prepareStatement("select cname from COURSE_DETAILS_RECORD  where cid = ?");
                ps.setInt(1, course.getCid());
                this.rs = ps.executeQuery();
                if (this.rs.next()) {
                    this.updateCourse(course);
                }
            }
            catch (Exception ex) {
                System.out.println("Error in check() -->" + ex.getMessage());
                ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.ps, (Connection)this.connection);
            }
        }
        finally {
            ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.ps, (Connection)this.connection);
        }
    }

    public void deleteCourse(int cid) {
        try {
            try {
                this.connection = ConProvider.getConnection();
                this.ps = this.connection.prepareStatement("delete from COURSE_DETAILS_RECORD where cid=?");
                this.ps.setInt(1, cid);
                this.ps.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                ConProvider.cleanUp((Statement)this.ps, (Connection)this.connection);
            }
        }
        finally {
            ConProvider.cleanUp((Statement)this.ps, (Connection)this.connection);
        }
    }

    public void updateCourse(CourseSecond course) {
        try {
            try {
                this.connection = ConProvider.getConnection();
                this.ps = this.connection.prepareStatement("update COURSE_DETAILS_RECORD set cname=?, cminduration=?, cmaxduration=?, CAUTHOR=?, COURSEROLEID =?where cid=?");
                System.out.println("UPDATE COURSE" + course.getCname());
                this.ps.setString(1, course.getCname());
                this.ps.setString(2, course.getCmin());
                this.ps.setInt(3, course.getCmax());
                this.ps.setString(4, course.getCauthor());
                this.ps.setInt(6, course.getCid());
                this.ps.setString(5, course.getCourseRoleId());
                this.ps.executeUpdate();
            }
            catch (SQLException e) {
                e.printStackTrace();
                ConProvider.cleanUp((Statement)this.ps, (Connection)this.connection);
            }
        }
        finally {
            ConProvider.cleanUp((Statement)this.ps, (Connection)this.connection);
        }
    }

    public List<CourseSecond> getAllCourses() {
        ArrayList<CourseSecond> courses;
        courses = new ArrayList<CourseSecond>();
        try {
            try {
                this.connection = ConProvider.getConnection();
                this.statement = this.connection.createStatement();
                this.rs = this.statement.executeQuery("select * from COURSE_DETAILS_RECORD");
                while (this.rs.next()) {
                    CourseSecond course = new CourseSecond();
                    course.setCid(this.rs.getInt("CID"));
                    course.setCname(this.rs.getString("CNAME"));
                    course.setCauthor(this.rs.getString("CAUTHOR"));
                    course.setCfileName(this.rs.getString("COURSEFILENAME"));
                    course.setCmin(this.rs.getString("MIN_DURATION"));
                 //   course.set(this.rs.getDate("COURSEDATE"));

                    courses.add(course);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
            }
        }
        finally {
            ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
        }
        return courses;
    }

    public List<CourseSecond> getAllCorsesforNormalUser(String courseRoleName) {
        ArrayList<CourseSecond> courses;
        courses = new ArrayList<CourseSecond>();
        ResultSet rs = null;
        try {
            try {
                this.connection = ConProvider.getConnection();
                this.statement = this.connection.createStatement();
                if (courseRoleName.equals("Test Analyst")) {
                    rs = this.statement.executeQuery("select * from COURSE_DETAILS_RECORD where COURSEROLEID = 'Test Analyst'");
                } else if (courseRoleName.equals("Team Lead")) {
                    rs = this.statement.executeQuery("select * from COURSE_DETAILS_RECORD where COURSEROLEID in ('Team Lead','Test Analyst')");
                } else if (courseRoleName.equals("Test Manager")) {
                    rs = this.statement.executeQuery("select * from COURSE_DETAILS_RECORD ");
                }
                while (rs.next()) {
                    CourseSecond course = new CourseSecond();
                    course.setCid(rs.getInt("CID"));
                    course.setCname(rs.getString("CNAME"));
                    course.setCmin(rs.getString("CMINDURATION"));
                    course.setCmax(rs.getInt("CMAXDURATION"));
                    course.setCauthor(rs.getString("CAUTHOR"));
                    course.setCfileName(rs.getString("COURSEFILENAME"));
                    course.setCourseRoleId(rs.getString("COURSEROLEID"));
                    courses.add(course);
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                ConProvider.cleanUp((ResultSet)rs, (Statement)this.statement, (Connection)this.connection);
            }
        }
        finally {
            ConProvider.cleanUp((ResultSet)rs, (Statement)this.statement, (Connection)this.connection);
        }
        return courses;
    }

    public CourseSecond getCourseById(int cid) {
        CourseSecond course;
        course = new CourseSecond();
        try {
            try {
                this.connection = ConProvider.getConnection();
                PreparedStatement preparedStatement = this.connection.prepareStatement("select * from COURSE_DETAILS_RECORD where cid=?");
                preparedStatement.setInt(1, cid);
                ResultSet rs = preparedStatement.executeQuery();
                if (rs.next()) {
                    course.setCid(rs.getInt("CID"));
                    course.setCname(rs.getString("CNAME"));
                    course.setCauthor(rs.getString("CAUTHOR"));
                    course.setCmin(rs.getString("MIN_DURATION"));
                    course.setTlEntrylevel(rs.getString("A_TL_EL"));
                    course.setTlpo(rs.getString("A_TL_PO"));
                    course.setTlad(rs.getString("A_TL_AD"));
                    course.setTlas(rs.getString("A_TL_AS"));
                    course.setTmel(rs.getString("A_TM_EL"));
                    course.setTmpo(rs.getString("A_TM_PO"));
                    course.setTmad(rs.getString("A_TM_AD"));
                    course.setTmas(rs.getString("A_TM_AS"));
                    course.setMtlEntrylevel(rs.getString("M_TL_EL"));
                    course.setMtlpo(rs.getString("M_TL_PO"));
                    course.setMtlad(rs.getString("M_TL_AD"));
                    course.setMtlas(rs.getString("M_TL_AS"));
                    course.setMtmel(rs.getString("M_TM_EL"));
                    course.setMtmpo(rs.getString("M_TM_PO"));
                    course.setMtmad(rs.getString("M_TM_AD"));
                    course.setMtmas(rs.getString("M_TM_AS"));
                    course.setDm(rs.getString("DM"));



                }
            }
            catch (SQLException e) {
                e.printStackTrace();
                ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
            }
        }
        finally {
            ConProvider.cleanUp((ResultSet)this.rs, (Statement)this.statement, (Connection)this.connection);
        }
        return course;
    }
}