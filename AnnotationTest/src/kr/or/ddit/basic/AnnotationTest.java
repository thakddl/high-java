package kr.or.ddit.basic;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationTest {
	public static void main(String[] args) {
		System.out.println("static변수값: " + PrintAnnotation.id);
		
		// Reflection 기능을 이용한 메서드 실행하기
		// 선언된 메서드 목록 가져오기
		Method[] declareMethods =Service.class.getDeclaredMethods();
		
		for(Method m : declareMethods) {
			System.out.println(m.getName());//메소드명 출력
			
			Annotation[] annos = m.getDeclaredAnnotations();
			
			for(Annotation ano: annos) {
				if(ano.annotationType().getSimpleName().equals("PrintAnnotation")) {
					PrintAnnotation printAnn = (PrintAnnotation) ano;
					for(int i=0; i<printAnn.count(); i++) {
						//count 값 만큼 value 출력하기
						System.out.print(printAnn.value());
					}
				}
			}
			System.out.println();
			
			//메서드 실행하기
			Class<?> clazz = Service.class;
			
			try {
				//객체 생성하기
				Service service = (Service) clazz.newInstance();
				
				m.invoke(service);
				
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}
