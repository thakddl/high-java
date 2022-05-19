package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Hotel {
	private Scanner scan;
	private Map<Integer, String> guestInfo;
	public Hotel() {
		super();
		scan = new Scanner(System.in);
		guestInfo = new HashMap<Integer, String>();
	}
	public void greeting() {		
		System.out.println("**************************");
		System.out.println("호텔 문을 열었습니다.");
		System.out.println("**************************\n");
		selectMenu();
	}
	public void selectMenu() {		
		System.out.println("*******************************************");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1.체크인  2.체크아웃 3.객실상태 4.업무종료");
		System.out.println("*******************************************");
		System.out.print("메뉴선택 => ");
		int selectMenu = Integer.parseInt(scan.nextLine());
		System.out.println();
		switch(selectMenu) {
			case 1:
				checkIn();
				break;
			case 2:
				checkOut();
				break;
			case 3:
				currentGuestList();
				break;
			case 4:
				close();
				return;
		}
		selectMenu();
	}
	public void checkIn() {		
		System.out.println("어느방에 체크인 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = Integer.parseInt(scan.nextLine());
		
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 => ");
		String cstmNm = scan.nextLine();
		
		if(guestInfo.put(roomNum, cstmNm) == null) {			
			System.out.println("체크인 되었습니다.");
		} else {
			System.out.println(roomNum + "은 이미 사용중인 방입니다.");
		}
	}
	public void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = Integer.parseInt(scan.nextLine());
		if(guestInfo.remove(roomNum) != null) {		
			System.out.println("체크아웃 되었습니다.");
		} else {
			System.out.println(roomNum + "방에는 체크인한 사람이 없습니다.");
		}
	}
	public void currentGuestList() {
		Iterator<Map.Entry<Integer, String>> guestIt= guestInfo.entrySet().iterator();
		while(guestIt.hasNext()) {
			Entry<Integer, String> entry = guestIt.next();
			System.out.printf("방 번호: %d, 투숙객: %s\n", entry.getKey(), entry.getValue());
		}
	}
	
	public void close() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}
	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		hotel.greeting();
	}
}
