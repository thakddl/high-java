package kr.or.ddit.basic;

public class T12_ThreadYieldTest {
/*
	yield() 메소드에 대하여...
	
	1.현재 실행대기중인 동등우선순위 이상의 다른 스레드에게 실행기회를 제공한다.
	2.현재 실핸중ㅇ인 스레드의 상태를 Runnable 상태로 바꾼다.
	  (WAITTING이나 BLOCKED 상태로 바뀌지 않는다.)
	3.yield() 메소드를 실행한다고 해서 현재 실행중인 스레드가 곧바로 Runnable 상태로 전이된다고 확신할 수 없다!!!(작업이 불안정하게 끝나면 안되기 때문에)
*/
	public static void main(String[] args) {
		Thread th1 = new YieldThreadEx1();
		Thread th2 = new YieldThreadEx2();
		th1.start();
		th2.start();
	}
}
class YieldThreadEx1 extends Thread {
	public YieldThreadEx1() {
		super("YieldThreadEx1");//스레드의 이름 설정하기
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);//Thread.currentThread().getName() 스레드의 이름 가져오기
			Thread.yield();//양보하기
		}
	}
}
class YieldThreadEx2 extends Thread {
	public YieldThreadEx2() {
		super("YieldThreadEx2");
	}
	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			
		}
	}
}