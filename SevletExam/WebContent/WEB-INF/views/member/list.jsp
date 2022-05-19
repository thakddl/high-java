<%@page import="kr.or.ddit.member.vo.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//jsp도 결국 서블릿이다.
	//왜냐하면 결국 이 문서를 서블릿으로 변환해서 실행하는 것이기 때문이다.
	//내장객체가 여러개 있다.
	//out, request, response
	
	List<MemberVO> memList = (List<MemberVO>) request.getAttribute("memList"); //object타입으로 들어오기 때문에 형변환을 해주어야 한다.
	String msg = request.getParameter("msg") == null? "" : request.getParameter("msg");
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- WEB-INF는 특수한 경로로 이 안에 있는것은 url로 바로 접근이 불가능하다. -->
<table border="1">
	<tr>
		<td>ID</td>
		<td>이름</td>
		<td>전화번호</td>
		<td>주소</td>
		<td>첨부파일ID</td>
	</tr>
	<%
		int memSize = memList.size();
		if(memSize>0){
			for(int i=0; i<memSize; i++){
	%>
	<tr>	
		<td><%=memList.get(i).getMemId()%></td>
		<td><a href="detail.do?memId=<%=memList.get(i).getMemId()%>"><%=memList.get(i).getMemName()%></a></td>
		<td><%=memList.get(i).getMemTel()%></td>
		<td><%=memList.get(i).getMemAddr()%></td>
		<td><%=memList.get(i).getAtchFileId()%></td>
	</tr>
	<% 
			}
		} else {
			
	%>
	<tr>
		<td colspan="4">회원정보가 없습니다.</td>
	</tr>
	<%
		}
	%>
</table>
<%
	if(msg.equals("성공")){		
%>
	<script>
		alert('정상 처리 되었습니다.');
	</script>
<% 
	} else if(msg.equals("실패")) {
%>
	<script>
		alert('처리 실패.');
	</script>
<%
	}
%>
</body>
</html>