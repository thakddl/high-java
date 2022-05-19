package kr.or.ddit.udp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpFileSend {
	public static void main(String[] args) throws InterruptedException {

		String receiveIp = "localhost";
		int port = 8888;

		File file = new File("d:/C_Lib/aaahoho.jpg");
		if (!file.exists()) {
			System.out.println("파일이 존재하지 않습니다.");
			System.exit(0);
		}

		DatagramSocket ds = null;

		long fileSize = file.length();

		long totalReadBytes = 0;

		long startTime = System.currentTimeMillis();

		try {
			ds = new DatagramSocket();
			InetAddress receiveAddr = InetAddress.getByName(receiveIp);

			String str = "start"; // 전송 시작을 알려주기 위한 문자열

			DatagramPacket dp = new DatagramPacket(str.getBytes(), str.getBytes().length, receiveAddr, port);

			ds.send(dp);

			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[1000];

			// 파일명 전송
			str = file.getName();
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, receiveAddr, port);
			ds.send(dp);

			// 총 파일 크기 정보를 전송
			str = String.valueOf(fileSize);
			dp = new DatagramPacket(str.getBytes(), str.getBytes().length, receiveAddr, port);
			ds.send(dp);

			while (true) {
				// 패킷전송간의 시간 간격 주기 위해서..
				Thread.sleep(10);

				int readBytes = fis.read(buffer, 0, buffer.length);

				if (readBytes == -1) {
					break;
				}
				dp = new DatagramPacket(buffer, readBytes, receiveAddr, port);

				ds.send(dp);

				totalReadBytes += readBytes;
				System.out.println("진행 상태 : " + totalReadBytes + "/" + fileSize + " Bytes("
						+ (totalReadBytes * 100 / fileSize) + " %)");
			}
			long endTime = System.currentTimeMillis();
			long diffTime = endTime - startTime;
			double transferSpeed = fileSize / diffTime;

			System.out.println("걸린 시간 : " + diffTime + " (ms)");

			System.out.println("평균 전송속도 : " + transferSpeed + " Bytes/ms");

			System.out.println("전송 완료...");

			fis.close();
			ds.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}