package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.SqlMapClientFactory;
import vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	private SqlMapClient smc;
	private static IBoardDao boardDao;
	
	private BoardDaoImpl() {
		if(smc==null) {
			smc = SqlMapClientFactory.getInstance();
		}
	}
	public static IBoardDao getInstance() {
		if(boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;	
	}
	
	public List<BoardVO> getBoardList(){
		
		List<BoardVO> result = new ArrayList<BoardVO>();
		try {
			result =  smc.queryForList("board.getBoardList");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
		
	}
	@Override
	public BoardVO getBoard(String boardId) {
		BoardVO result = new BoardVO();
		try {
			
			result = (BoardVO) smc.queryForObject("board.getBoard", boardId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int insertBoard(BoardVO vo) {
		int result = 0;
		try {
			result =  smc.update("board.insertBoard", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int updatebBoard(BoardVO vo) {
		int result = 0;
		try {
			result =  smc.update("board.updatebBoard", vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public int deleteBoard(String boardId) {
		int result = 0;
		try {
			result =  smc.update("board.deleteBoard", boardId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	
}
