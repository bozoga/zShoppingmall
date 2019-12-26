<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css?a=aaa">
<title>쇼핑몰</title>
<script>
	function check_join(){
		
		if(fm.id.value==""){
			alert("아이디를 입력해주세요");
		}
		else{
			fm.action="insertNotice.do";
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
        <div><h3 style="text-align: center;">공지사항</h3></div>
        <div class="secjoin1">
            <form id="fm">
                <table>
                    <tr>
                        <td class="join_td1">제목</td>
                        <td class="join_td2"><input type="text" id="subject" name="subject" style="width: 180px"></td>
                    </tr>
                    <tr>
                        <td class="join_td1">글쓴이</td>
                        <td class="join_td2"><input type="text" id="writer" name="writer" style="width: 180px"></td>
                    </tr>
                                     <tr>
                        <td class="join_td1">내용</td>
                        <td class="join_td2"><textarea id="content" name="content" style="width: 500px;height: 200px;"></textarea></td>
                    </tr>

                </table>
                    <div class="loginbox">
                        <a href="javascript:check_join()"><p class="join_btn1">등록</p></a>
                    </div>
                    <div class="loginbox">
                        <a href="index.jsp"><p class="join_btn2">취소</p></a>
                    </div>
            </form>
        </div>
    </div>
</body>
</html>