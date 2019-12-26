<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="nav">
		<div class="dropdown" style="padding-left: 40px;">
			<button class="dropbtn" onclick="location.href='list.do?div=100'">하드&터프케이스</button>
			<div class="dropdown-content">
				<a href="list.do?div=1">캐릭터디자인</a> <a href="list.do?div=2">감성디자인</a> <a href="list.do?div=3">드로잉디자인</a>
				<a href="list.do?div=4">레터링디자인</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn" onclick="location.href='list.do?div=200'">젤리케이스</button>
			<div class="dropdown-content">
				<a href="list.do?div=11">캐릭터디자인</a> <a href="list.do?div=12">감성디자인</a> <a href="list.do?div=13">드로잉디자인</a>
				<a href="list.do?div=14">레터링디자인</a>
			</div>
		</div>
		<div class="dropdown">
			<button class="dropbtn" onclick="location.href='list.do?div=300'">투명하드케이스</button>
		</div>
		<div class="dropdown">
			<button class="dropbtn" onclick="location.href='list.do?div=400'">일반케이스</button>
		</div>
		<div class="dropdown">
			<button class="dropbtn" onclick="location.href='list.do?div=500'">에어팟 케이스</button>
		</div>
		<div class="dropdown">
			<button class="dropbtn" onclick="location.href='list.do?div=600'">버즈 케이스</button>
		</div>
	</div>
</body>
</html>