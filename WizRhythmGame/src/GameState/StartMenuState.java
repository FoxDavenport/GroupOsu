package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import GameEnvironment.Music;

//start menu with the OSU logo and the press space bar

public class StartMenuState extends GameState{
	
	
	private BufferedImage imageBG;
	private int angle; 
	private Music intro;
	
	public StartMenuState(GameStateManager gsm) {
		
		this.gsm = gsm;
		intro = new Music("osu.mp3", false);
		intro.start();
		
		init();
	}
	

	@Override
	public void init() {
		try {
			imageBG = ImageIO.read(
					getClass().getResourceAsStream(
							"/image/bg1.jpg")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void update() {
		angle += 3;
	}
	
	@Override
	public void draw(Graphics2D g) {
		
		// background
		g.drawImage(imageBG, 0, 0, null);
		
		// title
		g.setColor(Color.ORANGE);
		g.setFont(new Font(
				"a�����ȸB", 
				Font.PLAIN, 
				130));
		g.drawString("Rhythm Game", 200, 180);
		
		// flash info
		double alpha = 255 * Math.sin(angle * Math.PI / 180);	
		if (angle >= 175) angle = 0;
		g.setFont(new Font(
				"a���������B", 
				Font.PLAIN, 
				50));
		g.setColor(new Color(255, 255, 255, (int)alpha));
		g.drawString(
				"Press space bar to continue",
				255, 580);
		
		// version info
		g.setColor(Color.white);
		g.setFont(new Font(
				"a���������B", 
				Font.PLAIN, 
				35));
		g.drawString("1.0.1", 610,670);
		
	
	}

	
	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_SPACE) 
			gsm.setState(GameStateManager.MENUSTATE);
			intro.close();
	}
	
	@Override
	public void keyReleased(int k) {
		
	}

}
