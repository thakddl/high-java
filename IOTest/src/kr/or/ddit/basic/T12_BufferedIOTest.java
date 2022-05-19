package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 성능향상을 위한 보조 스트림 예제2
 * (문자 기반의 Buffered 스트림 사용 예제) 
 */

public class T12_BufferedIOTest {
	public static void main(String[] args) {
		FileReader fr = null;
		
		try {
			fr = new FileReader("src/kr/or/ddit/basic/T11_BufferedIOTest.java");
			
//			int c;
//			while((c=fr.read()) != -1) {
//				System.out.print((char) c);
//			}
			
			// 한줄씩 읽을 수 있도록 해주는 readLine 매소드를 이용하기 위해 BufferedReader 사용함.
			BufferedReader br = new BufferedReader(fr);
			String temp = "";
			for (int i = 1; (temp = br.readLine()) != null; i++) {//readLine은 스트링 리턴
				System.out.printf("%4d: %s\n", i, temp);
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
