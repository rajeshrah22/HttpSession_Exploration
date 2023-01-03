package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SaveStudent
 */
@WebServlet("/SaveStudent")
public class SaveStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
//		
//		HttpSession session = request.getSession(true);
//		
//		String username = (String) session.getAttribute("user");
		
		
		String studentName = request.getParameter("studentName").trim();
		String studentAge =  request.getParameter("age").trim();
		
		Student student = new Student(studentName, studentAge);
		//session.setAttribute(username, student);
		
		//out.println(session.getAttribute(username));
		System.out.println(request.getParameter("studentName") + " " + request.getParameter("age"));
		System.out.println(student);
		
		//out.println(username);
	}

}
