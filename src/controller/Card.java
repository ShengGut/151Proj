package controller;

public class Card {

	private int cardID;
	private String frontSide; // Inputted by user
	private String backSide;  // Inputted by user
	private int rating;       // Selected by user, range only from 0-5
	private int repetition = 0;
	private int interval = 1;
	private double difficultyFactor;
	private long nextReviewDate;
	
	public Card (int cardID, String frontSide, String backSide, int rating, int repetition, int interval, long nextReviewDate) {
        this.cardID = cardID;
        this.frontSide = frontSide;
        this.backSide = backSide;
        this.rating = rating;
        this.repetition = repetition;
        this.interval = interval;
        this.difficultyFactor = 2.5;
        this.nextReviewDate = nextReviewDate;
    }
	
	public String getFrontSide() {
		return frontSide;
	}
	public String getBackSide() {
		return backSide;
	}
	public int getID() {
		return cardID;
	}
	public int getRating() {
		return rating;
	}
	public int getRepetition() {
		return repetition;
	}
	public int setRepetition(int newRepetition) {
		return repetition = newRepetition;
	}
	public int getInterval() {
		return interval;
	}
	public int setInterval(int newInterval) {
		return interval = newInterval;
	}
	public double getDifficultyFactor() {
		return difficultyFactor;
	}
	public long getNextReviewDate() {
		return nextReviewDate;
	}
}
