package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * 자카르타 프로젝트의 fileupload 모듈을 이용한 파일 업로드 예제
 */
@WebServlet("/upload")
public class UploadServlet extends HttpServlet {
	//업로드 디렉토리 설정
	private static final String UPLOAD_DIR = "upload_files";
	//메모리 임계 크기(이 크기가 넘어가면 레파지토리 위치에 임시파일로 저장됨)
	private static final int MEMORY_THRESHOLD = 1024*1024*3;
	//파일 1개당 최대 크기
	private static final int MAX_FILE_SIZE = 1024*1024*40;
	//요청파일 최대 크기
	private static final int MAX_REQUEST_SIZE = 1024*1024*50;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Multipart Parsing전에 파라미터 정보 조회해 보기
		System.out.println("Multipart Parsing 전: " + req.getParameter("sender")); 
		
		//인코딩 타입이 multipart/form-data인지 확인
		if(ServletFileUpload.isMultipartContent(req)) {
			
			//폼 필드 데이터 저장용
			Map<String, String> formMap = new HashMap<>();
			
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
			
			ServletFileUpload upload = new ServletFileUpload(factory);
			
			upload.setFileSizeMax(MAX_FILE_SIZE);
			upload.setSizeMax(MAX_REQUEST_SIZE);
			
			// 웹애플리케이션 루트 디렉토리 기준 업로드 경로 설정하기
			String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
	         
			try {
				List<FileItem> formItems = upload.parseRequest(req);
	            
				if(formItems != null && formItems.size() > 0) {
					for (FileItem item : formItems) {
						if(!item.isFormField()) { // 파일인 경우...
							//전체경로를 제외한 파일명만 추가하기
							String fileName = item.getName();/*new File(item.getName()).getName();*/
							String filePath = uploadPath + File.separator + fileName;
							
							File storeFile = new File(filePath);
							
							item.write(storeFile);
							System.out.println("파일 저장 경로: " + filePath);
							
						} else { // 폼데이터인 경우...
							//폼필드의 값이 한글인 경우에는 해당 문자열을 적절히 변환해주어야 한다.
							formMap.put(item.getFieldName(), item.getString("UTF-8"));
						}
					}
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Multipart Parsing 후: " + formMap.get("sender")); 
			resp.setContentType("text/html");
			resp.setCharacterEncoding("UTF-8");
			resp.getWriter().print("업로드 완료됨");
			
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
