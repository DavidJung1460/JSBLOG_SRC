<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />
<html lang="UTF-8">
<head>
<title>Main Page</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<div id="content" style="box-shadow: 0 0 0em 0em rgba(0, 0, 0, 0);">
		<div>
			<br /><br /> 
			글 번호 : <span id="id"><i>${board.id} </i></span>&nbsp; &nbsp;
			작성자 : <span><i>${board.user.nickName}	</i></span>&nbsp;&nbsp;
			등록일자 : 
			<span>
				<i>
					<fmt:parseDate var="regDate" value="${board.regDate}" pattern="yyyy-MM-dd'T'HH:mm" type="both"/>
					<fmt:formatDate pattern="yyyy.dd.MM" value="${regDate}"/>
				</i></span> &nbsp;&nbsp; <br/><br/>
			<%-- 조회수 : <span><i>${board.count}	</i></span>&nbsp;&nbsp;<br /><br /> --%>
		</div>
		<div class="form-group">
			<h3>${board.title}</h3>
		</div>
		<hr style="border-top: solid 2px gray;">
		<div class="form-group">
			<div><%-- ${fn:replace(fn:replace(board.contents,'&lt;','<'),'&gt;','>')} --%>
				<%-- <c:out value="${board.contents}" /> --%>
				${board.contents}
			</div>
		</div>
		<hr style="border-top: solid 2px gray;">
		<br />
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		
		<c:if test="${board.user.id eq principal.user.id || principal.user.grade.gradeCd eq 4}">
			 <button id ="btn-updateForm"class="btn btn-warning" onclick="index.updateForm(${board.id})">수정</button>
<%-- 			<a href="/board/${board.id}/updateForm" class="btn btn-warning" >수정</a>
 --%>			<button id="btn-delete" class="btn btn-danger">삭제</button>
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
						<div>${reply.contents}</div>
						<div class="d-flex">
							<div class="font-italic">작성자 : ${reply.user.nickName}</div>	&nbsp;&nbsp;
							<c:if test="${principal.user.username eq reply.user.username || principal.user.grade.gradeCd eq 4}">
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
<script type="text/javascript">
$("#btn-reply-update-show").on("click",()=>{
	$("")
	$("#reply-update-div").show();
});
</script>
<jsp:include page="/WEB-INF/views/cm/left.jsp" />
<script src="/js/board/board.js"></script>
