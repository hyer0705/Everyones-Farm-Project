<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- header include -->
<%@include file="../include/admin_header.jsp" %>
<!-- list css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/oneonone/admin_oneonone_list.css" />
<!-- 검색창 css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/admin_search_input.css" />
<!-- admin pagination css -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/css/admin/admin_pagination.css" />

<!-- body 부분 -->
<div class="wrapper">
	<main class="main__oneonone">
		<!-- 1대1문의 제목 -->
		<div class="oneonone__title-wrap">
			<span class="oneonone__title-text">[농업인 회원] 1대1 문의</span>
		</div>
		
		<!-- 목록 테이블 -->
		<div class="oneonone__table-wrap">
			<table class="oneonone__table">
				<colgroup>
					<col width="10%">
					<col width="45%">
					<col width="15%">
					<col width="15%">
					<col width="15%">
				</colgroup>
				<tr class="oneonone__tr-head">
					<th>번호</th>
					<th>제목</th>
					<th>문의자</th>
					<th>작성일</th>
					<th>답변상태</th>
				</tr>
				<c:choose>
					<%-- userOneOnOneList 가 null 일 때 --%>
					<c:when test="${empty farmerOneOnOneList }">
						<tr class="oneonone__tr-data">
							<td colspan="5">조회된 문의가 없습니다.</td>
						</tr>
					</c:when>
					<%-- userOneOnOneList 가 null이 아닐 때 --%>
					<c:when test="${not empty farmerOneOnOneList }">
						<c:forEach items="${farmerOneOnOneList }" var="f">
						<tr class="oneonone__tr-data">
							<td><a href="<%=request.getContextPath() %>/admin/oneonone/farmer/detail?questionNo=${f.questionNo }">${f.questionNo }</a></td>
							<td><div class="oneonone__td-title"><a href="<%=request.getContextPath() %>/admin/oneonone/farmer/detail?questionNo=${f.questionNo }">${f.title }</a></div></td>
							<td><div class="oneonone__td-name">${f.farmerId }</div></td>
							<td>
							<fmt:formatDate value="${f.regDate }" pattern="yyyy-MM-dd"/>
							</td>
							<td>
								<c:choose>
									<c:when test="${f.isAnswered eq 0 }">
										<c:out value="답변 대기"></c:out>
									</c:when>
									<c:when test="${f.isAnswered eq 1 }">
										<c:out value="답변 완료"></c:out>
									</c:when>
								</c:choose>
							</td>
						</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
		</div>
		
		<!-- 검색창 -->
		<div class="member-list__search-wrap">
			<input type="text" name="search" id="member-search" placeholder="검색어를 입력하세요" /><button id="search-btn">검색</button>
		</div>
		
		<!-- include pagination -->
		<%@include file="./farmer_pagination.jsp" %>
		
	</main>
</div>

<!-- 검색 이벤트 js -->
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/admin/oneonone/admin_oneonone_farmer_search_event.js"></script>
<!-- footer include -->
<%@include file="../include/admin_footer.jsp" %>