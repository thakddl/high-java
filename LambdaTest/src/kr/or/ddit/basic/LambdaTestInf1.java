package kr.or.ddit.basic;

//함수적 인터페이스 => 추상메서드가 하나뿐인 인터페이스
@FunctionalInterface
public interface LambdaTestInf1 {
	//반환값이 없고 매개변수도 없는 추상 메서드 선언
	public void test();
}
@FunctionalInterface
interface LambdaTestInf2 {
	//반환값이 없고 매개변수는 있는 추상 메서드 선언
	public void test(int a);
}
@FunctionalInterface
interface LambdaTestInf3 {
	//반환값도 있고 매개변수도 있는 추상 메서드 선언
	public int test(int a, int b);
}