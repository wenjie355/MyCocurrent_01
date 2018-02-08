package com.atguigu.cocurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket1// 资源类
{
	private int number = 30;
	private Lock lock = new ReentrantLock();// List list = new ArrayList();

	public void sale()// 对外暴露的业务方法
	{
		// biz.....
		// Lock lock = new ReentrantLock();//List list = new ArrayList();
		lock.lock();
		try {
			if (number > 0) {
				System.out.println(Thread.currentThread().getName() + "\t卖出第：" + (number--) + "\t还剩下：" + number);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}

/**
 * 
 * @Description: 3个售票员(线程) 卖出 30张票 （工程代码）
 * @author zzyy
 * @date 2018年1月29日
 * 
 *       1 线程 操作 资源类 2 高内聚 低耦合 备注：资源类自身高内聚了什么样的方法，按照同步锁的要求，对外提供统一的服务接口调用
 *       Thread(Runnable target, String name) Allocates a new Thread object.
 */
public class Test4 {
	public static void main(String[] args) throws InterruptedException {
		Ticket1 ticket = new Ticket1();

		// new Thread(() -> { for (int i = 1; i <=40; i++) ticket.sale(); },
		// "A").start();
		// new Thread(() -> { for (int i = 1; i <=40; i++) ticket.sale(); },
		// "B").start();
		// new Thread(() -> { for (int i = 1; i <=40; i++) ticket.sale(); },
		// "C").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 40; i++) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ticket.sale();
				}
			}
		}, "A").start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 40; i++) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ticket.sale();
				}
			}
		}, "B").start();

		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 40; i++) {
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ticket.sale();
				}
			}
		}, "C").start();

	}
}

// 1 class MyThread Implements Runnable

// 2 匿名内部类，new接口

// 3 Lambda Express 简化版本
