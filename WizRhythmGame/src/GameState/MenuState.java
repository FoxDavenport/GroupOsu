package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import GameEnvironment.Music;
import GameEnvironment.Track;


public class MenuState extends GameState{

	// image
	private BufferedImage imageMenuBackground; 
	private BufferedImage imageMenuBackground2; 
	private BufferedImage imageMenuBackground3; 
	private BufferedImage imageMenuBackground4; 
	private BufferedImage imageHoverOn;
	private BufferedImage imageYellowStar;
	private BufferedImage imageGrayStar;
	private BufferedImage imageHoverOff;
	private BufferedImage imagetop;
	private BufferedImage imagebot;
	
	// track
	private int currentChoice;
	private ArrayList<Track> tracks;
	
	// track value
	public static final int adrenaline_MUSIC = 0;
	public static final int Mariya_MUSIC  = 1;
	public static final int Lupin_MUSIC = 2;
	public static final int BrandNewWorld_MUSIC=3;
	
	// selected music
	private Music selectedMusic;
	
	// if made level yet or not
	private boolean developing = false;
	private int alpha = 0;
	private int delayAlpha = 0;
	
	
	public MenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		currentChoice = 0;
		//makes the tracks array using the tracks class
		tracks = new ArrayList<Track>();

		tracks.add(new Track("Anime Girls", "adrenaline", 2));
		tracks.add(new Track("Shiawase no Monosashi", "Mariya", 3));
		tracks.add(new Track("Macross", "82.99 F.M", 4));
		tracks.add(new Track("D-51", "Brand New World", 3));
		
