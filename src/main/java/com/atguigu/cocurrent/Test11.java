package com.atguigu.cocurrent;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test11 {

	public static void main(String[] args) {
		/*ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
		ReentrantLock reentrantLock = new ReentrantLock();*/
		Myqueue myqueue = new Myqueue();
		new Thread(() -> {
			myqueue.write("读锁和写锁互斥");
		}, "WRITE \t").start();
		for (int i = 1; i <= 100; i++) {
			new Thread(() -> {
				myqueue.read();
			}, "READ \t").start();
		}
		
	}

}

class Myqueue{
	private Object obj;
	private  ReentrantReadWriteLock rl = new ReentrantReadWriteLock();
	
	public void read() {
		rl.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t "+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rl.readLock().unlock();
		}
	}
	//this 表示当前类的对象
	//this这个关键字其代表的就是：对象中的成员变量或者方法
	public void write(Object obj) {
		rl.writeLock().lock();
		try {
			this.obj=obj;
			System.out.println(Thread.currentThread().getName()+"\t "+obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rl.writeLock().unlock();
		}
	}
	

}