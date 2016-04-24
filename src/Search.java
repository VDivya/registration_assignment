

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		          
		
		          
		try{  
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
		"jdbc:mysql://localhost:3306/users","root","");  
		              
		PreparedStatement ps=con.prepareStatement("select * from users order by CreatedOn DESC");  
		//ps.setInt(1,roll);  
		              
		out.print("<table width=50% border=1>");  
		out.print("<caption>Result:</caption>");  
		  
		ResultSet rs=ps.executeQuery();  
		              
		/* Printing column names */  
		ResultSetMetaData rsmd=rs.getMetaData();  
		int total=rsmd.getColumnCount();  
		out.print("<tr>");  
		for(int i=2;i<=total;i++)  
		{  
		out.print("<th>"+rsmd.getColumnName(i)+"</th>");  
		}  
		  
		out.print("</tr>");  
		              
		/* Printing result */  
		  
		while(rs.next())  
		{  
		out.print("<tr><td>"+rs.getString(2)+"</td><td>"+
		rs.getString(3)+"</td><td>"+rs.getString(4)+"</td><td>"+
				rs.getString(5)+"</td><td>"+ rs.getString(6)+"</td><td>"
						+ rs.getString(7)+"</td><td>" + rs.getString(8)+"</td><td>"+
						rs.getString(9)+"</td><td>"+rs.getString(10)+"</td></tr>");  
		                  
		}  
		  
		out.print("</table><br><br><br>");
		out.println("<p><a href='index.html'>Home</a></p>");
		
		              
		}catch (Exception e2) {e2.printStackTrace();}  
		          
		finally{out.close();}  
		  
	}

}
