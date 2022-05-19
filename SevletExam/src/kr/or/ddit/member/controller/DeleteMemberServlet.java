package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/delete.do")
public class DeleteMemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터정보 가져오기
		String memId = req.getParameter("memId");

		// 2. 서비스 객체 가져오기
		IMemberService memberService = MemberServiceImpl.getInstance();

		// 3. 회원정보 조회
		int cnt = memberService.deleteMember(memId);
		
		String msg = "";
		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		// 4. 목록 조회 화면으로 이동
		// req.getRequestDispatcher("/member/list.do").forward(req, resp);//forward 방식은
		// 다시 요청해서 응답을 받는 방식이 아니고 응답의 결과를 이걸로 주는 것 뿐이다.

		String redirectUrl = req.getContextPath() + "/member/list.do?msg=" + URLEncoder.encode(msg, "UTF-8");
		// 요청을 여기로 다시하는 방식이다.
		resp.sendRedirect(redirectUrl);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
