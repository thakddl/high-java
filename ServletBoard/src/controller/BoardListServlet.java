package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardService;
import vo.BoardVO;

@WebServlet("/board/list.do")
public class BoardListServlet extends HttpServlet {
	private IBoardService boardService;
	
	public BoardListServlet() {
		if(boardService==null) {
			boardService = BoardServiceImpl.getInstance();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<BoardVO> list = boardService.getBoardList();
		
		request.setAttribute("boardList", list);
		request.getRequestDispatcher("/WEB-INF/views/board/list.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}
