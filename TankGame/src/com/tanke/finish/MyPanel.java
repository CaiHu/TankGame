package com.tanke.finish;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.JPanel;

/*
 * �ҵ����
 */
class MyPanel extends JPanel implements KeyListener, Runnable {

	Hero hero = null;

	// ������˵�̹����
	Vector<EnemyTank> ets = new Vector<>();
	Vector<Node> nodes = new Vector<>();
	int enSize = 7;

	// ��������ͼƬ
	Image img1 = null;
	Image img2 = null;
	Image img3 = null;
	Image img4 = null;

	// ����ը������
	Vector<Bomb> bombs = new Vector<>();

	// ���캯��
	public MyPanel(String flag) {

		// �ָ���¼
		Recorder.getRecording();

		hero = new Hero(370, 470);
		if (flag.equals("newgame")) {
			// ��ʼ������̹��
			for (int i = 0; i < enSize; i++) {
				// �������˵�̹�˶���
				EnemyTank et = new EnemyTank((i + 1) * 180, 0);
				et.setColor(0);
				et.setDirect(2);
				// ����
				ets.add(et);
				// ������ϵ�̹�˽�������̹��
				et.setEts(ets);

				// �������˵�̹��
				Thread t = new Thread(et);
				t.start();
				// ���������һ���ӵ�
				Shot s = new Shot(et.x + 40, et.y + 80, 2);
				// �ӵ����������
				et.ss.add(s);
				Thread t2 = new Thread(s);
				t2.start();
			}
		} else {
			nodes = new Recorder().getNode();
			// ��ʼ������̹��
			for (int i = 0; i < nodes.size(); i++) {
				Node node = nodes.get(i);
				// �������˵�̹�˶���
				EnemyTank et = new EnemyTank(node.x, node.y);
				et.setColor(0);
				et.setDirect(node.direct);
				// ����
				ets.add(et);
				// ������ϵ�̹�˽�������̹��
				et.setEts(ets);

				// �������˵�̹��
				Thread t = new Thread(et);
				t.start();
				// ���������һ���ӵ�
				Shot s = new Shot(et.x + 40, et.y + 80, 2);
				// �ӵ����������
				et.ss.add(s);
				Thread t2 = new Thread(s);
				t2.start();
			}
		}
		AePlayWave apw = new AePlayWave("e:\\bgm.wav");
		apw.start();
		// ��ʼ��ͼƬ
		img1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/1.png"));
		img2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/2.png"));
		img3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/3.png"));
		img4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/4.png"));
	}

	// ������ʾ��Ϣ
	public void showInfo(Graphics g) {
		this.drawTank(150, 700, g, 0, 1);
		Font myFont = new Font("����", Font.BOLD, 30);
		g.setFont(myFont);
		g.drawString(Recorder.getMyLife() + "", 250, 750);
		this.drawTank(550, 700, g, 0, 0);
		g.drawString(Recorder.getEnNum() + "", 650, 750);

		// ����
		g.drawString("�ɼ���", 950, 200);
		this.drawTank(900, 300, g, 0, 0);
		g.drawString("X", 1000, 350);
		g.drawString(Recorder.getDead() + "", 1050, 350);
	}

