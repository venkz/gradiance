package ncsu.csc.db.beans;

public class Question {
	
	private int qid;
	private String text;
	private int attemptId;
	private String option1;
	private String option2;
	private String option3;
	private String option4;
	private int seqId;
	
	private int dueDatePassed;
	private int answerChoosen;
	private int isCorrect;
	private String explanation;
	private int correctOption;
	private String fullExplanation;
	
	public int getDueDatePassed() {
		return dueDatePassed;
	}
	public void setDueDatePassed(int dueDatePassed) {
		this.dueDatePassed = dueDatePassed;
	}
	public int getAnswerChoosen() {
		return answerChoosen;
	}
	public void setAnswerChoosen(int answerChoosen) {
		this.answerChoosen = answerChoosen;
	}
	public int getIsCorrect() {
		return isCorrect;
	}
	public void setIsCorrect(int isCorrect) {
		this.isCorrect = isCorrect;
	}
	public String getExplanation() {
		return explanation;
	}
	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}
	public int getCorrectOption() {
		return correctOption;
	}
	public void setCorrectOption(int correctOption) {
		this.correctOption = correctOption;
	}
	public String getFullExplanation() {
		return fullExplanation;
	}
	public void setFullExplanation(String fullExplanation) {
		this.fullExplanation = fullExplanation;
	}
	public int getQid() {
		return qid;
	}
	public void setQid(int qid) {
		this.qid = qid;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getAttemptId() {
		return attemptId;
	}
	public void setAttemptId(int attemptId) {
		this.attemptId = attemptId;
	}
	public String getOption1() {
		return option1;
	}
	public void setOption1(String option1) {
		this.option1 = option1;
	}
	public String getOption2() {
		return option2;
	}
	public void setOption2(String option2) {
		this.option2 = option2;
	}
	public String getOption3() {
		return option3;
	}
	public void setOption3(String option3) {
		this.option3 = option3;
	}
	public String getOption4() {
		return option4;
	}
	public void setOption4(String option4) {
		this.option4 = option4;
	}
	public int getSeqId() {
		return seqId;
	}
	public void setSeqId(int seqId) {
		this.seqId = seqId;
	}
}
