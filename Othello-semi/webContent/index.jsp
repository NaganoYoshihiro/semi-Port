<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String noRoom = (String)request.getAttribute("noRoom");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>The オセロ</title>
<link rel="stylesheet" href="index.css">
</head>
<body>

	<form action="/Othello/RoomResult" method="post" >
	    PW：<input type='text' name='no'size="30" class="example1">
	    <input type='submit' name='bttn' value='対戦' class="sample4"><br>
	</form>
	<% if(noRoom == "true"){%>
		<font color="black">※その部屋は存在しません</font><br>
		<font color="black">※1111を入力してください</font>
	<% }%>
</body>
</html>