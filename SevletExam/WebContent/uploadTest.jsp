<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>File Upload 예제</title>
</head>
<body>
	<h3>아파치 자카르타 프로젝트 즉 외부의 fileUpload 모듈을 이용한 파일 업로드</h3>
	<!-- <form action="upload" enctype="application/x-www-form-urlencoded"> 기본값-->
	<form action="upload" method="post" enctype="multipart/form-data"> 
		파일선택: <input type="file" name="uploadFile">
		전송한 사람: <input type="text" name="sender">
		<input type="submit" value="파일 업로드 하기">
	</form>
	
	<h3>서블릿3부터 톰켓에서 스스로 지원하는 Part 인터페이스를 이용한 파일 업로드</h3>
	<form action="upload2" method="post" enctype="multipart/form-data"> 
		파일선택: <input type="file" name="uploadFile">
		전송한 사람: <input type="text" name="sender">
		<input type="submit" value="파일 업로드 하기">
	</form>
</body>
</html>