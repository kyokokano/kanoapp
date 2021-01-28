<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.*"%>
<%
	Acce acce=(Acce)request.getAttribute("acce");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/acceapp/Admin/Update" method="post" enctype="multipart/form-data">
商品名:<input type="text" name="name" value="<%=acce.getName() %>" required><br>
価格:<input type="number" name="price" step="10" value="<%=acce.getPrice()%>" required><br>
<img src="/acceapp/upload/<%=acce.getImgname()%>" id="preview"><br>
商品画像:<input type="file" name="imgname" id="imgname"><br>
<input type="hidden" name="id" value="<%=acce.getId() %>">
<input type="hidden" name="orgname" value="<%=acce.getImgname() %>">
<button type="submit">更新</button>
</form>
</body>
</html>