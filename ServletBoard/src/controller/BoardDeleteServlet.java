package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardServiceImpl;
import service.IBoardService;

@WebServlet("/board/delete.do")
public class BoardDeleteServlet extends HttpServlet {
	private IBoardService boardService;
	
	public BoardDeleteServlet() {
		if(boardService==null) {
			boardService = BoardServiceImpl.getInstance();
		}
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String boardId = request.getParameter("boardId")==null? "" : request.getParameter("boardId");
		int cnt = boardService.deleteBoard(boardId);
		
		response.getWriter().print(cnt);
	}

}
