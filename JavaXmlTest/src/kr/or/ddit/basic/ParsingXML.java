package kr.or.ddit.basic;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParsingXML {
	
	public void parse() {
	
		try {
			
			// Dom Document 객체 생성을 위한 메서드
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = dbf.newDocumentBuilder();
			
			String svcKey = "qLq78EfxQYIfq5NZ%2F%2FBSf3ibJImJpqwgU0A%2FvPCBiXqj1hf6jERst1u%2B9tA9zXW2JG0lJDgYEhSKTK63Tama3g%3D%3D";
			String tourSeq = "1334";
			
			URL url = new URL("http://apis.data.go.kr/6300000/tourDataService/tourDataItem?tourSeq="+tourSeq+"&serviceKey="+svcKey);
			
			// Dom 파서로부터 입력받은 url 파싱요청
			Document xmlDoc = builder.parse(url.toString());
			
			// 루트 엘리먼트 객체 가져오기
			Element root = xmlDoc.getDocumentElement();
			
			// 하위 엘리먼트 접근하기 
			Element bodyEl = (Element) root.getElementsByTagName("msgBody").item(0);
		
			System.out.println(bodyEl.getElementsByTagName("name").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("dCodeNm").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("closeDay").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("priceDesc").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("parkDesc").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("ownerDriver").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("addr1").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("addr2").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("dTimeEtc").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("contents1").item(0).getTextContent());
			System.out.println(bodyEl.getElementsByTagName("homepage").item(0).getTextContent());
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new ParsingXML().parse();
	}

}
