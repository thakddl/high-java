<%@page import="vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
</head>

<body>
	<div class="bgBox">
		<div class="main">
			<div class="titleWrap mb-5">
				<h2 class="pageTitle pb-3">게시판</h2>
			</div>
			<div class="conWrap">
				<div class="text-align-right mb-3">
					<a id="insertBtn" class="btn btn-danger" href="insert.do">등록하기</a>
				</div>
				<table id="cardCmnyTable" class="table">
					<thead>
						<tr>
							<th scope="row">순번</th>
							<th scope="col">제목</th>
							<th scope="col">게시물 보기</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<BoardVO> boardList = (List<BoardVO>) request.getAttribute("boardList");
										if (boardList.size() > 0) {
											for (int i=0; i<boardList.size(); i++) {
						%>
						<tr>
							<td><%=i+1 %></td>
							<td><%=boardList.get(i).getTitle() %></td>
							<td><a class="btn btn-primary" href="detail.do?boardId=<%=boardList.get(i).getBoard_id() %>">보기</a></td>
						</tr>
						<%
								}
							} else {
						%>
						<tr>
							<td colspan="100%">등록된 게시물이 없습니다.</td>
						</tr>
	
						<%
							}
						%>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</body>
</html>