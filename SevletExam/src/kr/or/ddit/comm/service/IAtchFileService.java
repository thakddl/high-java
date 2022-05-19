package kr.or.ddit.comm.service;
/**
 * 첨부파일 저장을 위한 공통 서비스용 인터페이스
 */

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.comm.vo.AtchFileVO;

public interface IAtchFileService {

	/**
	 * 첨부파일 목록을 저장하기 위한 메서드
	 * 
	 * @param req Part객체를 꺼내기 위한 요청객체
	 * @return 저장한 첨부파일 정보
	 * @throws Exception
	 */
	public AtchFileVO saveAtchFileList(HttpServletRequest req) throws Exception;

	/**
	 * 첨부파일 목록 조회하기
	 * 
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) throws SQLException;

	/**
	 * 첨부파일 세부목록 조회하기
	 * 
	 * @param atchFileVO
	 * @return
	 * @throws SQLException
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) throws SQLException;

}