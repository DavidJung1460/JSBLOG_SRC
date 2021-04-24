<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
		<form action="#" id="boardSaveForm" method="post" enctype="multipart/form-data">
			<input type="hidden" id="pgmCd" name="pgmCd" value="${pgmCd}">
			<div class="form-group">
				<label for="title">Title:</label>
				<label for="privateChk" style="float:right;">비공개 게시글 : <input type="checkbox" id="privateChk" ></label>
				<label for="noticeChk" style="float:right; margin-right: 2%">공지 등록 : <input type="checkbox" id="noticeChk" ></label>
				<label for="mainNoticeChk" style="float:right; margin-right: 2%;">메인화면 현출 : <input type="checkbox" id="mainNoticeChk" ></label>
				<input type="text" class="form-control" placeholder="Enter Title" id="title" name="title" >
				<input type="hidden" id="privateYn" name="privateYn" value="N">
				<input type="hidden" id="mainNoticeYn" name="mainNoticeYn" value="N">
				<input type="hidden" id="noticeYn" name="noticeYn" value="N">
			</div>
			<div class="form-group"  id="boardPassDiv" style="display: none;">
				<label for="boardPass">게시물 비밀번호:</label> <input type="password"
					class="form-control" placeholder="Enter Password" id="boardPass"
					name="boardPass" >
			</div>
			<div class="form-group"  id="subjectDiv" style="display: none;">
				<label for="subject">Subject:</label> <input type="text"
					class="form-control" placeholder="Enter Title" id="subject"
					name="subject" >
			</div>
			<div class="form-group"  id="fileUploadDiv" style="display: none;">
				<label for="inputFile" >썸네일 이미지:</label>
					<div class="custom-file" id="inputFile">
						<input name="file" type="file" class="custom-file-input"
							id="customFile"> <label class="custom-file-label"
							for="customFile">파일을 선택해 주세요.</label>
					</div>
			</div>
			<div class="form-group">
				<label for="comments">Content:</label>
				<textarea class="form-control summernote" rows="5" id="contents"
					name="contents"></textarea>
			</div>
			
		</form>
		<button id="btn-save" class="btn btn-primary">작성</button>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
		<br /> <br />
	</div>
</body>
</html>
<script>
	$('.summernote').summernote({
		placeholder : 'Enter Contents',
		tabsize : 2,
		height : 300
	});
	$('#mainNoticeChk').on('change', function() {
		if ($("#mainNoticeChk").is(":checked")) {
			$('#subjectDiv').show();
			$('#fileUploadDiv').show();
		} else {
			$('#subjectDiv').hide();
			$('#fileUploadDiv').hide();
		}
	});
	$('#privateChk').on('change', function() {
		if ($("#privateChk").is(":checked")) {
			$('#boardPassDiv').show();
		} else {
			$('#boardPassDiv').hide();
		}
	});
	$(".custom-file-input").on("change", function() {
		  var fileName = $(this).val().split("\\").pop();
		  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
		});
</script>

<jsp:include page="/WEB-INF/views/cm/left.jsp" />
<script src="/js/board/board.js"></script>
