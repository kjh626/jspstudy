<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
</head>
<body>
	
	<div class="wrap">
		<h1>학생 관리</h1>
		<div>
			<input type="button" value="신규학생등록" onclick="goWrite()">
		</div>
		
		<hr>
		
		<div>
			<label for="avg">평균</label>
			<input type="text" name="begin" id='begin' placeholder="begin"> ~
			<input type="text" name="end" id='end' placeholder="end">
			<input type="button" value="조회" onclick="goDetail()">
			<input type="button" value="전체조회" onclick="goAll()">
		</div>
		
		<hr>
		
		<div id="open">
			<c:forEach var="student" items="${studentlist}" varStatus="vs" step="3">
				<div>${vs.count}위 ${student.name}님 ${student.ave}점</div>
			</c:forEach>
		</div>
		
		<hr>
		
		<table border="1">
		<%-- 돔 인식을 위한 태그가 필요해서 span태그 하나 줌 --%>
			<caption>전체 학생 : <span id="student_count">${studentlistCount}</span>명</caption>
			<thead>
				<tr>
					<td>학번</td>
					<td>성명</td>
					<td>국어</td>
					<td>영어</td>
					<td>수학</td>
					<td>평균</td>
					<td>학점</td>
					<td>버튼</td>
				</tr>
			</thead>
			<%-- tbody는 비워둘 것 (돔 조작을 통해 만들 예정) --%>
			<tbody id="student_list">
				<c:choose>
					<c:when test="${studentlistCount == 0}">
						<tr>
							<td colspan="8">등록된 학생이 없습니다.</td>
						</tr>
					</c:when>
					<c:otherwise>
						<c:forEach var="student" items="${studentlist}">
							<tr>
								<td>${student.stuNo}</td>
								<td>${student.name}</td>
								<td>${student.kor}</td>
								<td>${student.eng}</td>
								<td>${student.math}</td>
								<td>${student.ave}</td>
								<td>${student.grade}</td>
								<td>
									<input type="button" class="btn_detail" value="상세" data-stu_no="${student.stuNo}">
									<input type="button" class="btn_remove" value="삭제" onclick='goRemove()'>
									<form id="frm_remove" method="post" action="${contextPath}/remove.do">
										<input type="hidden" name="stuNo" value="${student.stuNo}">
									</form>
								</td>
							</tr>
						</c:forEach>
					</c:otherwise>
				</c:choose>
			</tbody>
			<tfoot id="list_average">
				<tr>
					<td colspan="5"></td>
					<td></td>
					<td colspan="2"></td>
				</tr>	
			</tfoot>
		</table>
		
		
	</div>
	
	<script>
	
		function goAll(){
			<c:forEach var="student" items="${studentlist}" varStatus="vs" step="3">
				$('#open').append('<div>' + 위 + '${student.name}님 ${student.ave}점</div>')
			</c:forEach>
		}
		function goWrite(){
			location.href = '${contextPath}/write.do';
		}
		$(function(){
			$('.btn_detail').on('click', function(){
				location.href = '${contextPath}/detail.do?stuNo=' + $(this).data('stu_no') + '&begin=' + $('#begin').val() + '&end=' + $('#end').val();
			})
		})
		function goRemove(){
			// 주소로 remove.do 하면 못하게 하도록 튕겨내는 것 4장에서 했었다.. , 삭제버튼을 form방식으로 만들고 포스트방식으로만 삭제 가능하게 만들어 줬었음.
			// GET방식(주소로 요청) 으로 요청하면 서비스에 잘못된 요청 alert 넣어줘서 처리했었다.
			if(confirm('삭제할거야?')){ 
				// location 안 쓰고 frm_remove에 서브밋을 해준다
				$('#frm_remove').submit();
			} 
		}
	
	</script>

</body>
</html>