<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />
<html lang="UTF-8">
<head>
<title>Main page</title>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no" />
<link rel="stylesheet" href="assets/css/main.css" />
</head>
<body class="is-preload">
	<!-- Content -->
	<div id="content">
		<div class="inner">
			<c:if test="${!empty mainNoticeBoards.content}">
				<c:forEach var="mainNotice" items="${mainNoticeBoards.content}">
					<!-- Post -->
					<article class="box post post-excerpt">
						<header>
							<!-- Title -->
							<h2>
								<a href="#">${mainNotice.title}</a>
							</h2>
							<!-- subject -->
							<p>${mainNotice.subject}</p>
						</header>
						<div class="info">
							<!-- 등록일자 영역  -->
							<span class="date"><span class="month">
							<c:set var="year"  value="${fn:substring(mainNotice.regDate,0,4)}"/>
							<c:set var="month"  value="${fn:substring(mainNotice.regDate,5,7)}"/>
							<c:set var="day"  value="${fn:substring(mainNotice.regDate,8,10)}"/>
								<c:choose>
									<c:when test="${month eq '01'}">Jan</c:when>
									<c:when test="${month eq '02'}">Feb</c:when>
									<c:when test="${month eq '03'}">Mar</c:when>
									<c:when test="${month eq '04'}">Apr</c:when>
									<c:when test="${month eq '05'}">May</c:when>
									<c:when test="${month eq '06'}">Jun</c:when>
									<c:when test="${month eq '07'}">Jul</c:when>
									<c:when test="${month eq '08'}">Aug</c:when>
									<c:when test="${month eq '09'}">Sep</c:when>
									<c:when test="${month eq '10'}">Oct</c:when>
									<c:when test="${month eq '11'}">Nov</c:when>
									<c:otherwise>Dec</c:otherwise>
								</c:choose>
							</span>
								<span class="day">${day}</span><span class="year">, ${year}</span></span>
							<!--
									Note: You can change the number of list items in "stats" to whatever you want.
								-->
							<!-- 아이콘영역  -->
							<ul class="stats">
								<li><a href="#" class="icon fa-comment">16</a></li>
								<li><a href="#" class="icon fa-heart">32</a></li>
								<li><a href="#" class="icon brands fa-twitter">64</a></li>
								<li><a href="#" class="icon brands fa-facebook-f">128</a></li>
								<li><a href="#" class="icon fa-thumbs-up">128</a></li>
							</ul>
						</div>
						<!-- 썸네일영역 -->
						<a href="#" class="image featured"><img src="/files/${mainNotice.filename}" alt="" /></a>
						
							<%-- ${fn:replace(fn:replace(mainNotice.contents,'&lt;','<'),'&gt;','>')} --%>
						${mainNotice.contents}
						
					</article>
				</c:forEach>
			</c:if>
			<!-- Pagination -->
			<!--<div class="pagination">
				<a href="#" class="button previous">Previous Page</a>-->
				<!-- <div class="pages">
					<a href="#" class="active">1</a> <a href="#">2</a> <a href="#">3</a>
					<a href="#">4</a> <span>&hellip;</span> <a href="#">20</a>
				</div> 
				<a href="#" class="button next">Next Page</a>
			</div>-->
			<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${mainNoticeBoards.first}">
					<li class="page-item disabled"><a href="#" class="button next disabled">Previous Page</a></li>
					<li class="page-item"><a href="?page=${mainNoticeBoards.number+1}" class="button next">Next Page</a></li>
				</c:when>
				<c:when test="${mainNoticeBoards.last }">
					<li class="page-item"><a href="?page=${mainNoticeBoards.number-1}" class="button next">Previous Page</a></li>
					<li class="page-item disabled"><a href="#" class="button next disabled">Next Page</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a href="?page=${mainNoticeBoards.number-1}" class="button next">Previous Page</a></li>
					<li class="page-item"><a href="?page=${mainNoticeBoards.number+1}" class="button next">Next Page</a></li>
				</c:otherwise>
			</c:choose>			
		</ul>
		</div>
	</div>
	<jsp:include page="/WEB-INF/views/cm/left.jsp" />
</body>
</html>
