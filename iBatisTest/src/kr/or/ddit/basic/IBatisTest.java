package kr.or.ddit.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;

public class IBatisTest {
	public static void main(String[] args) {
		//iBatis를 이용하여 DB자료를 처리하는 순서
		//1. iBatis의 환경 설정 파일을 읽어와 실행시킨다.
		
		try {
			//1-1. xml문서 읽어오기
			Charset charset = Charset.forName("utf-8");
			Resources.setCharset(charset);
			
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			//1-2. 위에서 읽어옴 Reader객체를 이용하여 실제 작업을 진행할 객체 생성하기
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close();// Reader객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			// 2-1. insert 작업 연습
			System.out.println("insert작업 시작...");

			// 1)저장할 데이터를 VO에 담는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("d001");
			mv.setMemName("김동지");
			mv.setMemTel("020393");
			mv.setMemAddr("충남");
			
			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			//  형식) smc.insert("namespace값.id값", 파라미터클래스); <settings useStatementNamespaces="true" />
			// 반환값 : 성공하면 null이 반환된다.
//			Object obj = smc.insert("member.insertMember", mv);
//			if (obj == null) {
//				System.out.println("insert작업 성공...");
//			} else {
//				System.out.println("insert작업 실패...!");
//			}
//			System.out.println("--------------------------------------");
//			
			// 2-2. update 연습
			System.out.println("update작업 시작...");
	         
			MemberVO mv2 = new MemberVO();
			mv2.setMemId("d001");
			mv2.setMemName("이순신");
			mv2.setMemTel("011-989-3375");
			mv2.setMemAddr("대전시 중구 대흥동");
			
			// update()메서드의 반환값은 성공한 레코드 수 이다.
//			int cnt = smc.update("member.updateMember", mv2);
//			if (cnt > 0) {
//				System.out.println("update작업 성공...");
//			} else {
//				System.out.println("update작업 실패..!");
//			}
			
			// 2-3. delete 연습
			System.out.println("delete 작업 시작...");
			
			//delete매서드의 반환 값: 성공한 레코드 수 
			int cnt2 = smc.delete("member.deleteMember", "d001");
			if (cnt2 > 0) {
				System.out.println("delete 작업 성공...");
			} else {
				System.out.println("delete 작업 실패...");
			}
			System.out.println("--------------------------------------");

			// 2-4. select 연습
			// 1) 응답결과가 여러개인 경우
			System.out.println("select 연습 시작(응답결과가 여러개인 경우...)");
			/*
		   		응답 결과가 여러개 일 경우에는 quertForList메서드를 사용한다.
		   		이 메서드는 여러개의 레코드를 VO에 담은 후 이 VO데이터를 List에 
		     	추가해 주는 작업을 자동으로 수행한다.
	        */
			
			List<MemberVO> memList = smc.queryForList("member.getMemberAll");
			
//			for(MemberVO mv3 : memList) {
//				System.out.println("ID: " + mv3.getMemId());
//				System.out.println("이름: " + mv3.getMemName());
//				System.out.println("전화: " + mv3.getMemTel());
//				System.out.println("주소: " + mv3.getMemAddr());
//				
//				System.out.println("--------------------------------------");
//			}
			// 2) 응답결과가 한개인 경우
			System.out.println("select 연습 시작(결과가 1개일 경우)...");
			
			//응답결과가 한개가 확실할 경우에는 queryObject메서드를 이용한다.
			MemberVO mv4 = (MemberVO) smc.queryForObject("member.getMember", "a1234");
			System.out.println("ID: " + mv4.getMemId());
			System.out.println("이름: " + mv4.getMemName());
			System.out.println("전화: " + mv4.getMemTel());
			System.out.println("주소: " + mv4.getMemAddr());
			System.out.println("출력 끝...");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
}
