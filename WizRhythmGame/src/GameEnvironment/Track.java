package GameEnvironment;

//general class for storing info about a song 
//aka singer, title, and difficulty
public class Track {

	private String singerName; 
	private String titleName;
	private int difficulty;
	
	public Track(String singerName, String titleName, int difficulty) {
		this.singerName = singerName;
		this.titleName = titleName;
		this.difficulty = difficulty;
	}
	public String getSingerName() {
		return singerName;
	}
	public void setSingerName(String singerName) {
		this.singerName = singerName;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

}
