package kr.or.ddit.basic;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class T08_ServletFilter implements Filter {
/*
 * 서블릿 필터에 대하여...
 * 개별 서블릿에서 공통적으로 수행해야 하는 작업을 중간에 인터셉트해서 설정하는 것. request나 response 두 시점에서 모두 활용가능
 *  
 * 1. 사용 목적
 *   - 클라이언트의 요청을 수행하기 전에 가로채 필요한 작업을 수행할 수 있다.
 *   - 클라이언트에 응답정보를 제공하기 전에 응답정보에 필요한 작업을 수행할 수 있다.
 * 
 * 2. 사용 예
 *   - 인증필터
 *   - 데이터 압축필터
 *   - 인코딩 필터
 *   - 로깅 및 감사처리 필터
 *   - 이미지 변환 필터 등
*/

	@Override
	public void destroy() {
		// 필터 객체가 웹컨테이너에 의해 서비스로부터 제거되기 전에 호출됨.
		System.out.println("T08_ServletFilter => destroy() 호출됨.");	
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc)
			throws IOException, ServletException {
		// 주요 필요한 작업은 모두 여기에 한다.
		System.out.println("T08_ServletFilter 시작.");
		
		// 클라이언트의 IP주소 가져오기
		String ipAddress = req.getRemoteAddr();
		
		System.out.println("IP 주소: " + ipAddress + "\n 포트번호: "
							+ req.getRemotePort() + "\n 현재시간: "
							+ new Date());
		
		// 필터체인을 실행한다 (req, resp 객체 전달) 
		// 필터 여러개가 연결되어 있는 것. 
		// 혹시나 나 자신 말고 다른 필터가 있으면 그 다음 필터의 doFilter를 실행시킴
		fc.doFilter(req, resp);
		
		System.out.println("T08_ServletFilter 완료.");

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		System.out.println("T08_ServletFilter => init() 호출됨.");
		
		//초기화 파라미터 정보 가져오기
		String initParam = config.getInitParameter("init-param");
		System.out.println("initParam: " + initParam);
		
	}
	
}
