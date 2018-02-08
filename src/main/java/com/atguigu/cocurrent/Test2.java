package com.atguigu.cocurrent;

import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test2 {
	public static void main(String[] args) {
		/*
		 * new Thread(() -> { System.out.println("runnable 方法实现"); }, "thread name");
		 */
		ticket ticket = new ticket();
		try {
			//
			new Thread(() -> {
				ticket.sale();
			}, "A").start();
			new Thread(() -> {
				ticket.sale();
			}, "B").start();
			new Thread(() -> {
				ticket.sale();
			}, "C").start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

/**
 * 线程 操作资源类，高内聚，低耦合
 * 
 * @author Administrator
 *
 */
class ticket {
	private int ticket = 30;
	Lock lock = new ReentrantLock();
	public  void sale() {
		lock.lock();
		try {
			while (ticket > 0) {
				System.out.println(Thread.currentThread().getName() + "卖出第" + (ticket--) +"张票,"+ "剩余票数" + ticket);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}
