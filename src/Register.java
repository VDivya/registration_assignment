

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter(); 
		String firstName=request.getParameter("name");
		String lastName=request.getParameter("lname");
		String address1=request.getParameter("address1");
		String address2=request.getParameter("address2");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		String zip=request.getParameter("zip");
		//String country=request.getParameter("country");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/users","root","");
			PreparedStatement st=connection.prepareStatement("INSERT INTO `users`.`users` (`FirstName`, `LastName`, `Address1`, `Address2`, `City`, `State`, `Zip`) VALUES (?,?,?,?, ?,?,?);");
			st.setString(1,firstName);
			st.setString(2,lastName);
			st.setString(3,address1);
			st.setString(4,address2);
			st.setString(5,city);
			st.setString(6,state);
			//if(!(zip.contains(null)|| zip.contains(" ")))
			st.setString(7,zip);
			//st.setString(8,country);
			int i=st.executeUpdate();
			if(i>0)
				request.getRequestDispatcher("/confirmation.jsp").forward(request, response);
			else
				out.println("Registration Failed. Please try Again");
			
			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
