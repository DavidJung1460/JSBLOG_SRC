<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />

<html lang="UTF-8">
<head>
<title>공지사항 목록</title>
</head>
<body>
	<div id="content" style="box-shadow: 0 0 0em 0em rgba(0, 0, 0, 0);">
		<img src="/images/banner.png" style="width: 100%; height: 20%;"><br />
		<br />
		<%-- <h2></h2>
		<c:forEach var="board" items="${boards.content}">
			<div class="card m-2" style="width: 100%; height: 80%">
				<img class="card-img-top" src="img_avatar1.png" alt="Card image"	style="width: 100%">
				<div class="card-body">
					<h4 class="card-title">${board.title}</h4>
					<!-- <p class="card-text">내용</p> -->
					<a href="/board/${board.id}" class="btn btn-primary">상세보기</a>
				</div>
			</div>
		</c:forEach> --%>
		<div class="form-group" style="width: 80%; float: right;">
			<!-- <label for="sel1">Select list:</label> -->
			<form action="/board/listForm" method="get">
			<input type="hidden" id="pgmCd" name="pgmCd" value="${pgmCd}">
			<input type="hidden" id="gradeCd" name="gradeCd" value="${principal.user.grade.gradeCd}">
			
				<c:choose>
					<c:when test="${empty search.searchSelect}">
						<select class="form-control" id="searchSelect" name="searchSelect"
							style="display: inline; width: 25%;">
							<option selected>제목</option>
							<option>글쓴이</option>
						</select>
						<input type="text" style="display: inline; width: 54%"
							class="form-control" placeholder="Input Search" id="searchText"
							name="searchText">
					</c:when>
					<c:otherwise>
						<select class="form-control" id="searchSelect" name="searchSelect"
							style="display: inline; width: 25%;">
							<c:choose>
								<c:when test="${search.searchSelect eq '제목'}">
									<option selected>제목</option>
									<option>글쓴이</option>
								</c:when>
								<c:otherwise>
									<option>제목</option>
									<option selected>글쓴이</option>
								</c:otherwise>
							</c:choose>
						</select>
						<input type="text" style="display: inline; width: 54%"
							class="form-control" placeholder="Input Search" id="searchText"
							name="searchText" value="${search.searchText}">
					</c:otherwise>
				</c:choose>
				<button id="btn-search" class="btn btn-primary"
					style="width: 18%; margin-top: -0.8%">조회</button>
			</form>
		</div>
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col" class="text-center">제목</th>
					<th scope="col" class="text-center">글쓴이</th>
					<th scope="col" class="text-center">작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="notice" items="${noticeBoards.content}">

					<%-- 리스트 출력 --%>
					<tr>
						<td style="width: 65%"><c:choose>
								<c:when test="${notice.privateYn eq 'Y'}">
									<a onclick="privatePassSave('${notice.id}');" data-toggle="modal" href="#myModal"  style="color: black;">
										<input type="hidden" id="${notice.id}-private"
										value="${notice.privateYn}"> <strong>${notice.title}&nbsp;&nbsp;[공지사항] <c:if test="${notice.replys.size() ne '0'}">(${notice.replys.size()})</c:if></strong>
										<img src="/images/cm/icon/icon1.png"
										style="width: 20px; height: 20px; display: inline;">
									</a>
								</c:when>
								<c:otherwise>
									<a class="text-reset" href="/board/${notice.id}"><Strong>${notice.title} &nbsp;&nbsp;[공지사항] <c:if test="${notice.replys.size() ne '0'}">(${notice.replys.size()})</c:if></Strong></a>
								</c:otherwise>
							</c:choose></td>
						<!-- 작성자, 작성일, 조회수 -->
						<td style="width: 25%" class="text-center">${notice.user.nickName}</td>
						<td style="width: 10%" class="text-center"><fmt:parseDate
								var="regDate" value="${notice.regDate}"
								pattern="yyyy-MM-dd'T'HH:mm" type="both" /> <fmt:formatDate
								pattern="yyyy.MM.dd" value="${regDate}" /></td>
					</tr>
				</c:forEach>
				<c:forEach var="board" items="${boards.content}">

					<%-- 리스트 출력 --%>
					<tr>
						<td style="width: 65%"><c:choose>
								<c:when test="${board.privateYn eq 'Y'}">
									<a onclick="privatePassSave('${board.id}');" data-toggle="modal" href="#myModal"  style="color: black;">
										<input type="hidden" id="${board.id}-private"
										value="${board.privateYn}"> ${board.title} <img
										src="/images/cm/icon/icon1.png"
										style="width: 20px; height: 20px; display: inline;"><c:if test="${board.replys.size() ne '0'}"> (${board.replys.size()})</c:if>
									</a>
								</c:when>
								<c:otherwise>
									<a class="text-reset" href="/board/${board.id}">${board.title}<c:if test="${board.replys.size() ne '0'}"> (${board.replys.size()})</c:if></a>
								</c:otherwise>
							</c:choose></td>
						<!-- 작성자, 작성일, 조회수 -->
						<td style="width: 25%" class="text-center">${board.user.nickName}</td>
						<td style="width: 10%" class="text-center"><fmt:parseDate
								var="regDate" value="${board.regDate}"
								pattern="yyyy-MM-dd'T'HH:mm" type="both" /> <fmt:formatDate
								pattern="yyyy.MM.dd" value="${regDate}" /></td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
		<c:if test="${principal.user.grade.gradeCd  eq 4 || userUseYn eq 'Y'}">
			<button type="button" id="btn-reply-saveForm" class="btn btn-primary"
				onclick="index.saveForm();">글쓰기</button><br/><br/>
		</c:if>

		<ul class="pagination justify-content-center">
			<c:choose>
				<c:when test="${boards.first}">
					<li class="page-item disabled"><a class="page-link disabled" href="#">Previous</a></li>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number+1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Next</a></li>
				</c:when>
				<c:when test="${boards.last }">
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number-1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Previous</a></li>
					<li class="page-item disabled"><a class="page-link disabled" href="#">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number-1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Previous</a></li>
					<li class="page-item"><a class="page-link"
						href="?page=${boards.number+1}&searchSelect=${search.searchSelect}&searchText=${search.searchText}">Next</a></li>
				</c:otherwise>
			</c:choose>


		</ul>
		<br />
		<!-- The Modal -->
		<div class="modal fade" id="myModal"  style="margin-top : 10%;">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">게시글의 비밀번호를 입력해주세요.</h4>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<input type="password" 	class="form-control" placeholder="Enter Password" id="boardPass" name="boardPass" >
					</div>

					<!-- Modal footer -->
					<div class="modal-footer" >
						<button type="button" class="btn btn-primary" id ="btn-private-chk"  style="width:47.5%; display:inline;">입력</button>
						<button type="button" class="btn btn-danger" data-dismiss="modal" style="width:47.5%; display:inline;">취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
<jsp:include page="/WEB-INF/views/cm/left.jsp" />
<script src="/js/board/board.js"></script>