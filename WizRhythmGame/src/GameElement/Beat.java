package GameElement;

//this class is supposed to be for the BPM/beat sound effect
//Helps us later during making notes for the GameStates

public class Beat {

	private int time;
	private String noteName;
	//initialize our variables
	
	public Beat(int time, String noteName) {
		this.time = time;
		this.noteName = noteName;
		//sets up basics of the beat class as time and note
	}
	
	public int getTime() {
		return time; 
		//gets time
	}
	public void setTime(int time) {
		this.time = time;
		//sets time
	}
	public String getNoteName() {
		return noteName;
		//returns type of note
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
		//sets type of note
	}
	
}
