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
	NoticeVO nvo = (NoticeVO) session.getAttribute("nvo");
%>
<script>
	function check_join() {

		if (fm.id.value == "") {
			alert("아이디를 입력해주세요");
		} else {
			fm.action = "insertReview.do";
			fm.method = "post";
			fm.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section">
		<div>
			<h3 style="text-align: center;">공지사항</h3>
		</div>
		<div class="seclist1">
			<table>
				<tr>
					<td>제목</td>
					<td><%=nvo.getSubject()%></td>
				</tr>
				<tr>
					<td>작성자</td>
					<td><%=nvo.getWriter()%></td>
				</tr>
				<tr>
					<td>작성일</td>
					<td><%=nvo.getRegdate()%></td>
				</tr>
				<tr>
					<td colspan="2"><%=nvo.getContent()%></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>