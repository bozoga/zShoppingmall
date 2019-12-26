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
	ArrayList<GoodsVO> list = (ArrayList<GoodsVO>) session.getAttribute("cartList");
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
		
	</script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<%@ include file="nav.jsp"%>
	<div class="section">
		<div>
			<h3 style="text-align: center;">장바구니</h3>
		</div>
		<div class="seccart1">
			<div class="goodslist1">
			<form name="form" method="get">
				<table class="">
				<tr>
					<td colspan="7"><hr class="sn_hr"></td>
				</tr>
					<tr>
						<td class="sc_td1"><input type="checkbox"></td>
						<td class="sc_td2">이미지</td>
						<td class="sc_td3">상품정보</td>
						<td class="sc_td4">판매가</td>
						<td class="sc_td5">수량</td>
						<td class="sc_td6">합계</td>
						<td class="sc_td7">선택</td>
					</tr>
					<tr>
					<td colspan="7"><hr class="sn_hr"></td>
				</tr>
					<%
						if (list != null) {
							for (GoodsVO gvo : list) {
					%>
					<tr>
						<td><input type="checkbox"></td>
						<td><img src="<%=gvo.getImg()%>" style="width: 80px;"></td>
						<td><%=gvo.getName()%></td>
						<td><%=gvo.getPrice()%>원</td>
						<td>
							<input type=hidden name="sell_price" value="<%=gvo.getPrice()%>"> 
							<input type="button" value="-" onclick="del();">
							<input type="text" name="amount" value="1" size="3" onchange="change();" style="width: 30px;text-align: center;">
							<input type="button" value="+" onclick="add();">
						</td>
						<td><input type="text" name="sum" size="11"  style="text-align: right;" readonly>원</td>
						<td>
							<input type="button" value="구매" onclick="location.href='deleteCart.do?id=<%=gvo.getId()%>'">
							<input type="button" value="삭제" onclick="location.href='deleteCart.do?id=<%=gvo.getId()%>'">
						</td>
					</tr>
					<tr>
					<td colspan="7"><hr class="sn_hr"></td>
				</tr>
					<%
						}
						}
					%>
				</table>
				<div>total: <p id="sum">price</p></div>
				</form>
				<div class="loginbox">
							<a href="javascript:check_join()"><p class="join_btn1">구매하기</p></a>
						</div>
						<div class="loginbox">
							<a href=""><p class="join_btn2">장바구니</p></a>
						</div>
			</div>
		</div>
	</div>
</body>
</html>