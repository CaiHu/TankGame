package com.tanke.finish;

/*
 * 子弹类
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
			// 子弹向上
			case 0:
				y -= speed;
				break;
			// 子弹向右
			case 1:
				x += speed;
				break;
			// 子弹向下
			case 2:
				y += speed;
				break;
			// 子弹向左
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