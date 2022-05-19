package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;

public class T05_FileInputStream {
	public static void main(String[] args) {
		// FileInputStream객체를 이용한 파일 내용 읽기(바이트 단위로 입력)
		FileInputStream fis = null;//선언
		
		try {
			//방법1(파일 정보를 문자열로 지정하기)
			fis = new FileInputStream("d:/D_Other/test2.txt");
			
			// 방법2 (파일 정보를 File 객체를 이용하여 지정하기)
			//fis = new FileInputStream(new File("d:/D_Other/test2.txt"));
			
			int c; // 읽어온 데이터를 저장할 변수
			
			//읽어온 값이 -1이면 파일의 끝까지 읽었다는 의미임.
			while((c=fis.read()) != -1) {
				//읽어온 자료 출력하기
				System.out.print((char)c);
			}
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				fis.close();  // 작업 후 스트림 닫기
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
