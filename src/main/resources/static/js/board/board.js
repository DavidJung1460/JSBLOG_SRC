/**
 * 
 */
 var boardIdForPassChkId = "";
 
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
		$("#btn-private-chk").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.privateChk();
		});
	},
	
	save:function(){
	
		if ($("#privateChk").is(":checked")) {
			$("#privateYn").val("Y");
		} 
		if ($("#mainNoticeChk").is(":checked")) {
			$("#mainNoticeYn").val("Y");
		} 
		if ($("#noticeChk").is(":checked")) {
			$("#noticeYn").val("Y");
		} 
		var form = $('#boardSaveForm')[0];
		var data = new FormData(form);
		let pgmCd = $('#pgmCd').val();
		$.ajax({//글쓰기 수행 요청
			type : "POST",
			enctype: 'multipart/form-data',
			url : "/api/board/"+pgmCd,
			data : data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			//contentType : "application/json;charset=utf-8",
			//dataType : "json"
		}).done(function(resp){//정상
			alert("글쓰기가 완료되었습니다.");
			console.log(resp);
			location.href="/board/listForm/"+pgmCd;
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
			location.href="/board/listForm";
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("글삭제가 실패하였습니다.");
		});
	},
	update:function(){
		let id = $("#id").val();
	
		if ($("#privateChk").is(":checked")) {
			$("#privateYn").val("Y");
		} 
		if ($("#mainNoticeChk").is(":checked")) {
			$("#mainNoticeYn").val("Y");
		} 
		if ($("#noticeChk").is(":checked")) {
			$("#noticeYn").val("Y");
		} 
		var form = $('#boardUpdateForm')[0];
		var data = new FormData(form);
		
		/*// 수정인데 글번호가 자동증가 된다.
		$.ajax({//글쓰기 수행 요청
			type : "PUT",
			url : "/api/board/"+id,
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		}).done(function(resp){//정상
			alert("글수정이 완료되었습니다.");
			console.log(resp);
			location.href='/board/'+id;
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("글수정이 실패하였습니다.");
		});*/
		
		$.ajax({//글수정 수행 요청
			type : "PUT",
			enctype: 'multipart/form-data',
			url : "/api/board/"+id,
			data : data,
			processData: false,
			contentType: false,
			cache: false,
			timeout: 600000,
			//contentType : "application/json;charset=utf-8",
			//dataType : "json"
		}).done(function(resp){//정상
			alert("글 수정이 완료되었습니다.");
			console.log(resp);
			location.href='/board/'+id;
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("글 수정이 실패하였습니다.");
		});
		
	},
	replySave:function(){
		let data = {
			contents:$("#reply-content").val()
		};
		let boardId = $("#boardId").val();
		
		$.ajax({//댓글쓰기 수행 요청
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
		$.ajax({//댓글삭제 수행 요청
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
	},
	replyUpdate:function(boardId, replyId){
		$.ajax({//댓글삭제 수행 요청
			type : "PUT",
			url : "/api/board/"+boardId+"/reply/"+replyId,
			dataType : "json"
		}).done(function(resp){//정상
			alert("댓글 수정이 완료되었습니다.");
			location.href='/board/'+boardId;
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("댓글 수정이 실패하였습니다.");
		});
	},
	updateForm:function(boardId){
		location.href = "/board/"+boardId+"/updateForm";
	},
	saveForm:function(){
		var pgmCd = $("#pgmCd").val();
		location.href = "/board/saveForm/"+pgmCd;
	},
	privateChk:function(){
		let data = {
			id:boardIdForPassChkId,
			boardPass:$("#boardPass").val()
		};
		let gradeCd = $("#gradeCd").val();
		$.ajax({//댓글쓰기 수행 요청
			type : "POST",
			url : "/api/board/boardPassChk",
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			dataType : "text"
		}).done(function(message){//정상
			if(message == "일치"){
				location.href='/board/'+boardIdForPassChkId;
			} else{
				if(gradeCd!="4"){
					alert("게시물 비밀번호와 일치하지 않습니다.");
				}else{
					location.href='/board/'+boardIdForPassChkId;
				}
			}
		}).fail(function(error){//실패sda
			console.log(JSON.stringify(error));
			alert("비밀번호 조회가 실패하였습니다.");
		});
	}
}

function privatePassSave(id){
	boardIdForPassChkId=id;
}

index.init();

