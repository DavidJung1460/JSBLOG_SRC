<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="/WEB-INF/views/cm/header.jsp" />
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>Main Page</title>
</head>
<body>
	<div class="container">
		<h2></h2>
		<c:forEach var="board" items="${boards.content}">
			<div class="card m-2" style="width: 100%; height: 80%">
				<!-- <img class="card-img-top" src="img_avatar1.png" alt="Card image"	style="width: 100%"> -->
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<!-- <p class="card-text">내용</p> -->
					<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>
		</c:forEach>
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:when>
				<c:when test="${boards.last }">
					<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
					<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${boards.number-1}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="?page=${boards.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>
			
			
		</ul>
		<br />

		<!-- 		<p>Image at the bottom (card-img-bottom):</p>
		<div class="card" style="width: 400px">
			<div class="card-body">
				<h4 class="card-title">Jane Doe</h4>
				<p class="card-text">Some example text some example text. Jane
					Doe is an architect and engineer</p>
				<a href="#" class="btn btn-primary">See Profile</a>
			</div>
			<img class="card-img-bottom" src="img_avatar6.png" alt="Card image"
				style="width: 100%">
		</div> -->
	</div>
</body>
</html>
<jsp:include page="/WEB-INF/views/cm/footer.jsp" />
