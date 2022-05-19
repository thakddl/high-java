package kr.or.ddit.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Board {
/*	
   	위의 테이블을 작성하고 게시판을 관리하는
	다음 기능들을 구현하시오.

	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
*/
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in);
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 메 뉴 선 택 ===");
		System.out.println("  1. 전체 게시물 목록");
		System.out.println("  2. 게시물 작성");
		System.out.println("  3. 게시물 수정");
		System.out.println("  4. 게시물 삭제");
		System.out.println("  5. 게시물 검색");
		System.out.println("  6. 종료.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	public void selectMenu() {
		int menu;
		do {
			displayMenu();
			menu = Integer.parseInt(scan.nextLine());
			switch(menu) {
				case 1: //전체 게시물 목록
					getListAll();
					break;
				case 2: //게시물 작성
					insertPost();
					break;
				case 3: //게시물 수정
					updatePost();
					break;
				case 4: //게시물 삭제
					deletePost();
					break;
				case 5: //게시물 검색
					searchPost();
					break;
				case 6: //종료
					System.out.println("프로그램 종료");
					break;
				default:
					System.out.println("없는 메뉴 입니다. 메뉴를 다시 선택해주세요.");
					
			}
		} while (menu != 6);
	}

	private void getListAll() {
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		System.out.println("순번\t제목\t작성자\t작성일\t\t내용");
		System.out.println("----------------------------------------------------------------");
		
		try {
			conn = BoardJDBCUtil.getConnection();
			String sql = "select * from jdbc_board";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs == null) {
				System.out.println("등록된 게시물이 없습니다.");
				return;
			}
			while(rs.next()) {
				System.out.printf("%d\t%s\t%s\t%.10s\t%s\n", rs.getInt("BOARD_NO"), rs.getString("BOARD_TITLE"), rs.getString("BOARD_WRITER"), rs.getString("BOARD_DATE"), rs.getString("BOARD_CONTENT"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			BoardJDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	private void insertPost() {
		
		
		try {
			conn = BoardJDBCUtil.getConnection();
			
			System.out.println("등록하실 게시물을 작성해주세요.");
			System.out.print("제목을 입력하세요>> ");
			String bTitle = scan.nextLine();
			System.out.print("작성자를 입력하세요>> ");
			String bWriter = scan.nextLine();
			System.out.print("내용을 입력하세요>> ");
			String bContent = scan.nextLine();
			
			String sql = "insert into jdbc_board values (board_seq.nextVal, ?, ?, sysdate, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTitle);
			pstmt.setString(2, bWriter);
			pstmt.setString(3, bContent);
			int result = pstmt.executeUpdate();		
			
			if(result > 0) {
				System.out.println("게시물 등록이 완료되었습니다.");
			} else {
				System.out.println("게시물 등록을 실패하였습니다. 다시 시도해 주세요.");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			BoardJDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	private void updatePost() {
		try {
			
			System.out.print("수정할 게시물의 번호를 입력해주세요>> ");
			int bNo = Integer.parseInt(scan.nextLine());
			if(!checkPost(bNo)) {
				System.out.print("없는 게시물 입니다. ");
				updatePost();
				return;
			}
			
			System.out.print("새로운 제목을 입력해주세요>> ");
			String bTit = scan.nextLine();
			System.out.print("새로운 작성자를 입력해주세요>> ");
			String bWrt = scan.nextLine();
			System.out.print("새로운 내용을 입력해주세요>> ");
			String bCon = scan.nextLine();
			
			conn = BoardJDBCUtil.getConnection();
			String sql = "update jdbc_board set board_title=?, board_writer=?, board_content=?, board_date=sysdate where board_no=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTit);
			pstmt.setString(2, bWrt);
			pstmt.setString(3, bCon);
			pstmt.setInt(4, bNo);
			
			int result = pstmt.executeUpdate();
			
			if(result>0) {
				System.out.println("수정이 완료되었습니다.");
			} else {
				System.out.println("수정이 실패했습니다.");
			}
				
		} catch(SQLException e) {
//			e.printStackTrace();
			System.out.println("제목과 작성자는 빈값일 수 없습니다.");
		} finally {
			BoardJDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
	}

	private boolean checkPost(int bNo) {
		try {
			conn = BoardJDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_no = " + bNo;
			
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			
			if(result>0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			BoardJDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
		return false;
		
	}

	private void deletePost() {
		try {
			System.out.print("삭제할 게시물의 번호를 입력하세요.>> ");
			int no = Integer.parseInt(scan.nextLine());
			if(!checkPost(no)) {
				System.out.print("없는 게시물 입니다. ");
				deletePost();
				return;
			}
			conn = BoardJDBCUtil.getConnection();
			
			String sql = "delete from jdbc_board where board_no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("삭제가 완료되었습니다.");
			} else {
				System.out.println("실패하였습니다. 다시 시도해 주세요.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BoardJDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}
	
	private void searchPost() {
		try {
			System.out.print("검색할 게시물의 키워드를 입력해주세요.>> ");
			String word = scan.nextLine();
			
			conn = BoardJDBCUtil.getConnection();
			
			String sql = "select * from jdbc_board where board_title like '%"+word+"%' or board_content like '%"+word+"%'";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			System.out.println();
			System.out.println("----------------------------------------------------------------");
			System.out.println("순번\t제목\t작성자\t작성일\t\t내용");
			System.out.println("----------------------------------------------------------------");			
			while(rs.next()) {
				System.out.printf("%d\t%s\t%s\t%.10s\t%s\n", rs.getInt("BOARD_NO"), rs.getString("BOARD_TITLE"), rs.getString("BOARD_WRITER"), rs.getString("BOARD_DATE"), rs.getString("BOARD_CONTENT"));
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			BoardJDBCUtil.disConnect(conn, stmt, pstmt, rs);
		}
		
	}

	public static void main(String[] args) {
		Board board = new Board();
//		board.selectMenu();
		board.searchPost();
	}
}
