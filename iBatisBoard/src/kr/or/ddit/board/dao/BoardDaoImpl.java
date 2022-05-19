package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.util.JDBCUtil;
import kr.or.ddit.board.util.SqlMapClientFactory;
import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	private static IBoardDao boardDao;
	private static SqlMapClient smc;
	
	private BoardDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	public static IBoardDao getInstence() {
		boardDao = new BoardDaoImpl();
		return boardDao;
	}
	
	@Override
	public List<BoardVO> getListAll() {
		List<BoardVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("boardMap.getListAll");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;	
	}

	@Override
	public int insertPost(BoardVO bv) {
		int result = 0;
		try {
			result = smc.update("boardMap.insertPost", bv);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updatePost(BoardVO bv) {
		int result = 0;
		try {
			result = smc.update("boardMap.updatePost", bv);
		} catch(SQLException e) {
//			e.printStackTrace();
			System.out.println("제목과 작성자는 빈값일 수 없습니다.");
		}
		return result;
	}

	@Override
	public boolean checkPost(int boardNo) {
		boolean isExist = false;
		try {
			int result = (int) smc.queryForObject("boardMap.checkPost", boardNo);
			if(result>0) {
				isExist = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return isExist;
	}
	
	@Override
	public int deletePost(int boardNo) {
		int result = 0;
		try {
			result = smc.delete("boardMap.deletePost", boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

	@Override
	public List<BoardVO> searchPost(String word) {
		List<BoardVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("boardMap.searchPost", word);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
