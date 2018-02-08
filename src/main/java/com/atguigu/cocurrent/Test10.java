package com.atguigu.cocurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareData1 {
	private int number = 0;
	// 锁的对象，相当于锁的整个对象
	// 锁只有一个,调用lock.lock(),将范围锁升级为表锁

	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();

	public void increment() throws InterruptedException {
		//想执行这个代码，必须锁的状态是开的,将需要锁的部分上锁
		lock.lock();
		try {
			// 1 判断，为1进来
			while (number != 0) {
				condition.await();// this.wait();
			}
			
			// 2 干活
			++number;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			// 3 通知
			condition.signalAll();// this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void decrement() throws InterruptedException {
		// 想执行这个代码，必须锁的状态是开的
		lock.lock();
		try {
			// 1 判断
			if (number == 0) {
				condition.await();// this.wait();
			}
			// 2 干活
			--number;
			System.out.println(Thread.currentThread().getName() + "\t" + number);
			// 3 通知
			condition.signalAll();// this.notifyAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/*
	 * public synchronized void increment() throws InterruptedException { //1 判断
	 * while(number != 0) { this.wait();//A C } //2 干活 ++number;
	 * System.out.println(Thread.currentThread().getName()+"\t"+number); //3 通知
	 * this.notifyAll(); }
	 * 
	 * public synchronized void decrement() throws InterruptedException { //1 判断
	 * while(number == 0) { this.wait(); } //2 干活 --number;
	 * System.out.println(Thread.currentThread().getName()+"\t"+number); //3 通知
	 * this.notifyAll(); }
	 */

}

/**
 * 
 * @Description: 现在两个线程， 可以操作初始值为零的一个变量， 实现一个线程对该变量加1，一个线程对该变量减1，
 *               交替，来5轮，变量初始值为零。
 * @author zzyy
 * @date 2018年1月29日
 */
public class Test10 {
	public static void main(String[] args) {
		
		
	List<Object> synchronizedList =
			Collections.synchronizedList(new ArrayList<>());
		
		ShareData1 sd = new ShareData1();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(100);
					sd.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "A").start();

		/*
		 * new Thread(() -> { for (int i = 1; i <=10; i++) { try { Thread.sleep(200);
		 * sd.decrement(); } catch (InterruptedException e) { e.printStackTrace();. } }
		 * }, "B").start();
		 */
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(200);
					sd.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "B").start();
		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(300);
					sd.increment();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "C").start();

		new Thread(() -> {
			for (int i = 1; i <= 10; i++) {
				try {
					Thread.sleep(400);
					sd.decrement();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, "D").start();

	}
}
