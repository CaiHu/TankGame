package com.tanke.finish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

/*
 * ��ʼ�������
 */
public class myStartPanel extends JPanel {
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.green);
		Font myFont = new Font("����", Font.BOLD, 50);
		g.setFont(myFont);
		g.drawString("��һ��", 320, 300);
	}
}
