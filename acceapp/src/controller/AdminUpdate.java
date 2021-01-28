package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.AcceDAO;
import model.Acce;

@WebServlet("/Admin/Update")
@MultipartConfig
public class AdminUpdate extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		if(id == null) {
			response.sendRedirect("/acceapp/Admin");
			return;
		}
		AcceDAO dao=new AcceDAO();
		Acce acce=dao.findOne(Integer.parseInt(id));
		request.setAttribute("acce", acce);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/admin/update.jsp");
		rd.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id=request.getParameter("id");
		String name=request.getParameter("name");
		String price=request.getParameter("price");
		String orgname=request.getParameter("orgname");
		Part part=request.getPart("imgname");
		String imgname;
		if(part.getSize()==0) {
			imgname=orgname;
		}else {
			imgname=part.getSubmittedFileName();
			String path=getServletContext().getRealPath("/upload");
			part.write(path+File.separator+imgname);
		}
		AcceDAO dao=new AcceDAO();
		dao.updateOne(new Acce(Integer.parseInt(id),name,Integer.parseInt(price),imgname));
		List<Acce> list=dao.findAll();
		request.setAttribute("list", list);
		request.setAttribute("msg", "1件更新しました");
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/admin/main.jsp");
		rd.forward(request, response);
	}

}