package ncsu.csc.db.beans;

import java.util.Date;

public class HWRecords {
	
	private String hwName;
	private int hwId;
	private int attemptId;
	private int attemptNumber;
	private int score;
	private Date startdate;
	private Date enddate;
	private int numattempts;
	private String topics;
	private int mindiffrange;
	private int maxdiffrange;
	private int scorescheme;
	private int numquestions;
	private int correctpoints;
	private int incorrectpoints;
	private int randomseed;
	
	
	
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public int getNumattempts() {
		return numattempts;
	}
	public void setNumattempts(int numattempts) {
		this.numattempts = numattempts;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	public int getScorescheme() {
		return scorescheme;
	}
	public void setScorescheme(int scorescheme) {
		this.scorescheme = scorescheme;
	}
	public int getCorrectpoints() {
		return correctpoints;
	}
	public void setCorrectpoints(int correctpoints) {
		this.correctpoints = correctpoints;
	}
	public int getIncorrectpoints() {
		return incorrectpoints;
	}
	public void setIncorrectpoints(int incorrectpoints) {
		this.incorrectpoints = incorrectpoints;
	}

	public int getMaxAttempts() {
		return maxAttempts;
	}
	public void setMaxAttempts(int maxAttempts) {
		this.maxAttempts = maxAttempts;
	}
	public int getAttempsMade() {
		return attempsMade;
	}
	public void setAttempsMade(int attempsMade) {
		this.attempsMade = attempsMade;
	}
	private int maxAttempts;
	private int attempsMade;
	
	public String getHwName() {
		return hwName;
	}
	public void setHwName(String hwName) {
		this.hwName = hwName;
	}
	public int getHwId() {
		return hwId;
	}
	public void setHwId(int hwId) {
		this.hwId = hwId;
	}
	public int getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(int attemptId) {
		this.attemptId = attemptId;
	}
	public int getAttemptNumber() {
		return attemptNumber;
	}
	public void setAttemptNumber(int attemptNumber) {
		this.attemptNumber = attemptNumber;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public int getNumquestions() {
		return numquestions;
	}
	public void setNumquestions(int numquestions) {
		this.numquestions = numquestions;
	}
	public int getMindiffrange() {
		return mindiffrange;
	}
	public void setMindiffrange(int mindiffrange) {
		this.mindiffrange = mindiffrange;
	}
	public int getMaxdiffrange() {
		return maxdiffrange;
	}
	public void setMaxdiffrange(int maxdiffrange) {
		this.maxdiffrange = maxdiffrange;
	}
	public int getRandomseed() {
		return randomseed;
	}
	public void setRandomseed(int randomseed) {
		this.randomseed = randomseed;
	}
	
}
