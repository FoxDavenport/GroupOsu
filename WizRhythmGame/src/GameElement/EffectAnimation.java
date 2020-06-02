package GameElement;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

//class is supposed to pop up your combos during play
//so the color changes for how long combo goes and shows number of them
//also has a hit effect to show you hit the note

public class EffectAnimation {


	private boolean hitEffect;
	private boolean comboEffect;
	private boolean comboColor;
	//initialize our boolean for showing them or not

	private int hitAlpha; 
	private int comboAlpha;
	private int comboColorAlpha;
	//initialize our ints for the different hits, combos, and combo colors


 	private int comboCount; 
	private String comboMotion;
	//more initialization
	
	
	// hit effect index
	private int index;
	
	
	public EffectAnimation() {
		
		hitEffect = false;	
		comboEffect = false;
		comboColor = false;
	
		hitAlpha = 0;
		comboAlpha = 0;
		comboColorAlpha = 0;
		
		index = 0;
		
		//initially set everything to 0 and false as beginning of level
	}
	
	public void setPosition(String noteType) {
		if(noteType.equals("S")) index = 0;
		else if(noteType.equals("D")) index = 1;
		else if(noteType.equals("f")) index = 2;
		else if(noteType.equals("Space")) index = 3;
		else if(noteType.equals("J")) index = 5;
		else if(noteType.equals("K")) index = 6;
		else if(noteType.equals("L")) index = 7;
		//sets each key to a specific part of the index
		//also helps with effect appearing in right area
	}
	
	public void update() {
		
		if(hitEffect) {
			if (hitAlpha >= 250) hitEffect = false;
		}
		if(comboEffect) {
			if (comboAlpha >= 100) comboEffect = false;
		}
		if(comboColor) {
			if (comboColorAlpha >= 250) comboColor = false;
		}
		//sets bounds of where you have to time it for the combo to initiate
		//if you're too far away from the timing then you don't get combo
		
		
		if (hitEffect) {
			hitAlpha += 10;
		}
		if (comboEffect) {
			comboAlpha += 3;
		}
		if (comboColor) {
			comboColorAlpha += 15;
		}
		if (comboAlpha >= 60) {
			comboColor = true;
		}
		
		//when you time it so you get the combo and extra points
		
	}
	
	public void draw(Graphics2D g) {
		
		if (hitEffect) {
			g.setColor(new Color(
					hitAlpha, 
					hitAlpha, 
					hitAlpha));
			if(index == 3) {
				g.fillRect(
						228 + index * 104, 
						580,
						104,
						40
						);
				g.fillRect(
						228 + (index + 1) * 104, 
						580,
						100,
						40
						);
			}
			if(index != 3) {
				g.fillRect(
						228 + index * 104, 
						580,
						100,
						40
						);
			}
			
		}
		
		//shows your combos and various stuff
		//I searched this up on the internet for convenience as I didn't want to do the math for it
		
		g.setRenderingHint(
				RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		//gets everything to show up
		

		if (comboEffect) {
			g.setColor(new Color(
					200, 
					255,
					200,
					255  - comboColorAlpha
					));
			g.setFont(new Font("Elephant", Font.BOLD, 30));
			g.drawString(
					comboMotion, 
					520, 
					400 + (int)comboAlpha
					);	
			g.drawString(
					String.valueOf(comboCount), 
					700,
					400 + (int)comboAlpha
					); 
			
			//more combo stuff to show up
		}
		
	}
	
	public void setCombo(String comboMotion, int combo) {
		this.comboMotion = comboMotion;
		comboCount = combo;
		
		//declares what your combo is
	}
	

	public void setEffect() {
		
		hitAlpha = 0;
		comboAlpha = 0;
		comboColorAlpha = 0;
		
		hitEffect = true; 
		comboEffect = true;
		
		//declares effects true, so they show up
	}
	
}


