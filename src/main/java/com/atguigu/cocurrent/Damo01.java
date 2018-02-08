package com.atguigu.cocurrent;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Threada{
	
	private Integer number=1;
	private Lock lock=new ReentrantLock();
	private Condition c1=lock.newCondition();
	private Condition c2=lock.newCondition();
	private Condition c3=lock.newCondition();
	
	public void print5(int tag) {
		lock.lock();
		try {
			while (number!=1) {
				c1.await();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t AA"+"\t ����"+tag);
			}
			number=2;
			c2.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}
	}
	public void print10(int tag) {
		lock.lock();
		try {
			while (number!=2) {
				c2.await();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t BB"+"\t ����"+tag);
			}
			number=3;
			c3.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}
	}
	
	public void print15(int tag) {
		lock.lock();
		try {
			while (number!=3) {
				c3.await();
			}
			for (int i = 0; i < 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t CC"+"\t ����"+tag);
			}
			number=1;
			c1.signal();
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			lock.unlock();
		}
	}
	
}
/**
 *  ���߳�֮�䰴˳����ã�ʵ��A->B->C
 * �����߳�������Ҫ�����£�
 * 
 * AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 * ����
 * AA��ӡ5�Σ�BB��ӡ10�Σ�CC��ӡ15��
 * @author lzg
 *
 */
public class Damo01 {
	/**
	 *1、 线程定点通知，A->B->C线程的通信传递，使用condition对象的await()方法和signal()方法，
	 * 来完成定点通知。
	 *2、 线程的产生，当调用.start()方法的时候,并不一定立马开启线程运行，而是等待CPU的调用
	 * @param args
	 */
	public static void main(String[] args) {
		Threada threada = new Threada();
		for (int i = 0; i < 10; i++) {
			int temp=i;
			new Thread(() -> {
				threada.print5(temp);
			}, "your Thread A").start();
		}
		for (int i = 0; i < 10; i++) {
			int temp=i;
			new Thread(() -> {
				threada.print10(temp);
			}, "your Thread B").start();
		}
		for (int i = 0; i < 10; i++) {
			int temp=i;
			new Thread(() -> {
				threada.print15(temp);
			}, "your Thread C").start();
		}
		
		
	}

}
