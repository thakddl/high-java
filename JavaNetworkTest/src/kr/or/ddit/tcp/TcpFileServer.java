package kr.or.ddit.tcp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpFileServer {
	private ServerSocket server;
	private Socket socket;
	private OutputStream out;
	private FileInputStream fis;
	
	public void serverStart() {
		try {
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			
			while(true) {
				System.out.println("파일 전송 대기중...");
				socket = server.accept();
				
				System.out.println("파일 전송 시작");
				fis = new FileInputStream("d:/D_Other/tulips.jpg");
				
				out = socket.getOutputStream();
				
				BufferedInputStream bis = new BufferedInputStream(fis);
				BufferedOutputStream bos = new BufferedOutputStream(out);
				
				int c = 0;
				while((c = bis.read()) != -1) {
					bos.write(c);
				}
				bis.close();
				bos.close();
				
				System.out.println("파일 전송 완료");
						
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(fis != null) {
				try {fis.close();} catch(IOException ex) {}
			}
			if(out != null) {
				try {out.close();} catch(IOException ex) {}
			}
			if(socket != null) {
				try {socket.close();} catch(IOException ex) {}
			}
			if(server != null) {
				try {server.close();} catch(IOException ex) {}
			}
		}
	}
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
	}
}
