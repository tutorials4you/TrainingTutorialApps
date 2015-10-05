package com.course.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.course.dao.CourseDao;
import com.course.model.CourseSecond;

public class CourseController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private static String INSERT_OR_EDIT = "/user.jsp";
    private static String INSERT_OR_EDIT = "/editCourseDetails.jsp";
    private static String PASS_COURSE = "/indexToPass.jsp";
    private static String LIST_COURSES = "/courseDetails.jsp";
    private static String LAUNCH_COURSE = "/launchCourse.jsp";
   // private static String INSERT_OR_EDIT_NORMAL_USER = "/editCourse.jsp";
   // private static String PASS_COURSE_NORMAL_USER = "/indexToPass.jsp";
   // private static String LIST_COURSES_NORMAL_USER = "/courseDetails.jsp";
    private static String LAUNCH_COURSE_NORMAL_USER = "/launchCourseNormalUser.jsp";
    
    private CourseDao dao;

    public CourseController() {
        super();
        dao = new CourseDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        HttpSession session = request.getSession(true);
        String userRole = session.getAttribute("userRole").toString();
        String action = request.getParameter("action");
        System.out.println(action);
        if (action.equalsIgnoreCase("delete")&&userRole.equals("admin")){
            int cid = Integer.parseInt(request.getParameter("cid"));
            dao.deleteCourse(cid);
            forward = LIST_COURSES;
            request.setAttribute("courses", dao.getAllCourses()); 
        } else if (action.equalsIgnoreCase("edit")&&userRole.equals("admin")){
            forward = INSERT_OR_EDIT;
            int cid = Integer.parseInt(request.getParameter("cid"));
            CourseSecond course = dao.getCourseById(cid);
            request.setAttribute("course", course);
        } else if (action.equalsIgnoreCase("listOfCourse")&&(userRole.equals("admin"))){
            forward = LIST_COURSES;
            request.setAttribute("courses", dao.getAllCourses());
            System.out.println(dao.getAllCourses());
        }else if (action.equalsIgnoreCase("launchCourse")&&(userRole.equals("admin"))){
            forward = LAUNCH_COURSE;
            request.setAttribute("courses", dao.getAllCourses());
            System.out.println(dao.getAllCourses());
        }else if (action.equalsIgnoreCase("launchCourse")&&(userRole.equals("Test Manager")||userRole.equals("Test Analyst")||userRole.equals("Team Lead"))){
            forward = LAUNCH_COURSE_NORMAL_USER;
            request.setAttribute("courses", dao.getAllCorsesforNormalUser(userRole));
        }else if (action.equalsIgnoreCase("launch")){
            String cid = request.getParameter("cid");
            System.out.println("**********"+action);
            forward = PASS_COURSE+"?ver="+cid;
            session.setAttribute("cid",cid);  
        }else if (action.equalsIgnoreCase("launchNomal")&&userRole.equals("Manager")||userRole.equals("Test Analyst")||userRole.equals("Team Lead")){
            String cid = request.getParameter("cid");
            System.out.println("**********"+action);
            forward = PASS_COURSE+"?ver="+cid;
            session.setAttribute("cid",cid);  
        } else {
            forward = INSERT_OR_EDIT;
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CourseSecond course = new CourseSecond();
        course.setCid(request.getParameter("cid"));
        course.setCname(request.getParameter("cname"));
        course.setCauthor(request.getParameter("cauthor"));
        course.setCmin(request.getParameter("minDuration"));
        course.setTlEntrylevel(request.getParameter("atlel"));
        course.setTlpo(request.getParameter("atlpo"));
        course.setTlad(request.getParameter("atl_ad"));
        course.setTlas(request.getParameter("atlas"));
        course.setTmel(request.getParameter("atmel"));
        course.setTmpo(request.getParameter("atmpo"));
        course.setTmad(request.getParameter("atm_ad"));
        course.setTmas(request.getParameter("atmas"));
        course.setMtlEntrylevel(request.getParameter("mEntrylevel"));
        course.setMtlpo(request.getParameter("mtlpo"));
        course.setMtlad(request.getParameter("mtl_ad"));
        course.setMtlas(request.getParameter("mtlas"));
        course.setMtmel(request.getParameter("mtmel"));
        course.setMtmpo(request.getParameter("mtmpo"));
        course.setMtmad(request.getParameter("mtm_ad"));
        course.setMtmas(request.getParameter("mtmas"));
        course.setDm(request.getParameter("dm"));
        String courseId =request.getParameter("cid").toString();
        if(courseId.length()!=0)
        {
        	System.out.println("IF BLOCK OF POST");
            course.setCid(courseId);
            dao.checkCourse(course);
        }
        
    	RequestDispatcher view = request.getRequestDispatcher(LIST_COURSES);
        request.setAttribute("courses", dao.getAllCourses());
        view.forward(request, response);
    }
}
