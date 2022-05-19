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
                <h2 class="pageTitle pb-3">게시물 등록</h2>
            </div>
            <form action="insert.do" method="post" id="form">
            	<div class="form-group">
            		<label class="label pr-5">제목</label>
            		<input class="input-text" type="text" name="title">
            	</div>
            	<div class="form-group">
            		<label class="label pr-5">내용</label>
            		<textarea name="content"></textarea>
            	</div>
            	<div class="text-align-center">
            		<input id="submitBtn" class="btn btn-danger" type="button" value="등록">
            	</div>
            </form>
            <script>
            $("#submitBtn").click(function(){
        		$.ajax({
        			type: "post",
        			url: "insert.do",
        			data: $("#form").serialize(),
        			success: function(result){
        				if(result=="성공"){
							alert("입력되었습니다.");
							location.href="list.do"
						} else {
							alert(result+"입력 실패.");
							location.href="list.do"
						}
        			}
        		});
        	});
            </script>
        </div>
    </div>
</body>
</html>