package kr.or.ddit.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 클래스에 선언된 메서드의 메타정보 가져오기
 * @author PC-16
 *
 */
public class T03_MethodMetadataTest {
	public static void main(String[] args) throws ClassNotFoundException {
		
		//Class 객체 생성
		Class<?> klass = Class.forName("kr.or.ddit.reflection.SampleVO");
		
		//클래스에서 선언된 모든 메서드의 메타데이터 정보 가져오기
		Method[] methodArr = klass.getDeclaredMethods();
		
		for(Method m : methodArr) {
			System.out.println("메서드 명: " + m.getName());
			System.out.println("메서드 리턴타입: " + m.getReturnType());
			
			//해당 메서드의 접근제어자 정보 가져오기
			int modFlag = m.getModifiers();
			System.out.println("메서드 접근제어자: " + Modifier.toString(modFlag));
			
			//해당 메서드의 파라미터값 가져오기
			Class<?>[] paramArr = m.getParameterTypes();
			System.out.println("메서드 파라미터 타입: ");
			for(Class c : paramArr) {
				System.out.print(c.getName() + " | ");
			}
			System.out.println();
			
			// 해당 메서드에서 던지는 예외타입 가져오기
			Class<?>[] exTypeArr = m.getExceptionTypes();
			for(Class<?> clazz : exTypeArr) {
				System.out.print(clazz.getName() + " | ");
			}
	         
			// 해당 메서드에 존재하는 Annotation타입 정보 가져오기
			Annotation[] annos = klass.getAnnotations();
			for(Annotation anno : annos) {
				System.out.print(anno.annotationType().getName() + " | ");
			}
			System.out.println();
			System.out.println("---------------------------------------");

			
		}
	}
}
