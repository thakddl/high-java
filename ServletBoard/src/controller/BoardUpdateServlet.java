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

@WebServlet("/board/update.do")
public class BoardUpdateServlet extends HttpServlet {
	private IBoardService boardService;
	
	
	
	public BoardUpdateServlet() {
		if(boardService==null) {
			boardService = BoardServiceImpl.getInstance();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardId = request.getParameter("boardId")==null? "" : request.getParameter("boardId");
		BoardVO vo = boardService.getBoard(boardId);
		request.setAttribute("board", vo);
		
		request.getRequestDispatcher("/WEB-INF/views/board/update.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardId = request.getParameter("boardId")==null? "" : request.getParameter("boardId");
		String title = request.getParameter("title")==null? "" : request.getParameter("title");
		String content = request.getParameter("content")==null? "" : request.getParameter("content");
		
		BoardVO vo = new BoardVO();
		vo.setBoard_id(boardId);
		vo.setTitle(title);
		vo.setContent(content);
		
		int cnt = boardService.updatebBoard(vo);
		response.setCharacterEncoding("UTF-8");
		if(cnt > 0) {
			response.getWriter().print("성공");
		} else {
			response.getWriter().print("실패");
		}
		
	}

}
