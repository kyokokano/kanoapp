<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,model.*"%>
<%
	String searchWord=(String)request.getAttribute("searchWord");
	searchWord=searchWord==null?"":searchWord;
	List<Acce> list=(List<Acce>)request.getAttribute("list");
	Integer total=(Integer)request.getAttribute("total");
	Integer limit=(Integer)request.getAttribute("limit");
	Integer pageNo=(Integer)request.getAttribute("pageNo");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Accessories Menu</title>
  <link rel="stylesheet" href="http://yui.yahooapis.com/3.18.1/build/cssreset/cssreset-min.css">
  <link rel="stylesheet" href="css/main.css"/>
</head>
<body>
  <div id="wrapper">
<form action="/acceapp/Main" method="get">
<input type="text" name="searchWord" value="<%=searchWord%>">
<button type="submit">検索</button>
</form>
<% if(list != null&&list.size()>0){ %>
<% if(total<=limit){ %>
<%}else{ %>
<p>全<%=total %>件中 <%=(pageNo-1)*limit+1 %>~<%=pageNo*limit>total?total:pageNo*limit %>件を表示</p>
<ul>
<%if(pageNo>1){ %>
<li><a href="/acceapp/Main?searchWord=<%=searchWord %>&page=<%=pageNo-1 %>"><span aria-hidden="true">&larr;</span>前へ</a>
<%} %>
<%if(pageNo*limit<total){ %>
<li><a href="/acceapp/Main?searchWord=<%=searchWord %>&page=<%=pageNo+1 %>">次へ<span aria-hidden="true">&rarr;</span></a>
<%} %>
</ul>
<%} %>
    <h1>Accessories Menu</h1>
    <div id="menu">
    <%for(Acce d:list){ %>
      <div>
        <img src="upload/<%=d.getImgname() %>">
        <p><%=d.getName() %></p>
        <p><%=d.getPrice() %>円</p>
      </div>
     <%} %>
    </div>
     <%} %>
  </div>
</body>
</html>