package com.tanke.finish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * 开始界面面板
 */
public class myStartPanel extends JPanel {
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.green);
		Font myFont = new Font("楷体", Font.BOLD, 50);
		g.setFont(myFont);
		g.drawString("第一关", 320, 300);
	}
}
