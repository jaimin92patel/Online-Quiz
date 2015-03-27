// CIN-304393575 NAME: JAIMIN PATEL
package quizPackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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



@WebServlet("/Quiz")
public class Quiz extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new ServletException(e);
		}
		//Database Connection with user
		ArrayList<Questions> arrayList = new ArrayList<Questions>();
		Connection c = null;
        try
        {            
        	String username = "cs320stu163";
			String password = "!#pWw.D#";
			String host = "cs3.calstatela.edu";
			String port = "3306";
			String dbName = "cs320stu163";
			String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
        	
            c = DriverManager.getConnection( url, username, password );
            
            Statement stmt1 = c.createStatement();
            
            ResultSet rs = stmt1.executeQuery( "SELECT * FROM quiz ORDER BY RAND() LIMIT 5" );
            
            while(rs.next()) {
            	arrayList.add(new Questions(rs.getInt(1),rs.getString(2),rs.getString(3)));
            }
        }
        catch( SQLException e )  {
            throw new ServletException( e );
        }
        finally  {
        	try  {
                if( c != null ) c.close();
            }
            catch( SQLException e )  {
                throw new ServletException( e );
            }
        }
        getServletContext().setAttribute( "question", arrayList );		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		@SuppressWarnings("unchecked")
		ArrayList<Questions> arrayList = (ArrayList<Questions>)getServletContext().getAttribute("question");
		Questions que;
		que = (Questions) getServletContext().getAttribute("question1");
		String s;
		s = (String)getServletContext().getAttribute("que_id");
		if(s == null)
			s = "";
		int count = 0;
		String que_count = (String) getServletContext().getAttribute("que_count");
		if (que_count == null)
			count = 0;
		else
			count = Integer.parseInt(que_count);
		
		if(count < 6)
		{
			if (getServletContext().getAttribute("errorMessage") == null)
			{
				for(int i = 0; i < arrayList.size(); i++)
				{
					que = arrayList.get(i);
					if(count == 5)
						count++;
					if(s.contains(""+que.getId()))
						continue;
					else
					{
						count++;
						break;
					}
				}
				s = s + que.getId() + " ";		
			}
		}
		String progress = getProgress(count);
		request.setAttribute("progress", progress);
		
		
		getServletContext().setAttribute("question1", que);
		getServletContext().setAttribute("que_id", s);
		getServletContext().setAttribute("que_count",""+count);
		getServletContext().removeAttribute("errorMessage");
		
		
		if(count <= 5)
			request.getRequestDispatcher( "/WEB-INF/Quiz.jsp" ).forward(request,response);
		else	
		{	
			String abc = (String)getServletContext().getAttribute("true_ans");
			int true_count = 0;
			
			if(abc != null)
				true_count = Integer.parseInt(abc);
			String score = getScore(true_count);
			request.setAttribute("score",score);
			request.setAttribute("progress", progress);
			request.getRequestDispatcher( "/WEB-INF/ScoreReport.jsp" ).forward(request,response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		String ans_true = (String) getServletContext().getAttribute("true_ans");
		int true_ans = 0;
		if (ans_true == null)
			true_ans = 0;
		else
			true_ans = Integer.parseInt(ans_true);
		
		Questions que = (Questions) getServletContext().getAttribute("question1");
				
		if(request.getParameter("answer") == null)
		{
			getServletContext().setAttribute("errorMessage", "1");
			response.sendRedirect("Quiz");
			return;
		}
		String answer  = (String) request.getParameter("answer");
				
		if(answer.equals(que.getAnswer()))
			true_ans++;
		
		getServletContext().setAttribute("true_ans", ""+true_ans);
		response.sendRedirect("Quiz");
	}
	
	public String getScore(int true_count){
		String quiz_score = null;
		if(true_count == 0)	
			quiz_score = "Terrible";
		else if(true_count == 1)	
			quiz_score = "Poor";
		else if(true_count == 2)	
			quiz_score = "Below Average";
		else if(true_count == 3)	
			quiz_score = "Average";
		else if(true_count == 4)	
			quiz_score = "Above Average";
		else if(true_count == 5)	
			quiz_score = "Awesome";	
		return quiz_score;
	}
	
	public String getProgress(int count){
		String progress = null;
		if(count == 1)	
			progress = "0% Progress";
		else if(count == 2)	
			progress = "20% Progress";
		else if(count == 3)	
			progress = "40% Progress";
		else if(count == 4)	
			progress = "60% Progress";
		else if(count == 5)	
			progress = "80% Progress";
		else
			progress = "100% Progress";
		return progress;
	}
}