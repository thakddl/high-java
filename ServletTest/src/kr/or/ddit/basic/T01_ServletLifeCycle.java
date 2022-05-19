package kr.or.ddit.basic;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T01_ServletLifeCycle extends HttpServlet {
/**
 * 서블릿에 대하여...
 * - 컨테이너(서블릿엔진)에 의해 관리되는 자바 기반의 웹 컴포넌트로서,
 *   동적인 웹컨텐츠 생성을 가능하게 한다.
 *   
 */
	@Override
	public void init() throws ServletException {
		// 초기화 코드 작성
		System.out.println("T01_ServletLifeCycle init() 호출됨...");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 실제 작업 수행이 시작되는 시점...(자바의 메인 메서드 역할)
		System.out.println("service 호출됨...");
		super.service(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메인 매서드 방식이 GET인 경우 호출됨
		System.out.println("doGet 호출됨...");
		throw new ServletException("예외 강제 발생");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 메인 매서드 방식이 POST인 경우 호출됨
		System.out.println("doPost 호출됨...");
	}
	
	@Override
	public void destroy() {
		// 객체 소멸시(컨테이너로부터 서블릿 객체 제거시) 필요한 코드 작성
		System.out.println("destroy 호출됨...");
	}
}
