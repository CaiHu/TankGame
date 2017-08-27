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
 * 我的面板
 */
class MyPanel extends JPanel implements KeyListener, Runnable {

	Hero hero = null;

	// 定义敌人的坦克组
	Vector<EnemyTank> ets = new Vector<>();
	Vector<Node> nodes = new Vector<>();
	int enSize = 7;

	// 定义四张图片
	Image img1 = null;
	Image img2 = null;
	Image img3 = null;
	Image img4 = null;

	// 定义炸弹集合
	Vector<Bomb> bombs = new Vector<>();

	// 构造函数
	public MyPanel(String flag) {

		// 恢复记录
		Recorder.getRecording();

		hero = new Hero(370, 470);
		if (flag.equals("newgame")) {
			// 初始化敌人坦克
			for (int i = 0; i < enSize; i++) {
				// 创建敌人的坦克对象
				EnemyTank et = new EnemyTank((i + 1) * 180, 0);
				et.setColor(0);
				et.setDirect(2);
				// 加入
				ets.add(et);
				// 把面板上的坦克交给敌人坦克
				et.setEts(ets);

				// 启动敌人的坦克
				Thread t = new Thread(et);
				t.start();
				// 给敌人添加一颗子弹
				Shot s = new Shot(et.x + 40, et.y + 80, 2);
				// 子弹加入给敌人
				et.ss.add(s);
				Thread t2 = new Thread(s);
				t2.start();
			}
		} else {
			nodes = new Recorder().getNode();
			// 初始化敌人坦克
			for (int i = 0; i < nodes.size(); i++) {
				Node node = nodes.get(i);
				// 创建敌人的坦克对象
				EnemyTank et = new EnemyTank(node.x, node.y);
				et.setColor(0);
				et.setDirect(node.direct);
				// 加入
				ets.add(et);
				// 把面板上的坦克交给敌人坦克
				et.setEts(ets);

				// 启动敌人的坦克
				Thread t = new Thread(et);
				t.start();
				// 给敌人添加一颗子弹
				Shot s = new Shot(et.x + 40, et.y + 80, 2);
				// 子弹加入给敌人
				et.ss.add(s);
				Thread t2 = new Thread(s);
				t2.start();
			}
		}
		AePlayWave apw = new AePlayWave("e:\\bgm.wav");
		apw.start();
		// 初始化图片
		img1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/1.png"));
		img2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/2.png"));
		img3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/3.png"));
		img4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/img/4.png"));
	}

	// 画出提示信息
	public void showInfo(Graphics g) {
		this.drawTank(150, 700, g, 0, 1);
		Font myFont = new Font("楷体", Font.BOLD, 30);
		g.setFont(myFont);
		g.drawString(Recorder.getMyLife() + "", 250, 750);
		this.drawTank(550, 700, g, 0, 0);
		g.drawString(Recorder.getEnNum() + "", 650, 750);

		// 分数
		g.drawString("成绩：", 950, 200);
		this.drawTank(900, 300, g, 0, 0);
		g.drawString("X", 1000, 350);
		g.drawString(Recorder.getDead() + "", 1050, 350);
	}

