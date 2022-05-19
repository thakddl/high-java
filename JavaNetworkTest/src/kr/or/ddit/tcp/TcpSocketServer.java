package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TcpSocketServer {
	public static void main(String[] args) throws IOException {
		// TCP 소켓 통신을 하기 위해 ServerSocket 생성
		ServerSocket server = new ServerSocket(7777);//하나의 서버 안에 여러 프로세스들을 구분하기 위해서 필요하다.
		System.out.println("서버가 접속을 기다립니다.");
		
		// accept()매서드는 client에서 요청이 올때 까지 계속 기다린다.
		// 연결 요청이 오면 Socket객체를 생성해서 Client의 Socket을 연결한다.
		Socket socket = server.accept();
		
		// ------------------------------------------
		// 이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		System.out.println("접속한 클라이언트 정보");
		System.out.println("주소: " + socket.getInetAddress());
		
		// Client에게 메세지 보내기
		// OutputStream객체를 통하여 전송한다.
		// 접속한 Socket의 getOutputStream()메서드를 이용한다.
		OutputStream out = socket.getOutputStream();
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("어서오세요");
		System.out.println("끝 ");
		
		dos.close();
		server.close();
		
	}
	
}
