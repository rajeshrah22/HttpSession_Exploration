package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CreateUser
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		File file = new File("C:\\temp\\userData.txt");
		BufferedWriter writer = null;
		
		if (!file.exists() && !file.isDirectory()) {
			writer = new BufferedWriter(new FileWriter(file));
			file.createNewFile();
			writer.write("userName, password");
			writer.newLine();
			writer.write(userName + ", " + password);
			writer.newLine();
			
			writer.close();
		}
		else {
			writer = new BufferedWriter(new FileWriter(file, true));
			writer.write(userName + ", " + password);
			writer.newLine();
			
			writer.close();
		}
		
		LoginValidation.readInfo();
		
		RequestDispatcher dp = request.getRequestDispatcher("index.html");
		dp.include(request, response);
		
		PrintWriter out = response.getWriter();
		
		out.println("<p>--------------</p>"
				+ "<p>New User created: " + userName + "</p>");
		
	}

}
