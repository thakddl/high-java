package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class TcpFileClient {
	// 클라이언트는 서버에 접속하여 서버가 보내주는 파일을 D드라이브의 C_Lib폴더에 저장한다.
	private Socket socket;
	private InputStream in;
	private FileOutputStream fos;
	
	public void clientStart() {
		try {
			socket = new Socket("localhost", 7777);
			System.out.println("파일 다운로드 시작...");
			
			fos = new FileOutputStream("d:/C_Lib/addaddda.jpg");
			in = socket.getInputStream();
			
			BufferedInputStream bis = new BufferedInputStream(in);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			
			int c = 0;
			while((c = bis.read()) != -1) {
				bos.write(c);
			}
			bis.close();
			bos.close();
			
			System.out.println("파일 다운로드 완료");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {in.close();} catch(IOException ex) {}
			}
			if(fos != null) {
				try {fos.close();} catch(IOException ex) {}
			}
			if(socket != null) {
				try {socket.close();} catch(IOException ex) {}
			}
		}
	}
	public static void main(String[] args) {
		new TcpFileClient().clientStart();
	}
} 
