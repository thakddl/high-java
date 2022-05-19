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

@WebServlet("/board/detail.do")
public class BoardDetailServlet extends HttpServlet {
	private IBoardService boardService;
	
	
	
	public BoardDetailServlet() {
		if(boardService==null) {
			boardService = BoardServiceImpl.getInstance();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String boardId = request.getParameter("boardId")==null? "" : request.getParameter("boardId");
		BoardVO vo = boardService.getBoard(boardId);
		request.setAttribute("board", vo);
		
		request.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(request, response);
	}

}
