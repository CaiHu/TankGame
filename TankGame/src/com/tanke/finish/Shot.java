package com.tanke.finish;

/*
 * �ӵ���
 */
public class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 5;
	boolean isLive = true;

	public Shot(int x, int y, int direct) {
		super();
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			switch (direct) {
			// �ӵ�����
			case 0:
				y -= speed;
				break;
			// �ӵ�����
			case 1:
				x += speed;
				break;
			// �ӵ�����
			case 2:
				y += speed;
				break;
			// �ӵ�����
			case 3:
				x -= speed;
				break;
			}
			if (x < 0 || x > 800 || y < 0 || y > 600) {
				this.isLive = false;
				break;
			}
		}
	}
}