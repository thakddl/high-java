package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class T05_SetTest {
	/*
	 * - List와 Set의 차이점
	 * 
	 * 1. List - 입력한 데이터의 순서가 있다. - 중복되는 데이터를 저장할 수 있다.
	 * 2. Set - 입력한 데이터의 순서가 없다. - 중복되는 데이터를 저장할 수 없다.
	 * 
	 */
	public static void main(String[] args) {

		Set hs1 = new HashSet();

		// Set에 데이터를 추가할 때도 add()메서드를 사용한다.
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);

		System.out.println("Set의 데이터 : " + hs1);
		System.out.println();

		// Set은 데이터의 순서가 없고, 중복을 허용하지 않는다.
		// 그래서 이미 있는 데이터를 add하면 false를 반환하고, 데이터는 추가되지 않는다.
		boolean isAdded = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();

		isAdded = hs1.add("CC");
		System.out.println("중복 될때 : " + isAdded);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();

		// Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에 자료를 삭제 후
		// 새로운 데이터를 추가해 주어야 한다.

		// 삭제하는 메서드
		// 1) clear() => Set의 데이터 전체 삭제
		// 2) remove(삭제할 자료) => 해당 자료 삭제

		// 예) 'FF'를 'EE'로 수정하기
		hs1.remove("FF"); // FF자료 삭제
		System.out.println("삭제 후 Set 데이터 : " + hs1);
		System.out.println();

		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();

		// hs1.clear(); // 전체자료 삭제
		// System.out.println("Clear 후 Set 데이터 : " + hs1);
		System.out.println("Set의 자료 개수 : " + hs1.size());
		System.out.println();

		// Set은 데이터의 순서가 없기 때문에 List처럼 인덱스로 데이터를 하나씩 불러올 수 없다.
		// 그래서 데이터를 하나씩 얻기 위해서는 Iterator를 이용 해야한다.
		// Set의 데이터를 Iterator로 변환하기 => iterator()메서드 호출.
		Iterator it = hs1.iterator();

		// 데이터 갯수만큼 반복하기
		// hasNext() => 포인터 다음 위치에 데이터가 있으면 true, 없으면 false를 반환한다.
		while (it.hasNext()) { // 다음 자료가 있는지 검사
			// next() -> 포인터를 다음 자료 위치로 이동하고, 이동한 위치의 자료를 반환한다,
			System.out.println(it.next());

		}

	}
}