package kr.or.ddit.member.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/list.do") //확장자 do는 servlet을 타고 있음을 뜻한다. do를 안써도 되지만 의미를 전달
public class SelectAllMemberServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 1. 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 2. 회원정보 조회
		List<MemberVO> memList = memberService.getAllMemberList();
		
		req.setAttribute("memList", memList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/list.jsp");
		dispatcher.forward(req, resp); // 뷰페이지로 전달
		
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
