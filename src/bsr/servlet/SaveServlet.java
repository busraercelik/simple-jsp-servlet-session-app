package bsr.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bsr.model.Note;
import bsr.model.User;
import bsr.service.NoteService;
import bsr.service.NoteServiceImpl;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	NoteService noteService = new NoteServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		String textAreaValue = request.getParameter("note");
		System.out.println("textAreaValue: "+textAreaValue);
		
		//retrieves the current session, and if one doesn't exist yet,
		//returns null. 
		HttpSession session = request.getSession(false);
		if (session != null) {
			String username = (String) session.getAttribute("username");
			System.out.println("username: "+username);
			Note note = noteService.getNote(username);
			note.setNote(textAreaValue);
			noteService.updateNote(note);
			
			//pw.println("<h1>Your note: \n</h1><br>"+note.getNote());
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("welcome.jsp");
			dispatcher.forward(request, response);
			return;
		}
		pw.close();
	}

}
