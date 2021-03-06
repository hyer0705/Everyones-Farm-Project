<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@include file="../include/header.jsp" %>


<link rel="stylesheet" href="<%= request.getContextPath() %>/resources/css/activity/experienceList.css" />
<script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>

<script type="text/javascript">
$(document).ready(function() {
	
	/* 이미지 파일이 없는 체험 활동에 기본 이미지 출력 */
	$(".list-image").each(function() {
		if($(this).find("img").length == 0) {
			$(this).prepend($("<img src='<%=request.getContextPath() %>/resources/image/activity/no-image.png' width='270' height='270'>"))
		} 
	})
	
	/* 엔터 누르면 검색 버튼 클릭 이벤트 발생 */
	$(".title").keypress(function(event) {
		if(event.which == 13) {
			$(".btnSearch").click();
			return false;
		}
	}) 
	
})
</script>

<div style="margin-top:170px"></div>


<div class="activity-top">
	<div class="top-div">
		<div class="top-title">농장 체험</div>
		<div class="top-sub">Farm Activity</div>
	</div>
</div>

<div class="activity-content">
	

	<div class="activity-search">
		<form action="<%=request.getContextPath()%>/activity/activityList" method="post">
			<input type="hidden" name="isHelp" value="0">
			<select name="filter" class="filter">
				<option value="activityTitle">체험명</option>
				<option value="farmName">농장명</option>
			</select>
			<input type="text" name="title" class="title">
			<button class="btnSearch"><img alt="검색" src="<%=request.getContextPath() %>/resources/image/findfarm/search_icon.png" width="25"></button>
		</form>
	</div>

	
	<div class="activity-list">
		<c:forEach items="${activityList }" var="activity">
	
		<div class="row" onclick="javascript:location.href='<%=request.getContextPath()%>/activity/activityDetail.do?activityNo=${activity.activityNo}'">
		
			<div class="list-image">
			
				<c:forEach items="${fileList }" var="file">
					<c:if test="${file.postNo eq activity.activityNo }">
						<img src="<%=request.getContextPath() %>/resources/upload/${file.fileRename }" alt="체험활동 이미지" width="270" height="270">	
					</c:if>
				</c:forEach>
				
			</div>
			
			
			<div class="caption">
				<div class="caption-title">${activity.title }</div>
				<div class="caption-content">${activity.farmName }</div>
				<div class="caption-content">${activity.firstAddress } ${activity.secondAddress } (${activity.zonecode })</div>
			</div>
			
		
		</div>
		
		</c:forEach>
		
		
		<!-- section pagination -->
		<c:choose>
			<c:when test="${filter eq null && title eq null}">
				<div class="paging">
					<div class="paging-in">
						<a href="<%=request.getContextPath()%>/activity/activityList?isHelp=0" class="nav first"></a>
					</div>
					<c:choose>
						<c:when test="${paging.currentPage eq 1 }">
							<div class="paging-in pre">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage}&isHelp=0" class="nav prev"></a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="paging-in pre">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage-1}&isHelp=0" class="nav prev"></a>
							</div>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
						<div class="paging-in">
							<c:choose>
								<c:when test="${page eq paging.currentPage }">
									<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${page}&isHelp=0" class="num active green">${page}</a>
								</c:when>
								<c:otherwise>
									<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${page}&isHelp=0" class="num active">${page}</a>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
			
					<c:choose>
						<c:when test="${paging.currentPage eq paging.lastPage }">
							<div class="paging-in nex">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage}&isHelp=0" class="nav next"></a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="paging-in nex">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage+1}&isHelp=0" class="nav next"></a>
							</div>
						</c:otherwise>
					</c:choose>
			
					<div class="paging-in">
						<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.lastPage}&isHelp=0" class="nav last"></a>
					</div>
				</div>
			</c:when>
	
	
			<c:otherwise>
				<div class="paging">
					<div class="paging-in">
						<a href="<%=request.getContextPath()%>/activity/activityList?isHelp=0&filter=${filter}&title=${title}" class="nav first"></a>
					</div>
					<c:choose>
						<c:when test="${paging.currentPage eq 1 }">
							<div class="paging-in pre">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage}&isHelp=0&filter=${filter}&title=${title}" class="nav prev"></a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="paging-in pre">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage-1}&isHelp=0&filter=${filter}&title=${title}" class="nav prev"></a>
							</div>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
						<div class="paging-in">
							<c:choose>
								<c:when test="${page eq paging.currentPage }">
									<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${page}&isHelp=0&filter=${filter}&title=${title}" class="num active green">${page}</a>
								</c:when>
								<c:otherwise>
									<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${page}&isHelp=0&filter=${filter}&title=${title}" class="num active">${page}</a>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forEach>
			
					<c:choose>
						<c:when test="${paging.currentPage eq paging.lastPage }">
							<div class="paging-in nex">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage}&isHelp=0&filter=${filter}&title=${title}" class="nav next"></a>
							</div>
						</c:when>
						<c:otherwise>
							<div class="paging-in nex">
								<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.currentPage+1}&isHelp=0&filter=${filter}&title=${title}" class="nav next"></a>
							</div>
						</c:otherwise>
					</c:choose>
			
					<div class="paging-in">
						<a href="<%= request.getContextPath() %>/activity/activityList?cPage=${paging.lastPage}&isHelp=0&filter=${filter}&title=${title}" class="nav last"></a>
					</div>
				</div>
			</c:otherwise>
		</c:choose>
		<!-- // section pagination -->
		
		
	</div>

</div>


<%@include file="../include/footer.jsp" %>
