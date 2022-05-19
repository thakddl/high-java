package kr.or.ddit.basic;

import java.io.FileOutputStream;
import java.io.IOException;

public class T06_FileOutputStream {
	public static void main(String[] args) {
		
		//파일에 출력하기
		FileOutputStream fos = null;
		try {
			// 출력용 FileOutputStream 객체 생성
			fos = new FileOutputStream("d:/D_Other/out.txt");
			//바이트 기반에서는 1바이트씩 읽어오기 때문에 2~3바이트인 문자가 깨질 수 밖에 없다.
			//그래서 문자열 기반 스트리밍을 만들었다.
			for(char ch = 'a'; ch<='z'; ch++) {
				fos.write(ch);
			}
			
			System.out.println("파일에 쓰기 작업 완료");
			
		} catch(IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
	            fos.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }
		}
	}
}
