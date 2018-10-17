package me.dilan.game.view;

import java.awt.Dimension;

import javax.swing.JFrame;

import me.dilan.game.Game;

public class Window extends JFrame{

	private static final long serialVersionUID = 5057154673954608117L;

	public Window(Game game) {
		
		
		
		setTitle("Game");
		add(game);
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(700, 500));
		setResizable(false);
		setVisible(true);
	}
	
	
	
	
}
