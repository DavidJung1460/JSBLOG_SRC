<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="/WEB-INF/views/cm/header.jsp" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>

<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>updateForm Page</title>
</head>
<body>
	<div class="container">
		<form>
			<input type="hidden" id="id" value="${principal.user.id}">
			<div class="form-group">
				<label for="username">Username:</label> 
				<input type="text"
					class="form-control" value="${principal.user.username}" placeholder="Enter username" id="username" name="username" readonly>
			</div>

			<c:choose>
				<c:when test="${empty principal.user.oauth}">
					<div class="form-group">
						<label for="pwd">Password:</label> <input type="password"
							class="form-control" placeholder="Enter password" id="password"
							name="password">
					</div>
					<div class="form-group">
						<label for="email">Email address:</label> <input type="email"
							class="form-control" value="${principal.user.email}"
							placeholder="Enter email" id="email" name="email">
					</div>
				</c:when>
				<c:otherwise>
					<div class="form-group">
						<label for="email">Email address:</label> <input type="email"
							class="form-control" value="${principal.user.email}"
							placeholder="Enter email" id="email" name="email"
							readonly="readonly">
					</div>
				</c:otherwise>
			</c:choose>
			<!-- <div class="form-group">
				<label for="pwd">Password Check:</label> 
				<input type="password"
					class="form-control" placeholder="Enter password Check" id="pwdChk">
			</div> -->
		
			
		</form>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<button id="btn-update" class="btn btn-primary">수정</button>
		
	</div>
	<br />
</body>
</html>
<script src="/js/um/user.js"></script>
<jsp:include page="/WEB-INF/views/cm/footer.jsp" />