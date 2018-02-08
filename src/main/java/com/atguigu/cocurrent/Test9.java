package com.atguigu.cocurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test9 {
	public static void main(String[] args) {
		ShareResource resource = new ShareResource();
			new Thread(() -> {
				for (int i = 0; i < 5; i++) {
					resource.print5(i);
				}
			}, "A").start();
			new Thread(() -> {
				for (int i = 1; i <=5; i++) {
					resource.print10(i);
				}
			}, "B").start();
			
			
			new Thread(() -> {
				for (int i = 1; i<=5; i++) {
					resource.print15(i);
				}
			}, "C").start();
		}

	}

class ShareResource {
	private int number = 1;
	Lock lock = new ReentrantLock();
	Condition c1 = lock.newCondition();
	Condition c2 = lock.newCondition();
	Condition c3 = lock.newCondition();

	public void print5(int loopnum) {

		lock.lock();// 相当于行锁
		try {
			// 判断
			while (number != 1) {
				c1.await();
			}
			// 操作
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + 5 + "\t loopnum:" + loopnum);
			}
			// 通知
			number = 2;
			c2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print10(int loopnum) {

		lock.lock();
		try {
			// 判断
			// 不等于2,说明该别的线程打印，当前线程就应该等待
			while (number != 2) {
				c2.await();
			}
			// 操作
			for (int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName() + 10 + "\t loopnum:" + loopnum);
			}
			// 通知
			number = 3;
			c3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public void print15(int loopnum) {

		lock.lock();
		try {
			// 判断
			while (number != 3) {
				c3.await();
			}
			// 操作
			for (int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName() + 15 + "\t loopnum:" + loopnum);
			}
			// 通知
			number = 3;
			c1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}