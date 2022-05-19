package kr.or.ddit.member.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.member.vo.MemberVO;

/**
 * Service객체는 Dao에 설정된 메서드를 원하는 작업에 맞게 호출하여 결과를 받아오고,
 * 받아온 데이터를 Controller에게 보내주는 역할을 수행한다.
 */
public interface IMemberService {
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메서드
	 * 
	 * @param mv DB에 insert할 자료가 저장된 MemberVO 객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고 실패하면 0이 반환된다.
	 * @throws SQLException
	 */
	public int insertMember(MemberVO mv);
	
	/**
	 * 주어진 회원 ID가 존재하는지 여부를 알아내기 위한 메소드
	 * @param memId 검색할 회원 ID
	 * @return 해당 회원ID가 있으면 true, 없으면 false
	 */
	public boolean checkMember(String memId);
	
	/**
	 * 하나의 MemberVO자료를 이용하여 DB를 update하는 메서드
	 * @param mv update할 회원 정보가 담긴 MemberVO객체
	 * @return 작업성공:1, 작업실패: 0
	 */
	public int updateMember(MemberVO mv);
	
	/**
	 * 회원아이디를 매개변수로 받아서 해당 회원 정보를 삭제하는 메서드
	 * @param memId 삭제할 회원 ID
	 * @return 작업성공:1, 작업실패: 0
	 */
	public int deleteMember(String memId);
	
	/**
	 * 코드를 가져와서 List에 담아
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMemberList();

	public List<MemberVO> searchMember(MemberVO mv);
	
	
	
	
	
}
