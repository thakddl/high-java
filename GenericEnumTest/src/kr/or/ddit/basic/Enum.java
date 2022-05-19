package kr.or.ddit.basic;

import java.util.Scanner;

import kr.or.ddit.basic.T01_EnumTest.Season;

public class Enum {
	public enum Planet {
		수성(2439), 
		금성(6052), 
		지구(6371), 
		화성(3390), 
		목성(69911), 
		토성(58232), 
		천왕성(25362), 
		해왕성(24622);
		
		private int km;
		
		Planet(int km){
			this.km = km;
		}

		public int getKm() {
			return km;
		}
	}
	public static double area(Planet planet) {
		return 4*Math.PI*planet.getKm()*planet.getKm();
	}
	public static void main(String[] args) {
//		문제) 태양계 행성을 나타내는 enum Planet을 이용하여 구하시오.
//		(단, enum 객체 생성시 반지름을 이용하도록 정의하시오.)
		Scanner scan = new Scanner(System.in);
		while(true) {
			try {
				System.out.print("면적을 구하고자 하는 태양계 행성 이름을 입력해주세요: ");
				String name = scan.nextLine();
				Planet planet = Planet.valueOf(name);
				System.out.printf("%s의 면적: %ekm²\n", name, area(planet));
			} catch (IllegalArgumentException e) {
				System.out.println("태양계 행성 이름이 아닙니다.");
				for(Planet planet : Planet.values()) {
					System.out.printf("%s의 면적: %ekm²\n", planet.name(), area(planet));
				}	
				break;
			}
		}
	}
}
