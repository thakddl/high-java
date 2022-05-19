package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Race {
	static List<Horse> horses = new ArrayList<>();
	
	public static void main(String[] args) {
		RankData rd = new RankData();
		List<Thread> horseThs = new ArrayList<>();
		
		for(int i=0; i<10; i++) {
			horses.add(new Horse(i+1+"번 말", rd));
			horseThs.add(new Thread(horses.get(i)));
		}
		PrintRaceThread printTh = new PrintRaceThread(rd);
		printTh.start();

		for(Thread horseTh : horseThs) {
			horseTh.start();
		}
		
	}
	
}
class RankData {	
	private int finishedHorse;

	public int getFinishedHorse() {
		return finishedHorse;
	}

	public void setFinishedHorse(int finishedHorse) {
		this.finishedHorse++;
	}
	
	synchronized public int addFinishedHorse() {
		finishedHorse++;
		return finishedHorse;
	}
	
}
class PrintRaceThread extends Thread {
	private RankData rd;
	
	public PrintRaceThread(RankData rd) {
		this.rd = rd;
	}

	public void print() {		
		while (true) {
			
			System.out.println("==========================================================");
			System.out.println("경기시작");
			System.out.println("==========================================================");
			for(Horse horse : Race.horses) {
				System.out.println(horse.getCurrentLocation());
			}		
			if(rd.getFinishedHorse()==10) {
				Collections.sort(Race.horses);
				System.out.println("=============");
				System.out.println("경기결과");
				System.out.println("=============");
				for(int i=0; i<10; i++) {
					Horse horse = Race.horses.get(i);
					System.out.println(horse.getName() + ": " + horse.getRank() + "등");
				}
				break;
			} 
			
			try {
				Thread.sleep(300);
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			
		}
	}
	@Override
	public void run() {
		print();
	}
}

class Horse implements Comparable<Horse>, Runnable{
	RankData rd;
	String name;
	String currentLocation;
	int rank;
	
	public Horse(String name, RankData rd) {
		this.rd = rd;
		this.name = name;
		this.currentLocation = name + ": 레디";
	}

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getCurrentLocation() {
		return currentLocation;
	}


	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public void run() {
		
		for(int i = 0; i < 50; i++) {
			String str = "";
			for(int j = 0; j < 50; j++ ) {
				if(i==j) {
					str += ">";
				} else {
					str += "-";
				}
			}
			try {
				Thread.sleep((int)(Math.random()*300));
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			setCurrentLocation(name + ": " + str);
		}
		synchronized(this) {
			this.setRank(rd.addFinishedHorse());
		}
	}

	@Override
	public int compareTo(Horse horse) {
		return (this.getRank() >  horse.getRank()) ? 1 : 
			   (this.getRank() == horse.getRank()) ? 0 : -1;
	}
} 
