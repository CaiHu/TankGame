package com.tanke.finish;

import java.util.Vector;

/*
 * ���˵�̹��
 */
public class EnemyTank extends Tank implements Runnable {
	// ����һ�����������ʵ���������е�̹��
	Vector<EnemyTank> ets = new Vector<>();

	int times = 0;
	// ����һ����������ŵ����ӵ�
	Vector<Shot> ss = new Vector<>();

	// �õ�����ϵ�̹��
	public void setEts(Vector<EnemyTank> vv) {
		this.ets = vv;
	}

	// �ж��Ƿ����̹���ص�
	public boolean isTouchOther() {
		boolean b = false;
		switch (this.direct) {
		case 0:
			// ȡ������̹��
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
						Thread t = new Thread(s);
						t.start();
					}
				}
			}
			// ��̹�˲�������ķ���
			this.direct = (int) (Math.random() * 4);
			// �жϵ����Ƿ��Ѿ�����
			if (this.isLive == false) {
				// �������˳��߳�
				break;
			}

		}
	}

}
