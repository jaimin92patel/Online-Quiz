// CIN-304393575 NAME: JAIMIN PATEL
package quizPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import quizPackage.Questions;

@WebServlet("/AddRemoveQuestions")
public class AddRemoveQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		

		ArrayList<Questions> entries = new ArrayList<Questions>();
		Connection c = null;

		try {
			String username = "cs320stu163";
			String password = "!#pWw.D#";
			String host = "cs3.calstatela.edu";
			String port = "3306";
			String dbName = "cs320stu163";

			String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

			c = DriverManager.getConnection(url, username, password);
			Statement stmt = c.createStatement();

			if (request.getParameter("delete") != null) {
			
				stmt.executeUpdate("DELETE from quiz WHERE id="+ request.getParameter("delete"));
			}
			
			
			ResultSet rs = stmt.executeQuery("select * from quiz");

			while (rs.next()) {
				Questions entry = new Questions(rs.getInt("id"),
						rs.getString("question"),
						rs.getString("answer"));
				entries.add(entry);
			}

		} 
		catch (SQLException e) {
			throw new ServletException(e);
		}
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}

		request.setAttribute("entries", entries);
		request.getRequestDispatcher("AddRemoveQuestions.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		int id; 
		id=0;
		String que = request.getParameter("que");
		String ans;
		
		//To check true or false
		if (request.getParameter("true") != null) {
			ans = "TRUE";
		} else {
			ans = "FALSE";
		}
		
		//Database Connection
		Connection c = null;
		try {
			String username = "cs320stu163";
			String password = "!#pWw.D#";
			String host = "cs3.calstatela.edu";
			String port = "3306";
			String dbName = "cs320stu163";

			String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;

			String sql = "insert into quiz (id,question, answer) values (? ,?, ?)";

			// Update fields
			c = DriverManager.getConnection(url, username, password);
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, que);
			pstmt.setString(3, ans);
			pstmt.executeUpdate();
			
		} 
		catch (SQLException e) {
			throw new ServletException(e);
		}
		finally {
			try {
				if (c != null)
					c.close();
			} catch (SQLException e) {
				throw new ServletException(e);
			}
		}
		
		response.sendRedirect("AddRemoveQuestions");
	}
}
