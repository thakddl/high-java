package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Hotel {
	private Scanner scan;
	private Map<Integer, String> guestInfo;

	public Hotel() {
		super();
		this.scan = new Scanner(System.in);
		this.guestInfo = new HashMap<>();
	}

	public void greeting() {
		// 저장된 고객정보를 읽어와 Map에 저장하기
		ObjectInputStream ois = null;
		try {
			// 입력용 스트림 객체 생성
			ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream("d:/D_Other/guestInfo.bin")));
			Guest guest = null;
			while ((guest = (Guest) ois.readObject()) != null) {
				guestInfo.put(guest.getRoomNum(), guest.getName());
				// System.out.println(guest.getRoomNum()+":"+ guest.getName());
			}

		} catch (ClassNotFoundException e) {
			System.out.println("Guest클래스가 없어서 캐스팅할 수 없음");
		} catch (IOException e) {
			System.out.println("체크인 정보 로드 완료");
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				// 파일이 없으면 객체가 생성되지 않으므로 null이된다.
				// ObjectInputStream은 파일의 내용이 없으면 null값을 반환하고 자동으로 close()된다.
				// System.out.println("널 익셉");
			}
		}
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
		switch (selectMenu) {
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

		if (guestInfo.put(roomNum, cstmNm) == null) {
			saveInfo();
			System.out.println("체크인 되었습니다.");
		} else {
			System.out.println(roomNum + "은 이미 사용중인 방입니다.");
		}
	}

	public void checkOut() {
		System.out.println("어느방을 체크아웃 하시겠습니까?");
		System.out.print("방번호 입력 => ");
		int roomNum = Integer.parseInt(scan.nextLine());
		if (guestInfo.remove(roomNum) != null) {
			saveInfo();
			System.out.println("체크아웃 되었습니다.");
		} else {
			System.out.println(roomNum + "번 방에는 체크인한 사람이 없습니다.");
		}
	}

	public void currentGuestList() {
		Set<Map.Entry<Integer, String>> guestInfoSet = guestInfo.entrySet();
		Iterator<Map.Entry<Integer, String>> guestIt = guestInfoSet.iterator();
		if (guestInfoSet.isEmpty()) {
			System.out.println("아직 체크인한 고객이 없습니다.");
			return;
		}
		while (guestIt.hasNext()) {
			Entry<Integer, String> entry = guestIt.next();
			System.out.printf("방 번호: %d, 투숙객: %s\n", entry.getKey(), entry.getValue());
		}
	}

	public void close() {
		System.out.println("**************************");
		System.out.println("호텔 문을 닫았습니다.");
		System.out.println("**************************");
	}

	public void saveInfo() {
		Iterator<Map.Entry<Integer, String>> guestIt = guestInfo.entrySet().iterator();
		System.out.println("도달");
		ObjectOutputStream oos = null;
		Guest guest = null;
		try {
			//출력용 스트림 객체 생성
			oos = new ObjectOutputStream(new BufferedOutputStream(
										 new FileOutputStream("d:/D_Other/guestInfo.bin")));
			
			while (guestIt.hasNext()) {
				Entry<Integer, String> entry = guestIt.next();
				guest = new Guest(entry.getKey(), entry.getValue());
				// 쓰기 작업
				oos.writeObject(guest);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Hotel hotel = new Hotel();
		hotel.greeting();
	}
}

// 고객 정보 VO
class Guest implements Serializable {
	private int roomNum;
	private String name;

	public Guest(int roomNum, String name) {
		super();
		this.roomNum = roomNum;
		this.name = name;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}