<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="page" />

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 목록</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css" integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="${contextPath}/resources/js/lib/jquery-3.6.4.min.js"></script>
<script>
	$(function(){
		// 작성 화면으로 이동
		$('#btn_write').on('click', function(){
			location.href = '${contextPath}/writeBoard.do';
		})
		// 삭제 링크 클릭
		$('.link_remove').on('click', function(event){
			// 클릭한 놈은 => this (클릭한 링크)
			// 임시코드 : 작성일자 alert( $(this).parent().prev().text() );
			if(confirm('삭제할까요?') == false){
				event.preventDefault();   // <button> 태그의 기본 동작인 submit 속성의 동작을 막는다.
				return;
			}
		})
	})
</script>
</head>
<body>
	
	<div>
		<h1>게시글 목록</h1>
		<div>
			<input type="button" value="작성하기" id="btn_write">
		</div>
		<div>
			<table border="1">
				<thead>
					<tr>
						<td>게시글번호</td>
						<td>제목</td>
						<td>작성일자</td>
						<td>삭제</td>
					</tr>
				</thead>
				<tbody>
						<%-- foreach문의 items에는 배열이나 리스트를 넣어준다. --%>
						<%-- 셀렉트문 짤 때 DESC(5,4,3,2,1) 내림차순을 줬기 때문에 늦게 작성된 게시글이 테이블 맨 위로 정렬. 그래서 인덱스가 0이 되는거 --%>
						<c:forEach items="${boardList}" var="board" varStatus="vs">
							<tr>
								<td><fmt:formatNumber value="${boardListCount - vs.index}" pattern="#,##0" /></td>
								<td>
									<a class="link_detail" href="${contextPath}/getBoardByNo.do?board_no=${board.board_no}">${board.title}</a>
								</td>
								<td><fmt:formatDate value="${board.created_date}" pattern="yy.MM.dd" /></td>
								<td>
									<form id="frm_remove" method="post" action="${contextPath}/removeBoard.do">
										<input type="hidden" name="board_no" value="${board.board_no}">
										<button class="link_remove"><i class="fa-solid fa-x"></i></button>
									</form>
								</td>
								<%-- ★★★ board_no 파라미터로 넘기는 것이 가장 중요 ★★★ --%>
							</tr>
						</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>