<%@page import="vo.BoardVO"%>
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
                <h2 class="pageTitle pb-3">게시물 수정</h2>
            </div>
            <%
            	BoardVO vo = (BoardVO) request.getAttribute("board");
            %>
            <form action="update.do" method="post" id="form">
            	<div class="form-group">
            		<label class="label pr-5">제목:</label>
            		<span><%=vo.getTitle()%></span>
            	</div>
            	<div class="form-group">
            		<label class="label pr-5">내용</label>
            		<div><%=vo.getContent() %></div>
            	</div>
            	<div class="text-align-center">
					<a class="btn btn-primary" href="update.do?boardId=<%=vo.getBoard_id() %>">수정</a>
					<input class="btn btn-danger" type="button" value="삭제" role="delete" data-id="<%=vo.getBoard_id() %>">
            	</div>
            </form>
			<script>
				$("input[role=delete]").click(function(){
					let boardId = $(this).data("id");
					$.ajax({
						type: "post",
						url: "delete.do",
						data: { "boardId": boardId },
						dataType: "json",
						success: function(result){
							if(result>0){
								alert("삭제되었습니다.");
								location.href="list.do"
							} else {
								alert("삭제 실패.");
								location.href="list.do"
							}
						},
						error: function(request, status, error) { alert("code:" + request.status + "\n" + "error:" + error); }
					})
				});
			</script>
        </div>
    </div>
</body>
</html>