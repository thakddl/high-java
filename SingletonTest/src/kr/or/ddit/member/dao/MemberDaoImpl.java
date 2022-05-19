package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;

public class MemberDaoImpl implements IMemberDao {
	
	private static IMemberDao memDao;

	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private MemberDaoImpl() {}
	
	public static IMemberDao getInstance() {
		if(memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	@Override
	public int insertMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "INSERT INTO mymember (" +
					 	 "    mem_id," +
						 "    mem_name," +
						 "    mem_tel," +
						 "    mem_addr" +
						 ") VALUES (?, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());
			
			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean isExist = false;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select count(*) as cnt from mymember where MEM_ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			if(rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if(cnt>0) {
				isExist = true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "update mymember " +
						 "   set mem_name = ?," +
						 "       mem_tel = ?," +
						 "       mem_addr = ?" +
						 " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());
			
			cnt = pstmt.executeUpdate();

			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			conn = JDBCUtil.getConnection();
			String sql = "delete from mymember " +
						 " where mem_id = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();

			
		} catch (SQLException e) {
			System.out.println(memId + "회원 삭제 실패!");
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			String sql = "select * from mymember";
			stmt = conn.createStatement();
			//executeQuery는 resultSet을 반환하며 row단위로 읽어 올 수 있다.
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");

				MemberVO vo = new MemberVO(); 
				vo.setMemId(memId);
				vo.setMemName(memName);
				vo.setMemTel(memTel);
				vo.setMemAddr(memAddr);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return list;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> list = new ArrayList<>();
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = "select * from mymember where 1=1";
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				sql += "and mem_id = ?";
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				sql += "and mem_name = ?";
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				sql += "and mem_tel = ?";
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				sql += "and mem_addr like '%' || ? || '%'";
			}
			pstmt = conn.prepareStatement(sql);
			
			int idx = 1;
			
			if(mv.getMemId() != null && !mv.getMemId().equals("")) {
				pstmt.setString(idx++, mv.getMemId());
			}
			if(mv.getMemName() != null && !mv.getMemName().equals("")) {
				pstmt.setString(idx++, mv.getMemName());
			}
			if(mv.getMemTel() != null && !mv.getMemTel().equals("")) {
				pstmt.setString(idx++, mv.getMemTel());
			}
			if(mv.getMemAddr() != null && !mv.getMemAddr().equals("")) {
				pstmt.setString(idx++, mv.getMemAddr());
			}

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String memId = rs.getString("mem_id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");

				MemberVO vo = new MemberVO(); 
				vo.setMemId(memId);
				vo.setMemName(memName);
				vo.setMemTel(memTel);
				vo.setMemAddr(memAddr);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		return list;
	}

}
