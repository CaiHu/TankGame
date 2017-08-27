package com.tanke.finish;

import java.util.Vector;

/*
 * �ҵ�̹��
 */
public class Hero extends Tank {
	// �ӵ�
	Vector<Shot> ss = new Vector<>();
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
	}

	// ����
	public void ShotEnemy() {
		switch (this.direct) {
		case 0:
			// ����һ���ӵ�
			s = new Shot(x + 37, y - 10, 0);
			// ���ӵ����뵽����
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
		// �����ӵ�
		Thread t2 = new Thread(s);
		t2.start();
	}

	// ̹������
	public void moveUp() {
		y -= speed;
	}

	// ̹������
	public void moveRight() {
		x += speed;
	}

	// ̹������
	public void moveDown() {
		y += speed;
	}

	// ̹������
	public void moveLeft() {
		x -= speed;
	}
}
