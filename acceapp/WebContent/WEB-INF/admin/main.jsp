<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*"%>
<%
	List<Acce> list=(List<Acce>)request.getAttribute("list");
	String msg=(String)request.getAttribute("msg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.container{
padding-top:20px;
padding-bottom:60px;
}
td>img{
width:200px;
}
input.form-control,
.alert{
	width:500px;
}
table.table{
	width:70%;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
<div class="container"></div>
<a href="/acceapp/Main" class="btn btn-outline-info btn-sm float-right">公開ページを見る</a>
<% if(msg != null){ %>
<div class="alert alert-success" role="alert">
<%=msg %>
</div>
<%} %>
<form class="mt-3" action="/acceapp/Admin" method="post" enctype="multipart/form-data">
<form class="form-group">
<label for="name">商品名</label>
<input type="text" name="name" id="name" class="form-control" placeholder="商品名を入力" required>
</div>
<div class="form-group">
<label for="price">価格</label>
<input type="number" name="price" id="price" class="form-control" step="10" placeholder="価格を入力" required>
</div>
<div class="form-group">
<label for="imgname">商品画像</label>
<input type="file" name="imgname" class="form-control" id="imgname">
</div>
<button type="submit" class="btn btn-primary">登録</button>
</form>
<% if(list != null && list.size()>0){ %>
<table class="table table-bordered mt-5">
<%for(Acce a:list){ %>
<tr>
<td><img src="/acceapp/upload/<%=a.getImgname() %>"></td>
<td><%=a.getId() %></td>
<td><%=a.getName() %></td>
<td><%=a.getPrice() %></td>
<td>
<a href="/acceapp/Admin/Update?id=<%=a.getId() %>">更新</a>
</td>
<td>
<a href="/acceapp/Admin/Delete?id=<%=a.getId() %>" onclick="return confirm('削除してよろしいですか？')">削除</a>
</td>
</tr>
<%} %>
</table>
<%} %>
</div>
</body>
</html>