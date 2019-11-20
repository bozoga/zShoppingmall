<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css?a=aaa">
<title>쇼핑몰</title>
<script>
	function check_login(){
		
		if(fm.id.value==""){
			alert("아이디를 입력해주세요");
		}
		else{
			fm.action="login.do";
			fm.method="post";
			fm.submit();
		}
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section">
		<div class="seclog1">
		<form id="fm">
			<fieldset class="fs1">
				<div><h2>MEMBER LOGIN</h2></div>
				<div class="loginbox">
					<p class="id">
						<input type="text" name="id" id="id" >
					</p>
				</div>
				<div class="loginbox">
					<p class="password">
						<input type="text" name="password" id="password">
					</p>
				</div>
				<div class="loginbox">
					<a href="javascript:check_login()"><p class="log_btn1">로그인</p></a>
                </div>
                <div class="loginbox">
					<div class="idsearch"><a href="">아이디찾기</a>&nbsp;&nbsp;<a href="">비밀번호찾기</a></div>
				</div>
                <div class="loginbox">
					<a href=""><p class="log_btn2">회원가입</p></a>
				</div>
			</fieldset>
			</form>
		</div>
	</div>
</body>
</html>