package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginValidation
 */
@WebServlet("/LoginValidation")
public class LoginValidation extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static HashMap<String, String> userMap = new HashMap<String, String>();
	private static File file = new File("C:\\temp\\userData.txt");
	
	
	public void init(ServletConfig config) throws ServletException { 
		super.init(config);
		
		try {
			//if file does not exist, creates new file and writes the heading
			if (!file.exists() && !file.isDirectory()) {
				BufferedWriter writer = new BufferedWriter(new FileWriter(file));
				file.createNewFile();
				writer.write("userName, password");
				writer.newLine();
				
				writer.close();
			}
			//read from file for users and respective passwords
			else {
				readInfo();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void readInfo () {
		try {
		BufferedReader rdr = new BufferedReader(new FileReader(file));
		
		String line = rdr.readLine();
		while ((line = rdr.readLine()) != null) {
			String[] lineArr = line.split(", "); 
			
			userMap.put(lineArr[0].trim(), lineArr[1].trim());
		}
		
		rdr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginValidation() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	private boolean validateUser (String userName, String password) {
		String mapPassword;
		if ((mapPassword = userMap.get(userName)) != null) {
			return mapPassword.equals(password);
		}
		return false;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//get parameters from request object
		PrintWriter out = response.getWriter();
		
		String userName = request.getParameter("userName").trim();
		String password = request.getParameter("password").trim();
		
		//check for null and empty values
		if (userName == null || password == null || 
				password.equals("") || userName.equals("")) {
			out.println("Please enter both username and password. </br> </br>");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
			requestDispatcher.include(request, response);
		}
		//check for correct userName and password
		else if ( validateUser(userName, password) ) {
			HttpSession session = request.getSession();
			session.setAttribute("user", userName);
			
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/CreateStudent");
			requestDispatcher.forward(request, response);
		}
		//if wrong username or password
		else {
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.html");
			requestDispatcher.include(request, response);
			out.println("Wrong username or password. <br/><br/>");
			out.println("<form action='DirectUser' method='POST'>"
					+ "<input type='submit' value='Create User'>"
					+ "</form>");
		}
	}

}
