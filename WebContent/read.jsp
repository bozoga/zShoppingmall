<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
<title>쇼핑몰</title>
<%
	GoodsVO gvo = (GoodsVO) session.getAttribute("gvo");
	ArrayList<ReviewVO> rlist = (ArrayList<ReviewVO>) session.getAttribute("rlist");
%>

<body onload="init();">
	<script>
		var sell_price;
		var amount;
		function init() {
			sell_price = document.form.sell_price.value;
			amount = document.form.amount.value;
			document.form.sum.value = sell_price;
			change();
		}
		function add() {
			hm = document.form.amount;
			sum = document.form.sum;
			hm.value++;
			sum.value = parseInt(hm.value) * sell_price;
			change();
		}
		function del() {
			hm = document.form.amount;
			sum = document.form.sum;
			if (hm.value > 1) {
				hm.value--;
				sum.value = parseInt(hm.value) * sell_price;
				change();
			}
		}
		function change() {
			hm = document.form.amount;
			sum = document.form.sum;
			if (hm.value < 0) {
				hm.value = 0;
			}
			var sumvalue=parseInt(hm.value) * sell_price;
			sum.value = sumvalue;
			document.getElementById("sum").innerHTML=sumvalue;
		}
		function check_buy(){
			form.action="buyGoods.do?id=<%=gvo.getId()%>";
			form.method = "post";
			form.submit();
		}
	</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section" style="text-align: center;">
		<div>
			<h3 style="text-align: center;">상품리스트</h3>
		</div>
		<div class="secread1" style="width:100%">
			<div class="read_img">
				<img src="<%=gvo.getImg()%>" width="600px">
			</div>
			<div class="wrap_read">
				<hr style="border-color: black;" />
				<div style="padding: 10px; font-size: 20px;">
					<%=gvo.getName()%>
				</div>
				<hr />
				<div style="padding: 10px; font-size: 16px;height: 30px;">
					판매가&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<%=gvo.getPrice()%>원
				</div>
				<div style="padding: 10px; font-size: 16px;height: 30px;">
					수량&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<% if(Integer.parseInt(gvo.getStock())>0){
						out.print(gvo.getStock()+"개");
						}else{
							out.print("품절");}%>
				</div>
				<hr />

				<div style="padding: 0px; font-size: 16px;">
					<form name="form" method="post">
						<table class="read_table" style="text-align: center;margin:20px 0px;">
							<tr>
								<td style="width: 250px;height: 30px">상품명</td>
								<td style="width: 100px;">상품수</td>
								<td style="width: 120px;">가격</td>
							</tr>
							<tr>
								<td style="width: 250px;height: 30px"><%=gvo.getName()%></td>
								<td><input type=hidden name="sell_price"
									value="<%=gvo.getPrice()%>"> <input type="button"
									value="-" onclick="del();"> <input type="text"
									name="amount" value="1" size="3" onchange="change();"
									style="width: 30px; text-align: center;"> <input
									type="button" value="+" onclick="add();"></td>
								<td><input type="text" name="sum" size="11"
									style="text-align: right;">원</td>
							</tr>
						</table>
						<hr style="margin-bottom: 30px;"/>

						&nbsp;&nbsp;&nbsp;&nbsp;Total :
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<p id="sum" style="display: inline-block; font-size: 25px;margin-bottom: 30px;">price</p>
						원
					</form>
					<hr style="margin-bottom: 30px;"/>
					<div class="loginbox">
						<a href="javascript:check_buy()"><p class="join_btn1">구매하기</p></a>
					</div>
					<div class="loginbox">
						<a href="insertCart.do?id=<%=gvo.getId()%>"><p class="join_btn2">장바구니</p></a>
					</div>

				</div>

			</div>
			
			<div class="review">
				<hr />
				<div>
					<h3 style="text-align: center;">사용후기</h3>
				</div>
				<hr style="margin: 2px;"/>
				<%
					if (rlist != null) {
						for (ReviewVO e : rlist) {
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
						<a href="deleteReview2.do?id=<%=e.getId()%>&bid=<%=e.getBid()%>">삭제</a>
						<%}} %>
						</td>
						
					</tr>
				</table>
				<hr style="margin: 2px;"/>
				<%
					}
					}
				%>
				<form id="fm" method="post">
					<div class="loginbox">
						<a href="insertReview.jsp?id=<%=gvo.getId()%>"><p class="join_btn3">리뷰쓰기</p></a>
					</div>
					<div class="loginbox">
						<a href="index.jsp"><p class="join_btn4">취소</p></a>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>