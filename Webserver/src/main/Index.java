package main;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ir.assignments.three.DocScore;
import ir.assignments.three.Utilities;

/**
 * Servlet implementation class Index
 */
@WebServlet("")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	Utilities u;
	String top;
	public Index() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsp = "/Results.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jsp);
		dispatcher.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String query = request.getParameter("query");
		u = (Utilities)request.getServletContext().getAttribute("u");
		List<String> termList = Arrays.asList(query.split(" "));
		List<DocScore> topdocs = Utilities.calculateDocScores(termList, 10);
		for (DocScore doc : topdocs) {
			response.getWriter().println(Utilities.docid2docurl.get(doc.docId));
			response.getWriter().println(doc.score);
		}
		response.getWriter().println(query);
		response.getWriter().println(termList);

		response.getWriter().println(getServletContext().getRealPath("/stopwords.txt"));

	}

}
