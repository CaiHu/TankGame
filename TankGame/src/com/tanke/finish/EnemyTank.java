package com.tanke.finish;

import java.util.Vector;

/*
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable {
	// 定义一个向量，访问到面板上所有的坦克
	Vector<EnemyTank> ets = new Vector<>();

	int times = 0;
	// 定义一个向量，存放敌人子弹
	Vector<Shot> ss = new Vector<>();

	// 得到面板上的坦克
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// 判断是否出现坦克重叠
	public boolean isTouchOther() {
		boolean b = false;
		switch (this.direct) {
		case 0:
			// 取出所有坦克
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et != this) {
					if (et.direct == 0 || et.direct == 2) {
						if (this.x >= et.x && this.x <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x >= et.x && this.x <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et != this) {
					if (et.direct == 0 || et.direct == 2) {
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y + 80 >= et.y
								&& this.y + 80 <= et.y + 80) {
							return true;
						}
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y + 80 >= et.y
								&& this.y + 80 <= et.y + 80) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et != this) {
					if (et.direct == 0 || et.direct == 2) {
						if (this.x >= et.x && this.x <= et.x + 80 && this.y + 80 >= et.y && this.y + 80 <= et.y + 80) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 80 && this.y + 80 >= et.y && this.y + 80 <= et.y + 80) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y + 80 >= et.y
								&& this.y + 80 <= et.y + 80) {
							return true;
						}
						if (this.x + 80 >= et.x && this.x + 80 <= et.x + 80 && this.y + 80 >= et.y
								&& this.y + 80 <= et.y + 80) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et != this) {
					if (et.direct == 0 || et.direct == 2) {
						if (this.x >= et.x && this.x <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 80 && this.y >= et.y && this.y <= et.y + 80) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {
						if (this.x >= et.x && this.x <= et.x + 80 && this.y + 80 >= et.y && this.y + 80 <= et.y + 80) {
							return true;
						}
						if (this.x >= et.x && this.x <= et.x + 80 && this.y + 80 >= et.y && this.y + 80 <= et.y + 80) {
							return true;
						}
					}
				}
			}
			break;

		}
		return b;
	}

	public EnemyTank(int x, int y) {
		super(x, y);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			switch (this.direct) {
			case 0:
				for (int i = 0; i < 30; i++) {
					if (y > 0 && !this.isTouchOther()) {
						y -= speed;
					}

					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 1:
				for (int i = 0; i < 30; i++) {
					if (x < 720 && !this.isTouchOther()) {
						x += speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 2:
				for (int i = 0; i < 30; i++) {
					if (y < 510 && !this.isTouchOther()) {
						y += speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			case 3:
				for (int i = 0; i < 30; i++) {
					if (x > 0 && !this.isTouchOther()) {
						x -= speed;
					}
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			}
			this.times++;
			if (times % 2 == 0) {
				if (isLive) {
					if (ss.size() < 5) {
						Shot s = null;
						switch (direct) {
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
						Thread t = new Thread(s);
						t.start();
					}
				}
			}
			// 让坦克产生随机的方向
			this.direct = (int) (Math.random() * 4);
			// 判断敌人是否已经死亡
			if (this.isLive == false) {
				// 死亡后，退出线程
				break;
			}

		}
	}

}
