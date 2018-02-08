package com.atguigu.cocurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 终于出来了！！！！
 * @author Administrator
 *
 */
public class Test6 {

	public static void main(String[] args) throws InterruptedException {
		NumAndLetter n = new NumAndLetter();
		new Thread(() -> {
			for (int i = 0; i < 26; i++) {
				n.addNum();
			}
		}, "B").start();
		TimeUnit.SECONDS.sleep(1);
		new Thread(() -> {
			for (int i = 0; i < 26; i++) {
				n.addLetter();
			}
		}, "A").start();
		
		
	}

}

/**
 * 资源类
 * 
 * @author Administrator
 *
 */
class NumAndLetter {

	private int num = 0;
	//private static char[] c = new char[26];
	private String result = "";
	// 默认最后一位是数字
	private boolean b = true;
	private Lock lock = new ReentrantLock();
	private Condition condition = lock.newCondition();
	private int letter = 97;
	
	/**
	 * 操作数字
	 */
	public void addNum() {
		lock.lock();
		try {
			// 判断
			while (b) {
				condition.await();
			}
			// 操作
			for (int i = 0; i < 2; i++) {
				result += (num++) + " ";
			}
			b=true;
			//通知
			TimeUnit.SECONDS.sleep(1);
			System.out.println("线程  "+Thread.currentThread().getName()+":"+result);
			
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
	/**
	 * 添加字母
	 */
	
	public void addLetter() {
		lock.lock();
		try {
			// 判断
			while (b==false) {
				condition.await();
			}
			if(letter<=123) {
				// 操作
					result +=(char)(letter++)+" ";
			}
			b=false;
			//通知
			TimeUnit.SECONDS.sleep(1);
			System.out.println("线程  "+Thread.currentThread().getName()+":"+result);
			condition.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
}