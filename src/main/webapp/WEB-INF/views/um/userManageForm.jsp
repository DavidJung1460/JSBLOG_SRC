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
<title>userManageForm Page</title>
</head>
<body>
	<div id="content" style="box-shadow: 0 0 0em 0em rgba(0, 0, 0, 0);">
		<form>
			<input type="hidden" id="id" value="${user.id}">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" value="${user.username}"
					placeholder="Enter username" id="username" name="username" readonly>
			</div>

			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" placeholder="Enter password" id="password"
					name="password">
			</div>
			<div class="form-group">
				<label for="email">Email address:</label> <input type="email"
					class="form-control" value="${user.email}"
					placeholder="Enter email" id="email" name="email">
			</div>
			<div class="form-group">
				<label for="nickName">Nick name:</label> <input type="text"
					class="form-control" value="${user.nickName}"
					placeholder="Enter Nick name" id="nickName" name="nickName">
			</div>

			<div class="form-group">
				<label for="ip">IP:</label> <input type="text" class="form-control"
					value="${user.ip}" id="ip" name="ip" readonly="readonly" >
				<button id="btn-delete" class="btn btn-danger">차단</button>
				<button id="btn-delete" class="btn btn-primary">차단해제</button>
			</div>

			<div class="form-group">
				<label for="gradeName">Grade:</label> <br /> <select
					class="form-control" id="gradeName" name="gradeName"
					style="display: inline; width: 30%;">
					<c:forEach var="grade" items="${grade}">
						<c:choose>
							<c:when test="${user.grade.gradeName eq grade.gradeName}">
								<option selected="selected">${grade.gradeName}</option>
							</c:when>
							<c:otherwise>
								<option>${grade.gradeName}</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div id="datePickerDiv">
				<c:choose>
					<c:when test="${!empty useAuth}">
						<%-- <fmt:parseDate var="transGradeStartDate"
							value="${useAuth.gradeStartDate}" pattern="yyyy-MM-dd'T'HH:mm"
							type="both" />
						<fmt:formatDate pattern="yyyy.dd.MM"
							value="${transGradeStartDate}" />
						<fmt:parseDate var="transGradeExDate"
							value="${useAuth.gradeExDate}" pattern="yyyy-MM-dd'T'HH:mm"
							type="both" />
						<fmt:formatDate pattern="yyyy.dd.MM" value="${transGradeExDate}" /> --%>

						<label for="datePicker">권한기간:</label>
						<br />
						<input type="text" id="gradeStartDate" name="gradeStartDate"
							class="calcal" placeholder="Click! "
							style="width: 30%; display: inline;"
							value="${fn:substring(useAuth.gradeStartDate,0,10)}">
				~ <input type="text" id="gradeExDate" name="gradeExDate"
							class="calcal" placeholder="Click! "
							style="width: 30%; display: inline;" value="${fn:substring(useAuth.gradeExDate,0,10)}">
					</c:when>
					<c:otherwise>
						<label for="datePicker">권한기간:</label>
						<br />
						<input type="text" id="gradeStartDate" name="gradeStartDate"
							class="calcal" placeholder="Click! "
							style="width: 35%; display: inline;">
				~ <input type="text" id="gradeExDate" name="gradeExDate"
							class="calcal" placeholder="Click! "
							style="width: 35%; display: inline;">
					</c:otherwise>
				</c:choose>
			</div>
		</form>
		<br />
		<button id="btn-Manage" class="btn btn-primary">수정</button>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	</div>
	<br />


</body>
</html>
<script type="text/javascript">
	var gradeCdChk = '<c:out value="${user.grade.gradeCd}"/>';
	if(gradeCdChk < 3){
		$('#datePickerDiv').hide();
	}
	$('#gradeStartDate').datepicker({
		format : "yyyy-mm-dd", // 달력에서 클릭시 표시할 값 형식
		language : "kr", // 언어(js 추가가 필요하다.)
		todayHighlight : true
	});
	$('#gradeExDate').datepicker({
		format : "yyyy-mm-dd", // 달력에서 클릭시 표시할 값 형식
		language : "kr",// 언어(js 추가가 필요하다.)
		todayHighlight : true
	});
	$('#gradeName').on('change', function() {
		if (this.value == 'CUSTOMER' || this.value == 'USER') {
			$('#datePickerDiv').hide();
		} else {
			$('#datePickerDiv').show();
		}
	});
</script>
<jsp:include page="/WEB-INF/views/cm/left.jsp" />
<script src="/js/um/user.js"></script>
