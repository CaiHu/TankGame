package com.tanke.finish;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/*
 * 纪录类
 */
public class Recorder {
	// 敌人数量
	private static int enNum = 7;
	// 我的生命数量
	private static int myLife = 3;
	// 消灭敌人数量
	private static int dead = 0;
	// 从文件中恢复记录点
	static Vector<Node> nodes = new Vector<>();

	// 记录数据
	private static FileWriter fw = null;
	private static BufferedWriter bw = null;
	// 恢复数据
	private static FileReader fr = null;
	private static BufferedReader br = null;

	private Vector<EnemyTank> ets = new Vector<>();

	// 恢复
	public Vector<Node> getNode() {
		try {
			fr = new FileReader("e:\\myRecorder.txt");
			br = new BufferedReader(fr);

			String n = "";
			n = br.readLine();
			dead = Integer.parseInt(n);
			while ((n = br.readLine()) != null) {
				String[] xyz = n.split(" ");
				for (int i = 0; i < xyz.length; i++) {
					Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
					nodes.add(node);
				}

			}
			dead = Integer.parseInt(n);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return nodes;
	}

	public Vector<EnemyTank> getEts() {
		return ets;
	}

	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}

	// 敌人坦克的坐标，方向
	public void keep() {
		try {
			fw = new FileWriter("e:\\myRecorder.txt");
			bw = new BufferedWriter(fw);
			bw.write(dead + "\r\n");

			// 当前敌人的坐标和方向
			for (int i = 0; i < ets.size(); i++) {
				EnemyTank et = ets.get(i);
				if (et.isLive) {
					String recorde = et.x + " " + et.y + " " + et.direct;
					bw.write(recorde + "\r\n");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 写出到文件
	public static void keepRecording() {
		try {
			fw = new FileWriter("e:\\myRecorder.txt");
			bw = new BufferedWriter(fw);
			bw.write(dead + "\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 读入到游戏
	public static void getRecording() {
		try {
			fr = new FileReader("e:\\myRecorder.txt");
			br = new BufferedReader(fr);
			try {
				String n = br.readLine();
				dead = Integer.parseInt(n);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public static int getEnNum() {
		return enNum;
	}

	public static void setEnNum(int enNum) {
		Recorder.enNum = enNum;
	}

	public static int getMyLife() {
		return myLife;
	}

	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}

	public static int getDead() {
		return dead;
	}

	public static void setDead(int dead) {
		Recorder.dead = dead;
	}

	// 减少敌人数
	public static void reduceEnnum() {
		enNum--;
	}

	// 增加消灭数
	public static void deadEnnum() {
		dead++;
	}
}