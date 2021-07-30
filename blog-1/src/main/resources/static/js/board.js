let index = {
	init: function() {
		$("#btn-insert").on("click", ()=>{
			this.insert();
		});
		$("#btn-update").on("click", ()=>{
			this.update();
		});
		$("#btn-delete").on("click", ()=>{
			this.deleteById();
		});
		$("#btn-reply-insert").on("click", ()=>{ 
			this.replyInsert();
		});
	},
	
	insert: function() {
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/api/board",
			data: JSON.stringify(data), // http bodydata
			contentType: "application/json; charset=utf-8", // MINE TYPE
			dataType: "json" // 요청 -> 응답 Default: String[JSON] => jsObject로 변경
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax통신을 이용, json 변경->insert 요청
	},

	update: function() { 

		let data = {
			id: $("#id").val(),
			user: $("#user").val(),
			title: $("#title").val(),
			content: $("#content").val()
		};

		// console.log(data);
		
		$.ajax({
			type: "PUT",
			url: `/api/board/${data.id}`,
			data: JSON.stringify(data), // http bodydata
			contentType: "application/json; charset=utf-8", // MINE TYPE
			dataType: "json" // 요청 -> 응답 Default: String[JSON] => jsObject로 변경
		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax통신을 이용, json 변경->insert 요청
	},

	deleteById: function() {
		var id = $("#id").text();

		$.ajax({
			type: "DELETE",
			url: "/api/board/"+id,
			dataType: "json" // 요청 -> 응답 Default: String[JSON] => jsObject로 변경
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax통신을 이용, json 변경->insert 요청
	},

	replyInsert: function(){
		let data = {
				userId: $("#userId").val(),
				boardId: $("#boardId").val(),
				content: $("#reply-content").val()
		};

		// console.log(data);
		
		$.ajax({ 
			type: "POST",
			url: `/api/board/${data.boardId}/reply`,
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp){
			alert("댓글작성이 완료되었습니다.");
			location.href = `/board/${data.boardId}`;
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); 
	}

}


index.init();