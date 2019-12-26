<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css?a=aaa">
<title>쇼핑몰</title>
<% MemberVO mvo = (MemberVO)session.getAttribute("userInfo"); %>
<script>
	function check_updateMember(){
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
		else if(fm.password.value!=fm.passwordck.value){
			alert("비밀번호와 비밀번호 확인이 다릅니다");
			fm.password.focus();
			return false;
		}
		else if(fm.name.value==""){
			alert("이름을 입력해주세요");
			fm.name.focus();
			return false;
		}
		else if(fm.address.value==""){
			alert("주소를 입력해주세요");
			fm.addressme.focus();
			return false;
		}
		else{
			fm.action="updateMember.do";
			fm.method="post";
			fm.submit();
		}
	}
	function check_deleteMember(){
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
		else if(fm.password.value!=fm.passwordck.value){
			alert("비밀번호와 비밀번호 확인이 다릅니다");
			fm.password.focus();
			return false;
		}
		else{
			if(confirm("정말 탈퇴하시겠습니까??") == true){
				fm.action="deleteMember.do";
				fm.method="post";
			    fm.submit();
			}
		} 
	}
</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
    <div class="section">
        <div><h3 style="text-align: center;">회원정보 수정</h3></div>
        <div class="secjoin1">
            <form id="fm">
                <table>
                    <tr>
                        <td class="join_td1">아이디</td>
                        <td class="join_td2"><input type="text" id="id" name="id" value="<%=mvo.getId() %>" style="width: 180px" readonly></td>
                    </tr>
                    <tr>
                        <td class="join_td1">비밀번호</td>
                        <td class="join_td2"><input type="password" id="password" name="password" style="width: 180px"></td>
                    </tr>
                    <tr>
                        <td class="join_td1">비밀번호 확인</td>
                        <td class="join_td2"><input type="text" id="passwordck" name="passwordck" style="width: 180px"></td>
                    </tr>
                    <tr>
                        <td class="join_td1">이름</td>
                        <td class="join_td2"><input type="text" id="name" name="name" value="<%=mvo.getName() %>" style="width: 180px"></td>
                    </tr>
                    <tr>
                        <td class="join_td1">주소</td>
                        <td class="join_td2"><input type="text" id="address" name="address" value="<%=mvo.getAddress() %>" style="width: 400px"></td>
                    </tr>
                    <tr>
                        <td class="join_td1">핸드폰번호</td>
                        <td class="join_td2"><input type="text" id="phone" name="phone" value="<%=mvo.getPhone() %>" style="width: 180px"></td>
                    </tr>
                </table>
                    <div class="loginbox">
                        <a href="javascript:check_updateMember()"><p class="join_btn1">정보수정</p></a>
                    </div>
                    <div class="loginbox">
                        <a href="javascript:check_deleteMember()"><p class="join_btn2">회원탈퇴</p></a>
                    </div>
            </form>
        </div>
    </div>
</body>
</html>