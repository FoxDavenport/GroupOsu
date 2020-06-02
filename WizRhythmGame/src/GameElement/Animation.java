package GameElement;

import java.awt.image.BufferedImage;
import java.util.Date;

//overall class is supposed to make the notes transition smoothly
//slows down the game a bit if the amount of info is too much resulting in less frames

public class Animation {

	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delayTime;
	
	private boolean occuredEffectOnce;
	
	//gets all of our variables we'll be using initialized 
	
	public Animation() {
		occuredEffectOnce = false;
		//sets the first cycle to false
	}
	
	public void setFrames(BufferedImage[] frames) {
		this.frames = frames;
		currentFrame = 0;
		startTime = new Date().getTime();
		occuredEffectOnce = false;
		
		//sets up the basics of the frames and starts incrementing time
	}
	
	public void setDelay(long d) { delayTime = d; } //have a delay for smooth transitions
	public void setFrame(int index) { currentFrame = index; } //figure out current frame
	
	public void update() { 
//updates the frames to keep game running smoothly
		if(delayTime == - 1) return;
		
		long elapsed = new Date().getTime() - startTime;
		//get time elapsed
		if(elapsed > delayTime) {
			currentFrame++;
			startTime = new Date().getTime();
			//if elapsed time larger than delayTime than increment the next frame and reset star time
			//this ensures frames are updating consistently 
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
			occuredEffectOnce = true;
			//if the current frame is equal to the last then repeat cycle
			//sets the first cycle being passes to true
		}
		
	}
	
	public int getFrame() { return currentFrame; } //gets current frame
	public BufferedImage getImage() { return frames[currentFrame]; } //gets current image for the current frame
	public boolean hasOccuredOnce( ) { return occuredEffectOnce; } //shows that the cycle occurred at least one
}
