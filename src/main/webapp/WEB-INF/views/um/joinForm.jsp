<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />
<!DOCTYPE html>
<html lang="UTF-8">
<head>
<title>joinForm Page</title>
</head>
<body>
	<div id="content"  style="box-shadow: 0 0 0em 0em rgba(0, 0, 0, 0);">
		<form>
			<div class="form-group">
				<label for="username">Username (아이디):</label> 
				<input type="text"
					class="form-control" placeholder="Enter username" id="username" name="username">
			</div>
			<div class="form-group">
				<label for="pwd">Password (비밀번호):</label> 
				<input type="password"
					class="form-control" placeholder="Enter password" id="password" name="password">
			</div>
			<div class="form-group">
				<label for="username">Nickname (별명):</label> 
				<input type="text"
					class="form-control" placeholder="Enter username" id="nickName" name="nickName">
			</div>
			<!-- <div class="form-group">
				<label for="pwd">Password Check:</label> 
				<input type="password"
					class="form-control" placeholder="Enter password Check" id="pwdChk">
			</div> -->
			<div class="form-group">
				<label for="email">Email address:</label> 
				<input type="email"
					class="form-control" placeholder="Enter email" id="email" name="email">
			</div>
			<!-- Card -->
<div class="card card-image"
  style="background-image:  url('/images/cm/flower.png');  /* url(https://mdbcdn.b-cdn.net/img/Photos/Horizontal/Work/4-col/img%20%2814%29.jpg); */
  background-repeat : no-repeat;
	background-size: cover; ">

  <!-- Content -->
  <div class="rgba-black-strong py-5 px-4">
    <div class="row d-flex justify-content-center">
      <div class="col-md-10 col-xl-8">

        <!--Accordion wrapper-->
        <div class="accordion md-accordion accordion-5" id="accordionEx5" role="tablist"
          aria-multiselectable="true">

          <!-- Accordion card -->
          <div class="card mb-4">

            <!-- Card header -->
            <div class="card-header p-0 z-depth-1" role="tab" id="heading30">
              <a data-toggle="collapse" data-parent="#accordionEx5" href="#collapse30" aria-expanded="true"
                aria-controls="collapse30">
                <i class="fas fa-cloud fa-2x p-3 mr-4 float-left black-text" aria-hidden="true"></i>
                <h4 class="text-uppercase white-text mb-0 py-3 mt-1">
                  정봄 인베스트먼트 <br/>이용약관
                </h4>
              </a>
            </div>

            <!-- Card body -->
            <div id="collapse30" class="collapse show" role="tabpanel" aria-labelledby="heading30"
              data-parent="#accordionEx5">
              <div class="card-body rgba-black-light white-text z-depth-1">
                <p class="p-md-4 mb-0">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                  terry richardson ad squid. 3 wolf moon officia aute,
                  non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch
                  3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda
                  shoreditch et.</p>
              </div>
            </div>
          </div>
          <!-- Accordion card -->

          <!-- Accordion card -->
          <div class="card mb-4">

            <!-- Card header -->
            <div class="card-header p-0 z-depth-1" role="tab" id="heading31">
              <a data-toggle="collapse" data-parent="#accordionEx5" href="#collapse31" aria-expanded="true"
                aria-controls="collapse31">
                <i class="fas fa-comment-alt fa-2x p-3 mr-4 float-left black-text" aria-hidden="true"></i>
                <h4 class="text-uppercase white-text mb-0 py-3 mt-1">
                  개인정보처리방침
                </h4>
              </a>
            </div>

            <!-- Card body -->
            <div id="collapse31" class="collapse" role="tabpanel" aria-labelledby="heading31"
              data-parent="#accordionEx5">
              <div class="card-body rgba-black-light white-text z-depth-1">
                <p class="p-md-4 mb-0">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                  terry richardson ad squid. 3 wolf moon officia aute,
                  non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch
                  3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda
                  shoreditch et.</p>
              </div>
            </div>
          </div>
          <!-- Accordion card -->

          <!-- Accordion card -->
          <div class="card mb-4">

            <!-- Card header -->
            <div class="card-header p-0 z-depth-1" role="tab" id="heading32">
              <a data-toggle="collapse" data-parent="#accordionEx5" href="#collapse32" aria-expanded="true"
                aria-controls="collapse32">
                <i class="fas fa-cogs fa-2x p-3 mr-4 float-left black-text" aria-hidden="true"></i>
                <h4 class="text-uppercase white-text mb-0 py-3 mt-1">
                 정봄 인베스트먼트 <br/>프로모션 내역
                </h4>
              </a>
            </div>

            <!-- Card body -->
            <div id="collapse32" class="collapse" role="tabpanel" aria-labelledby="heading32"
              data-parent="#accordionEx5">
              <div class="card-body rgba-black-light white-text z-depth-1">
                <p class="p-md-4 mb-0">Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus
                  terry richardson ad squid. 3 wolf moon officia aute,
                  non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch
                  3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda
                  shoreditch et.</p>
              </div>
            </div>
          </div>
          <!-- Accordion card -->
        </div>
        <!--/Accordion wrapper-->

      </div>
    </div>
  </div>
  <!-- Content -->
</div>
<!-- Card -->
<br/>
			<div class="form-group form-check">
				<label class="form-check-label"> 
				<input	class="form-check-input" type="checkbox" id="perInfoChk" > 이용약관 및 개인정보처리방침 동의 여부 (필수)<br/>
				<input	class="form-check-input" type="checkbox" id="promotionChk"> 프로모션 정보 제공 동의 여부 (선택)
				</label>
			</div>
		</form>
		<button id="btn-save" class="btn btn-primary">회원가입</button>
		<button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
	</div>
	<br />
</body>
</html>
<jsp:include page="/WEB-INF/views/cm/left.jsp" />
<script src="/js/um/user.js"></script>
