<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function check_search(){
		if(frm1.keyword.value==""){
			return false;
		}else{
			frm1.action="searchGoods.do";
			frm1.method="post"
			frm1.submit();
		}
	}
</script>
</head>
<body>
	<div class="header">
		<div class="hdiv1">
		<form name="frm1">
			<ul>
				<li><a href="login.jsp">로그인</a></li>
				<li><a href="join.jsp">회원가입</a></li>
				<li><a href="cart.do">장바구니</a></li>
				<li><a href="myPage.do">마이페이지</a></li>
				<li><a href="notice.jsp">커뮤니티</a></li>
				<li><a href="review.jsp">REVIEW</a></li>
				<li><a href="#">Q&A</a></li>
 				<%if(session.getAttribute("userID")!=null){ %>
				<li><a href="#"><%=session.getAttribute("userID") %>님 환영합니다</a></li>
				<li><a href="logout.do">로그아웃</a></li>
				<%if(session.getAttribute("userID").equals("owner")){ %>
				<li><a href="managerPage.jsp">매니저페이지</a></li>
 				<li style="margin-top: -1px;margin-left: 245px;">
					<input type="text" id="keyword" name="keyword" class="keyword" style="width: 200px">
				</li>
 				<li style="margin-left: 3px;">
					<a href="javascript:check_search()"><img src="img/search-icon.png"></a>
				</li>
				<%}else{ %>
 				<li style="margin-top: -1px;margin-left: 345px;">
					<input type="text" id="keyword" name="keyword" class="keyword" style="width: 200px">
				</li>
 				<li style="margin-left: 3px;">
					<a href="javascript:check_search()"><img src="img/search-icon.png"></a>
				</li>
				<%}} else{%>
 				<li style="margin-top: -3px;margin-left: 550px;">
					<input type="text" id="keyword" name="keyword" class="keyword" style="width: 200px">
				</li>
 				<li style="margin-top: -1px;margin-left: 3px;">
					<a href="javascript:check_search()"><img src="img/search-icon.png"></a>
				</li>
				<%} %>
			</ul>
			</form>
		</div>
		<br>
		<div class="hdiv2">
			<a href="index.jsp"><img src="img/theninemall.jpg"></a>
		</div>
	</div>
</body>
</html>