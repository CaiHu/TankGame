package com.tanke.finish;

import java.util.Vector;

/*
 * 我的坦克
 */
public class Hero extends Tank {
	// 子弹
	Vector<Shot> ss = new Vector<>();
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
	}

	// 开火
	public void ShotEnemy() {
		switch (this.direct) {
		case 0:
			// 创建一颗子弹
			s = new Shot(x + 37, y - 10, 0);
			// 把子弹加入到向量
			ss.add(s);
			break;
		case 1:
			s = new Shot(x + 90, y + 37, 1);
			ss.add(s);
			break;
		case 2:
			s = new Shot(x + 37, y + 90, 2);
			ss.add(s);
			break;
		case 3:
			s = new Shot(x - 15, y + 37, 3);
			ss.add(s);
			break;
		}
		// 启动子弹
		Thread t2 = new Thread(s);
		t2.start();
	}

	// 坦克向上
	public void moveUp() {
		y -= speed;
	}

	// 坦克向右
	public void moveRight() {
		x += speed;
	}

	// 坦克向下
	public void moveDown() {
		y += speed;
	}

	// 坦克向左
	public void moveLeft() {
		x -= speed;
	}
}
