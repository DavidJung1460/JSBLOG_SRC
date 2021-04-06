/**
 * 
 */
 
 let index = {
	init:function(){
		$("#btn-save").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.save();
		});
		$("#btn-delete").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.deleteById();
		});
		$("#btn-update").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.update();
		});
		$("#btn-reply-save").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.replySave();
		});
	},
	
	save:function(){
		let data = {
			title:$("#title").val(),
			contents:$("#contents").val()
		};

		$.ajax({//글쓰기 수행 요청
			type : "POST",
			url : "/api/board",
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		}).done(function(resp){//정상
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("글쓰기가 실패하였습니다.");
		});
	},

	deleteById:function(){
		var id = $("#id").text();
		$.ajax({//글삭제 수행 요청
			type : "DELETE",
			url : "/api/board/"+id,
			//data : JSON.stringify(data),
			//contentType : "application/json;charset=utf-8",
			dataType : "json"
		}).done(function(resp){//정상
			alert("글삭제가 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("글삭제가 실패하였습니다.");
		});
	},
	update:function(){
		let id = $("#id").val();
		let data = {
			title:$("#title").val(),
			contents:$("#contents").val()
		};
	
		// 수정인데 글번호가 자동증가 된다.
		$.ajax({//글쓰기 수행 요청
			type : "PUT",
			url : "/api/board/"+id,
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		}).done(function(resp){//정상
			alert("글수정이 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("글수정이 실패하였습니다.");
		});
	},
	replySave:function(){
		let data = {
			content:$("#reply-content").val()
		};
		let boardId = $("#boardId").val();
		
		$.ajax({//글쓰기 수행 요청
			type : "POST",
			url : "/api/board/"+boardId+"/reply",
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		}).done(function(resp){//정상
			alert("댓글작성이 완료되었습니다.");
			console.log(resp);
			location.href='/board/'+boardId;
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("댓글작성이 실패하였습니다.");
		});
	},
	replyDelete:function(boardId, replyId){
		$.ajax({//글쓰기 수행 요청
			type : "DELETE",
			url : "/api/board/"+boardId+"/reply/"+replyId,
			dataType : "json"
		}).done(function(resp){//정상
			alert("댓글이 삭제 완료되었습니다.");
			console.log(resp);
			location.href='/board/'+boardId;
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("댓글 삭제가 실패하였습니다.");
		});
	}
}

index.init();

