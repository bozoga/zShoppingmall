<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css?a=aa">
<title>쇼핑몰</title>
<%
	GoodsVO vo = new GoodsVO();
	GoodsDAO dao = GoodsDAO.getInstance();
	ArrayList<GoodsVO> list = null;
	if (session.getAttribute("list") != null) {
		list = (ArrayList<GoodsVO>) session.getAttribute("list");
	} else {
		list = dao.getGoodsList(vo);
	}
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
				if (list != null) {
					for (GoodsVO gvo : list) {
			%>
			<div class="goodslist1">
				<table>
					<tr>
						<td><a href="read.do?id=<%=gvo.getId()%>"><img
								src="<%=gvo.getImg()%>" width="400px"></a></td>
					</tr>
					<tr>
						<td class="sl_td1"><%=gvo.getName()%></td>
					</tr>
					<tr>
						<td class="sl_td2"><%=gvo.getPrice()%>원</td>
					</tr>
					<tr>
						<td class="sl_td2"><a
							href="insertCart.do?id=<%=gvo.getId()%>"><img
								src="img/icon_cart.png"></a></td>
					</tr>
				</table>
			</div>
			<%
				}
				}
			%>
		</div>
	</div>
</body>
</html>