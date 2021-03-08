package bsr.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// If create is false and the request has no valid HttpSession, this method
		// returns null.
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();

			response.sendRedirect("login.jsp");
			return;

			// response already committed error example
			/*
			 * //response is committed boolean dateExpired=true; if(dateExpired) {
			 * response.sendRedirect("login.jsp"); //end the control here //return; }
			 * boolean isTokenBad=true; if(isTokenBad) { response.sendRedirect("login.jsp");
			 * //return; }
			 */
		}
	}

}
