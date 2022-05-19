package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.JDBCUtil;
import kr.or.ddit.util.SqlMapClientFactory;

public class MemberDaoImpl implements IMemberDao {
	
	private static IMemberDao memDao;
	
	private SqlMapClient smc;
	
	private MemberDaoImpl() {
		smc = SqlMapClientFactory.getInstance();
	}
	
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
			cnt = smc.update("member.insertMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		boolean isExist = false;
		try {
			int cnt = (int) smc.queryForObject("member.checkMember", memId);
			
			if(cnt > 0) {
				isExist = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return isExist;
	}

	@Override
	public int updateMember(MemberVO mv) {
		int cnt = 0;
		try {
			cnt = smc.update("member.updateMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		try {
			cnt = smc.delete("member.deleteMember", memId);	
		} catch (SQLException e) {
			System.out.println(memId + "회원 삭제 실패!");
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		List<MemberVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("member.getMemberAll");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public List<MemberVO> searchMember(MemberVO mv) {
		List<MemberVO> list = new ArrayList<>();
		try {
			list = smc.queryForList("member.searchMember", mv);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return list;
	}

	@Override
	public MemberVO getMember(String memId) {
		MemberVO mv = new MemberVO();
		try {
			mv = (MemberVO) smc.queryForObject("member.getMember", memId);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return mv;
	}

}
