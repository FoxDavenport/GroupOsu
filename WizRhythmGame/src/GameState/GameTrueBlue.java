package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import GameElement.Beat;
import GameElement.EffectAnimation;
import GameElement.InterfaceBackground;
import GameElement.Note;
import GameElement.ResultBackground;
import GameEnvironment.Music;

//read GameAdrenaline for explanation
public class GameTrueBlue extends GameState
                   implements Runnable {

	private InterfaceBackground bg;
	private Music gameMusic;
	private Beat[] beats = null;
	
	
	private ArrayList<Note> notes = new ArrayList<Note>();
	
	
	private Thread game;
	
	private int score;
	private int combo;
	
	private EffectAnimation effect;
	
	private int startTime;
	
	private boolean displayResult;
	private boolean showingResult;
	private ResultBackground rbg;
	
	private boolean exit;
	
	private Music beatSound;
	
	public GameTrueBlue(GameStateManager gsm) {
		this.gsm = gsm;
		
		init();
	}
	
	@Override
	public void init() {
		score = 0; combo = 0;
		
		displayResult = false;
		showingResult = false;
		rbg = new ResultBackground();
		
		bg = new InterfaceBackground("/image/beach.jpg");
		
		setBeat();
		
		gameMusic = new Music("True Blue.mp3", false);
		gameMusic.start();
		
		effect = new EffectAnimation();
		
		game = new Thread(this);
		game.start();
		
		exit = false;
		
		
		beatSound = new Music("beat.mp3", false);
		
	}
	
	public void setBeat() {
		startTime = 1333; 
		int gap = (int)(60.0 / 360 * 1000);
		beats = new Beat[] {
				new Beat(startTime + gap * 4, "Space"),
				new Beat(startTime + gap * 7, "L"),
				new Beat(startTime + gap * 8, "D"),
				new Beat(startTime + gap * 11, "F"),
				new Beat(startTime + gap * 12, "F"),
				new Beat(startTime + gap * 14, "F"), 
				new Beat(startTime + gap * 18, "S"), 
				new Beat(startTime + gap * 19, "D"),
				new Beat(startTime + gap * 20, "F"),             
				new Beat(startTime + gap * 21, "D"),
				new Beat(startTime + gap * 22, "S"), 
				new Beat(startTime + gap * 25, "F"),
				new Beat(startTime + gap * 26, "K"),
				new Beat(startTime + gap * 29, "L"),
				new Beat(startTime + gap * 30, "L"),
				new Beat(startTime + gap * 32, "L"),  
				new Beat(startTime + gap * 36, "K"),
				new Beat(startTime + gap * 37, "J"),
				new Beat(startTime + gap * 38, "D"),
				new Beat(startTime + gap * 39, "S"),
				new Beat(startTime + gap * 40, "Space"), 
				new Beat(startTime + gap * 42, "D"),
				new Beat(startTime + gap * 44, "S"),
				new Beat(startTime + gap * 45, "D"),
				new Beat(startTime + gap * 47, "F"),
				new Beat(startTime + gap * 48, "F"),
				new Beat(startTime + gap * 51, "J"),
				new Beat(startTime + gap * 53, "L"), 
				new Beat(startTime + gap * 54, "S"), 
				new Beat(startTime + gap * 55, "D"),
				new Beat(startTime + gap * 56, "F"),              
				new Beat(startTime + gap * 57, "J"),
				new Beat(startTime + gap * 58, "K"),  
				new Beat(startTime + gap * 59, "L"),
				new Beat(startTime + gap * 60, "S"),  
				new Beat(startTime + gap * 61, "D"),
				new Beat(startTime + gap * 62, "F"), 
				new Beat(startTime + gap * 66, "S"), 
				new Beat(startTime + gap * 67, "D"),
				new Beat(startTime + gap * 70, "D"),
				new Beat(startTime + gap * 71, "D"),
				new Beat(startTime + gap * 72, "D"),
				new Beat(startTime + gap * 77, "S"),
				new Beat(startTime + gap * 78, "J"), 
				new Beat(startTime + gap * 79, "F"),             
				new Beat(startTime + gap * 80, "D"),
				new Beat(startTime + gap * 81, "S"), 
				new Beat(startTime + gap * 84, "S"),
				new Beat(startTime + gap * 85, "D"),
				new Beat(startTime + gap * 88, "S"),
				new Beat(startTime + gap * 89, "D"),
				new Beat(startTime + gap * 91, "S"),  
				new Beat(startTime + gap * 95, "S"),
				new Beat(startTime + gap * 96, "K"),
				new Beat(startTime + gap * 97, "F"),
				new Beat(startTime + gap * 98, "D"),
				new Beat(startTime + gap * 99, "S"), 
				new Beat(startTime + gap * 102, "F"),
				new Beat(startTime + gap * 103, "D"),
				new Beat(startTime + gap * 106, "S"),
				new Beat(startTime + gap * 107, "D"),
				new Beat(startTime + gap * 109, "F"), 
				new Beat(startTime + gap * 110, "D"),
				new Beat(startTime + gap * 111, "J"), 
				new Beat(startTime + gap * 112, "S"), 
				new Beat(startTime + gap * 113, "S"),
				new Beat(startTime + gap * 114, "D"),              
				new Beat(startTime + gap * 115, "J"),
				new Beat(startTime + gap * 116, "K"),  
				new Beat(startTime + gap * 117, "L"),
				new Beat(startTime + gap * 118, "D"), 
				new Beat(startTime + gap * 119, "K"),
				new Beat(startTime + gap * 120, "L"), 
				new Beat(startTime + gap * 123, "Space"), 
				new Beat(startTime + gap * 124, "Space"),
				new Beat(startTime + gap * 125, "Space"),
				new Beat(startTime + gap * 126, "Space"), 
				new Beat(startTime + gap * 128, "F"),
				new Beat(startTime + gap * 129, "D"),
				new Beat(startTime + gap * 130, "F"), 
				new Beat(startTime + gap * 131, "K"), 
				new Beat(startTime + gap * 132, "K"), 
				new Beat(startTime + gap * 134, "D"),
				new Beat(startTime + gap * 135, "S"),              
				new Beat(startTime + gap * 136, "D"),
				new Beat(startTime + gap * 137, "F"),  
				new Beat(startTime + gap * 138, "D"), 
				new Beat(startTime + gap * 140, "K"), 
				new Beat(startTime + gap * 141, "J"),
				new Beat(startTime + gap * 142, "K"), 
				new Beat(startTime + gap * 143, "Space"),
				new Beat(startTime + gap * 144, "Space"),
				new Beat(startTime + gap * 145, "L"),
				new Beat(startTime + gap * 146, "D"),
				new Beat(startTime + gap * 147, "F"),
				new Beat(startTime + gap * 148, "F"),
				new Beat(startTime + gap * 150, "F"), 
				new Beat(startTime + gap * 151, "S"), 
				new Beat(startTime + gap * 152, "D"),
				new Beat(startTime + gap * 153, "F"),             
				new Beat(startTime + gap * 154, "D"),
				new Beat(startTime + gap * 155, "S"), 
				new Beat(startTime + gap * 156, "F"),
				new Beat(startTime + gap * 157, "K"),
				new Beat(startTime + gap * 158, "L"),
				new Beat(startTime + gap * 159, "L"),
				new Beat(startTime + gap * 161, "L"),
				new Beat(startTime + gap * 162, "K"),
				new Beat(startTime + gap * 163, "J"),
				new Beat(startTime + gap * 164, "D"),
				new Beat(startTime + gap * 165, "S"),
				new Beat(startTime + gap * 166, "Space"), 
				new Beat(startTime + gap * 168, "D"),
				new Beat(startTime + gap * 170, "S"),
				new Beat(startTime + gap * 171, "D"),
				new Beat(startTime + gap * 173, "F"),
				new Beat(startTime + gap * 174, "F"),
				new Beat(startTime + gap * 177, "J"),
				new Beat(startTime + gap * 179, "L"), 
				new Beat(startTime + gap * 180, "S"), 
				new Beat(startTime + gap * 181, "D"),
				new Beat(startTime + gap * 182, "F"),              
				new Beat(startTime + gap * 183, "J"),
				new Beat(startTime + gap * 184, "K"),  
				new Beat(startTime + gap * 185, "L"),
				new Beat(startTime + gap * 186, "S"),  
				new Beat(startTime + gap * 187, "D"),
				new Beat(startTime + gap * 188, "F"), 
				new Beat(startTime + gap * 192, "S"), 
				new Beat(startTime + gap * 193, "D"),
				new Beat(startTime + gap * 196, "D"),
				new Beat(startTime + gap * 197, "D"),
				new Beat(startTime + gap * 198, "D"),
				new Beat(startTime + gap * 203, "S"),
				new Beat(startTime + gap * 204, "J"), 
				new Beat(startTime + gap * 205, "F"),             
				new Beat(startTime + gap * 206, "D"),
				new Beat(startTime + gap * 207, "S"), 
				new Beat(startTime + gap * 210, "S"),
				new Beat(startTime + gap * 211, "D"),
				new Beat(startTime + gap * 214, "S"),
				new Beat(startTime + gap * 215, "D"),
				new Beat(startTime + gap * 217, "S"),  
				new Beat(startTime + gap * 218, "S"),
				new Beat(startTime + gap * 219, "K"),
				new Beat(startTime + gap * 220, "F"),
				new Beat(startTime + gap * 221, "D"),
				new Beat(startTime + gap * 222, "S"), 
				new Beat(startTime + gap * 224, "F"),
				new Beat(startTime + gap * 225, "D"),
				new Beat(startTime + gap * 226, "S"),
				new Beat(startTime + gap * 227, "D"),
				new Beat(startTime + gap * 229, "F"), 
				new Beat(startTime + gap * 230, "D"),
				new Beat(startTime + gap * 231, "J"), 
				new Beat(startTime + gap * 232, "S"), 
				new Beat(startTime + gap * 233, "S"),
				new Beat(startTime + gap * 234, "D"),              
				new Beat(startTime + gap * 235, "J"),
				new Beat(startTime + gap * 236, "K"),  
				new Beat(startTime + gap * 237, "L"),
				new Beat(startTime + gap * 238, "D"), 
				new Beat(startTime + gap * 239, "K"),
				new Beat(startTime + gap * 240, "L"), 
				new Beat(startTime + gap * 241, "Space"), 
				new Beat(startTime + gap * 242, "Space"),
				new Beat(startTime + gap * 242, "Space"),
				new Beat(startTime + gap * 243, "Space"), 
				new Beat(startTime + gap * 244, "F"),
				new Beat(startTime + gap * 245, "D"),
				new Beat(startTime + gap * 246, "F"), 
				new Beat(startTime + gap * 247, "K"), 
				new Beat(startTime + gap * 248, "K"), 
				new Beat(startTime + gap * 250, "D"),
				new Beat(startTime + gap * 251, "S"),              
				new Beat(startTime + gap * 252, "D"),
				new Beat(startTime + gap * 253, "F"),  
				new Beat(startTime + gap * 254, "D"), 
				new Beat(startTime + gap * 255, "K"), 
				new Beat(startTime + gap * 256, "J"),
				new Beat(startTime + gap * 257, "K"), 
				new Beat(startTime + gap * 258, "Space"),
				new Beat(startTime + gap * 259, "J"),
				new Beat(startTime + gap * 260, "K"),  
				new Beat(startTime + gap * 261, "L"),
				new Beat(startTime + gap * 262, "S"),  
				new Beat(startTime + gap * 263, "D"),
				new Beat(startTime + gap * 264, "F"), 
				new Beat(startTime + gap * 268, "S"), 
				new Beat(startTime + gap * 269, "D"),
				new Beat(startTime + gap * 270, "D"),
				new Beat(startTime + gap * 271, "D"),
				new Beat(startTime + gap * 272, "D"),
				new Beat(startTime + gap * 273, "S"),
				new Beat(startTime + gap * 274, "J"), 
				new Beat(startTime + gap * 275, "F"),             
				new Beat(startTime + gap * 276, "D"),
				new Beat(startTime + gap * 278, "S"), 
				new Beat(startTime + gap * 279, "S"),
				new Beat(startTime + gap * 280, "D"),
				new Beat(startTime + gap * 281, "S"),
				new Beat(startTime + gap * 282, "D"),
				new Beat(startTime + gap * 283, "S"),  
				new Beat(startTime + gap * 284, "S"),
				new Beat(startTime + gap * 285, "K"),
				new Beat(startTime + gap * 286, "F"),
				new Beat(startTime + gap * 287, "D"),
				new Beat(startTime + gap * 288, "S"), 
				new Beat(startTime + gap * 289, "F"),
				new Beat(startTime + gap * 290, "D"),
				new Beat(startTime + gap * 291, "S"),
				new Beat(startTime + gap * 292, "D"),
				new Beat(startTime + gap * 293, "F"), 
				new Beat(startTime + gap * 294, "D"),
				new Beat(startTime + gap * 295, "J"), 
				new Beat(startTime + gap * 296, "S"), 
				new Beat(startTime + gap * 297, "S"),
				new Beat(startTime + gap * 298, "D"),              
				new Beat(startTime + gap * 299, "J"),
				new Beat(startTime + gap * 300, "K"),  
				new Beat(startTime + gap * 301, "L"),
				new Beat(startTime + gap * 302, "D"), 
				new Beat(startTime + gap * 303, "K"),
				new Beat(startTime + gap * 304, "L"), 
				new Beat(startTime + gap * 305, "Space"), 
				new Beat(startTime + gap * 306, "Space"),
				new Beat(startTime + gap * 307, "Space"),
				new Beat(startTime + gap * 308, "Space"), 
				new Beat(startTime + gap * 309, "F"),
				new Beat(startTime + gap * 310, "D"),
				new Beat(startTime + gap * 311, "F"), 
				new Beat(startTime + gap * 312, "K"), 
				new Beat(startTime + gap * 313, "K"), 
				new Beat(startTime + gap * 314, "D"),
				new Beat(startTime + gap * 315, "S"),              
				new Beat(startTime + gap * 316, "D"),
				new Beat(startTime + gap * 317, "F"),  
				new Beat(startTime + gap * 318, "D"), 
				new Beat(startTime + gap * 319, "K"), 
				new Beat(startTime + gap * 320, "J"),
				new Beat(startTime + gap * 321, "K"), 
				new Beat(startTime + gap * 322, "Space"),
				new Beat(startTime + gap * 323, "F"), 
				new Beat(startTime + gap * 325, "S"), 
				new Beat(startTime + gap * 326, "D"),
				new Beat(startTime + gap * 329, "D"),
				new Beat(startTime + gap * 330, "D"),
				new Beat(startTime + gap * 331, "D"),
				new Beat(startTime + gap * 336, "S"),
				new Beat(startTime + gap * 337, "J"), 
				new Beat(startTime + gap * 338, "F"),             
				new Beat(startTime + gap * 339, "D"),
				new Beat(startTime + gap * 340, "S"), 
				new Beat(startTime + gap * 343, "S"),
				new Beat(startTime + gap * 344, "D"),
				new Beat(startTime + gap * 348, "S"),
				new Beat(startTime + gap * 349, "D"),
				new Beat(startTime + gap * 350, "S"),  
				new Beat(startTime + gap * 344, "S"),
				new Beat(startTime + gap * 346, "K"),
				new Beat(startTime + gap * 347, "F"),
				new Beat(startTime + gap * 348, "D"),
				new Beat(startTime + gap * 349, "S"), 
				new Beat(startTime + gap * 350, "F"),
				new Beat(startTime + gap * 352, "D"),
				new Beat(startTime + gap * 353, "S"),
				new Beat(startTime + gap * 357, "D"),
				new Beat(startTime + gap * 358, "F"), 
				new Beat(startTime + gap * 359, "D"),
				new Beat(startTime + gap * 360, "J"), 
				new Beat(startTime + gap * 361, "S"), 
				new Beat(startTime + gap * 362, "S"),
				new Beat(startTime + gap * 363, "D"),              
				new Beat(startTime + gap * 364, "J"),
				new Beat(startTime + gap * 365, "K"),  
				new Beat(startTime + gap * 366, "L"),
				new Beat(startTime + gap * 367, "D"), 
				new Beat(startTime + gap * 368, "K"),
				new Beat(startTime + gap * 369, "L"), 
				new Beat(startTime + gap * 370, "Space"), 
				new Beat(startTime + gap * 371, "L"),
				new Beat(startTime + gap * 373, "L"),  
				new Beat(startTime + gap * 374, "K"),
				new Beat(startTime + gap * 375, "J"),
				new Beat(startTime + gap * 376, "D"),
				new Beat(startTime + gap * 377, "S"),
				new Beat(startTime + gap * 378, "Space"), 
				new Beat(startTime + gap * 380, "D"),
				new Beat(startTime + gap * 382, "S"),
				new Beat(startTime + gap * 383, "D"),
				new Beat(startTime + gap * 384, "F"),
				new Beat(startTime + gap * 385, "F"),
				new Beat(startTime + gap * 386, "J"),
				new Beat(startTime + gap * 388, "L"), 
				new Beat(startTime + gap * 389, "S"), 
				new Beat(startTime + gap * 390, "D"),
				new Beat(startTime + gap * 391, "F"),              
				new Beat(startTime + gap * 392, "J"),
				new Beat(startTime + gap * 393, "K"),  
				new Beat(startTime + gap * 394, "L"),
				new Beat(startTime + gap * 395, "S"),  
				new Beat(startTime + gap * 396, "D"),
				new Beat(startTime + gap * 397, "F"),
				new Beat(startTime + gap * 401, "S"), 
				new Beat(startTime + gap * 402, "D"),
				new Beat(startTime + gap * 406, "D"),
				new Beat(startTime + gap * 407, "D"),
				new Beat(startTime + gap * 408, "D"),
				new Beat(startTime + gap * 413, "S"),
				new Beat(startTime + gap * 414, "J"), 
				new Beat(startTime + gap * 415, "F"),             
				new Beat(startTime + gap * 416, "D"),
				new Beat(startTime + gap * 417, "S"), 
				new Beat(startTime + gap * 418, "S"),
				new Beat(startTime + gap * 419, "D"),
				new Beat(startTime + gap * 422, "S"),
				new Beat(startTime + gap * 423, "D"),
				new Beat(startTime + gap * 425, "S"),  
				new Beat(startTime + gap * 429, "S"),
				new Beat(startTime + gap * 430, "K"),
				new Beat(startTime + gap * 431, "F"),
				new Beat(startTime + gap * 432, "D"),
				new Beat(startTime + gap * 433, "S"), 
				new Beat(startTime + gap * 434, "F"),
				new Beat(startTime + gap * 435, "D"),
				new Beat(startTime + gap * 436, "S"),
				new Beat(startTime + gap * 437, "D"),
				new Beat(startTime + gap * 439, "F"), 
				new Beat(startTime + gap * 440, "D"),
				new Beat(startTime + gap * 441, "J"), 
				new Beat(startTime + gap * 442, "S"), 
				new Beat(startTime + gap * 443, "S"),
				new Beat(startTime + gap * 444, "D"),              
				new Beat(startTime + gap * 445, "J"),
				new Beat(startTime + gap * 446, "K"), 
				new Beat(startTime + gap * 447, "D"),
				new Beat(startTime + gap * 448, "D"),
				new Beat(startTime + gap * 453, "S"),
				new Beat(startTime + gap * 454, "J"), 
				new Beat(startTime + gap * 455, "F"),             
				new Beat(startTime + gap * 456, "D"),
				new Beat(startTime + gap * 457, "S"), 
				new Beat(startTime + gap * 460, "S"),
				new Beat(startTime + gap * 461, "D"),
				new Beat(startTime + gap * 465, "S"),
				new Beat(startTime + gap * 466, "D"),
				new Beat(startTime + gap * 467, "S"),  
				new Beat(startTime + gap * 468, "S"),
				new Beat(startTime + gap * 469, "K"),
				new Beat(startTime + gap * 470, "F"),
				new Beat(startTime + gap * 471, "D"),
				new Beat(startTime + gap * 472, "S"), 
				new Beat(startTime + gap * 473, "F"),
				new Beat(startTime + gap * 474, "D"),
				new Beat(startTime + gap * 475, "S"),
				new Beat(startTime + gap * 476, "D"),
				new Beat(startTime + gap * 477, "F"), 
				new Beat(startTime + gap * 478, "D"),
				new Beat(startTime + gap * 479, "J"), 
				new Beat(startTime + gap * 480, "S"), 
				new Beat(startTime + gap * 481, "S"),
				new Beat(startTime + gap * 482, "D"),              
				new Beat(startTime + gap * 483, "J"),
				new Beat(startTime + gap * 484, "K"),  
				new Beat(startTime + gap * 485, "L"),
				new Beat(startTime + gap * 486, "D"), 
				new Beat(startTime + gap * 487, "K"),
				new Beat(startTime + gap * 488, "L"), 
				new Beat(startTime + gap * 490, "Space"), 
				new Beat(startTime + gap * 491, "D"), 
				new Beat(startTime + gap * 492, "K"),
				new Beat(startTime + gap * 493, "L"), 
				new Beat(startTime + gap * 494, "Space"),
				new Beat(startTime + gap * 495, "Space"),
				new Beat(startTime + gap * 496, "Space"),
				new Beat(startTime + gap * 497, "Space"),
		};
	}
	
	@Override
	public void update() {
		
		
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			note.update();
			if(note.isProceeded() == false ) {
				notes.remove(i);
				i--;
			}
		}
		
		
		effect.update();
		
		if(displayResult) {
			if(!showingResult) {
				showingResult = true;
				rbg.playBgm();
				rbg.takeMusicTitle("dj Taka- True Blue");
				rbg.takeScore(score);
				rbg.writeScore();
				rbg.calRank();
			}
			rbg.update();
		}
		
	}

	@Override
	public void draw(Graphics2D g) {
		
		bg.draw(g);
		
		g.setColor(Color.WHITE);
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setFont(new Font("Elephant", Font.BOLD, 26));
		g.drawString("dj Taka- True Blue", 10, 694);
		
		g.setColor(Color.LIGHT_GRAY);
		g.setFont(new Font("Elephant", Font.BOLD, 26));
		g.drawString(String.valueOf(score), 636, 694);
		g.drawString("Hard",1140, 694);
		
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			note.draw(g);
		}
		
		effect.draw(g);
		
		if(displayResult) {
			rbg.draw(g);
		}
		
	}

	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_ESCAPE) {
			
			exit = true;
			
		
			gameMusic.close();
			rbg.closeBgm();
			
			for (int i = 0; i < notes.size(); i++) {
				Note note = notes.get(i);
				note.setProceeded(false);
			}
			game.interrupt();
			
			gsm.setStateInit(GameStateManager.MENUSTATE);
			gsm.setState(GameStateManager.MENUSTATE); 
			
		}
		
		if(!displayResult) {
			
			beatSound = new Music("beat.mp3", false);
			beatSound.start();
			
			if (k == KeyEvent.VK_S) {
				judge("S");
				bg.pressS();
			} 
			else if (k == KeyEvent.VK_D) {
				judge("D");
				bg.pressD();
			} 
			else if (k == KeyEvent.VK_F) {
				judge("F");
				bg.pressF();
			} 
			else if (k == KeyEvent.VK_SPACE) {
				judge("Space");
				bg.pressSpace();
			} 
			else if (k == KeyEvent.VK_J) {
				judge("J");
				bg.pressJ();
			} 
			else if (k == KeyEvent.VK_K) {
				judge("K");
				bg.pressK();
			} 
			else if (k == KeyEvent.VK_L) {
				judge("L");
				bg.pressL();
			}
		}
		
		else if(displayResult) {
			if(k == KeyEvent.VK_ENTER && rbg.getNextGame()) {
				gsm.setStateInit(GameStateManager.MENUSTATE);
				gsm.setState(GameStateManager.MENUSTATE); 
			}
			
		}
		
	}

	@Override
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_S) bg.releaseS();
		else if (k == KeyEvent.VK_D) bg.releaseD();
		else if (k == KeyEvent.VK_F) bg.releaseF();
		else if (k == KeyEvent.VK_SPACE) bg.releaseSpace();
		else if (k == KeyEvent.VK_J) bg.releaseJ();
		else if (k == KeyEvent.VK_K) bg.releaseK();
		else if (k == KeyEvent.VK_L) bg.releaseL();
	}

	public void judge(String input)
	{
		for (int i = 0; i < notes.size(); i++) {
			Note note = notes.get(i);
			if(input.equals(note.getNoteType()))
			{
				if (note.judge().equals("Miss")) {
					effect.setCombo("Miss", 0); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo = 0;
				}
				if (note.judge().equals("Perfect")){
					effect.setCombo("Perfect", combo); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo++;
					score += 100 + combo * 5;
				}
				if (note.judge().equals("Great")){
					effect.setCombo("Great", combo); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo++;
					score += 80 + combo * 5;
				}
				if (note.judge().equals("Good")){
					effect.setCombo("Good", combo); 
					effect.setPosition(note.getNoteType());
					effect.setEffect(); 
					combo++;
					score += 60 + combo * 5;
				}
				break;
			}
		}
	}
	
	@Override
	public void run() {
		
		int i =0;

			while (i < beats.length && !game.isInterrupted()) {

				if (beats[i].getTime() <= gameMusic.getTime()) {
					Note note = new Note(beats[i].getNoteName());
					notes.add(note);
					i++;
				}
			}
			
		if (!exit) {
			try {
				Thread.sleep(5000);
			} catch (Exception e) {e.printStackTrace();}
			rbg.calRank();
			displayResult = true;
		}

	}
	

}
