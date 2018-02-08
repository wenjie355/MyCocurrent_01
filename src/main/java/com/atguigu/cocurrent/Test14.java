package com.atguigu.cocurrent;

import java.util.concurrent.CountDownLatch;

public class Test14 {

	public static void main(String[] args) throws InterruptedException {
		
		CountDownLatch cd = new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t "+"完成");
				cd.countDown();
			}, Myenum.getCountry(i).getCoutry()).start();
		}
		cd.await();
		System.out.println("修成正果");
	}

}
