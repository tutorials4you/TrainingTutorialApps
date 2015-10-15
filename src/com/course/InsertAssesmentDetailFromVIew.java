package com.course;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.io.FilenameUtils;
import com.assesment.dom.ReteriveQuestion;
import com.assesment.dom.XmlConvertor;
import com.test.Assesment;

@WebServlet(value={"/InsertAssesmentDetail"})
@MultipartConfig(fileSizeThreshold=10485760, maxFileSize=52428800, maxRequestSize=104857600)
public class InsertAssesmentDetailFromVIew extends HttpServlet {
    int status,status2;
    String fileName;
    String location = "C://Users//IBM_ADMIN//Desktop//Upload";
    String fileName2 = null;
    String fileExtension = null;
    private static final long serialVersionUID = 1;

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        File fileSaveDir;
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        response.getWriter();
        String assesmentName = request.getParameter("assesmentName");
        String timeLimit = request.getParameter("timeLimit");
        String courseId = request.getParameter("courseId");
         for (Part part : request.getParts()) {
            this.fileName = this.getFileName(part);
            if (this.fileName == null || this.fileName.isEmpty()) continue;
            fileName2 = this.fileName;
            part.write(String.valueOf(this.location) + File.separator + fileName2);
        }
        fileExtension = FilenameUtils.getExtension((String)(String.valueOf(this.location) + fileName2));
        if (fileExtension.equals("xls")) {
            fileSaveDir = new File(this.location);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdirs();
            }     
            this.status=ReteriveQuestion.insertAssesmenDetails(assesmentName, timeLimit, courseId, fileName2);
            try {
				this.status2 = Assesment.insertQuestion(fileName2,courseId,assesmentName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        } 
        System.out.println("*********************************************");
        System.out.println("________" + assesmentName + "__________");
        System.out.println("________" + timeLimit + "__________");
        System.out.println("________" + courseId + "__________");
        System.out.println("________" + fileExtension + "__________");
        System.out.println("*********************************************");
           
        if (this.status > 0 && status2 > 0) {
            XmlConvertor.ConvertTOXml(fileName2);
            System.out.println("Mail Id "+fileName2);
            	//CourseDao dao = new CourseDao();
            	//request.setAttribute("courses", dao.getAllCourses());
            	out.println("<script type=\"text/javascript\">");
            	out.println("alert('FIle Uploaded Sycessfully');");
                out.println("</script>");
               // XmlConvertor.ConvertTOXml(fileName2);
        	 request.getRequestDispatcher("launchCourse.jsp").include(request, response);
            }
        }
    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= " + contentDisp);
        for (String token : contentDisp.split(";")) {
            if (!token.trim().startsWith("filename")) continue;
            return token.substring(token.indexOf("=") + 2, token.length() - 1);
        }
        return "";
    }
    
   
}