		try { //get all of our juicy images

			imageMenuBackground = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/song1Bg2.jpg")
					);
			imageMenuBackground2 = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/ThePlanIsSimple.jpg")
					);
			imageMenuBackground3 = ImageIO.read(
					getClass().getResourceAsStream(
							 "/image/lupin.jpg")
					);
			imageMenuBackground4 = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/EniesLobby.png")
					);
		
			imageHoverOn = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/ssbtn_hoverON.png")
					);
			imageHoverOff = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/ssbtn_hoverOFF.png")
					);
			imageGrayStar = ImageIO.read( 
					getClass().getResourceAsStream(
							"/image/grayStar.png")
					);
			imageYellowStar = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/yellowStar.png")
					);
			imagetop = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/ssi_top.png")
					);
			imagebot = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/ssi_bot.png")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		init();
	}
	
	@Override
	public void init() {
		//plays a part of the song if hovering over that song
		playMusic(tracks.get(currentChoice). 
				getTitleName()+"_hightlight.mp3"
				);
		
	}
	
	@Override //tells you that the song is still being developed if try to play song with no level
	public void update() {
		if (developing) {
			if (alpha >= 145) {
				developing = false;
				alpha = 0;
				delayAlpha = 0;
			}
		}

		if(developing) {
			delayAlpha += 5;
			if (delayAlpha >= 150)
				alpha += 5;
		}
			
	}
	
	

	@Override
	public void draw(Graphics2D g) {
		
		// background
		
		for (int i = 0; i < tracks.size(); i++) {
			if(i == currentChoice) {
				if(i == 0) {
					g.drawImage(imageMenuBackground, 0, 0, null);
				}
				if(i == 1) {
					g.drawImage(imageMenuBackground2, 0, 0, null);
				}
				if(i == 2) {
					g.drawImage(imageMenuBackground3, 0, 0, null);
				}
				if(i == 3) {
					g.drawImage(imageMenuBackground4, 0, 0, null);
				}
			}
		}
			
		//add some UI stuff
		g.drawImage(imagebot, 0, 120,null);
		g.drawImage(imagetop, 0, -10 ,null);
		
		g.setColor(Color.white);
		g.setFont(new Font(
				"HYHeadLine", 
				Font.PLAIN, 
				25));
		g.drawString(
				"LOCAL SCORES:",
				20, 124 
				);
		
		//scoreboard
		g.drawRect(20,150,300,350);
		
		for( int i = 0; i < 5 ; i++) {
			g.drawLine(20, 200 + i*50, 320, 200 + i*50);
			g.drawString("integrate database pls", 24, 190 + i*50);
		}
		
		// Indication to select music
		for (int i = 0; i < tracks.size(); i++) {
			//double dif = 200 * Math.sin(i * 124 * Math.PI / 180); <--????
			int yInterval = i * 100;
			
			// selected music
			
			if(i == currentChoice) {
				
				g.setColor(Color.white);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						36));
				g.drawString(
						tracks.get(i).getTitleName() + " - " + tracks.get(i).getSingerName(),
						20,50 
						);
				
				g.drawImage(
						imageHoverOn, 
						600, 100 + yInterval,
						null
						);
				
				g.setColor(Color.white);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						25));
				g.drawString(
						tracks.get(i).getSingerName(),
						740, 160 + yInterval 
						);
				g.drawString(
						tracks.get(i).getTitleName(),
						740, 130 + yInterval 
						);

			
				//shows difficulty
				int j;
				for (j = 0; j < tracks.get(i).getDifficulty(); j++) {
					g.drawImage(
							imageYellowStar, 
							740 + j*20, 174 +  yInterval , 
							null);
				}
				for (int k = 0; k < 5; k++) {
				g.drawImage(
						imageGrayStar, 
						740 + k*20, 174 +  yInterval ,
						null);
				}
			}
			
			// not selected music
			else {
				g.drawImage(
						imageHoverOff, 
						600, 100 + yInterval,
						null
						);
				
				g.setColor(Color.gray);
				g.setFont(new Font(
						"HYHeadLine", 
						Font.BOLD, 
						25));
				g.drawString(
						tracks.get(i).getSingerName(),
						740, 160 + yInterval 
						);
				g.drawString(
						tracks.get(i).getTitleName(),
						740, 130 + yInterval 
						);
				
				
			
			} 
		}
		
		
		//tells user developing stuff
		if(developing) {
			g.setColor(new Color(
					0, 0, 0, 
					150 - alpha
					));
			g.fillRect(0, 330, 1280, 70);
			g.setColor(new Color(
					200,
					255,
					200, 
					255 - alpha
					));
			g.setFont(new Font(
					"Elephant", 
					Font.BOLD,
					25
					));
			g.drawString(
					"Developing games.. ",
					520, 
					370
					);
		}
		
		
		
		
		
	}

	public void playMusic(String music) { 
		if(selectedMusic != null)
			selectedMusic.close();
		selectedMusic = new Music(music, true);
		selectedMusic.start();
	}
	
	public void select() {
		if (currentChoice == 0) {
			gsm.setState(GameStateManager.adrenaline_STATE);
		}
		if (currentChoice == 1) {
			gsm.setState(GameStateManager.Mariya_STATE);
		}
		if (currentChoice == 2) {
			gsm.setState(GameStateManager.Lupin_STATE);
		}
		if (currentChoice == 3) {
			gsm.setState(GameStateManager.BrandNewWorld_STATE);
		}
	}
	
	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_Y) {
			System.exit(0);
		}
		
		if(k == KeyEvent.VK_ENTER) {
			if (selectedMusic != null)
				selectedMusic.close();
			select();
		}
		
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1)
				currentChoice = tracks.size()- 1 ;
			
			if (selectedMusic != null)
				selectedMusic.close();
			
			playMusic(tracks.get(currentChoice). 
					getTitleName()+"_hightlight.mp3"
					);
		}
		
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == tracks.size())  
				currentChoice = 0;
			
			if (selectedMusic != null)
				selectedMusic.close();
			
			playMusic(tracks.get(currentChoice).
					getTitleName()+"_hightlight.mp3"
					);
		}
	}
	
	@Override
	public void keyReleased(int k) {}
	
}

