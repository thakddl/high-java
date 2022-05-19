package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	private IBoardDao boardDao;
	private static IBoardService boardSrv;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstence();
	}
	public static IBoardService getInstence() {
		boardSrv = new BoardServiceImpl();
		return boardSrv;
	}
	@Override
	public List<BoardVO> getListAll() {
		return boardDao.getListAll();
	}

	@Override
	public int insertPost(BoardVO bv) {
		return boardDao.insertPost(bv);
	}

	@Override
	public int updatePost(BoardVO bv) {
		return boardDao.updatePost(bv);
	}

	@Override
	public boolean checkPost(int boardNo) {
		return boardDao.checkPost(boardNo);
	}

	@Override
	public int deletePost(int boardNo) {
		return boardDao.deletePost(boardNo);
	}

	@Override
	public List<BoardVO> searchPost(String word) {
		return boardDao.searchPost(word);
	}

}
