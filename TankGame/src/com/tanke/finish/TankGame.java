package com.tanke.finish;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


/**
 * @version ̹����Ϸ2.0
 * @author Pc 1������̹�� 2��̹�˿����ƶ� 3�����Է����ӵ� 4���ӵ��������� 5�����е��ˣ�̹����ʧ 6�����˱����У����ֱ�ըЧ��
 *         7�����˿��ƶ� 8�����˿��Է��ӵ� 9�����˻����ң�����ʧ 10����ֹ̹���ص� 11�������˳� 12�������Ͼ� 13������
 */
public class TankGame extends JFrame implements ActionListener {
	MyPanel mp = null;
	myStartPanel msp = null;

	// ����Ҫ�Ĳ˵�
	JMenuBar jmb = null;
	// ��ʼ��Ϸ
	JMenu jm1 = null;
	JMenuItem jmi1 = null;
	// �˳�ϵͳ
	JMenuItem jmi2 = null;
	// �����˳�
	JMenuItem jmi3 = null;
	// �Ͼ���Ϸ
	JMenuItem jmi4 = null;

	public static void main(String[] args) {
		TankGame tg = new TankGame();
	}

	// ���캯��
	public TankGame() {

		// �����˵��Լ�ѡ��
		jmb = new JMenuBar();
		jm1 = new JMenu("��Ϸ(G)");
		jm1.setMnemonic('G');

		jmi1 = new JMenuItem("��ʼ��Ϸ(N)");
		jmi1.setMnemonic('N');
		jmi2 = new JMenuItem("�˳���Ϸ(T)");
		jmi2.setMnemonic('T');
		jmi3 = new JMenuItem("�����˳�(E)");
		jmi3.setMnemonic('E');
		jmi4 = new JMenuItem("�Ͼ���Ϸ(S)");
		jmi4.setMnemonic('S');
		// ��jmi1��Ӧ
		jmi1.addActionListener(this);
		jmi1.setActionCommand("newgame");
		// ��jmi2��Ӧ
		jmi2.addActionListener(this);
		jmi2.setActionCommand("exit");
		// ��jmi3��Ӧ
		jmi3.addActionListener(this);
		jmi3.setActionCommand("exit2");
		// ��jmi4��Ӧ
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
		// ���û��ĵ�����д���
		if (e.getActionCommand().equals("newgame")) {
			mp = new MyPanel("newgame");
			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ɾ����ʼҳ
			this.remove(msp);
			// ������Ϸҳ
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			// ��ʾˢ��
			this.setVisible(true);
		} else if (e.getActionCommand().equals("exit")) {
			// ��������
			Recorder.keepRecording();
			// �˳�
			System.exit(0);
		} else if (e.getActionCommand().equals("exit2")) {
			Recorder rd = new Recorder();
			rd.setEts(mp.ets);
			// ����
			rd.keep();
			// �˳�
			System.exit(0);
		} else if (e.getActionCommand().equals("game")) {
			mp = new MyPanel("con");

			// ����mp�߳�
			Thread t = new Thread(mp);
			t.start();
			// ɾ����ʼҳ
			this.remove(msp);
			// ������Ϸҳ
			this.add(mp);
			// ע�����
			this.addKeyListener(mp);
			// ��ʾˢ��
			this.setVisible(true);
		}
	}
}
