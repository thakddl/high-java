package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.Date;

public class TcpSocketClient {
	public static void main(String[] args) throws IOException {
		
		String serverIp = "127.0.0.1";
		//자기자신의 컴퓨터를 나타내는 방법
		//IP: 127.0.0.1
		//컴이름: localHost
		System.out.println(serverIp + " 서버에 접속중입니다.");
		
		// 소켓을 생성해서 서버에 연결을 요청한다.
		Socket socket = new Socket(serverIp, 7777);
		
		// 연결이 되면 이 후의 명령이 실행된다.
		System.out.println("연결 되었습니다.");
		
		//서버에서 보내온 편지 받기
		
		// 메세지를 받기위해 InputSteam객체를 이용한다.
		// Socket getInputStream()메서드를 이용.
		InputStream in = socket.getInputStream();
		DataInputStream dis = new DataInputStream(in);
		
		// 서버로부터 받은 메세지 출력하기
		System.out.println("받은 메세지: " + dis.readUTF());
		System.out.println("연결 종료.");
		dis.close();
		socket.close();
	}
}
