package kr.or.ddit.member.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.comm.service.AtchFileServiceImpl;
import kr.or.ddit.comm.service.IAtchFileService;
import kr.or.ddit.comm.vo.AtchFileVO;
import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/member/update.do")
@MultipartConfig(
		 fileSizeThreshold = 1024*1024*3, 
		 maxFileSize = 1024*1024*40,
		 maxRequestSize = 1024*1024*50)
public class UpdateMemberServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. 파라미터정보 가져오기
		String memId = req.getParameter("memId");

		// 2. 서비스 객체 가져오기
		IMemberService memberService = MemberServiceImpl.getInstance();
		
		// 3. 회원정보 조회
		MemberVO mv = memberService.getMember(memId);
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		
		if(mv.getAtchFileId() > 0) { //첨부파일이 존재하면
			//첨부파일 정보 조회
			AtchFileVO fileVO = new AtchFileVO();
			fileVO.setAtchFileId(mv.getAtchFileId());
			List<AtchFileVO> atchFileList = null;
			try {
				atchFileList = fileService.getAtchFileList(fileVO);
			} catch (Exception e) {
				e.printStackTrace();
			}
			req.setAttribute("atchFileList", atchFileList);
		}

		req.setAttribute("mv", mv);
		
		// 4. 업데이트 화면으로 포워딩
		req.getRequestDispatcher("/WEB-INF/views/member/updateForm.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. 요청 파라미터 정보 가져오기
		String memId = req.getParameter("memId");
		String memName = req.getParameter("memName");
		String memTel = req.getParameter("memTel");
		String memAddr = req.getParameter("memAddr");
		String atchFileId = req.getParameter("atchFileId");

		// 2. 서비스 객체 생성하기
		IMemberService memberService = MemberServiceImpl.getInstance();
		IAtchFileService fileService = AtchFileServiceImpl.getInstance();
		AtchFileVO atchFileVO = new AtchFileVO();
		
		try {
			atchFileVO = fileService.saveAtchFileList(req);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 기존 첨부파일 아이디 정보 가져오기와 설정하기
		if(atchFileVO != null && atchFileVO.getAtchFileId() > 0) {} 
		else { //새로운 업데이트 내용이 없는 경우
			atchFileVO = new AtchFileVO(); //초기화
			atchFileVO.setAtchFileId(atchFileId == null? -1 : Long.parseLong(atchFileId));
		}
		
		// 3. 회원정보 수정
		MemberVO mv = new MemberVO();
		mv.setMemId(memId);
		mv.setMemName(memName);
		mv.setMemTel(memTel);
		mv.setMemAddr(memAddr);
		if(atchFileVO == null) {//신규첨부파일이 없는경우
			mv.setAtchFileId(Long.parseLong(atchFileId));
		} else {			
			mv.setAtchFileId(atchFileVO.getAtchFileId());
		}
		
		int cnt = memberService.updateMember(mv);

		String msg = "";
		if (cnt > 0) {
			msg = "성공";
		} else {
			msg = "실패";
		}
		req.setAttribute("msg", msg);

		// 4. 목록 조회 화면으로 이동
		// req.getRequestDispatcher("/member/list.do").forward(req, resp);//forward 방식은
		// 다시 요청해서 응답을 받는 방식이 아니고 응답의 결과를 이걸로 주는 것 뿐이다.

		String redirectUrl = req.getContextPath() + "/member/list.do?msg=" + URLEncoder.encode(msg, "UTF-8");
		// 요청을 여기로 다시하는 방식이다.
		resp.sendRedirect(redirectUrl);
	}
}
