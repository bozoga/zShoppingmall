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
	ReviewVO vo = new ReviewVO();
	ReviewDAO dao = ReviewDAO.getInstance();
	ArrayList<ReviewVO> list = dao.getAllReviewList(vo);
%>
<script>
	
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section">
	<div class="secreview1" style="text-align: center;">
		<div class="review">
				<hr />
				<div>
					<h3 style="text-align: center;">사용후기</h3>
				</div>
				<hr style="margin: 2px;"/>
				<%
					if (list != null) {
						for (ReviewVO e : list) {
				%>
				<table style="text-align: left;">
					<tr>
						<td rowspan="3"><img src="<%=e.getImg()%>" width="80px"></td>
						<td><%=e.getWriter()%></td>
					</tr>
					<tr>
						<td style="font-size: 13px;color: grey"><%=e.getContent()%></td>
					</tr>
					<tr>
						<td style="font-size: 13px;color: grey"><%=e.getRegdate().substring(0,10)%> 에 등록된 리뷰입니다
						<%if(session.getAttribute("userID")!=null){
								if(session.getAttribute("userID").equals("owner")){ %>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="deleteReview.do?id=<%=e.getId()%>">삭제</a>
						<%}} %>
						</td>
					</tr>
				</table>
				<hr style="margin: 2px;"/>
				<%
					}
					}
				%>
			</div>
			</div>
	</div>
</body>
</html>