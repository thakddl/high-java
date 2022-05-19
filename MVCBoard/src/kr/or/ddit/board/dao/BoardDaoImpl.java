package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board.util.JDBCUtil;
import kr.or.ddit.board.vo.BoardVO;

public class BoardDaoImpl implements IBoardDao {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	@Override
	public List<BoardVO> getListAll() {
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from jdbc_board";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
				bv.setBoardTitle(rs.getString("BOARD_TITLE"));
				bv.setBoardWriter(rs.getString("BOARD_WRITER"));
				bv.setBoardDate(rs.getString("BOARD_DATE"));
				bv.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				list.add(bv);
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnection(conn, stmt, pstmt, rs);
		}
		return list;	
	}

	@Override
	public int insertPost(BoardVO bv) {
		int result = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "insert into jdbc_board values (board_seq.nextVal, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			
			result = pstmt.executeUpdate();		
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnection(conn, stmt, pstmt, rs);
		}
		return result;
	}

	@Override
	public int updatePost(BoardVO bv) {
		int result = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update jdbc_board set board_title=?, board_writer=?, board_content=?, board_date=sysdate where board_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bv.getBoardTitle());
			pstmt.setString(2, bv.getBoardWriter());
			pstmt.setString(3, bv.getBoardContent());
			pstmt.setInt(4, bv.getBoardNo());
			
			result = pstmt.executeUpdate();
			
				
		} catch(SQLException e) {
//			e.printStackTrace();
			System.out.println("제목과 작성자는 빈값일 수 없습니다.");
		} finally {
			JDBCUtil.disConnection(conn, stmt, pstmt, rs);
		}
		return result;
	}

	@Override
	public boolean checkPost(int boardNo) {
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_no = " + boardNo;
			
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result>0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnection(conn, stmt, pstmt, rs);
		}
		return false;
	}
	
	@Override
	public int deletePost(int boardNo) {
		int result = 0;
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnection(conn, stmt, pstmt, rs);
		}
		return result;
	}

	@Override
	public List<BoardVO> searchPost(String word) {
		List<BoardVO> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_title like '%"+word+"%' or board_content like '%"+word+"%'";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bv = new BoardVO();
				bv.setBoardNo(rs.getInt("BOARD_NO"));
				bv.setBoardTitle(rs.getString("BOARD_TITLE"));
				bv.setBoardWriter(rs.getString("BOARD_WRITER"));
				bv.setBoardDate(rs.getString("BOARD_DATE"));
				bv.setBoardContent(rs.getString("BOARD_CONTENT"));
				
				list.add(bv);
			}	
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnection(conn, stmt, pstmt, rs);
		}
		return list;
	}


}