	// ��д
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 800, 600);
		// ��ʾ��Ϣ
		this.showInfo(g);
		// �����Լ���̹��
		if (hero.isLive) {
			this.drawTank(hero.getX(), hero.getY(), g, hero.direct, 1);
		}
		// �����ҷ��ӵ�
		for (int i = 0; i < hero.ss.size(); i++) {
			// ȡ���ӵ�
			Shot myShot = hero.ss.get(i);
			if (myShot != null && myShot.isLive == true) {
				g.draw3DRect(myShot.x, myShot.y, 5, 5, false);
			}
			// ��������ɾ���������ӵ�
			if (myShot.isLive == false) {
				hero.ss.remove(myShot);
			}
		}
		// �������˵�̹��
		for (int i = 0; i < ets.size(); i++) {
			EnemyTank et = ets.get(i);
			if (et.isLive) {
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				// �ٻ������˵��ӵ�
				for (int j = 0; j < et.ss.size(); j++) {
					// ȡ���ӵ�
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, enemyShot.y, 5, 5, false);
					} else {
						// �������̹���������ӵ�Ҳ����
						et.ss.remove(enemyShot);
					}

				}
			}
		}
		// ����ը����ը
		for (int i = 0; i < bombs.size(); i++) {
			Bomb b = bombs.get(i);
			if (b.life > 8) {
				g.drawImage(img1, b.x, b.y, 80, 60, this);
			} else if (b.life > 5) {
				g.drawImage(img2, b.x, b.y, 80, 60, this);
			} else if (b.life > 2) {
				g.drawImage(img3, b.x, b.y, 80, 60, this);
			} else {
				g.drawImage(img4, b.x, b.y, 80, 60, this);
			}
			// ����ֵ���ϼ���
			b.lifeDown();
			// ����ֵΪ�㣬�Ƴ�������
			if (b.life == 0) {
				bombs.remove(b);
			}
		}
	}

	// �ж��Ƿ�����
	public void hitMe() {
		for (int i = 0; i < ets.size(); i++) {
			// ȡ��ÿһ��̹��
			EnemyTank et = ets.get(i);
			for (int j = 0; j < et.ss.size(); j++) {
				// ȡ���ӵ�
				Shot s = et.ss.get(j);
				if (hero.isLive) {
					this.hitTank(s, hero);
				}
			}
		}
	}

	// �ж��Ƿ��Ѿ����е���̹��
	public void hitEnemy() {
		for (int i = 0; i < hero.ss.size(); i++) {
			// ȡ���ӵ�
			Shot myShot = hero.ss.get(i);
			if (myShot.isLive == true) {
				// ȡ��ÿ���з�̹�˽�����ײ���
				for (int j = 0; j < ets.size(); j++) {
					// ȡ��̹��
					EnemyTank et = ets.get(j);
					if (et.isLive) {
						if (this.hitTank(myShot, et)) {
							Recorder.reduceEnnum();
							Recorder.deadEnnum();
						}
					}
				}
			}
			// ��������ɾ���������ӵ�
			if (myShot.isLive == false) {
				hero.ss.remove(myShot);
			}
		}
	}

	// �ӵ��Ƿ����̹��
	public boolean hitTank(Shot s, Tank et) {
		boolean b2 = false;
		// �жϸ�̹�˵ķ���
		switch (et.direct) {
		// �������ϻ�����
		case 0:
		case 2:
			if (s.x > et.x && s.x < et.x + 80 && s.y > et.y && s.y < et.y + 60) {
				// ����
				// �ӵ�����
				s.isLive = false;
				// ̹������
				et.isLive = false;
				b2 = true;
				// ����һ����ը
				Bomb b = new Bomb(et.x, et.y);
				// ����������
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if (s.x > et.x && s.x < et.x + 60 && s.y > et.y && s.y < et.y + 80) {
				// ����
				// �ӵ�����
				s.isLive = false;
				// ̹������
				et.isLive = false;
				b2 = true;
				// ����һ����ը
				Bomb b = new Bomb(et.x, et.y);
				// ����������
				bombs.add(b);
			}
			break;
		}
		return b2;
	}

	// ����̹�˵ĺ���
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		// �ж���ʲô���͵�̹��
		switch (type) {
		case 1:
			g.setColor(Color.RED);
			break;
		default:
			g.setColor(Color.blue);
			break;
		}
		// �жϷ���
		switch (direct) {
		case 0:
			// ��ߵľ���
			g.fillRect(x, y, 20, 80);
			// �ұߵľ���
			g.fillRect(x + 60, y, 20, 80);
			// �м�ľ���
			g.fillRect(x + 20, y + 18, 40, 45);
			// �м����Ͳ
			g.fillRect(x + 37, y - 10, 5, 45);
			break;
		case 1:
			// ��ߵľ���
			g.fillRect(x, y, 80, 20);
			// �ұߵľ���
			g.fillRect(x, y + 60, 80, 20);
			// �м�ľ���
			g.fillRect(x + 18, y + 20, 45, 40);
			// �м����Ͳ
			g.fillRect(x + 45, y + 37, 45, 5);
			break;
		case 2:
			// ��ߵľ���
			g.fillRect(x, y, 20, 80);
			// �ұߵľ���
			g.fillRect(x + 60, y, 20, 80);
			// �м�ľ���
			g.fillRect(x + 20, y + 18, 40, 45);
			// �м����Ͳ
			g.fillRect(x + 37, y + 45, 5, 45);
			break;
		case 3:
			// ��ߵľ���
			g.fillRect(x, y, 80, 20);
			// �ұߵľ���
			g.fillRect(x, y + 60, 80, 20);
			// �м�ľ���
			g.fillRect(x + 18, y + 20, 45, 40);
			// �м����Ͳ
			g.fillRect(x - 10, y + 37, 45, 5);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// ������
	@Override
	public void keyPressed(KeyEvent e) {
		// �����ҵ�̹�˵ķ���
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// ����
			this.hero.setDirect(0);
			this.hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// ����
			this.hero.setDirect(1);
			this.hero.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// ����
			this.hero.setDirect(2);
			this.hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// ����
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		// �ж���ҿ���
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// ����
			if (this.hero.ss.size() < 7) {
				this.hero.ShotEnemy();
			}

		}
		// �������»���Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void run() {
		// ÿ��100�����ػ�
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// �ж��Ƿ���е���̹��
			this.hitEnemy();
			// �ж��Ƿ������
			this.hitMe();
			// �ػ�
			this.repaint();
		}

	}
}
