package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	
	public List<BoardVO> getListAll();
	public int insertPost(BoardVO bv);
	public int updatePost(BoardVO bv);
	public boolean checkPost(int boardNo);
	public int deletePost(int boardNo);
	public List<BoardVO> searchPost(String word);
	
}
