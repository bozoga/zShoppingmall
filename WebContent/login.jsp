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
			fm.id.focus();
			return false;
		}
		else if(fm.password.value==""){
			alert("비밀번호를 입력해주세요");
			fm.password.focus();
			return false;
		}
		else{
			fm.action="login.do";
			fm.method="post";
			fm.submit();
		}
	}
    function pressEnter(){
        if(event.keyCode == 13){
            check_login();
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
						<input type="text" name="id" id="id" placeholder="아이디">
					</p>
				</div>
				<div class="loginbox">
					<p class="password">
						<input type="password" name="password" id="password" placeholder="패스워드" onKeyDown="pressEnter()">
					</p>
				</div><br>
				<div class="loginbox">
					<a href="javascript:check_login()"><p class="log_btn1">로그인</p></a>
                </div>
                <div class="loginbox">
					<a href="join.jsp"><p class="log_btn2">회원가입</p></a>
				</div>
			</fieldset>
			</form>
		</div>
	</div>
</body>
</html>