	// 重写
	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, 800, 600);
		// 提示信息
		this.showInfo(g);
		// 画出自己的坦克
		if (hero.isLive) {
			this.drawTank(hero.getX(), hero.getY(), g, hero.direct, 1);
		}
		// 画出我方子弹
		for (int i = 0; i < hero.ss.size(); i++) {
			// 取出子弹
			Shot myShot = hero.ss.get(i);
			if (myShot != null && myShot.isLive == true) {
				g.draw3DRect(myShot.x, myShot.y, 5, 5, false);
			}
			// 从向量中删除死亡的子弹
			if (myShot.isLive == false) {
				hero.ss.remove(myShot);
			}
		}
		// 画出敌人的坦克
		for (int i = 0; i < ets.size(); i++) {
			EnemyTank et = ets.get(i);
			if (et.isLive) {
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), 0);
				// 再画出敌人的子弹
				for (int j = 0; j < et.ss.size(); j++) {
					// 取出子弹
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive) {
						g.draw3DRect(enemyShot.x, enemyShot.y, 5, 5, false);
					} else {
						// 如果敌人坦克死亡，子弹也死亡
						et.ss.remove(enemyShot);
					}

				}
			}
		}
		// 画出炸弹爆炸
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
			// 生命值不断减少
			b.lifeDown();
			// 生命值为零，移除出向量
			if (b.life == 0) {
				bombs.remove(b);
			}
		}
	}

	// 判断是否集中我
	public void hitMe() {
		for (int i = 0; i < ets.size(); i++) {
			// 取出每一个坦克
			EnemyTank et = ets.get(i);
			for (int j = 0; j < et.ss.size(); j++) {
				// 取出子弹
				Shot s = et.ss.get(j);
				if (hero.isLive) {
					this.hitTank(s, hero);
				}
			}
		}
	}

	// 判断是否已经击中敌人坦克
	public void hitEnemy() {
		for (int i = 0; i < hero.ss.size(); i++) {
			// 取出子弹
			Shot myShot = hero.ss.get(i);
			if (myShot.isLive == true) {
				// 取出每个敌方坦克进行碰撞检测
				for (int j = 0; j < ets.size(); j++) {
					// 取出坦克
					EnemyTank et = ets.get(j);
					if (et.isLive) {
						if (this.hitTank(myShot, et)) {
							Recorder.reduceEnnum();
							Recorder.deadEnnum();
						}
					}
				}
			}
			// 从向量中删除死亡的子弹
			if (myShot.isLive == false) {
				hero.ss.remove(myShot);
			}
		}
	}

	// 子弹是否击中坦克
	public boolean hitTank(Shot s, Tank et) {
		boolean b2 = false;
		// 判断该坦克的方向
		switch (et.direct) {
		// 方向向上或向下
		case 0:
		case 2:
			if (s.x > et.x && s.x < et.x + 80 && s.y > et.y && s.y < et.y + 60) {
				// 击中
				// 子弹死亡
				s.isLive = false;
				// 坦克死亡
				et.isLive = false;
				b2 = true;
				// 创建一个爆炸
				Bomb b = new Bomb(et.x, et.y);
				// 放入向量中
				bombs.add(b);
			}
			break;
		case 1:
		case 3:
			if (s.x > et.x && s.x < et.x + 60 && s.y > et.y && s.y < et.y + 80) {
				// 击中
				// 子弹死亡
				s.isLive = false;
				// 坦克死亡
				et.isLive = false;
				b2 = true;
				// 创建一个爆炸
				Bomb b = new Bomb(et.x, et.y);
				// 放入向量中
				bombs.add(b);
			}
			break;
		}
		return b2;
	}

	// 画出坦克的函数
	public void drawTank(int x, int y, Graphics g, int direct, int type) {
		// 判断是什么类型的坦克
		switch (type) {
		case 1:
			g.setColor(Color.RED);
			break;
		default:
			g.setColor(Color.blue);
			break;
		}
		// 判断方向
		switch (direct) {
		case 0:
			// 左边的矩形
			g.fillRect(x, y, 20, 80);
			// 右边的矩形
			g.fillRect(x + 60, y, 20, 80);
			// 中间的矩形
			g.fillRect(x + 20, y + 18, 40, 45);
			// 中间的炮筒
			g.fillRect(x + 37, y - 10, 5, 45);
			break;
		case 1:
			// 左边的矩形
			g.fillRect(x, y, 80, 20);
			// 右边的矩形
			g.fillRect(x, y + 60, 80, 20);
			// 中间的矩形
			g.fillRect(x + 18, y + 20, 45, 40);
			// 中间的炮筒
			g.fillRect(x + 45, y + 37, 45, 5);
			break;
		case 2:
			// 左边的矩形
			g.fillRect(x, y, 20, 80);
			// 右边的矩形
			g.fillRect(x + 60, y, 20, 80);
			// 中间的矩形
			g.fillRect(x + 20, y + 18, 40, 45);
			// 中间的炮筒
			g.fillRect(x + 37, y + 45, 5, 45);
			break;
		case 3:
			// 左边的矩形
			g.fillRect(x, y, 80, 20);
			// 右边的矩形
			g.fillRect(x, y + 60, 80, 20);
			// 中间的矩形
			g.fillRect(x + 18, y + 20, 45, 40);
			// 中间的炮筒
			g.fillRect(x - 10, y + 37, 45, 5);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	// 键按下
	@Override
	public void keyPressed(KeyEvent e) {
		// 设置我的坦克的方向
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// 向上
			this.hero.setDirect(0);
			this.hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// 向右
			this.hero.setDirect(1);
			this.hero.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// 向下
			this.hero.setDirect(2);
			this.hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// 向左
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		// 判断玩家开火
		if (e.getKeyCode() == KeyEvent.VK_J) {
			// 开火
			if (this.hero.ss.size() < 7) {
				this.hero.ShotEnemy();
			}

		}
		// 必须重新绘制Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void run() {
		// 每隔100毫秒重绘
		while (true) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 判断是否击中敌人坦克
			this.hitEnemy();
			// 判断是否击中我
			this.hitMe();
			// 重绘
			this.repaint();
		}

	}
}
