package GameElement;

import java.awt.Color;

import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import GameEnvironment.Music;
import java.util.Scanner;

//class tells you how well you did on the level
public class ResultBackground {
	
	//scanner object
	Scanner input = new Scanner(System.in); 

	//initialize name of song, score, and your letter grade
	private String musicTitle;
	private int score;
	private String rank;
	private String name;
	
	// initialize more
	private boolean step10n;
	private boolean step20n;
	private boolean step30n;
	private boolean step40n;
	private boolean nextgame;
	private int angle;

	// has the result screen music play later
	private Music bgm;
	
	//initially doesn't play
	boolean isPlayed = false;
	
	public ResultBackground() {
		
		//initialize more stuff
		step10n = false;
		step20n = false;
		step30n = false;
		step40n = false;
		nextgame = false;
		angle = 155;
		
		// BGM no play yet
		bgm = new Music("resultBgm.mp3", false);
		
	}
	
	// figures out how well you did
	public void calRank() {
		if(score <= 8000) rank = "F";
		else if(score>8000 && score <= 10000) rank = "D-";
		else if(score>10000 && score <= 13000) rank = "D";
		else if(score>13000 && score <= 16000) rank = "D+";
		else if(score>16000 && score <= 19000) rank = "C-";
		else if(score>19000 && score <= 22000) rank = "C";
		else if(score>22000 && score <= 25000) rank = "C+";
		else if(score>25000 && score <= 28000) rank = "B-";
		else if(score>28000 && score <= 31000) rank = "B";
		else if(score>25000 && score <= 34000) rank = "B+";
		else if(score>34000 && score <= 40000) rank = "A"; 
		else if(score>40000 && score <= 45000) rank = "A+";
		else if(score>45000 && score <= 50000) rank = "S-";
		else if(score>50000 && score <= 60000) rank = "S"; 
		else if(score>60000) rank = "S+"; 
	}
	public void writeScore() {
			
		// writes out score
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		String dateStr = sdf.format(cal.getTime());
		
		// score 
		String scoreStr = String.valueOf(score);
		
		// tells you the song name
		String titleStr = musicTitle;
		
		// FileWriter
		FileWriter fw;
		BufferedWriter writer;
				
		try {
			
			fw = new FileWriter(
					new File(
							"ScoreLog.txt"),
					         true
					         );
			writer = new BufferedWriter(fw);
			
			//adds your scores to the ScoreLog.txt
				
			
			writer.write(dateStr + "\t\t");
			writer.write(titleStr + "\t\t");
			writer.write(scoreStr);
			writer.newLine();
			writer.flush();
			fw.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void update() {
		
		// 'next game Enter' info control
		angle += 3;
		
		// allows you to go to next game
		if(bgm.getTime() >= 6500) {
			nextgame = true;
		}
		
	} 
	
	public void draw(Graphics2D g) {
		
		// results are drawn by this method
		g.setColor(new Color(0, 0, 0, 150));
		g.fillRect(
				400, 
				240,
				480, 
				180
				);
		
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//starts with title
		if(bgm.getTime() >= 3000 || step10n == true) {
			step10n = true;
			g.setColor(new Color(200, 255, 200));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD, 
					25
					));
			g.drawString(musicTitle, 440, 300);
		}
		
		//now does score
		if(bgm.getTime() >= 4000 || step20n == true) {
			step20n = true;
			g.setColor(new Color(200, 255, 200));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					25
					));
			g.drawString(
					"Score : "+String.valueOf(score), 
					440, 
					370
					);
		}
		
		//now tells rank
		if(bgm.getTime() >= 6500 || step30n == true) {
			step30n = true;
			g.setColor(Color.green);
			g.setFont(new Font(
					"Elephant",
					Font.BOLD, 
					100
					));
			g.drawString(rank, 760, 390);
		}
		
		// now let you go to next level
		if(bgm.getTime() >= 7500 || step40n == true) {
			step40n = true;
			double alpha = 255 * Math.sin(angle * Math.PI / 180);	
			if (angle >= 175) angle = 0;
			g.setColor(new Color(255, 255, 100, (int)alpha));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					27
					));
			g.drawString(
					"next game ENTER", 
					500, 
					450
					);
		}
		//searched up math formulas for this online
	
	}
	
	public void playBgm() {
		isPlayed = true;
		bgm.start();
		//plays the bgm
	}
	
	public void closeBgm() {
		if(!isPlayed) return;
		bgm.close();
		//stops the bgm
	}
	
	public boolean getNextGame() { return nextgame; }
	public void takeMusicTitle(String title){ musicTitle = title; }
	public void takeScore(int score) { this.score = score; }
	//basic stuff to get scores, title, and next game
}
