<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css?a=aaa">
<title>쇼핑몰</title>
<%
	GoodsVO vo = new GoodsVO();
	GoodsDAO dao = GoodsDAO.getInstance();
	ArrayList<GoodsVO> list = dao.getGoodsList(vo);
%>
<script>
	
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section">
		<div>
			<h3 style="text-align: center;">상품리스트</h3>
		</div>
		<div class="seclist1">
			<%
				for (GoodsVO gvo : list) {
			%>
			<table>
				<tr>
					<td><a href="read.do?id=<%=gvo.getId()%>"><img
							src="<%=gvo.getImg()%>" width="300px"></a></td>
				</tr>
				<tr>
					<td>이름 : <%=gvo.getName()%></td>
				</tr>
				<tr>
					<td>가격 : <%=gvo.getPrice()%>원</td>
				</tr>
				<tr>
					<td>수량 : <%=gvo.getStock()%></td>
				</tr>
			</table>
			<%
				}
			%>
		</div>
	</div>
</body>
</html>