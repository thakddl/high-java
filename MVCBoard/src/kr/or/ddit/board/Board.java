package kr.or.ddit.board;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

public class Board {
/*	
   	위의 테이블을 작성하고 게시판을 관리하는
	다음 기능들을 구현하시오.

	기능 구현하기 ==> 전체 목록 출력, 새글작성, 수정, 삭제, 검색 
*/
	private IBoardService boardSrv;
	private Scanner scan = new Scanner(System.in);
	
	
	public Board() {
		boardSrv = new BoardServiceImpl();
	}

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
		
		List<BoardVO> bl = boardSrv.getListAll();
		
		if(bl == null) {
			System.out.println("등록된 게시물이 없습니다.");
			return;
		}
		for(BoardVO bv : bl) {
			System.out.printf("%d\t%s\t%s\t%.10s\t%s\n", bv.getBoardNo(), 
														 bv.getBoardTitle(), 
														 bv.getBoardWriter(), 
														 bv.getBoardDate(), 
														 bv.getBoardContent());
		}
		
	}

	private void insertPost() {
	
		System.out.println("등록하실 게시물을 작성해주세요.");
		System.out.print("제목을 입력하세요>> ");
		String boardTitle = scan.nextLine();
		System.out.print("작성자를 입력하세요>> ");
		String boardWriter = scan.nextLine();
		System.out.print("내용을 입력하세요>> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		
		int cnt = boardSrv.insertPost(bv);		
		
		if(cnt > 0) {
			System.out.println("게시물 등록이 완료되었습니다.");
		} else {
			System.out.println("게시물 등록을 실패하였습니다. 다시 시도해 주세요.");
		}

	}

	private void updatePost() {
		System.out.print("수정할 게시물의 번호를 입력해주세요>> ");
		int boardNo = Integer.parseInt(scan.nextLine());
		if(!checkPost(boardNo)) {
			System.out.print("없는 게시물 입니다. ");
			updatePost();
			return;
		}
		
		System.out.print("새로운 제목을 입력해주세요>> ");
		String boardTitle = scan.nextLine();
		System.out.print("새로운 작성자를 입력해주세요>> ");
		String boardWriter = scan.nextLine();
		System.out.print("새로운 내용을 입력해주세요>> ");
		String boardContent = scan.nextLine();
		
		BoardVO bv = new BoardVO();
		bv.setBoardTitle(boardTitle);
		bv.setBoardWriter(boardWriter);
		bv.setBoardContent(boardContent);
		bv.setBoardNo(boardNo);
		
		int cnt = boardSrv.updatePost(bv);
		
		if(cnt>0) {
			System.out.println("수정이 완료되었습니다.");
		} else {
			System.out.println("수정이 실패했습니다.");
		}
	}

	private boolean checkPost(int boardNo) {
		return boardSrv.checkPost(boardNo);
	}

	private void deletePost() {
		
		System.out.print("삭제할 게시물의 번호를 입력하세요.>> ");
		int boardNo = Integer.parseInt(scan.nextLine());
		if(!checkPost(boardNo)) {
			System.out.print("없는 게시물 입니다. ");
			deletePost();
			return;
		}
		
		int cnt = boardSrv.deletePost(boardNo);
		if(cnt>0) {
			System.out.println("삭제가 완료되었습니다.");
		} else {
			System.out.println("실패하였습니다. 다시 시도해 주세요.");
		}
		
	}
	
	private void searchPost() {
		System.out.print("검색할 게시물의 키워드를 입력해주세요.>> ");
		String word = scan.nextLine();
		
		System.out.println();
		System.out.println("----------------------------------------------------------------");
		System.out.println("순번\t제목\t작성자\t작성일\t\t내용");
		System.out.println("----------------------------------------------------------------");			
		
		List<BoardVO> bl = boardSrv.searchPost(word);
		
		if(bl == null) {
			System.out.println("등록된 게시물이 없습니다.");
			return;
		}
		for(BoardVO bv : bl) {
			System.out.printf("%d\t%s\t%s\t%.10s\t%s\n", bv.getBoardNo(), 
														 bv.getBoardTitle(), 
														 bv.getBoardWriter(), 
														 bv.getBoardDate(), 
														 bv.getBoardContent());
		}
	}

	public static void main(String[] args) {
		Board board = new Board();
		board.selectMenu();
	}
}
