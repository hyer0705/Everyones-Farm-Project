<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@include file="../../include/header.jsp" %>

<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
</head>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/mypage/deleteId.css" />
<style type="text/css">
ul {
	margin-bottom: 0px;
}
</style>
<!-- 네비바를 fiexd-top으로 설정했을 때 컨텐츠와 겹치는 문제 방지 -->
<div style="margin-top:200px"></div>
	<!-- Page Content -->
	<div class="headSpace" style="margin-top:200px;"></div>
	<div class="container">
		<div class="row">
		<div class="col-lg-3">
			<h3 class="my-4 text-left">회원 탈퇴</h3>
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
				<c:if test="${farmerInfo eq null }">
				<div class="panel-body">
					<a href="<%=request.getContextPath()%>/mypage/user/myActive">활동
						신청 현황</a>
				</div>
				</c:if>
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

			<c:if test="${farmerInfo ne null }">
				<div class="panel panel-default">
					<div class="panel-heading">
						<a href="<%=request.getContextPath()%>/mypage/user/basket">농업인
						</a>
					</div>
					<div class="panel-body">
						<a href="#">내 정보</a>
					</div>
					<div class="panel-body">
						<a href="/farmapp/mypage/selllist">판매 목록</a>
					</div>
					<div class="panel-body">
						<a href="/farmapp/mypage/dailyLoglist">영농 일지</a>
					</div>
					<div class="panel-body">
						<a href="/farmapp/mypage/activitylist" style="font-weight: bold;">체험
							신청내역</a>
					</div>

				</div>
			</c:if>
		</div>
			<div class="col-lg-1">
			<div class="emptyArea"></div>
			
				<h3><span class="glyphicon glyphicon-alert"></span>
				정말로 회원 탈퇴를 하시겠습니까? <br>그럼 비밀번호를 입력해주세요.</h3>
				<div class="ModifyUserInfo">

					<form action="<%=request.getContextPath() %>/leave" method="post">
						<!-- 			아이디 -->
					
						<!-- 			비밀번호 -->
						<div class="input-group">
							<label for="inputName" class="col-lg-3 control-label">비밀번호</label>
							<div class="input-group input-group-lg">
								<span class="input-group-addon glyphicon glyphicon-lock"
									id="sizing-addon1"></span> <input type="password"
									class="form-control" placeholder="비밀번호" name="userPW"
									aria-describedby="sizing-addon1">
							</div>
						</div>
						
						


						<button type="submit" class="btn btn-warning" onclick="alert('정말 탈퇴하시겠습니까?')">회원 탈퇴</button>

					</form>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../../include/footer.jsp" %>