package com.tanke.finish;

/*
 * ը����
 */
public class Bomb {
	// ����ը��������
	int x;
	int y;
	// ը��������
	int life = 12;
	Boolean isLive = true;

	public Bomb(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	// ��������ֵ
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}

}
