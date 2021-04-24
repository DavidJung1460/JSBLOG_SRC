<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />
<html lang="UTF-8">
<head>
<title>User Manage Page</title>
</head>
<body>
	<div id="content" style="box-shadow: 0 0 0em 0em rgba(0, 0, 0, 0);">
	<img src="/images/banner.png" style="width:100%; height:20%;"><br/><br/>
	<div class="form-group" style="width:80%;float: right;">
		<!-- <label for="sel1">Select list:</label> -->
		<form action="/um/userManageListForm" method="get">
			<c:choose>
				<c:when test="${empty search.searchSelect}">
					<select  class="form-control" id="searchSelect"  name="searchSelect"style="display: inline; width:25%;">
						<option selected>아이디</option>
						<option>닉네임</option>
					</select>
					<input type="text"  style="display: inline; width:54%"
											class="form-control" 
											placeholder="Input Search" id="searchText" name="searchText">
				</c:when>
				<c:otherwise>
					<select  class="form-control" id="searchSelect"  name="searchSelect"style="display: inline; width:25%;">
						<c:choose>
							<c:when test="${search.searchSelect eq '아이디'}">
								<option selected>아이디</option>
								<option>닉네임</option>
							</c:when>
							<c:otherwise>
								<option>아이디</option>
								<option selected>닉네임</option>
							</c:otherwise>
						</c:choose>
					</select>
					<input type="text"  style="display: inline; width:54%"
											class="form-control" 
											placeholder="Input Search" id="searchText" name="searchText" value="${search.searchText}">
				</c:otherwise>
			</c:choose>
			<button id="btn-search" class="btn btn-primary" style=" width:18%; margin-top: -0.8%">조회</button>
		</form>
	</div>
	
	<table class="table table-hover">
		<thead>
		<tr>
			<th scope="col" class="text-center">순번</th>
			<th scope="col" class="text-center">아이디</th>
			<th scope="col" class="text-center">닉네임</th>
			<th scope="col" class="text-center">등급</th>
		</tr>
	</thead>
	<tbody>
<c:forEach var="user" items="${users.content}">
		<%-- 리스트 출력 --%>
		<tr>
			<td style="width:5%" class="text-center">${user.id}</td>
			<td style="width: 35%" class="text-center">
				<a class="text-reset"	href="/um/${user.username}/userManageForm"class="text-center">${user.username}</a>
			</td>
			<td style="width: 30%" class="text-center">${user.nickName}</td>
			<td style="width: 30%" class="text-center">${user.grade.gradeCd}</td>
		</tr>
</c:forEach>

	</tbody>
</table>
	<c:choose>
		<c:when test="${empty search.searchSelect}">
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${users.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${users.number-1}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="?page=${users.number+1}">Next</a></li>
				</c:when>
				<c:when test="${users.last }">
					<li class="page-item"><a class="page-link" href="?page=${users.number-1}">Previous</a></li>
					<li class="page-item disabled"><a class="page-link" href="?page=${users.number+1}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${users.number-1}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="?page=${users.number+1}">Next</a></li>
				</c:otherwise>
			</c:choose>			
		</ul>
		</c:when>
		<c:otherwise>
		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${users.first}">
					<li class="page-item disabled"><a class="page-link" href="?page=${users.number-1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="?page=${users.number+1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Next</a></li>
				</c:when>
				<c:when test="${users.last }">
					<li class="page-item"><a class="page-link" href="?page=${users.number-1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Previous</a></li>
					<li class="page-item disabled"><a class="page-link" href="?page=${users.number+1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link" href="?page=${users.number-1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Previous</a></li>
					<li class="page-item"><a class="page-link" href="?page=${users.number+1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Next</a></li>
				</c:otherwise>
			</c:choose>			
		</ul>
		</c:otherwise>
	</c:choose>
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
<jsp:include page="/WEB-INF/views/cm/left.jsp" />
