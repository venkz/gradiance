package ncsu.csc.db.beans;

public class Enrollments {
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	public int getIsta() {
		return ista;
	}
	public void setIsta(int ista) {
		this.ista = ista;
	}
	public String getIstaStr() {
		return istaStr;
	}
	public void setIstaStr(String istaStr) {
		this.istaStr = istaStr;
	}
	private String token;
	private String coursename;
	private int ista;
	private String istaStr;

	
	
}
