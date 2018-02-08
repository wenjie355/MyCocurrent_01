package com.atguigu.cocurrent;

import java.util.Collections;

public class Test1 {

	public static void main(String[] args) {
		A a1 = new A();
		Thread t1 = new Thread(a1);
		Thread t2 = new Thread(a1);
		Thread t3 = new Thread(a1);
		t1.setName("窗口1");
		t1.setName("窗口2");
		t1.setName("窗口3");
		t1.start();
		t2.start();
		t3.start();
	}

}

class  A  implements Runnable {
  public static int ticket = 30;
	@Override
	public  void run() {
		synchronized(this) {
		while (ticket > 0) {
				ticket--;
				Thread currentThread = Thread.currentThread();
				String name = currentThread.getName();
				System.out.println("线程"+name+"当前剩余票数为:" + ticket);
				if (ticket == 0) {
					System.out.println("票已售空");
					return;
				}
			}
		}
	}
}