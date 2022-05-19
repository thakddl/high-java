package kr.or.ddit.basic;

public class T10_ThreadStateTest {
/*
	 <스레드의 상태>
     
     NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
     RUNNABLE : 실행 중 또는 실행 가능한 상태
     BLOCKED : 동기화블럭에 의해서 일시정지된 상태(Lock이 풀릴 때 까지 기다리는 상태)
     WAITTING, 
     TIMED_WAITTING : 스레드의 작업이 종료되지는 않았지만 실행 가능하지는 않은 일시정지 상태. 
                      TIMED_WAITTING은 일시정지 시간이 지정된 경우임.
     TERMINATED : 스레드의 작업이 종료된 상태
 */
	public static void main(String[] args) {
		StatePrintThread spt = new StatePrintThread(new TargetThread());
		spt.start();
	}
}
// 스레드의 상태를 출력하는 스레드 클래스
class StatePrintThread extends Thread {
	private Thread targetThread;//상태를 모니터링할 대상 스레드
	
	public StatePrintThread(Thread targetThread) {
		this.targetThread = targetThread;
	}
	
	@Override
	public void run() {
		while (true) {
			//스레드의 상태값 구하기
			Thread.State state = targetThread.getState();
			System.out.println("대상 스레드의 상태값: "+state);
			
			//NEW 상태인지 검사
			if(state == Thread.State.NEW) {
				targetThread.start();
			}
			
			//타겟 스레드가 종료 상태인지 검사
			if(state == Thread.State.TERMINATED) {
				break;
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}
}

//모니터링할 대상 스레드
class TargetThread extends Thread {
	@Override
	public void run() {
		for (long i = 1; i <= 1000000000L; i++) {} //시간지연용
		try {
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		for (long i = 1; i <= 1000000000L; i++) {} //시간지연용
	}
}
