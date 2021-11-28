package controller;

public class Date {
	private static long presentTime;
	
	
	public static long getPresentTime() {
		return  presentTime = System.currentTimeMillis();
	}
	
	//write method that compares presentTime to nextReviewDate from cards in the Json database

}
