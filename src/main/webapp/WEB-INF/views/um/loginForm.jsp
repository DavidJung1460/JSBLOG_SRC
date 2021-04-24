<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />
<html lang="UTF-8">
<head>
<title>loginForm Page</title>
</head>
<body>
	<div id="content"  style="box-shadow: 0 0 0em 0em rgba(0, 0, 0, 0);">
		<form action="/auth/loginProc" method="post">
			<div class="form-group">
				<label for="username">Username:</label> 
				<input type="text" class="form-control" placeholder="Enter username" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> 
				<input type="password" class="form-control" placeholder="Enter password" id="password" name="password">
			</div>
			
		<!-- 	<div class="form-group form-check">
				<label class="form-check-label"> 
				<input	class="form-check-input" type="checkbox" name="remember"> remember me
				</label>
			</div> -->
			<button id="btn-login" style="font-weight: bold;"class="btn btn-primary">로그인</button>
			<button type="button" style="background-image: none; font-weight: bold;"class="btn btn-warning"  onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=6e74fb94e1bd8860d3ec8ea3f54396d6&redirect_uri=http://59.11.77.35:8000/auth/kakao/callback&response_type=code'">카카오 로그인</button>
			<!-- <button type="button" style="background-image: none; font-weight: bold;"class="btn btn-warning"  onclick="location.href='https://kauth.kakao.com/oauth/authorize?client_id=6e74fb94e1bd8860d3ec8ea3f54396d6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code'">카카오 로그인</button> -->
		</form>
		
	</div>
	<br />
</body>
</html>
<jsp:include page="/WEB-INF/views/cm/left.jsp" />
