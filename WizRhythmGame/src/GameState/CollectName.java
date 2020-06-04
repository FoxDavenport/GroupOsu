package GameState;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import GameEnvironment.Music;
import Main.GameFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Panel;


public class CollectName implements ActionListener{
	
	private Music transition = new Music("wow.mp3", false);
	public static String userName;

    protected void initUI() throws MalformedURLException {
    	
    	ImageIcon i = new ImageIcon("src/image/beach.jpg");
    	Image background = i.getImage();
    	
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        
        JTextField name = new JTextField(5);
        name.setBackground(new Color(102, 51, 204));
        name.setFont(new Font("Tahoma", Font.PLAIN, 60));
        Dimension s1 = name.getPreferredSize();
        name.setBounds(600, 300, s1.width, s1.height);
        
        JLabel label = new JLabel("Name: ");
        label.setFont(new Font("Tahoma", Font.PLAIN, 60));
        label.setBackground(Color.DARK_GRAY);
        Dimension s = label.getPreferredSize();
        label.setBounds(400, 300, s.width, s.height);
        
        JButton b = new JButton("Play");
        b.setBackground(new Color(0, 153, 204));
        b.setFont(new Font("Tahoma", Font.PLAIN, 30));
        Dimension s2 = label.getPreferredSize();
        b.setBounds(400, 420, s2.width-60, s2.height-20);
        b.setFocusPainted(false);
        b.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e)
        	{
        		if(name.getText().equals(""))
        		{
        			System.out.println("Please enter a name");
        		}
        		else
        		{
        			transition.start();
        			addNameToTxt(name.getText());
        			userName = name.getText();
        			frame.setState(Frame.ICONIFIED);
        			new GameFrame();
        		}
        	}
        });
        
        JLabel l = new JLabel(i);
        l.setLayout(null);
        l.add(name);
        l.add(label);
        l.add(b);
        
        frame.getContentPane().add(l);
        frame.setSize(1000, 1000);
        
        //frame.getContentPane().setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        
        
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    public static void addNameToTxt(String name)
    {
    	 try { 
    		  
             // Open given file in append mode. 
             BufferedWriter out = new BufferedWriter( 
                    new FileWriter("src/DatabaseInfo/UserList.txt", true)); 
             out.write(name + "\n"); 
             out.close(); 
         } 
         catch (IOException e) { 
             System.out.println("exception occoured" + e); 
         } 
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new CollectName().initUI();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}