package kr.or.ddit.basic;

class Util2 {
/* 
 * 제한된 타입 파라미터(Bounded Parameter) 
 */
	//T는 T인데 넘버거나 넘버의 자식들로 범위를 한정한 것이다.
	public static <T extends Number> int compare(T t1, T t2) {
		double v1 = t1.doubleValue();
		double v2 = t2.doubleValue();
		
		return Double.compare(v1, v2);
	}
}
/* 
 * 제한된 타입 파라미터 예제(Bounded Parameter) 
 */
public class T05_GenericMethodTest {
	public static void main(String[] args) {
		int result1 = Util2.compare(10, 20);
		System.out.println(result1);
		
		int result2 = Util2.compare(3.14, 3);//여기에 스트링 같은 타입을 넣으면 타입오류가 생긴다. 위에서 타입을 제한하고 있기 때문에
		System.out.println(result2);
	}
}
