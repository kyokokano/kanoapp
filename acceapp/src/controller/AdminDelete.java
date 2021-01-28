package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AcceDAO;

@WebServlet("/Admin/Delete")
public class AdminDelete extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id != null) {
			AcceDAO dao=new AcceDAO();
			dao.deleteOne(Integer.parseInt(id));
			request.setAttribute("msg", "1件削除しました");
		}
		RequestDispatcher rd=request.getRequestDispatcher("/Admin");
		rd.forward(request, response);
	}
}