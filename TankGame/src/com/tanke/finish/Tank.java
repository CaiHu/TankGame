package com.tanke.finish;

/*
 * 坦克类
 */
public class Tank {
	// 坦克的横坐标
	int x = 0;
	// 坦克的纵坐标
	int y = 0;
	// 坦克的方向
	int direct = 0;
	// 坦克的速度
	int speed = 5;
	// 坦克的颜色
	int color;

	boolean isLive = true;

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}
