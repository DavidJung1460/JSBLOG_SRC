/**
 * 
 */
 
 let index = {
	init:function(){
		$("#btn-save").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.save();
		});
		$("#btn-update").on("click",()=>{//function(){} -> ()=>{} 사용이유 this를 바인딩하기 위해서! function을 사용하면 window객체를 가르킴
			this.update();
		});
	},
	
	save:function(){
		let data = {
			username:$("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		//console.log(data);
		
		//AJAX 호추시 default가 비동기 호출
		//AJAX 통신을 이용하여 데이터를 json으로 변경하여 insert요청
		$.ajax({//회원가입 수행 요청
			type : "POST",
			url : "/auth/um/joinProc",
			data : JSON.stringify(data),//javascript 오브젝트를 json으로 변환 받을때 httpRequestBody 필요
			contentType : "application/json;charset=utf-8",
			//응답이 왔을때 String 형식으로 buffer 전달 되나 전달받은 데이터 형식이 json이면 javascript object로 변환해줌
			//명시하지 않아도 알아서 오브젝트로 파싱해준다. 서버가 json으로 리턴해주면 javascript object로 변환
			dataType : "json"
		}).done(function(resp){//정상
			if(resp.status === 500){
				alert("회원가입이 실패하였습니다.")
			} else{
				alert("회원가입이 완료되었습니다.");
				console.log(resp);
				location.href="/";
			}
			
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("회원가입이 실패하였습니다.");
		});
	},
	
	update:function(){
		let data = {
			id:$("#id").val(),
			username: $("#username").val(),
			password:$("#password").val(),
			email:$("#email").val()
		};
		
		$.ajax({//회원수정 수행 요청
			type : "PUT",
			url : "/um/userUpdateProc",
			data : JSON.stringify(data),
			contentType : "application/json;charset=utf-8",
			dataType : "json"
		}).done(function(resp){//정상
			alert("회원수정이 완료되었습니다.");
			console.log(resp);
			location.href="/";
		}).fail(function(error){//실패
			console.log(JSON.stringify(error));
			alert("회원수정이 실패하였습니다.");
		});
	}
}

index.init();

