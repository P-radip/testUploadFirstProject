package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDetails;


@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		processRequest(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}


	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{   




		int choice = Integer.parseInt(request.getParameter("choice"));
		switch (choice) {
		case 1: {
			System.out.println("This is insert case");
			//This is insert operation.
			String name = request.getParameter("Uname");
			String email = request.getParameter("Uemail");
			String number =request.getParameter("number");
			String password=request.getParameter("password");
			
			/*System.out.println("Username is " +name);
		System.out.println("EmailId is "+email);
		System.out.println("Contact Number is "+number);
			 */

			String driver = "com.mysql.jdbc.Driver";
			String	url = "jdbc:mysql://localhost:3306/academicsmaster";
			String	username = "root";
			String	passwordForDb = "1818pvk";
			try
			{
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url,username,passwordForDb);
				String sql = "INSERT INTO userdetails ( name, email_id,contact,password )"
						+ "   VALUES"
						+ "   (?,?,?,?);";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, name);
				ps.setString(2, email);
				ps.setString(3, number);
				ps.setString(4, password);



				int updatedRows = ps.executeUpdate();
				System.out.println("updated rows are:- "+updatedRows);

			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("Driver class is not present");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println("SQL exception generation");
			}
		}
		break;

		case 2:
			//This is read operation.//SELECT * FROM tables
			System.out.println("This is read case");
			//This is insert operation.
			/*
			 * String name = request.getParameter("Uname"); String email =
			 * request.getParameter("Uemail"); String number
			 * =request.getParameter("number"); request.setAttribute("name",name);
			 * request.setAttribute("number",number); request.setAttribute("email",email);
			 * request.getRequestDispatcher("/showData.jsp").forward(request, response);
			 * 
			 */
			UserDetails details = new UserDetails();
			String driver = "com.mysql.jdbc.Driver";
			String	url = "jdbc:mysql://localhost:3306/academicsmaster";
			String	username = "root";
			String	passwordForDb = "1818pvk";
			
			
			try
			{
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url,username,passwordForDb);
				String sql = " SELECT * FROM userdetails ";
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs =ps.executeQuery();
				while(rs.next())
				{
				    details.setUserId(rs.getInt("u_id"));  
					details.setName(rs.getString("name"));            
					details.setEmailId(rs.getString("email_id"));
					details.setContact(rs.getString("contact"));
				    //System.out.println("Values are: "+details);
				    
				    String user =("user details are "+details);
				    request.setAttribute("user",user);
				    request.getRequestDispatcher("/showData.jsp").forward(request, response);
				    
				}
				
				
			}
			catch(ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("Driver class is not present");
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				System.out.println("SQL exception generation");
			}




			break;
		default:
			System.out.println("This is default case");
			
			break;
		}
	}


}


