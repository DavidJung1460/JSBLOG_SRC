<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/cm/header.jsp" />
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>Main Page</title>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
</head>
<body>
	<div class="container">
		<form action="" method="post">
			<input id="id" type="hidden" value="${board.id}">
			<div class="form-group">
				<label for="title">Title:</label> 
				<input type="text" class="form-control" placeholder="Enter Title" id="title" name="title" value="${board.title}">
			</div>
			<div class="form-group">
				<label for="comments">Content:</label>
				<textarea class="form-control summernote" rows="5"  id="contents" name="contents" >${board.contents}</textarea>
			</div>
		</form>
		<button id="btn-update" class="btn btn-primary">수정</button>
		<br/><br/>
	</div>
</body>
</html>
<script>
      $('.summernote').summernote({
        placeholder: 'Enter Contents',
        tabsize: 2,
        height: 300
      });
</script>
<script src="/js/board/board.js"></script>
<jsp:include page="/WEB-INF/views/cm/footer.jsp" />