package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AcceDAO;
import model.Acce;

@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final int LIMIT=10;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String searchWord=request.getParameter("searchWord");
			String page=(String)request.getParameter("page");
			int pageNo=page==null?1:Integer.parseInt(page);
			AcceDAO dao=new AcceDAO();
			int total=dao.getCount(searchWord);

		if (searchWord != null) {
			List<Acce> list=dao.getListBySearchWord(searchWord,LIMIT,(pageNo-1)*LIMIT);
			request.setAttribute("total",total);
			request.setAttribute("limit",LIMIT);
			request.setAttribute("searchWord",searchWord);
			request.setAttribute("list", list);
			request.setAttribute("pageNo",pageNo);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/main.jsp");
			rd.forward(request,response);
		}else {
			List<Acce> list=dao.findAll();
			request.setAttribute("total",total);
			request.setAttribute("limit",LIMIT);
			request.setAttribute("searchWord",searchWord);
			request.setAttribute("list", list);
			request.setAttribute("pageNo",pageNo);
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/view/main.jsp");
			rd.forward(request, response);
		}

	}
}