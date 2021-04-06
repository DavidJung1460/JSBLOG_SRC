<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/cm/header.jsp" />
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>loginForm Page</title>
</head>
<body>
	<div class="container">
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
			<button id="btn-login" class="btn btn-primary">로그인</button>
			<a href="https://kauth.kakao.com/oauth/authorize?client_id=6e74fb94e1bd8860d3ec8ea3f54396d6&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
			<img src="/images/kakao/kakao_login_medium_narrow.png" height="40px" width="160px">
			</a>
		</form>
		
	</div>
	<br />
</body>
</html>
<jsp:include page="/WEB-INF/views/cm/footer.jsp" />