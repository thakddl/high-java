package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
 * 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
 * 
 * 컴퓨터의 가위바위보는 난수를 이용하여 구하고
 * 사용자의 가위바위보는 showInputDialog()메소드를 이용하여 입력받는다.
 * 
 * 입력시간은 5초로 제안하고 카운트 다운을 진행한다.
 * 5초 안에 입력이 없으면 게임을 진것으로 처리한다.
 * 
 * 5초안에 입력이 완료되면 승패를 출력한다.
 * 결과 예시)
 * === 결과 ===
 * 컴퓨터: 가위
 * 당   신: 바위
 * 결   과: 당신이 이겼습니다.
*/
public class T07_ThreadGame {
	public static boolean inputCheck = false;
	static String userVal;
	public static void main(String[] args) {
		Thread th1 = new Count();
		Thread th2 = new Game();
		th1.start();
		th2.start();
		try {
			th2.join();
		} catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		String[] val = {"가위", "바위", "보"};
		int random = (int) (Math.random()*3);
		String computerVal = val[random];
		String result;
		if(computerVal.equals(userVal)) {
			result = "비겼습니다.";
		} else if(userVal.equals("가위") && computerVal.equals("보")
				  ||userVal.equals("바위") && computerVal.equals("가위")
				  ||userVal.equals("보") && computerVal.equals("바위")) {
			result = "당신이 이겼습니다.";
		} else {
			result = "컴퓨터가 이겼습니다.";
		}
		
		System.out.println("=== 결과 ===");
		System.out.println("컴퓨터: "+ computerVal);
		System.out.println("당   신: "+ userVal);
		System.out.println("결   과: "+ result);
	}
}
class Game extends Thread {
	
	@Override
	public void run() {
		
		T07_ThreadGame.userVal = JOptionPane.showInputDialog("가위바위보를 입력하세요.");
		T07_ThreadGame.inputCheck = true;
	}
}
class Count extends Thread {
	@Override
	public void run() {
		
		for (int i = 5; i >= 1; i--) {
			// 입력이 완료되었는지 여부를 검사하고 입력이 완료되면
			// run()메소드를 종료시킨다. 즉, 현재 스레드를 종료시킨다.
			if(T07_ThreadGame.inputCheck == true) {
				return;
			}
			
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초동안 잠시 멈춘다.
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			
		}
		
		//5초가 경과되었는데도 입력이 없으면 프로그램을 종료한다.
		System.out.println("5초가 지났습니다. 컴퓨터가 이겼습니다.");
		System.exit(0); // 프로그램을 종료시키는 명령.
		
	}
}
