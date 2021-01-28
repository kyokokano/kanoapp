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

@WebServlet("/Admin")
@MultipartConfig
public class Admin extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AcceDAO dao=new AcceDAO();
		List<Acce> list=dao.findAll();
		request.setAttribute("list", list);
		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/admin/main.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name=request.getParameter("name");
		int price=Integer.parseInt(request.getParameter("price"));
		Part part=request.getPart("imgname");
		String imgname=part.getSubmittedFileName();
		String path=getServletContext().getRealPath("/upload");
		part.write(path+File.separator+imgname);
		AcceDAO dao=new AcceDAO();
		dao.insertOne(new Acce(name,price,imgname));
		request.setAttribute("msg", "1件追加しました");
		doGet(request,response);
	}

}