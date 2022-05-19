package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class T07_ServletSessionTest extends HttpServlet {
/*
 * 세션(Session)객체에 대하여...
 * 
 * - 세션을 통해서 사용자(웹브라우저)별!!로 구분하여 정보를 관리할 수 있다.(세션ID 이용)
 *   (사용자별!! 정보를 서버쪽에 저장하고 유지하고 싶어서(쿠키는 클라이언트 쪽에 저장) 사용한다.) 
 *   쿠키에 JSESSIONID를 생성하여 서버가 개별 클라이언트의 세션을 인식한다.
 *   브라우저 > 애플리케이션에서 쿠키 만료기한을 보면 날짜가 아닌 session이라고 뜬다. 한 세션이 끝날때까지 즉 기본적으로 브라우저가 종료될 때까지 유지한다.  
 * - 쿠키를 사용할 때보다 보안이 향상된다.(정보가 서버에 저장되기 때문에...)
 * 
 * - 세션 객체를 가져오는 방법
 *   HttpSession session = req.getSession(boolean 값);
 *   boolean값: true인 경우   : 세션객체가 존재하지 않으면 새로 생성된다.
 *   		   false인 경우  : 세션객체가 존재하지 않으면 null을 리턴한다.
 *   
 * - 세션 삭제 처리 방법
 *  1. invalidate() 메서드 호출
 *  2. setMaxInactiveInterval(int interval) 메서드 호출
 *     => 일정시간(초)동안 요청이 없으면 세션 객체가 삭제됨.
 *  3. web.xml이 <session-config> 설정하기(분단위): 생성되는 모든 세션을 관리한다.
*/
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 세션을 가져오는데 없으면 새로 생성한다. 
		HttpSession session = req.getSession(true);
		
		// 생성 시간 가져오기
		Date createTime = new Date(session.getCreationTime());
		
		// 마지막 접근 시간 가져오기
		Date lastAccessTime = new Date(session.getLastAccessedTime());
		
		String title = "재방문을 환영합니다.";
		int visitCount = 0;
		String userId = "sem";
		
		if(session.isNew()) {
			title = "처음 방문을 환영합니다.";
			session.setAttribute("userId", userId);
		} else {
			visitCount = (Integer) session.getAttribute("visitCount");
			visitCount++;
			userId = (String) session.getAttribute("userId");
		}
		
		System.out.println("방문횟수: " + visitCount);
		session.setAttribute("visitCount", visitCount);
		
		// 응답헤더에 인코딩 및 Content type 설정
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html");
		
		PrintWriter out = resp.getWriter();
		
		out.println("<html><head><title>" + title + "</title></head>"
				   + "<body><h1 align=\"center\">" + title + "</h1>"
	               + "<h2 align=\"center\">세션정보</h2>"
	               + "<table border=\"1\" align=\"center\">"
	               + "<tr bgcolor=\"orange\">"
	               + "<th>구분</th><th>값</th></tr>"
	               + "<tr><td>세션ID</td><td>" + session.getId() + "</td></tr>"
	               + "<tr><td>생성시간</td><td>" + createTime + "</td></tr>"
	               + "<tr><td>마지막 접근시간</td><td>" + lastAccessTime + "</td></tr>"
	               + "<tr><td>User ID</td><td>" + userId + "</td></tr>"
	               + "<tr><td>방문 횟수 ID</td><td>" + visitCount + "</td></tr>"
	               + "</table></body></html>");
		
//		session.invalidate();//세션삭제
//		session.setMaxInactiveInterval(5);

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
