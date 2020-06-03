package GameElement;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Main.Game;

//logic for the note and falling down

public class Note {
	
	// note image
	private BufferedImage noteBasicImage;
	
	//increments the note to fall down
	private int x;
	private int y = 580 - 
			(1000 / Game.SLEEP_TIME * Game.NOTE_SPEED);
			                 

	// note type, proceeded
	private String noteType;
	private boolean proceeded = true;
	

	public Note(String noteType) {
		
		this.noteType = noteType;
	
		init();
	}

	public void init() {
		
		try {
			noteBasicImage = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/noteBasic.png"
							));
		} catch (Exception e) {	e.printStackTrace(); };	
		
		if (noteType.equals("S")) x = 228;
		else if (noteType.equals("D")) x = 332;
		else if (noteType.equals("F")) x = 436;
		else if (noteType.equals("Space")) x = 540;
		else if (noteType.equals("J")) x = 744;
		else if (noteType.equals("K")) x = 848;
		else if (noteType.equals("L")) x = 952;
		//initialize the notes and sets them to correct x position depending on which one they are
		
	}

	//updates their y position so note keeps falling
	public void update() {
		y += Game.NOTE_SPEED;
		if (y > 620) 
			close();
	}
	
	public void draw(Graphics2D g) {
		if (!noteType.equals("Space")) {
			g.drawImage(noteBasicImage, x, y, null);
		} else {
			g.drawImage(noteBasicImage, x, y, null);
			g.drawImage(noteBasicImage, x + 100, y, null);
			
			//draws the notes falling down
		}
	}


	public String judge() {
		 //basic system to judge if its good, great, or a miss
		if (y >= 625) {
			System.out.println("Miss2");
			close();
			return "Miss";
		}
		else if (y >= 575 && y <600 ) {
			System.out.println("Great");
			close();
			return "Great";
		}
		else if (y >= 600 & y<625) {
			System.out.println("Good");
			close();
			return "Good";
		} 
		else if (y >= 550 && y<575) {
			System.out.println("Perfect");
			close();
			return "Perfect";
		} 
		return "Miss";
	}
	
	public void close() { proceeded = false;}
	public int getY() { return y; }
	public String getNoteType() { return noteType; }
	public void setProceeded(boolean proceeded) { this.proceeded = proceeded; }
	public boolean isProceeded() { return proceeded; }
	//basic stuff for getting rid of notes, getting type of notes, and etc...
	
}
