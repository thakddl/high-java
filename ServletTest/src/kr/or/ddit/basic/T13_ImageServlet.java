package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class T13_ImageServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("image/jpeg");//컨텐트 타입설정
		
		ServletOutputStream out = resp.getOutputStream();
		
		FileInputStream fis = new FileInputStream("D:\\D_Other\\aaa.jpg");
		
		BufferedInputStream bis = new BufferedInputStream(fis);
		BufferedOutputStream bos = new BufferedOutputStream(out);
		
		int bytes = 0;//읽은 바이트 수
		while((bytes = bis.read()) != -1) {
			bos.write(bytes);
		}
		
		bis.close();
		bos.close();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
