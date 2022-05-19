package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Lotto {
	// 로또를 구매하는 프로그램 작성하기
	//
	// 사용자는 로또를 구매할 때 구매할 금액을 입력하고
	// 입력한 금액에 맞게 로또번호를 출력한다. 
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		loop:
		while (true) {
			System.out.println("====================");
			System.out.println("Lotto Program");
			System.out.println("--------------------");
			System.out.println("1. Lotto 구입");
			System.out.println("2. 프로그램 종료");
			System.out.println("====================");
			System.out.print("메뉴 선택: ");
			int setMenu = Integer.parseInt(scanner.nextLine());
			switch(setMenu) {
				case 1:
					System.out.println("로또 구입 시작");
					System.out.println("1000원에 로또 하나 입니다.");
					System.out.print("금액 입력: ");
					int setMoney = Integer.parseInt(scanner.nextLine());
					int lottoCnt = setMoney/1000;
					int change = setMoney%1000;
					System.out.println("\n행운의 로또번호는 아래와 같습니다.");
					List<Set> list = new ArrayList<>();
					for(int i=0; i<lottoCnt; i++) {						
						Set<Integer> lottoNumbers = new HashSet();
						while(lottoNumbers.size()<6) {
							int randomNum = (int) (Math.random()*45)+1;
							lottoNumbers.add(randomNum);
						}
						list.add(lottoNumbers);
					}
					for(int i=0; i<list.size(); i++) {
						System.out.printf("로또번호 %d: %s \n", i+1, list.get(i));
					}
					
					System.out.printf("받은 금액은 %d원이고 거스름돈은 %d원입니다.\n\n", setMoney, change);
					break;
				default:
				case 2:
					System.out.println("감사합니다.");
					break loop;
			}
		}
		
	}
}
