<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">

	<c:if test="${board.user.id == principal.user.id }">
		<a href="/board/${board.id }/updateForm" class="btn btn-warning">수정</a>
		<button id="btn-delete" class="btn btn-danger">삭제</button>
	</c:if>
	<button class="btn btn-info" onclick="history.go(-1)">뒤로</button>
	<br/><br/>
	
	<div>
		글번호 : <span id="id"><i>${board.id }</i></span>
		작성자 : <span><i>${board.user.username }</i></span>
	</div>
	
	<div class="form-group">
		<h3>제목 : ${board.title }</h3>
	</div>

	<div class="form-group">
		<div>${board.content}</div>
	</div>
</div>

<script>
	$('.summernote').summernote({
		placeholder : "내용을 입력하세요.",
		tabsize : 2,
		height : 300,
	});
</script>
<script src="/js/board.js"></script>

<%@ include file="../layout/footer.jsp"%>
