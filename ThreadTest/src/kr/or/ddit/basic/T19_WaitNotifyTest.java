package kr.or.ddit.basic;

public class T19_WaitNotifyTest {
/*
	wait() 메소드 => 동기화 영역에서 락을 풀고 Wait-Set영역(공유객체별로 존재)으로 이동시킨다.
	notify() 또는 notifyAll() 메소드 => Wait-Set영역에 있는 스레드를 깨워서 실행될 수 있도록 한다.
	(notify()는 하나, notifyAll()은 Wait-Set에 있는 전부를 깨운다.
	
	=> wait()와 notify(), notifyAll()은 동기화 영역에서만 실행할 수 있고, Object클래스에서 제공하는 메소드이다.
	
*/
	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA tha = new ThreadA(workObj);
		ThreadB thb = new ThreadB(workObj);
		
		tha.start();
		thb.start();
	}
}
//공통으로 사용할 객체
class WorkObject{
	public synchronized void methodA() {
		System.out.println("methodA()메소드에서 작업중...");
		notify();
		try {
			wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void methodB() {
		System.out.println("methodB()메소드에서 작업중...");
		notify();
		try {
			wait(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
//WorkObject의 methodA()만 호출하는 스레드
class ThreadA extends Thread {
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodA();
		}
		System.out.println("methodA 종료.");
	}
}

class ThreadB extends Thread {
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			workObj.methodB();
		}
		System.out.println("methodB 종료.");
	}
}


