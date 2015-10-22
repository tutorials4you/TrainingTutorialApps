package com.point;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.assesment.dom.AssesmentDao;
@WebServlet("/UploadAssesment")
public class UploadAssesments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		request.getRequestDispatcher("header.jsp").include(request, response);
		request.getRequestDispatcher("link.jsp").include(request, response);
		request.getRequestDispatcher("rightMenu.jsp").include(request, response);

		HttpSession session=request.getSession(false);
		if(session==null){
			response.sendRedirect("index.html");
		}else{
		
			AssesmentDao reteriveQuestion =new AssesmentDao();
			request.setAttribute("courseId", reteriveQuestion.getAllCourseID());
			//System.out.println("COURSE ID  "+reteriveQuestion.getAllCourseID());
			request.getRequestDispatcher("uploadAssesments.jsp").include(request, response);
			String msg=(String)request.getAttribute("msg");
			if(msg!=null){
				out.print("<p>"+msg+"</p>");
			}
		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
