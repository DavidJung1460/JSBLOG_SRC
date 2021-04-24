<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal"/>
</sec:authorize>
<!DOCTYPE html>
<jsp:include page="/WEB-INF/views/cm/common.jsp" />
<html lang="UTF-8">
<head>
<title>Left Page</title>
<script src="/js/cm/common.js"></script>
<script type="text/javascript">
let leftMenu = {
		init:function(){
			this.left();
			this.leftAuthDate();
		},
		
		left:function(){
			$.ajax({//회원수정 수행 요청
				type : "POST",
				url : "/auth/left",
				//data : param,
				contentType : "application/json;charset=utf-8",
				dataType : "json"
			}).done(function(data){//정상
				var menu = "";
				var results = data;
				$.each(results,function(i){
					if(results[i].leftMenuYn!= "N"){
						menu += "<li><a href="+results[i].pgmUrl+">"+results[i].pgmName+"</a></li>";
					}
				});
				$("#menuList").append(menu);
			}).fail(function(error){//실패
				alert("메뉴 조회에 실패하였습니다.");
			});
		},
		leftAuthDate:function(){
			$.ajax({//회원수정 수행 요청
				type : "POST",
				url : "/auth/leftAuthDate",
				//data : param,
				contentType : "application/json;charset=utf-8",
				dataType : "json"
			}).done(function(data){//정상
					var useAuthDate1 = "";
					var useAuthDate2 = "";
					var result = data;
					if(result!= "" && result!=null){
						useAuthDate1 = "기간 : "+result.gradeStartDate.substr(0,10)+" ~";
						useAuthDate2 = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+result.gradeExDate.substr(0,10);
					}
				$("#gradeStartDate").append(useAuthDate1);
				$("#gradeExDate").append(useAuthDate2);
			}).fail(function(error){//실패
			});
		}
	}
leftMenu.init();
</script>
</head>
<body class="is-preload" >
	<!-- Sidebar -->
	<div id="sidebar">

		<!-- Logo -->
		<h1 id="logo">
			<a href="/">JSpring Investment</a>
		</h1>
		<c:if test="${!empty principal}">
			<section class="box text-style1">
				<div class="inner">
					<p><strong>${principal.user.nickName} 님 반갑습니다.</strong></p>
					<p><strong> 등급 : ${principal.user.grade.gradeName}</strong></p>
					<c:if test="${principal.user.grade.gradeCd gt 2}">
						<p><strong id ="gradeStartDate"></strong><br/>
							   <strong id="gradeExDate"></strong></p>
					</c:if>
					<p><strong> 보유 할인권 : 0 장</strong></p>
				</div>
			</section>
		</c:if>
		
		<!-- Nav -->
		<nav id="nav">
			<ul id="menuList">
			</ul>
		</nav>

		<!-- Search -->
		<section class="box search">
			<form method="post" action="#">
				<input type="text" class="text" name="search" placeholder="Search" />
			</form>
		</section>

		<!-- Text -->
		<section class="box text-style1">
			<div class="inner">
				<p>
					<a href="https://www.youtube.com/channel/UC2wL9_9WcvyMUs1lvSs0-CA"target="_blank"> 
						<img alt="YouTube 아이콘" src="/images/youtube-icon-01.png"	style="width: 25px; height: 15px;"> 
						<strong>Youtube<br />
						정봄 인베스트<br /></strong>
					 <strong>경제/시사 분석<br />추천 종목 리딩 영상
					</strong></a>
				</p>
			</div>
		</section>

		<!-- Recent Posts -->
		<%-- <section class="box recent-posts">
			<header>
				<h2>Recent Posts</h2>
			</header>
			<ul>
				<li><a href="#">Lorem ipsum dolor</a></li>
				<li><a href="#">Feugiat nisl aliquam</a></li>
				<li><a href="#">Sed dolore magna</a></li>
				<li><a href="#">Malesuada commodo</a></li>
				<li><a href="#">Ipsum metus nullam</a></li>
			</ul>
		</section> --%>

		<!-- Recent Comments -->
		<%-- <section class="box recent-comments">
			<header>
				<h2>Recent Comments</h2>
			</header>
			<ul>
				<li>case on <a href="#">Lorem ipsum dolor</a></li>
				<li>molly on <a href="#">Sed dolore magna</a></li>
				<li>case on <a href="#">Sed dolore magna</a></li>
			</ul>
		</section> --%>

		<!-- Calendar -->
		<section class="box calendar">
			<div class="inner">
				<table id="calendar">
					<caption id="tbCalendarYM">July 2014</caption>
					<thead>
						<tr>
							<th scope="col" title="Sunday">S</th>
							<th scope="col" title="Monday">M</th>
							<th scope="col" title="Tuesday">T</th>
							<th scope="col" title="Wednesday">W</th>
							<th scope="col" title="Thursday">T</th>
							<th scope="col" title="Friday">F</th>
							<th scope="col" title="Saturday">S</th>
						</tr>
					</thead>
					<tbody>
						<tr></tr>
						<tr></tr>
						<tr></tr>
						<tr></tr>
						<tr></tr>
					</tbody>
				</table>
			</div>
		</section>

		<!-- Copyright -->
		<ul id="copyright">
			<li>&copy; JSpring. Investment.</li>
			<li>📞010-****-****</li>
			<li>🏛 경기도 성남시</li>
			<li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
		</ul>

	</div>

	<!-- Scripts -->
	<script type="text/javascript"> buildCalendar();</script>
	<script src="/assets/js/jquery.min.js"></script>
	<script src="/assets/js/browser.min.js"></script>
	<script src="/assets/js/breakpoints.min.js"></script>
	<script src="/assets/js/util.js"></script>
	<script src="/assets/js/main.js"></script>
</body>
</html>
