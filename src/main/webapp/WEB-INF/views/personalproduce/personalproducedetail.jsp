<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@include file="../include/farmdiaryheader.jsp" %>

<script src="//code.jquery.com/jquery-2.2.4.min.js"></script>
<!-- 합쳐지고 최소화된 최신 CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<!-- 부가적인 테마 -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<!-- 합쳐지고 최소화된 최신 자바스크립트 -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<style type="text/css">

#d1{
	padding: 300px;
}

#footer{
	height: 100px;
	clear: both;
}

</style>


<form action="<%= request.getContextPath() %>/personalproduce/personalproducedetail.do" method="post">
<div id="d1">
    <h3>개인 농산물</h3>
    <hr>
    
<input type="hidden" name="farmQnaQuestionNo" value="${detail.productNo}" />

 <table class="table">
        <colgroup>
            <col width="10%">
            <col width="20%">
            <col width="5%">
            <col width="20%">
        </colgroup>
    <tbody>
   	
    	<tr>
      	  <th class="success">상품명</th>
      	  <td>${detail.name}</td>
      	  <th class="success">가격</th>
      	  <td>${detail.price}</td>
      	</tr>
      	
      	<tr>
      	  <th class="success">품질</th>
      	  <td>${detail.quality}</td>
      	  <th class="success">유통기한</th>
      	  <td><fmt:formatDate value="${detail.expirationDate}" pattern="yyyy-MM-dd"/></td>
      	</tr>
      	<tr>
<!--       	  <th class="success">유튜브 링크</th> -->
<%--       	  <td>${detail.youtubeLink}</td> --%>
      	  <th></th>
      	  <td></td>
      	</tr>
      	<tr>
      	  <th></th>
      	  <td></td>
      	  <th></th>
      	  <td></td>
      	</tr>
   
    </tbody>
</table>
     	<div style="height: 300px;"> 	  
     	<hr>
 		 ${detail.explain}
 		 
 		 <hr>
<%--  		<button class="btn btn-success pull-right" style="margin-left:1%;" type="button" onclick="location.href='farmdiarydelete.do?farmDiaryNo=${detail.farmDiaryNo}'">삭제</button> --%>
<%-- 	    <button class="btn btn-success pull-right" style="margin-left:1%;" type="button" onclick="location.href='farmdiarymodify.do?farmDiaryNo=${detail.farmDiaryNo}'">수정</button> --%>
      	</div>


<br>
	  
      
</div>   
</form>
      

<%@include file="../include/footer.jsp" %>