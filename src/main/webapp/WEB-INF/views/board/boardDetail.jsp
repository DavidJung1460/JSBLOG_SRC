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
<title>Main Page</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<div class="container" style="color: black;">
		<div>
			<br /><br /> 
			글 번호 : <span id="id"><i>${board.id} </i></span> 작성자 : <span><i>${board.user.username}
			</i></span> <br />
			<br />
		</div>
		<div class="form-group">
			<h3>${board.title}</h3>
		</div>
		<hr style="border: solid 1px gray;">
		<div class="form-group">
			<div>${board.contents}</div>
		</div>
		<hr style="border: solid 1px gray;">
		<br />
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<c:if test="${board.user.id == principal.user.id}">
			<!-- <button id ="btn-update"class="btn btn-warning">수정</button> -->
			<a href="/board/${board.id}/updateForm" class="btn btn-warning">수정</a>
			<button id="btn-delete" class="btn btn-danger">삭제</button>
		</c:if>
		<br/>
		<br/>
		<div class="card">
			<form>
				<input type="hidden" id="boardId" value="${board.id}">
				<div class="card-body">
					<textarea id="reply-content" class="form-control" rows="1"></textarea>
				</div>
				<div class="card-footer">
					<button type="button" id="btn-reply-save" class="btn btn-primary">등록</button>
				</div>
			</form>
		</div>
		<br/>
		<div class="card">
			<div class="card-header card-headre-flush">댓글 리스트</div>
			<ul id="reply-box" class="list-group list-group-flush">
				<c:forEach var="reply" items="${board.replys}">
					<li id="reply-${reply.id}" class="list-group-item d-flex justify-content-between">
						<div>${reply.content}</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply.user.username}</div>	&nbsp;
							<c:if test="${principal.user.username == reply.user.username}">
								<button class="badge" onclick="index.replyDelete(${board.id},${reply.id})">삭제</button>
							</c:if>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</body>
<br/>
</html>

<script src="/js/board/board.js"></script>
<jsp:include page="/WEB-INF/views/cm/footer.jsp" />