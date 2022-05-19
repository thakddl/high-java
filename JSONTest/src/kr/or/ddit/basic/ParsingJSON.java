package kr.or.ddit.basic;

import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ParsingJSON {
	
	public void parse() {
		
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			
			String svcKey = "qLq78EfxQYIfq5NZ%2F%2FBSf3ibJImJpqwgU0A%2FvPCBiXqj1hf6jERst1u%2B9tA9zXW2JG0lJDgYEhSKTK63Tama3g%3D%3D";
			String tourSeq = "1334";
			
			URL url = new URL("http://apis.data.go.kr/6300000/tourDataService/tourDataItemJson?tourSeq="+tourSeq+"&serviceKey="+svcKey);
			System.out.println(url);
			
			JSONParser parser = new JSONParser();
			JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(url.openConnection().getInputStream()));
			
			JSONObject jsonData = (JSONObject) jsonObject.get("msgBody");
			
			System.out.println(jsonData.get("name"));
			System.out.println(jsonData.get("dCodeNm"));
			System.out.println(jsonData.get("closeDay"));
			System.out.println(jsonData.get("priceDesc"));
			System.out.println(jsonData.get("parkDesc"));
			System.out.println(jsonData.get("ownerDriver"));
			System.out.println(jsonData.get("addr1"));
			System.out.println(jsonData.get("addr2"));
			System.out.println(jsonData.get("dTimeEtc"));
			System.out.println(jsonData.get("contents1"));
			System.out.println(jsonData.get("homepage"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		new ParsingJSON().parse();
	}
}
