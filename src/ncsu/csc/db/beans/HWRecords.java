package ncsu.csc.db.beans;

public class HWRecords {

	private String hwName;
	private int hwId;
	private int attemptId;
	private int attemptNumber;
	private int score;
	
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
	
}
