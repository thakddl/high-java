package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class T09_FileEncodingTest {
	/*
	 * InputStreamReader객체는 파일 인코딩 방식을 지정할 수 있다.
	 * 형식) new InputStreamReader(바이트 기반 스트림 객체, 인코딩 방식)
	 * 
	 * - 인코딩 방식
	 * 한글 인코딩 방식은 크게 UTF-8과 EUC-KR 방식 두가지로 나뉜다.
	 * 원래 한글 윈도우는 CP949방식을 사용했는데, 윈도우를 개발한 
	 * 마이크로소프트사에서 EUC-KR방식에서 확장하였기 때문에 MS949라고도 부른다.
	 * CP949 는  EUC-KR의 확장이며, 하위 호환성이 있다.
	 *   - MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI계열: ASCII부터 발전되어온 것 예:MS949, UTF-8)
	 *   - UTF-8 => 유니코드UTF-8 인코딩 방식(영문자 및 숫자: 1byte, 한글: 3byte)
	 *   - US-ASCII => 영문전용 인코딩 방식
	 *   
	 *   ANSI라는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 
	 *   나중에 여기에 EUC-KR, CP949이라는 식으로 한글이 포함되었음.
	*/
	
	
	public static void main(String[] args) {
		//파일 인코딩을 이용하여 읽어오기
		FileInputStream fis = null;
		InputStreamReader isr = null;
		
		try {
			fis = new FileInputStream("d:/D_Other/test_ansi.txt");//파일리더는 UTF-8만 읽어오기 때문에 깨질 수도 있다. FileInputStream 은 인코딩 방식 지정이 가능
//			isr = new InputStreamReader(fis, "MS949");
			isr = new InputStreamReader(fis, "utf-8");
			
			int c;
			while((c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");

			isr.close(); //보조스트림만 닫아도 된다.

		} catch(IOException ex) {
			ex.printStackTrace();
		} 
	}
	
}
