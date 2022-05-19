package kr.or.ddit.basic;

public class T02_ThreadTest {
	public static void main(String[] args) {
		//멀티 스레드 프로그램 방식
		//방법1: Thread 클래스를 생성한 class의 인스턴스를 생성한 후 
		//      이 인스턴스의 start()메서드를 호출한다. run메서드는 main스레드에 의해 순차적으로 실행된다.
		MyThread1 th1 = new MyThread1();
//		th1.start();
		th1.run();
		//방법2: Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후 
		//      이 인스턴스를 Thread객체의 인스턴스를 생성할 때 생성자의 매개변수로 넘겨준다.
		//      이 때 생성된 Thread객체 인터페이스의 start()메서드를 호출한다.
		MyThread2 r1 = new MyThread2();
		Thread th = new Thread(r1);
//		th.start();
		th.run();
		
		//익명 클래스를 이용하는 방법
		//방법3: Runnable인터페이스를 구현한 익명클래스를 Thread 인스턴스를 생성할 때 매개변수로 넣어준다.
		Thread th3 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=1; i<=50; i++) {
					System.out.print("@");
					try {
						// Thread.sleep(시간)=>주어진 시간동안 작업을 잠시 멈춤.
						// 시간은 밀리세컨드 단위를 사용한다.
						// 즉, 1000은 1초를 의미한다.
						Thread.sleep(20);
					} catch(InterruptedException ex) {
						ex.printStackTrace();
					}
				}
			}
		});
//		th3.start();
		th3.run();
	}
}

class MyThread1 extends Thread {//자바에서는 상속을 한개 밖에 받을 수 없으므로 상속이 필요할 때는 사용할 수 없어서 다른 방법(인터페이스를 사용하는 방법)을 만들었다.
	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			System.out.print("*");
			try {
				// Thread.sleep(시간)=>주어진 시간동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(15);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
	}
}
class MyThread2 implements Runnable {

	@Override
	public void run() {
		for(int i=1; i<=50; i++) {
			System.out.print("$");
			try {
				// Thread.sleep(시간)=>주어진 시간동안 작업을 잠시 멈춤.
				// 시간은 밀리세컨드 단위를 사용한다.
				// 즉, 1000은 1초를 의미한다.
				Thread.sleep(25);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
		}
		
		
	}
	
}