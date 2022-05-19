package kr.or.ddit.basic;

public class T03_ThreadTest {
	public static void main(String[] args) {		
		//스레드 수행시간 체크해보기
		Thread th = new Thread(new MyRunner());
		
		//UTC(Universe Time Coordinated 협정 세계 표준시)를 사용하여 
		//1970년 1월 1일 0시 0분 0초를 기준으로 경과한 시간을 
		//밀리세컨드(1/1000초) 단위로 나타낸다.=>유닉스 타임스탬프
		long startTime = System.currentTimeMillis();
		th.start();
		try {
			th.join();
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("경과 시간: "+ (endTime - startTime));
	}

}

class MyRunner implements Runnable {

	@Override
	public void run() {
		long sum = 0;
		for(long i = 1L; i<=1000000000; i++) {
			sum += 1;
		}
		System.out.println("합계: " + sum);
		
	}
	
}
