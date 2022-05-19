package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Copy {
	public static void main(String[] args) {		
		try {		
			FileInputStream fis = new FileInputStream("d:/D_Other/Tulips.jpg");
			FileOutputStream fos = new FileOutputStream("d:/D_Other/TulipsCopy.jpg");
			
			int b;
			while((b = fis.read()) != -1) {
				fos.write(b);
			}
			fis.close();
			fos.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
}
