<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/cm/header.jsp" />
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>joinForm Page</title>
</head>
<body>
	<div class="container">
		<form>
			<div class="form-group">
				<label for="username">Username:</label> 
				<input type="text"
					class="form-control" placeholder="Enter username" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> 
				<input type="password"
					class="form-control" placeholder="Enter password" id="password" name="password">
			</div>
			<!-- <div class="form-group">
				<label for="pwd">Password Check:</label> 
				<input type="password"
					class="form-control" placeholder="Enter password Check" id="pwdChk">
			</div> -->
			<div class="form-group">
				<label for="email">Email address:</label> 
				<input type="email"
					class="form-control" placeholder="Enter email" id="email" name="email">
			</div>
			
			<div class="form-group form-check">
				<label class="form-check-label"> <input
					class="form-check-input" type="checkbox"> 이용약관 및 개인정보처리방침 동의 여부
				</label>
			</div>
		</form>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<button id="btn-save" class="btn btn-primary">회원가입</button>
		
	</div>
	<br />
</body>
</html>
<script src="/js/um/user.js"></script>
<jsp:include page="/WEB-INF/views/cm/footer.jsp" />