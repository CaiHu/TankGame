package com.tanke.finish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * @version 坦克游戏2.0
 * @author Pc 1、画出坦克 2、坦克可以移动 3、可以发射子弹 4、子弹可以连发 5、击中点人，坦克消失 6、敌人被击中，出现爆炸效果
 *         7、敌人可移动 8、敌人可以发子弹 9、敌人击中我，我消失 10、防止坦克重叠 11、存盘退出 12、继续上局 13、声音
 */
public class TankGame extends JFrame implements ActionListener {
	MyPanel mp = null;
	myStartPanel msp = null;

	// 我需要的菜单
	JMenuBar jmb = null;
	// 开始游戏
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	// 退出系统
	JMenuItem jmi2 = null;
	// 存盘退出
	JMenuItem jmi3 = null;
	// 上局游戏
	JMenuItem jmi4 = null;

	public static void main(String[] args) {
		TankGame tg = new TankGame();
	}

	// 构造函数
	public TankGame() {

		// 创建菜单以及选项
		jmb = new JMenuBar();
		jm1 = new JMenu("游戏(G)");
		jm1.setMnemonic('G');

		jmi1 = new JMenuItem("开始游戏(N)");
		jmi1.setMnemonic('N');
		jmi2 = new JMenuItem("退出游戏(T)");
		jmi2.setMnemonic('T');
		jmi3 = new JMenuItem("存盘退出(E)");
		jmi3.setMnemonic('E');
		jmi4 = new JMenuItem("上局游戏(S)");
		jmi4.setMnemonic('S');
		// 对jmi1响应
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		// 对jmi2响应
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		// 对jmi3响应
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit2");
		// 对jmi4响应
		jmi4.addActionListener(this);
		jmi4.setActionCommand("game");

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm1.add(jmi3);
		jm1.add(jmi4);
		jmb.add(jm1);

		msp = new myStartPanel();
		this.setJMenuBar(jmb);
		this.add(msp);
		this.setSize(1200, 1000);
		this.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 对用户的点击进行处理
		if (e.getActionCommand().equals("newgame")) {
			mp = new MyPanel("newgame");
			// 启动mp线程
			Thread t = new Thread(mp);
			t.start();
			// 删掉开始页
			this.remove(msp);
			// 加入游戏页
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			// 显示刷新
			this.setVisible(true);
		} else if (e.getActionCommand().equals("exit")) {
			// 保存数据
			Recorder.keepRecording();
			// 退出
			System.exit(0);
		} else if (e.getActionCommand().equals("exit2")) {
			Recorder rd = new Recorder();
			rd.setEts(mp.ets);
			// 保存
			rd.keep();
			// 退出
			System.exit(0);
		} else if (e.getActionCommand().equals("game")) {
			mp = new MyPanel("con");

			// 启动mp线程
			Thread t = new Thread(mp);
			t.start();
			// 删掉开始页
			this.remove(msp);
			// 加入游戏页
			this.add(mp);
			// 注册监听
			this.addKeyListener(mp);
			// 显示刷新
			this.setVisible(true);
		}
	}
}
