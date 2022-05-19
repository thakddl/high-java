package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Student implements Comparable<Student>{
	// 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
	// Student클래스를 만든다.
	// 생성자는 학번, 이름, 국어, 영어, 수학 점수만 매개변수로 받아서 처리한다.
	//
	// 이 Student객체들은 List에 저장하여 관리한다.
	// List에 저장된 데이터들을 학번의 오름차순으로 정렬하여 출력하는 부분과
	// 총점의 역순으로 정렬하는 부분을 프로그램 하시오.
	// (총점이 같으면 학번의 내림차순으로 정렬되도록 한다.)
	// (학번 정렬기준은 Student클래스 자체에서 제공하도록 하고,
	// 총점 정렬기준은 외부클래스에서 제공하도록 한다.)
	int studentNum;
	String name;
	int korScore;
	int engScore;
	int mathScore;
	int amount;
	int rank;
	public Student(int studentNum, String name, int korScore, int engScore, int mathScore) {
		super();
		this.studentNum = studentNum;
		this.name = name;
		this.korScore = korScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.amount = korScore + engScore + mathScore;
	}
	
	public int getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(int studentNum) {
		this.studentNum = studentNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKorScore() {
		return korScore;
	}

	public void setKorScore(int korScore) {
		this.korScore = korScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}
	
	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public static void main(String[] args) {
		List<Student> list = new ArrayList<Student>();
		list.add(new Student(1, "Rose", 90, 80, 85));
		list.add(new Student(2, "Lily", 63, 100, 74));
		list.add(new Student(3, "Lavender", 88, 79, 86));
		list.add(new Student(4, "Ylang", 80, 95, 80));
		list.add(new Student(5, "tulip", 80, 88, 85));
		
		Collections.shuffle(list);
		for(Student stu : list) {
			System.out.printf("%d. %s\n", stu.getStudentNum(), stu.getName());
		}
		System.out.println("=================================");
		
		Collections.sort(list);
		for(Student stu : list) {
			System.out.printf("%d. %s\n", stu.getStudentNum(), stu.getName());
		}
		System.out.println("=================================");
		
		Collections.sort(list, new SortByScore());
		for(Student stu : list) {
			System.out.printf("%d. %s\n", stu.getStudentNum(), stu.getName());
		}
		
	}

	@Override
	public int compareTo(Student std) {
		return new Integer(this.getStudentNum()).compareTo(std.getStudentNum());
	}
}

class SortByScore implements Comparator<Student> {

	@Override
	public int compare(Student std1, Student std2) {
		
		if(std1.getAmount()>std2.getAmount()) {
			return -1;
		} else if(std1.getAmount()==std2.getAmount()) {
			if(std1.getStudentNum()>std2.getStudentNum()) {
				return -1;
			} else if(std1.getStudentNum()==std2.getStudentNum()) {
				return 0;
			} else {
				return 1;
			}
		} else {
			return 1;
		}
		
	}
	
}