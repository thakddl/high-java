package service;

import java.util.List;

import dao.BoardDaoImpl;
import dao.IBoardDao;
import vo.BoardVO;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	private static IBoardService boardService;
	
	private BoardServiceImpl() {
		if(boardDao==null) {
			boardDao = BoardDaoImpl.getInstance();
		}
	}
	public static IBoardService getInstance() {
		if(boardService==null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	@Override
	public List<BoardVO> getBoardList() {
		return boardDao.getBoardList();
	}
	@Override
	public BoardVO getBoard(String boardId) {
		return boardDao.getBoard(boardId);
	}
	@Override
	public int insertBoard(BoardVO vo) {
		return boardDao.insertBoard(vo);
	}
	@Override
	public int updatebBoard(BoardVO vo) {
		return boardDao.updatebBoard(vo);
	}
	@Override
	public int deleteBoard(String boardId) {
		return boardDao.deleteBoard(boardId);
	}
	
	

}
