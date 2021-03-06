<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>판매 목록</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" />
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	
<style type="text/css">
.pagenate {margin-left :100px;
	
}
/* 마이페이지  */
.col-lg-3{
	margin-top : 80px;
}
/*경계선*/
#border{
 border : groove;
}
/* 페이지 1~10 까지의 수 */
li {
	list-style: none;
	float: left;
	padding: 6px;
}

/* 페이지 사이즈 */
#psize {
	margin-left: 350px;
}

/* 내정보 나타내기 */
#mypagesize {
	height: 200px; webkit-fill-available;
	border: groove;
}

.search {
	text-align: center;
}
.sumarry {
   width: 500px;
   height: 150px;
   background-color: white;
   text-align: center;
   margin-left: 100px;
}
.userInform {
   border: 2px solid black;
   width: 100%;
   height: 100%;
}
.userName {
   border-right: 1px solid #ccc;
}

.sessionName {
   color: green;
   font-size: large;
   font-weight: bold;
}


.selfIcon {
   text-align: left;
   border: 1px solid white;
   border-radius: 7px;
   color: white;
   background-color: lime;
   padding: 3px;
   margin-right: 100px;
   margin-bottom: 10px;
}
.panel-default {
   border: none;
}

.panel-default>.panel-heading {
   background-color: #D1E9CA;
   border: none;
   font-weight: bold;
   font-size: 17px;
}

a {
   text-decoration: none;
   color: black;
}

a:hover {
   text-decoration: none;
}
</style>

	
<!-- 네비바를 fiexd-top으로 설정했을 때 컨텐츠와 겹치는 문제 방지 -->
<!-- Page Content -->
<%@include file="../include/header.jsp" %>
<div style="clear: both; margin-top: 170px;"></div>
<div class="container">
	<div class="row" style="width: 1200px">
		<div class="col-lg-3">
			<h3 class="my-4 text-left">체험 신청내역</h3>
			<hr>
			<div class="panel panel-default">
				<div class="panel-heading">
					<a href="<%=request.getContextPath()%>/mypage/user/modify">회원</a>
				</div>
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/modify">회원정보
						수정</a>
				</div>
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/mypageO3List">1대
						1 문의</a>
				</div>
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/myActive">활동
						신청 현황</a>
				</div>
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/deleteId"
						style="color: #ccc;">회원 탈퇴</a>
				</div>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<a href="<%=request.getContextPath()%>/mypage/user/basket">주문</a>
				</div>
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/basket">장바구니</a>
				</div>
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/orderList">구매
						목록</a>
				</div>
			</div>


			<div class="panel panel-default">
				<div class="panel-heading">
					<a href="<%=request.getContextPath()%>/mypage/user/basket">농업인
					</a>
				</div>
				<div class="panel-body">
					<a href="#">내 정보</a>
				</div>
				<div class="panel-body">
					<a href="/farmapp/mypage/selllist?farmerno=${farmerInfo.farmerNo}">판매 목록</a>
				</div>
				<div class="panel-body">
					<a href="/farmapp/mypage/dailyLoglist?farmerno=${farmerInfo.farmerNo}">영농 일지</a>
				</div>
				<div class="panel-body">
					<a href="/farmapp/mypage/activitylist?farmerno=${farmerInfo.farmerNo}"   style="font-weight: bold;">체험
						신청내역</a>
				</div>

			</div>
		</div>
		
			<div id="root" style="width: 800px; margin-left:55px;">
				<div class="sumarry"   style="width : 800px; margin-left:0px;">
				<table class="userInform">
					<tr>
						<td class="userName">
							<!-- 농부세션 확인해서 c:if 추가 -->
							<div style = "text-align:center;">
								<span class="selfIcon" style="font-size: 30px; margin-left: 100px;">농업인</span>
							</div> 
							<div style = "text-align:center;"><span class="sessionName">${farmerInfo.name } </span> 님 안녕하세요</div>
							
						</td>
					</tr>
				</table>

			</div>
			<div id="color" >
				<a id="showActive" href="/farmapp/mypage/activityFarm?farmerno=${farmerInfo.farmerNo}"
					class="list-group-item list-group-item-action text-center font-weight-bold" style="background-color : yellow;">농장
					체험 신청 목록 이동 하기</a>
			</div>
			<div id="color2">
				<a id="showActive2" href="/farmapp/mypage/activityWork?farmerno=${farmerInfo.farmerNo}" class="list-group-item list-group-item-action text-center font-weight-bold">일손 체험 신청 목록 이동 하기</a>
			</div>

			
				<hr>
			</div>
		</div>
</div>
<%@include file="../include/footer.jsp" %>
<script	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script>
	function bts(id){
		var aj =  id.value;
		console.log(aj);
		$.ajax({
             type: "get",
             url: "/farmapp/mypage/activitylist2?applicationNo="+aj,
             async: false,     //값을 리턴시 해당코드를 추가하여 동기로 변경
             success: function (data) {
                 result = data;
                 if(result == 1){
//                	 location.reload();
                 if(id.innerHTML == '승인'){
//                 	 location.reload();
//                 	 $('#border').show()
                	 alert("미승인 처리 되었습니다.");
                	 id.innerHTML = "미승인"
               		 id.style.backgroundColor= 'rgb(255,0,0)';
                	 

//                 	 $('#border.show()').load(location.href+'#border.show()');
                 }else if(id.innerHTML == '미승인'){
                	 alert("승인 처리 되었습니다.");
                	 id.innerHTML = "승인"
               		 id.style.backgroundColor= 'rgb(0,255,0)';
                	 id.style.width = '70px';
//                 	 $('#border.show()').load(location.href+'#border.show()');
//                 	 $('#showActive').load(location.href+'#showActive');
//                 		 $('#border').show(700);
                 }
                 }
             }
         });	
	}
	
       
</script>


	