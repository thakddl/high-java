package kr.or.ddit.basic;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class T01_StreamCreateTest {
	public static void main(String[] args) {
//		List<Integer> list = Arrays.asList(1,2,3,4,5);
//		Stream<Integer> intStream = list.stream();// list으로 부터 Stream 생성
//		intStream.forEach(System.out::print);// forEach()최종연산
//		
//		//Stream은 일회용. Stream에 대해 최종연산을 수행하면 Stream이 닫힌다.
//		intStream = list.stream();
//		intStream.forEach(System.out::print);
		
		String[] strArr = {"a", "b", "c", "d"};
//		Stream<String> strStream = Stream.of(strArr);
		Stream<String> strStream = Arrays.stream(strArr);
		strStream.forEach(System.out::println);
		
		//기본형 스트림
		int[] intArr = {1, 2, 3, 4, 5};
		IntStream intStream = Arrays.stream(intArr);
//		intStream.forEach(System.out::println);
//		System.out.println("count = " + intStream.count());
		System.out.println("sum = " + intStream.sum());
		
		//객체 스트림
//		Integer[] intArr = {1, 2, 3, 4, 5};
//		Stream<Integer> intStream = Arrays.stream(intArr);
//		intStream.forEach(System.out::println);
//		System.out.println("count = " + intStream.count());
		
		
	}
}
