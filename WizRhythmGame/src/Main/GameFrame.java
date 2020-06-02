package Main;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.Date;

import javax.swing.JFrame;

import GameState.GameStateManager;

@SuppressWarnings("serial")
public class GameFrame extends JFrame implements
             Runnable, KeyListener{
	
	
	// game frame ( FPS = 60 )
	private int fps = 60; 
	private long MilliSecondPerFrame = 1000 / fps; 
	private Thread thread;
	private boolean running = false;
	
	// FPS check
	private Date lastTime = new Date();
	private long diffTime;
	private int frameCount = 0;
	private int nowFps = 0;
	
	//image
	private BufferedImage image;
	private Graphics2D g;
	
	// game state manager
	private GameStateManager gsm;
	
	public GameFrame() {
		
		
		thread = new Thread(this);
		thread.start();
		
		setUndecorated(true); 
		setTitle("Rhythm Game");
		setSize(Game.WIDTH, Game.HEIGHT);
		setResizable(false); 
		setLocationRelativeTo(null); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setShape(new RoundRectangle2D.Double(
				0,0,Game.WIDTH,Game.HEIGHT,100,100)); 
		setVisible(true);
		setLayout(null);
		addKeyListener(this);
		
	
		
	}
	
	public void init()
	{
		image = new BufferedImage(
				Game.WIDTH, Game.HEIGHT, 
				BufferedImage.TYPE_INT_RGB
				);
		g = (Graphics2D) image.getGraphics();
		
		gsm = new GameStateManager();
		
		running = true;

	}
	
	
	public void update() {
		gsm.update();
	}
	

	@Override
	public void keyPressed(KeyEvent k) {
		gsm.keyPressed(k.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent k) {
		gsm.keyReleased(k.getKeyCode());
	}

	@Override
	public void keyTyped(KeyEvent k) {}

	public void frameCount() {
		Date nowTime = new Date();
		diffTime = nowTime.getTime() - lastTime.getTime();
		 
		if(diffTime >= 1000) {
			nowFps = frameCount;
			frameCount = 0;
			lastTime = nowTime;
		}
		frameCount++;
	}

	
	public void draw() {
		gsm.draw(g);
		g.setFont(new Font("����ü", Font.BOLD, 10));
		g.drawString("FPS : "+String.valueOf(nowFps), 25, 25);
		
	}
	public void drawToScreen() {
		
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, Game.WIDTH, Game.HEIGHT,  null);
		g2.dispose();
		
	}

	
	@Override
	public void run() {
		
		init();

		long start;
		long elapsed;
		long wait;
		
		
		// game loop
		while(running) {
			
			start = System.nanoTime(); 
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = MilliSecondPerFrame - elapsed / 1000000;
			
			if(wait < 0 ) wait = 0;
			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			frameCount();
		}
		
	}
}
