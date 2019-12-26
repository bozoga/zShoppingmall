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
	NoticeVO vo = new NoticeVO();
	NoticeDAO dao = NoticeDAO.getInstance();
	
	int totalNotice = dao.getTotalNotice(vo);
	int listCount = 10;
	int totalPage = totalNotice/listCount;
	if (totalNotice % listCount > 0) {
	    totalPage++;
	}
	
	int nowPage=1;
	if(request.getParameter("nowPage")!=null){
		nowPage = Integer.parseInt(request.getParameter("nowPage"));
	}
	
	int startCount = (nowPage-1)*listCount+1;
	int endCount = startCount+listCount-1;
	
	ArrayList<NoticeVO> list = dao.getNoticeList(startCount,endCount);
	
%>
<script>
	
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section">
		<div>
			<h3 style="text-align: center;">공지사항</h3>
		</div>
		<div class="secnotice1">
			<table>
				<tr>
					<td colspan="4"><hr class="sn_hr"></td>
				</tr>
				<tr>
					<td class="sn_td1">번호</td>
					<td class="sn_td2">제목</td>
					<td class="sn_td3">작성자</td>
					<td class="sn_td4">작성일</td>
				</tr>
				<%if(list!=null){
					for (NoticeVO nvo : list) {
				%>
								<tr>
					<td colspan="4"><hr class="sn_hr"></td>
				</tr>
				<tr>
					<td class="sn_td1"><%=nvo.getIdx()%></td>
					<td><a href="readNotice.do?idx=<%=nvo.getIdx()%>"><%=nvo.getSubject()%></a></td>
					<td><%=nvo.getWriter()%></td>
					<td><%=nvo.getRegdate().substring(0,10)%></td>
				</tr>
				<%
					}}
				%>
								<tr>
					<td colspan="4"><hr class="sn_hr"></td>
				</tr>
			</table><br>
			<div style="text-align: center;">
			<% for(int i=1;i<=totalPage;i++){%>
				<a href="notice.jsp?nowPage=<%=i %>"><%=i %></a>
			<%} %>
			</div>
		</div>
		<%if((session.getAttribute("userID")!=null)){
		if(session.getAttribute("userID").equals("owner")) {%>
		<div class="loginbox">
			<a href="insertNotice.jsp"><p class="join_btn1">등록</p></a>
		</div>
		<div class="loginbox">
			<a href="index.jsp"><p class="join_btn2">취소</p></a>
		</div>
		<%}} %>
	</div>
</body>
</html>