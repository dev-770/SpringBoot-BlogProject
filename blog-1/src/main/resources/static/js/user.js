let index = {
	init: function() {
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save: function() {
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};

		//console.log(data);
		
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			data: JSON.stringify(data), // http bodydata
			contentType: "application/json; charset=utf-8", // MINE TYPE
			dataType: "json" // 요청 -> 응답 Default: String[JSON] => jsObject로 변경
		}).done(function(resp){
			alert("회원가입이 완료되었습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		}); // ajax통신을 이용, json 변경->insert 요청
	}
}

index.init();

