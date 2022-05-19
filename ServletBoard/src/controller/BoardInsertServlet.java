package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardService;
import vo.BoardVO;

@WebServlet("/board/insert.do")
public class BoardInsertServlet extends HttpServlet {
	private IBoardService boardService;
	
	
	
	public BoardInsertServlet() {
		if(boardService==null) {
			boardService = BoardServiceImpl.getInstance();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/views/board/insert.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String title = request.getParameter("title")==null? "" : request.getParameter("title");
		String content = request.getParameter("content")==null? "" : request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setTitle(title);
		vo.setContent(content);
		
		int cnt = boardService.insertBoard(vo);
		
		response.setCharacterEncoding("UTF-8");
		if(cnt > 0) {
			response.getWriter().print("성공");
		} else {
			response.getWriter().print("실패");
		}
	}

}
