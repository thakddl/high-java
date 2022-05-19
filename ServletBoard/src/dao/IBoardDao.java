package dao;

import java.util.List;

import vo.BoardVO;

public interface IBoardDao {

	List<BoardVO> getBoardList();

	BoardVO getBoard(String boardId);

	int insertBoard(BoardVO vo);

	int updatebBoard(BoardVO vo);

	int deleteBoard(String boardId);

}
