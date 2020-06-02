package GameEnvironment;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;


import javazoom.jl.player.Player;

//plays music and stops music
public class Music extends Thread{

	// music player taking ideas from Cory and internet due to how I structured my other stuff
	private Player player;
	private boolean isloop; 
	
	// initialize
	private File file;
	private FileInputStream fis;
	private BufferedInputStream bis;
	
	//reads files for later
	public Music(String name, boolean isloop) {
		
		try {
			this.isloop = isloop;
			file = new File(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//gets the time
	public int getTime()
	{
		if(player == null)
			return 0;
		return player.getPosition(); 
	}
	
	//closes the song
	public void close()
	{
		try {
			isloop = false;
			player.close();
			this.interrupt(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//plays the song
	public void run()
	{
		try {
			do {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis); 
				player = new Player(bis); 
				player.play();
			} while (isloop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
